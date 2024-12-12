package com.guigon95.veiculoservice.infra.gateway

import com.guigon95.veiculoservice.application.gateways.IVeiculoRepository
import com.guigon95.veiculoservice.domain.model.Veiculo
import com.guigon95.veiculoservice.infra.persistence.VeiculoRepository
import org.springframework.data.domain.Example
import org.springframework.data.domain.Sort

class VeiculoRepositoryJpa(
    private val veiculoRepository: VeiculoRepository,
    private val mapper: VeiculoEntityMapper
) : IVeiculoRepository {

    override fun salvar(veiculo: Veiculo): Veiculo {
        val entity = mapper.toEntity(veiculo)
        return mapper.toVeiculo(veiculoRepository.save(entity))
    }

    override fun findById(id: Long): Veiculo? {
        return mapper.toVeiculo(veiculoRepository.findById(id).orElse(null))
    }

    override fun findAll(example: Veiculo, sort : Sort): List<Veiculo> {
        val exampleOf = Example.of(mapper.toEntity(example))

        return veiculoRepository.findAll(exampleOf, sort).map {
            veiculoEnity ->
            mapper.toVeiculo(veiculoEnity)
        }
    }
}