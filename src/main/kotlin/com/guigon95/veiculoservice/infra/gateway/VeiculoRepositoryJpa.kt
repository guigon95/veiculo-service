package com.guigon95.veiculoservice.infra.gateway

import com.guigon95.veiculoservice.application.gateways.VeiculoGateway
import com.guigon95.veiculoservice.domain.model.Veiculo
import com.guigon95.veiculoservice.infra.persistence.VeiculoRepository
import org.springframework.data.domain.Example
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort

class VeiculoRepositoryJpa(
    private val veiculoRepository: VeiculoRepository,
    private val mapper: VeiculoEntityMapper
) : VeiculoGateway {

    override fun salvar(veiculo: Veiculo): Veiculo {
        val entity = mapper.toEntity(veiculo)
        return mapper.toVeiculo(veiculoRepository.save(entity))
    }

    override fun findById(id: Long): Veiculo? {
        val veiculo = veiculoRepository.findById(id)

        if(!veiculo.isPresent)
            return null

        return mapper.toVeiculo(veiculo.get())
    }

    override fun findAll(veiculo: Veiculo, sort: Sort): List<Veiculo> {
        val veiculoList = veiculoRepository.findAll(Example.of(mapper.toEntity(veiculo)))
        return veiculoList.map(
            
        ).toList
    }
}