package com.guigon95.VeiculoService.domain.usacase

import com.guigon95.VeiculoService.domain.model.Veiculo

interface VeiculoUseCase {
    fun salvarVeiculo(veiculo: Veiculo): Veiculo
    fun atualizarVeiculo(veiculo: Veiculo): Veiculo
}