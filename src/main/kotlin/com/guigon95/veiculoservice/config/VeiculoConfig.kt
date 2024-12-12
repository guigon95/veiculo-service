import com.guigon95.veiculoservice.application.gateways.IVeiculoRepository
import com.guigon95.veiculoservice.application.usacase.VeiculoUseCaseImpl
import com.guigon95.veiculoservice.domain.usecase.VeiculoUseCase
import com.guigon95.veiculoservice.infra.controller.VeiculoController
import com.guigon95.veiculoservice.infra.gateway.VeiculoEntityMapper
import com.guigon95.veiculoservice.infra.gateway.VeiculoRepositoryJpa
import com.guigon95.veiculoservice.infra.persistence.VeiculoRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class VeiculoConfig {

    @Bean
    fun veiculoUseCase(IVeiculoRepository: IVeiculoRepository): VeiculoUseCaseImpl {
        return VeiculoUseCaseImpl(IVeiculoRepository)
    }

    @Bean
    fun veiculoRepositorioJpa(
        veiculoRepository: VeiculoRepository,
        veiculoEntityMapper: VeiculoEntityMapper
    ): VeiculoRepositoryJpa {
        return VeiculoRepositoryJpa(
            veiculoRepository,
            veiculoEntityMapper
        )
    }

    @Bean
    fun veiculoEntityMapper(): VeiculoEntityMapper {
        return VeiculoEntityMapper()
    }

}