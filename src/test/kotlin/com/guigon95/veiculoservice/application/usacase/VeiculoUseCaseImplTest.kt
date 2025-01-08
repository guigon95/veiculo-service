package com.guigon95.veiculoservice.application.usacase

import com.guigon95.veiculoservice.application.exception.VeiculoNotFoundException
import com.guigon95.veiculoservice.application.gateways.IVeiculoRepository
import com.guigon95.veiculoservice.domain.enums.SituacaoEnum
import com.guigon95.veiculoservice.domain.model.Veiculo
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.springframework.data.domain.Sort
import java.math.BigDecimal

class VeiculoUseCaseImplTest {

    private lateinit var veiculoRepository: IVeiculoRepository
    private lateinit var veiculoUseCase: VeiculoUseCaseImpl

    @BeforeEach
    fun setUp() {
        veiculoRepository = mock(IVeiculoRepository::class.java)
        veiculoUseCase = VeiculoUseCaseImpl(veiculoRepository)
    }

    @Test
    fun salvarVeiculoSuccessfully() {
        val veiculo = Veiculo(1, "ABC1234", "Uno", "Fiat", 2021, "Branco", BigDecimal(1000), SituacaoEnum.A_VENDA)
        `when`(veiculoRepository.salvar(veiculo)).thenReturn(veiculo)

        val result = veiculoUseCase.salvarVeiculo(veiculo)

        assertEquals(veiculo, result)
    }

    @Test
    fun atualizarVeiculoSuccessfully() {
        val veiculo = Veiculo(1, "ABC1234", "Uno", "Fiat", 2021, "Branco", BigDecimal(1000), SituacaoEnum.A_VENDA)
        val veiculoSalvo = Veiculo(1, "ABC1234", "Uno", "Fiat", 2021, "Branco", BigDecimal(1000), SituacaoEnum.A_VENDA)
        `when`(veiculoRepository.findById(veiculo.id!!)).thenReturn(veiculoSalvo)
        `when`(veiculoRepository.salvar(veiculo)).thenReturn(veiculo)

        val result = veiculoUseCase.atualizarVeiculo(veiculo)

        assertEquals(veiculo, result)
    }

    @Test
    fun listarVeiculosSuccessfully() {
        val veiculo = Veiculo(1, "ABC1234", "Uno", "Fiat", 2021, "Branco", BigDecimal(1000), SituacaoEnum.A_VENDA)
        val veiculos = listOf(veiculo)
        `when`(veiculoRepository.findAll(veiculo, Sort.by("preco").ascending())).thenReturn(veiculos)

        val result = veiculoUseCase.listarVeiculos(veiculo, Sort.by("preco").ascending())

        assertEquals(veiculos, result)
    }

    @Test
    fun findByIdSuccessfully() {
        val id = 1L
        val veiculo = Veiculo(1, "ABC1234", "Uno", "Fiat", 2021, "Branco", BigDecimal(1000), SituacaoEnum.A_VENDA)
        `when`(veiculoRepository.findById(id)).thenReturn(veiculo)

        val result = veiculoUseCase.findById(id)

        assertEquals(veiculo, result)
    }

    @Test
    fun findByIdThrowsVeiculoNotFoundException() {
        val id = 1L
        `when`(veiculoRepository.findById(id)).thenReturn(null)

        assertThrows(VeiculoNotFoundException::class.java) {
            veiculoUseCase.findById(id)
        }
    }

    @Test
    fun atualizarVeiculoThrowsVeiculoNotFoundException() {
        val veiculo = Veiculo(1, "ABC1234", "Uno", "Fiat", 2021, "Branco", BigDecimal(1000), SituacaoEnum.A_VENDA)
        `when`(veiculoRepository.findById(veiculo.id!!)).thenReturn(null)

        assertThrows(VeiculoNotFoundException::class.java) {
            veiculoUseCase.atualizarVeiculo(veiculo)
        }
    }
}