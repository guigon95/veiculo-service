package com.guigon95.veiculoservice.application.gateways

import com.guigon95.veiculoservice.domain.model.Veiculo
import org.springframework.data.domain.Sort

interface IVeiculoRepository {
    fun salvar(veiculo: Veiculo): Veiculo

    fun findById(id: Long): Veiculo?

    fun findAll(example: Veiculo, sort: Sort): List<Veiculo>
}