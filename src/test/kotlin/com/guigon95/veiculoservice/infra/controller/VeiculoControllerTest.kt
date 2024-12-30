package com.guigon95.veiculoservice.infra.controller

import com.guigon95.veiculoservice.infra.controller.dto.VeiculoRequest
import com.guigon95.veiculoservice.adapter.dto.VeiculoResponse
import com.guigon95.veiculoservice.domain.enums.SituacaoEnum
import com.guigon95.veiculoservice.domain.model.Veiculo
import com.guigon95.veiculoservice.domain.usecase.VeiculoUseCase
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.springframework.data.domain.Sort
import java.math.BigDecimal

class VeiculoControllerTest {

    private lateinit var veiculoUseCase: VeiculoUseCase
    private lateinit var veiculoController: VeiculoController

    @BeforeEach
    fun setUp() {
        veiculoUseCase = mock(VeiculoUseCase::class.java)
        veiculoController = VeiculoController(veiculoUseCase)
    }

    @Test
    fun salvarVeiculoSuccessfully() {
        val veiculoRequest =
            VeiculoRequest("ABC1234", "Uno", "Fiat", 2021, "Branco", BigDecimal(1000), SituacaoEnum.A_VENDA)
        val veiculo = veiculoRequest.toVeiculo()
        val veiculoResponse = VeiculoResponse.from(veiculo)
        `when`(veiculoUseCase.salvarVeiculo(veiculo)).thenReturn(veiculo)

        val response = veiculoController.salvarVeiculo(veiculoRequest)

        assertEquals(veiculoResponse, response)
    }

    @Test
    fun atualizarVeiculoSuccessfully() {
        val id = 1L
        val veiculoRequest =
            VeiculoRequest("ABC1234", "Uno", "Fiat", 2021, "Branco", BigDecimal(1000), SituacaoEnum.A_VENDA)
        val veiculo = veiculoRequest.toVeiculo().apply { this.id = id }
        val veiculoResponse = VeiculoResponse.from(veiculo)
        `when`(veiculoUseCase.atualizarVeiculo(veiculo)).thenReturn(veiculo)

        val response = veiculoController.atualizarVeiculo(id, veiculoRequest)

        assertEquals(veiculoResponse, response)
    }

    @Test
    fun listarVeiculosAvendaSuccessfully() {
        val veiculo = Veiculo(SituacaoEnum.A_VENDA)
        val veiculoResponses = listOf(VeiculoResponse.from(veiculo))
        `when`(veiculoUseCase.listarVeiculos(veiculo, Sort.by("preco").ascending())).thenReturn(listOf(veiculo))

        val response = veiculoController.listarVeiculosAvenda()

        assertEquals(veiculoResponses, response)
    }

    @Test
    fun listarVeiculosVendidosSuccessfully() {
        val veiculo = Veiculo(SituacaoEnum.VENDIDO)
        val veiculoResponses = listOf(VeiculoResponse.from(veiculo))
        `when`(veiculoUseCase.listarVeiculos(veiculo, Sort.by("preco").ascending())).thenReturn(listOf(veiculo))

        val response = veiculoController.listarVeiculosVendidos()

        assertEquals(veiculoResponses, response)
    }

    @Test
    fun findByIdSuccessfully() {
        val id = 1L
        val veiculo = Veiculo(1L, "ABC1234", "Uno", "Fiat", 2021, "Branco", BigDecimal(1000), SituacaoEnum.A_VENDA)
        val veiculoResponse = VeiculoResponse.from(veiculo)
        `when`(veiculoUseCase.findById(id)).thenReturn(veiculo)

        val response = veiculoController.findById(id)

        assertEquals(veiculoResponse, response)
    }

    @Test
    fun atualizaSituacaoVeiculoSuccessfully() {
        val id = 1L

        val veiculo = Veiculo(1L, "ABC1234", "Uno", "Fiat", 2021, "Branco", BigDecimal(1000), SituacaoEnum.A_VENDA)
        val veiculoResponse = VeiculoResponse.from(veiculo)
        `when`(veiculoUseCase.findById(id)).thenReturn(veiculo)
        `when`(veiculoUseCase.atualizarVeiculo(veiculo)).thenReturn(veiculo.apply { situacao = SituacaoEnum.VENDIDO })

        val response = veiculoController.atualizaSituacaoVeiculo(id)

        assertThat(response?.id).isEqualTo(veiculoResponse.id)
        assertThat(response?.placa).isEqualTo(veiculoResponse.placa)
        assertThat(response?.modelo).isEqualTo(veiculoResponse.modelo)
        assertThat(response?.marca).isEqualTo(veiculoResponse.marca)
        assertThat(response?.ano).isEqualTo(veiculoResponse.ano)
        assertThat(response?.cor).isEqualTo(veiculoResponse.cor)
        assertThat(response?.preco).isEqualTo(veiculoResponse.preco)
        assertThat(response?.situacao).isEqualTo(SituacaoEnum.VENDIDO)
    }

    @Test
    fun reservarVeiculoSuccessfully() {
        val id = 1L
        val veiculo = Veiculo(1L, "ABC1234", "Uno", "Fiat", 2021, "Branco", BigDecimal(1000), SituacaoEnum.A_VENDA)
        val veiculoResponse = VeiculoResponse.from(veiculo)
        `when`(veiculoUseCase.findById(id)).thenReturn(veiculo)
        `when`(veiculoUseCase.atualizarVeiculo(veiculo)).thenReturn(veiculo.apply { situacao = SituacaoEnum.RESERVADO })

        val response = veiculoController.reservarVeiculo(id)

        assertThat(response?.id).isEqualTo(veiculoResponse.id)
        assertThat(response?.placa).isEqualTo(veiculoResponse.placa)
        assertThat(response?.modelo).isEqualTo(veiculoResponse.modelo)
        assertThat(response?.marca).isEqualTo(veiculoResponse.marca)
        assertThat(response?.ano).isEqualTo(veiculoResponse.ano)
        assertThat(response?.cor).isEqualTo(veiculoResponse.cor)
        assertThat(response?.preco).isEqualTo(veiculoResponse.preco)
        assertThat(response?.situacao).isEqualTo(SituacaoEnum.RESERVADO)
    }
}