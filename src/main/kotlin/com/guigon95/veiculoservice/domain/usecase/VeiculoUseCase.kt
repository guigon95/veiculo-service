package com.guigon95.veiculoservice.domain.usecase

import com.guigon95.veiculoservice.domain.model.Veiculo
import org.springframework.data.domain.Sort

interface VeiculoUseCase {
    fun salvarVeiculo(veiculo: Veiculo): Veiculo
    fun atualizarVeiculo(veiculo: Veiculo): Veiculo
    fun listarVeiculos(example: Veiculo, sort: Sort): List<Veiculo>
    fun findById(id: Long): Veiculo?
}