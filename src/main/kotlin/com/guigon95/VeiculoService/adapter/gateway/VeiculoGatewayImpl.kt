package com.guigon95.VeiculoService.adapter.gateway

import com.guigon95.VeiculoService.adapter.mapper.VeiculoMapper
import com.guigon95.VeiculoService.domain.model.Veiculo
import com.guigon95.VeiculoService.external.gateway.VeiculoGateway
import com.guigon95.VeiculoService.external.infrastructure.entity.VeiculoEntity
import com.guigon95.VeiculoService.external.infrastructure.repository.jpa.VeiculoRepository
import org.springframework.data.domain.Example
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Component

@Component
class VeiculoGatewayImpl(
        val veiculoMapper: VeiculoMapper,
        val veiculoRepository: VeiculoRepository
): VeiculoGateway {
    override fun salvar(veiculo: Veiculo): Veiculo {
        var veiculoEntity = veiculoMapper.veiculoToVeiculoEntity(veiculo)
        return veiculoMapper.veiculoEntityToVeiculo(veiculoRepository.save(veiculoEntity))
    }

    override fun findById(id: Long): Veiculo? {
        return veiculoMapper.veiculoEntityToVeiculo(veiculoRepository.findById(id).orElse(null))
    }

    override fun findAll(example: Veiculo, sort : Sort): List<Veiculo> {
        val exampleOf = Example.of(veiculoMapper.veiculoToVeiculoEntity(example))

        return veiculoRepository.findAll(exampleOf, sort).map {
            veiculoEnity ->
            veiculoMapper.veiculoEntityToVeiculo(veiculoEnity)
        }
    }

}