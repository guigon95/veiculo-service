package com.guigon95.VeiculoService.domain.model

import com.guigon95.VeiculoService.domain.enums.SituacaoEnum
import java.math.BigDecimal

data class Veiculo(
        var id: Long?,
        var placa: String?,
        var marca: String?,
        var modelo: String?,
        var ano: Int?,
        var cor: String?,
        var preco: BigDecimal?,
        var situacao: SituacaoEnum
){
        constructor(situacao: SituacaoEnum) : this(
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                situacao)

}