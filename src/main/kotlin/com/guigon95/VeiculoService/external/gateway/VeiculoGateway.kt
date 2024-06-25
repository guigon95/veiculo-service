package com.guigon95.VeiculoService.external.gateway

import com.guigon95.VeiculoService.domain.model.Veiculo

interface VeiculoGateway {
    fun salvar(veiculo: Veiculo): Veiculo

    fun findById(id: Long): Veiculo?
}