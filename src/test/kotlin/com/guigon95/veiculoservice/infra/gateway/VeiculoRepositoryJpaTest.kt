package com.guigon95.veiculoservice.infra.gateway

import com.guigon95.veiculoservice.domain.enums.SituacaoEnum
import com.guigon95.veiculoservice.domain.model.Veiculo
import com.guigon95.veiculoservice.infra.persistence.VeiculoRepository
import com.guigon95.veiculoservice.infra.persistence.entity.VeiculoEntity
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.springframework.data.domain.Example
import org.springframework.data.domain.Sort
import java.math.BigDecimal

class VeiculoRepositoryJpaTest {

    private lateinit var veiculoRepository: VeiculoRepository
    private lateinit var mapper: VeiculoEntityMapper
    private lateinit var veiculoRepositoryJpa: VeiculoRepositoryJpa

    @BeforeEach
    fun setUp() {
        veiculoRepository = mock(VeiculoRepository::class.java)
        mapper = mock(VeiculoEntityMapper::class.java)
        veiculoRepositoryJpa = VeiculoRepositoryJpa(veiculoRepository, mapper)
    }

    @Test
    fun salvarVeiculoSuccessfully() {
        val veiculo = Veiculo(1, "ABC1234", "Marca", "Modelo", 2021, "prata", BigDecimal(1000), SituacaoEnum.A_VENDA)
        val veiculoEntity = VeiculoEntity(1, "ABC1234", "Marca", "Modelo", 2021, "prata", BigDecimal(1000), SituacaoEnum.A_VENDA)
        `when`(mapper.toEntity(veiculo)).thenReturn(veiculoEntity)
        `when`(veiculoRepository.save(veiculoEntity)).thenReturn(veiculoEntity)
        `when`(mapper.toVeiculo(veiculoEntity)).thenReturn(veiculo)

        val result = veiculoRepositoryJpa.salvar(veiculo)

        assertThat(result).isEqualTo(veiculo)
    }

    @Test
    fun findByIdSuccessfully() {
        val id = 1L
        val veiculoEntity = VeiculoEntity(1, "ABC1234", "Marca", "Modelo", 2021, "prata", BigDecimal(1000), SituacaoEnum.A_VENDA)
        val veiculo = Veiculo(1, "ABC1234", "Marca", "Modelo", 2021, "prata", BigDecimal(1000), SituacaoEnum.A_VENDA)
        `when`(veiculoRepository.findById(id)).thenReturn(java.util.Optional.of(veiculoEntity))
        `when`(mapper.toVeiculo(veiculoEntity)).thenReturn(veiculo)

        val result = veiculoRepositoryJpa.findById(id)

        assertThat(result).isEqualTo(veiculo)
    }

    @Test
    fun findByIdReturnsNullWhenNotFound() {
        val id = 1L
        `when`(veiculoRepository.findById(id)).thenReturn(java.util.Optional.empty())

        val result = veiculoRepositoryJpa.findById(id)

        assertThat(result).isNull()
    }

    @Test
    fun findAllSuccessfully() {
        val veiculo = Veiculo(1, "ABC1234", "Marca", "Modelo", 2021, "prata", BigDecimal(1000), SituacaoEnum.A_VENDA)
        val veiculoEntity = VeiculoEntity(1, "ABC1234", "Marca", "Modelo", 2021, "prata", BigDecimal(1000), SituacaoEnum.A_VENDA)
        val veiculoEntities = listOf(veiculoEntity)
        val veiculos = listOf(veiculo)
        `when`(mapper.toEntity(veiculo)).thenReturn(veiculoEntity)
        `when`(veiculoRepository.findAll(Example.of(veiculoEntity), Sort.by("preco").ascending())).thenReturn(
            veiculoEntities
        )
        `when`(mapper.toVeiculo(veiculoEntity)).thenReturn(veiculo)

        val result = veiculoRepositoryJpa.findAll(veiculo, Sort.by("preco").ascending())

        assertThat(result).isEqualTo(veiculos)
    }

    @Test
    fun salvar_situacaoVeiculo(){
        val veiculo = Veiculo(SituacaoEnum.A_VENDA)
        val veiculoEntity = VeiculoEntity(1, "ABC1234", "Marca", "Modelo", 2021, "prata", BigDecimal(1000), SituacaoEnum.A_VENDA)
        `when`(mapper.toEntity(veiculo)).thenReturn(veiculoEntity)
        `when`(veiculoRepository.save(veiculoEntity)).thenReturn(veiculoEntity)
        `when`(mapper.toVeiculo(veiculoEntity)).thenReturn(veiculo)

        val result = veiculoRepositoryJpa.salvar(veiculo)

        assertThat(result).isEqualTo(veiculo)
    }
}