package com.guigon95.veiculoservice.infra.gateway

import com.guigon95.veiculoservice.domain.model.Veiculo
import com.guigon95.veiculoservice.infra.persistence.entity.VeiculoEntity

class VeiculoEntityMapper {
    fun toEntity(veiculo: Veiculo): VeiculoEntity {
        return VeiculoEntity(
            veiculo.id,
            veiculo.placa,
            veiculo.marca,
            veiculo.modelo,
            veiculo.ano,
            veiculo.cor,
            veiculo.preco,
            veiculo.situacao
        )
    }

    fun toVeiculo(veiculoEntity: VeiculoEntity): Veiculo {
        return Veiculo(
            veiculoEntity.id,
            veiculoEntity.placa,
            veiculoEntity.marca,
            veiculoEntity.modelo,
            veiculoEntity.ano,
            veiculoEntity.cor,
            veiculoEntity.preco,
            veiculoEntity.situacao
        )
    }
}