package com.guigon95.veiculoservice.infra.gateway

import com.guigon95.veiculoservice.domain.enums.SituacaoEnum
import com.guigon95.veiculoservice.domain.model.Veiculo
import com.guigon95.veiculoservice.infra.persistence.entity.VeiculoEntity
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class VeiculoEntityMapperTest {

    private val mapper = VeiculoEntityMapper()

    @Test
    fun toEntityMapsCorrectly() {
        val veiculo = Veiculo(1L, "ABC-1234", "Toyota", "Corolla", 2020, "Black", BigDecimal(50000.0), SituacaoEnum.A_VENDA)
        val entity = mapper.toEntity(veiculo)

        assertEquals(veiculo.id, entity.id)
        assertEquals(veiculo.placa, entity.placa)
        assertEquals(veiculo.marca, entity.marca)
        assertEquals(veiculo.modelo, entity.modelo)
        assertEquals(veiculo.ano, entity.ano)
        assertEquals(veiculo.cor, entity.cor)
        assertEquals(veiculo.preco, entity.preco)
        assertEquals(veiculo.situacao, entity.situacao)
    }

    @Test
    fun toVeiculoMapsCorrectly() {
        val entity = VeiculoEntity(1L, "ABC-1234", "Toyota", "Corolla", 2020, "Black", BigDecimal(50000.0), SituacaoEnum.A_VENDA)
        val veiculo = mapper.toVeiculo(entity)

        assertEquals(entity.id, veiculo.id)
        assertEquals(entity.placa, veiculo.placa)
        assertEquals(entity.marca, veiculo.marca)
        assertEquals(entity.modelo, veiculo.modelo)
        assertEquals(entity.ano, veiculo.ano)
        assertEquals(entity.cor, veiculo.cor)
        assertEquals(entity.preco, veiculo.preco)
        assertEquals(entity.situacao, veiculo.situacao)
    }

    @Test
    fun toEntityWithNullValues() {
        val veiculo = Veiculo(null, null, null, null, null, null, null, null)
        val entity = mapper.toEntity(veiculo)

        assertNull(entity.id)
        assertNull(entity.placa)
        assertNull(entity.marca)
        assertNull(entity.modelo)
        assertNull(entity.ano)
        assertNull(entity.cor)
        assertNull(entity.preco)
        assertNull(entity.situacao)
    }

    @Test
    fun toVeiculoWithNullValues() {
        val entity = VeiculoEntity(null, null, null, null, null, null, null, null)
        val veiculo = mapper.toVeiculo(entity)

        assertNull(veiculo.id)
        assertNull(veiculo.placa)
        assertNull(veiculo.marca)
        assertNull(veiculo.modelo)
        assertNull(veiculo.ano)
        assertNull(veiculo.cor)
        assertNull(veiculo.preco)
        assertNull(veiculo.situacao)
    }
}