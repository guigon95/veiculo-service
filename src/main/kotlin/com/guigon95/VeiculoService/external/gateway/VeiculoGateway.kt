package com.guigon95.VeiculoService.external.gateway

import com.guigon95.VeiculoService.domain.model.Veiculo
import org.springframework.data.domain.Example
import org.springframework.data.domain.Sort

interface VeiculoGateway {
    fun salvar(veiculo: Veiculo): Veiculo

    fun findById(id: Long): Veiculo?

    fun findAll(example: Veiculo, sort: Sort): List<Veiculo>
}