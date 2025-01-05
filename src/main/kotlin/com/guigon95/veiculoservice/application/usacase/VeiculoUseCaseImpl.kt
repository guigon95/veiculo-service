package com.guigon95.veiculoservice.application.usacase

import com.guigon95.veiculoservice.application.exception.VeiculoNotFoundException
import com.guigon95.veiculoservice.application.gateways.IVeiculoRepository
import com.guigon95.veiculoservice.domain.model.Veiculo
import com.guigon95.veiculoservice.domain.usecase.VeiculoUseCase
import org.springframework.data.domain.Sort

class VeiculoUseCaseImpl(
    private val iVeiculoRepository: IVeiculoRepository
): VeiculoUseCase {

    override fun salvarVeiculo(veiculo: Veiculo): Veiculo {
        return iVeiculoRepository.salvar(veiculo)
    }

    override fun atualizarVeiculo(veiculo: Veiculo): Veiculo {
        val veiculoSalvo = iVeiculoRepository.findById(veiculo.id!!)
        veiculoSalvo?.placa = veiculo.placa
        veiculoSalvo?.ano = veiculo.ano
        veiculoSalvo?.cor = veiculo.cor
        veiculoSalvo?.marca = veiculo.marca
        veiculoSalvo?.modelo = veiculo.modelo
        veiculoSalvo?.preco = veiculo.preco

        return iVeiculoRepository.salvar(veiculo)
    }

    override fun listarVeiculos(example: Veiculo, sort: Sort): List<Veiculo> {
        return iVeiculoRepository.findAll(example, sort)
    }

    override fun findById(id: Long): Veiculo {
        iVeiculoRepository.findById(id)?.let {
            return it
        }

        throw VeiculoNotFoundException()
    }


}