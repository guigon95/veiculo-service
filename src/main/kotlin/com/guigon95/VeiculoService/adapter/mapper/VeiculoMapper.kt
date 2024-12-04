package com.guigon95.VeiculoService.adapter.mapper

import com.guigon95.VeiculoService.domain.model.Veiculo
import com.guigon95.VeiculoService.external.infrastructure.entity.VeiculoEntity
import org.springframework.stereotype.Component

@Component
class VeiculoMapper {

    fun veiculoToVeiculoEntity(veiculo: Veiculo): VeiculoEntity {
        return VeiculoEntity(
                id = veiculo.id,
                placa = veiculo.placa,
                marca = veiculo.marca,
                modelo = veiculo.modelo,
                ano = veiculo.ano,
                cor = veiculo.cor,
                preco = veiculo.preco,
                situacao = veiculo.situacao
        )
    }

    fun veiculoEntityToVeiculo(veiculoEntity: VeiculoEntity): Veiculo {
        return Veiculo(
                id = veiculoEntity.id,
                placa = veiculoEntity.placa,
                marca = veiculoEntity.marca,
                modelo = veiculoEntity.modelo,
                ano = veiculoEntity.ano,
                cor = veiculoEntity.cor,
                preco = veiculoEntity.preco,
                situacao = veiculoEntity.situacao
        )
    }


}