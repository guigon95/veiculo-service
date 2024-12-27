package com.guigon95.veiculoservice.application.usacase

import com.guigon95.veiculoservice.application.gateways.IVeiculoRepository
import com.guigon95.veiculoservice.domain.model.Veiculo
import com.guigon95.veiculoservice.domain.usecase.VeiculoUseCase
import org.springframework.data.domain.Sort

class VeiculoUseCaseImpl(
    val IVeiculoRepository: IVeiculoRepository
): VeiculoUseCase {

    override fun salvarVeiculo(veiculo: Veiculo): Veiculo {
        return IVeiculoRepository.salvar(veiculo)
    }

    override fun atualizarVeiculo(veiculo: Veiculo): Veiculo {
        val veiculoSalvo = IVeiculoRepository.findById(veiculo.id!!)
        veiculoSalvo?.placa = veiculo.placa
        veiculoSalvo?.ano = veiculo.ano
        veiculoSalvo?.cor = veiculo.cor
        veiculoSalvo?.marca = veiculo.marca
        veiculoSalvo?.modelo = veiculo.modelo
        veiculoSalvo?.preco = veiculo.preco

        return IVeiculoRepository.salvar(veiculo)
    }

    override fun listarVeiculos(example: Veiculo, sort: Sort): List<Veiculo> {
        return IVeiculoRepository.findAll(example, sort)
    }

    override fun findById(id: Long): Veiculo? {
        return IVeiculoRepository.findById(id)
    }


}