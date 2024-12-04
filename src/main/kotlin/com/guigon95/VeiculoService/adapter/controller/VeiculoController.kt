package com.guigon95.VeiculoService.adapter.controller

import com.guigon95.VeiculoService.adapter.dto.VeiculoRequest
import com.guigon95.VeiculoService.adapter.dto.VeiculoResponse
import com.guigon95.VeiculoService.domain.usacase.VeiculoUseCase
import org.springframework.stereotype.Controller

@Controller
class VeiculoController(private val veiculoUseCase: VeiculoUseCase) {

    fun salvarVeiculo(veiculoRequest: VeiculoRequest): VeiculoResponse {
        val veiculo = veiculoRequest.toVeiculo()

        return VeiculoResponse.from(veiculoUseCase.salvarVeiculo(veiculo))
    }

    fun atualizarVeiculo(id: Long, veiculoRequest: VeiculoRequest): VeiculoResponse {
        var veiculo = veiculoRequest.toVeiculo()
        veiculo.id = id

        var veiculoAlterado = veiculoUseCase.atualizarVeiculo(veiculo)
        return VeiculoResponse.from(veiculoAlterado)
    }
}