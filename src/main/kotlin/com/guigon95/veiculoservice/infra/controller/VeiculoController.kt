package com.guigon95.veiculoservice.infra.controller

import com.guigon95.veiculoservice.infra.controller.dto.VeiculoRequest
import com.guigon95.veiculoservice.adapter.dto.VeiculoResponse
import com.guigon95.veiculoservice.domain.enums.SituacaoEnum
import com.guigon95.veiculoservice.domain.model.Veiculo
import com.guigon95.veiculoservice.domain.usecase.VeiculoUseCase
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Controller

@Controller
class VeiculoController(
    private val veiculoUseCase: VeiculoUseCase
) {

    fun salvarVeiculo(veiculoRequest: VeiculoRequest): VeiculoResponse {
        val veiculo = veiculoRequest.toVeiculo()

        return VeiculoResponse.from(veiculoUseCase.salvarVeiculo(veiculo))
    }

    fun atualizarVeiculo(id: Long, veiculoRequest: VeiculoRequest): VeiculoResponse {
        var veiculo = veiculoRequest.toVeiculo()
        veiculo.id = id

        val veiculoAlterado = veiculoUseCase.atualizarVeiculo(veiculo)
        return VeiculoResponse.from(veiculoAlterado)
    }

    fun listarVeiculosAvenda(): List<VeiculoResponse> {
        val lista = veiculoUseCase.listarVeiculos(Veiculo(SituacaoEnum.A_VENDA), Sort.by("preco").ascending())

        return lista.map {
            veiculo ->
            VeiculoResponse.from(veiculo)
        }
    }

    fun listarVeiculosVendidos(): List<VeiculoResponse> {
        val lista = veiculoUseCase.listarVeiculos(Veiculo(SituacaoEnum.VENDIDO), Sort.by("preco").ascending())

        return lista.map {
            veiculo ->
            VeiculoResponse.from(veiculo)
        }
    }

    fun findById(id: Long): VeiculoResponse? {
        val veiculo = veiculoUseCase.findById(id)

        return VeiculoResponse.from(veiculo)
    }

    fun atualizaSituacaoVeiculo(id: Long): VeiculoResponse? {
        val veiculo = veiculoUseCase.findById(id)
        veiculo.situacao = SituacaoEnum.VENDIDO
        return VeiculoResponse.from(veiculoUseCase.atualizarVeiculo(veiculo))
    }

    fun reservarVeiculo(id: Long): VeiculoResponse? {
        val veiculo = veiculoUseCase.findById(id)
        veiculo.situacao = SituacaoEnum.RESERVADO

        return VeiculoResponse.from(veiculoUseCase.atualizarVeiculo(veiculo))
    }


}