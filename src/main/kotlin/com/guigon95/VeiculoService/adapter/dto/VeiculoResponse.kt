package com.guigon95.VeiculoService.adapter.dto

import com.guigon95.VeiculoService.domain.model.Veiculo
import java.math.BigDecimal

data class VeiculoResponse(
        val placa: String,
        val marca: String,
        val modelo: String,
        val ano: Int,
        val cor: String,
        val preco: BigDecimal
) {
    companion object {
        fun from(veiculo: Veiculo): VeiculoResponse {
            return VeiculoResponse(
                    placa = veiculo.placa,
                    marca = veiculo.marca,
                    modelo = veiculo.modelo,
                    ano = veiculo.ano,
                    cor = veiculo. cor,
                    preco = veiculo.preco
            )
        }
    }
}
