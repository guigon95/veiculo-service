package com.guigon95.veiculoservice.infra.api

import com.guigon95.veiculoservice.adapter.dto.VeiculoResponse
import com.guigon95.veiculoservice.domain.enums.SituacaoEnum
import com.guigon95.veiculoservice.infra.controller.VeiculoController
import com.guigon95.veiculoservice.infra.controller.dto.VeiculoRequest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.springframework.http.HttpStatus
import java.math.BigDecimal

class VeiculoApiTest {

    private lateinit var veiculoController: VeiculoController
    private lateinit var veiculoApi: VeiculoApi

    @BeforeEach
    fun setUp() {
        veiculoController = mock(VeiculoController::class.java)
        veiculoApi = VeiculoApi(veiculoController)
    }

    @Test
    fun salvarVeiculoSuccessfully() {
        val veiculoRequest = VeiculoRequest("ABC1234", "Fusca", "VW", 1970, "Azul", BigDecimal(10000.0), SituacaoEnum.A_VENDA)
        val veiculoResponse = VeiculoResponse(1, "ABC1234", "Fusca", "VW", 1970, "Azul", BigDecimal(10000.0), SituacaoEnum.A_VENDA)
        `when`(veiculoController.salvarVeiculo(veiculoRequest)).thenReturn(veiculoResponse)

        val response = veiculoApi.salvar(veiculoRequest)

        assertEquals(HttpStatus.CREATED, response.statusCode)
        assertEquals(veiculoResponse, response.body)
    }

    @Test
    fun atualizarVeiculoSuccessfully() {
        val id = 1L
        val veiculoRequest = VeiculoRequest("ABC1234", "Fusca", "VW", 1970, "Azul", BigDecimal(10000.0), SituacaoEnum.A_VENDA)
        val veiculoResponse = VeiculoResponse(1, "ABC1234", "Fusca", "VW", 1970, "Azul", BigDecimal(10000.0),  SituacaoEnum.A_VENDA)
        `when`(veiculoController.atualizarVeiculo(id, veiculoRequest)).thenReturn(veiculoResponse)

        val response = veiculoApi.atualizarVeiculo(id, veiculoRequest)

        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(veiculoResponse, response.body)
    }

    @Test
    fun getVeiculoByIdSuccessfully() {
        val id = 1L
        val veiculoResponse = VeiculoResponse(1, "ABC1234", "Fusca", "VW", 1970, "Azul", BigDecimal(10000.0), SituacaoEnum.A_VENDA)
        `when`(veiculoController.findById(id)).thenReturn(veiculoResponse)

        val response = veiculoApi.getVeiculoById(id)

        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(veiculoResponse, response.body)
    }

    @Test
    fun updateSituacaoSuccessfully() {
        val id = 1L
        val veiculoResponse = VeiculoResponse(1, "ABC1234", "Fusca", "VW", 1970, "Azul", BigDecimal(10000.0), SituacaoEnum.VENDIDO)
        `when`(veiculoController.atualizaSituacaoVeiculo(id)).thenReturn(veiculoResponse)

        val response = veiculoApi.updateSituacao(id)

        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(veiculoResponse, response.body)
    }

    @Test
    fun reservarVeiculoSuccessfully() {
        val id = 1L
        val veiculoResponse = VeiculoResponse(1, "ABC1234", "Fusca", "VW", 1970, "Azul", BigDecimal(10000.0), SituacaoEnum.RESERVADO)
        `when`(veiculoController.reservarVeiculo(id)).thenReturn(veiculoResponse)

        val response = veiculoApi.reservarVeiculo(id)

        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(veiculoResponse, response.body)
    }

    @Test
    fun listarVeiculosSuccessfully() {
        val veiculoResponses = listOf(VeiculoResponse(1, "ABC1234", "Fusca", "VW", 1970, "Azul", BigDecimal(10000.0), SituacaoEnum.A_VENDA))
        `when`(veiculoController.listarVeiculosAvenda()).thenReturn(veiculoResponses)

        val response = veiculoApi.listarVeiculos()

        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(veiculoResponses, response.body)
    }

    @Test
    fun listarVeiculosVendidosSuccessfully() {
        val veiculoResponses = listOf(VeiculoResponse(1, "ABC1234", "Fusca", "VW", 1970, "Azul", BigDecimal(10000.0), SituacaoEnum.VENDIDO))
        `when`(veiculoController.listarVeiculosVendidos()).thenReturn(veiculoResponses)

        val response = veiculoApi.listarVeiculosVendidos()

        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(veiculoResponses, response.body)
    }
}