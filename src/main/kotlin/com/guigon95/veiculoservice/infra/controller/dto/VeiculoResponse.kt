package com.guigon95.veiculoservice.adapter.dto

import com.guigon95.veiculoservice.domain.enums.SituacaoEnum
import com.guigon95.veiculoservice.domain.model.Veiculo
import java.math.BigDecimal

data class VeiculoResponse(
        val placa: String?,
        val marca: String?,
        val modelo: String?,
        val ano: Int?,
        val cor: String?,
        val preco: BigDecimal?,
        val situacao: SituacaoEnum
) {
    companion object {
        fun from(veiculo: Veiculo): VeiculoResponse {
            return VeiculoResponse(
                    placa = veiculo.placa,
                    marca = veiculo.marca,
                    modelo = veiculo.modelo,
                    ano = veiculo.ano,
                    cor = veiculo. cor,
                    preco = veiculo.preco,
                    situacao = veiculo.situacao

            )
        }
    }
}
