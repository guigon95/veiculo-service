package com.guigon95.VeiculoService.domain.usecase

import com.guigon95.VeiculoService.domain.model.Veiculo
import org.springframework.data.domain.Sort

interface VeiculoUseCase {
    fun salvarVeiculo(veiculo: Veiculo): Veiculo
    fun atualizarVeiculo(veiculo: Veiculo): Veiculo
    fun listarVeiculos(example: Veiculo, sort: Sort): List<Veiculo>
}