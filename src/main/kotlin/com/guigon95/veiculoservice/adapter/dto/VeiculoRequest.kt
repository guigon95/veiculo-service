package com.guigon95.veiculoservice.adapter.dto

import com.guigon95.veiculoservice.domain.enums.SituacaoEnum
import com.guigon95.veiculoservice.domain.model.Veiculo
import jakarta.validation.constraints.Digits
import jakarta.validation.constraints.Min
import java.math.BigDecimal

data class VeiculoRequest(
        val placa: String,
        val marca: String,
        val modelo: String,
        @Min(value = 1900, message = "O ano deve ser maior que 1900")
        val ano: Int,
        val cor: String,
        @Digits(integer = 9, fraction = 2)
        val preco: BigDecimal,
        val situacao: SituacaoEnum
) {

    fun toVeiculo() : Veiculo {
        return Veiculo(
                id = null,
                placa = this.placa,
                marca = this.marca,
                modelo = this.modelo,
                ano = this.ano,
                cor = this.cor,
                preco = this.preco,
                situacao = this.situacao
        )
    }
}
