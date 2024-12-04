package com.guigon95.VeiculoService.application.usacase

import com.guigon95.VeiculoService.domain.model.Veiculo
import com.guigon95.VeiculoService.domain.usecase.VeiculoUseCase
import com.guigon95.VeiculoService.external.gateway.VeiculoGateway
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Component

@Component
class VeiculoUseCaseImpl(
        val veiculoGateway: VeiculoGateway
): VeiculoUseCase {

    override fun salvarVeiculo(veiculo: Veiculo): Veiculo {
        return veiculoGateway.salvar(veiculo)
    }

    override fun atualizarVeiculo(veiculo: Veiculo): Veiculo {
        var veiculoSalvo = veiculoGateway.findById(veiculo.id!!)
        veiculoSalvo?.placa = veiculo.placa
        veiculoSalvo?.ano = veiculo.ano
        veiculoSalvo?.cor = veiculo.cor
        veiculoSalvo?.marca = veiculo.marca
        veiculoSalvo?.modelo = veiculo.modelo
        veiculoSalvo?.preco = veiculo.preco

        return veiculoGateway.salvar(veiculo)
    }

    override fun listarVeiculos(example: Veiculo, sort: Sort): List<Veiculo> {
        return veiculoGateway.findAll(example, sort)
    }


}