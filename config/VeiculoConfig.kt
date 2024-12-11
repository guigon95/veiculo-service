import com.guigon95.veiculoservice.application.gateways.IVeiculoRepository
import com.guigon95.veiculoservice.application.usacase.VeiculoUseCaseImpl
import com.guigon95.veiculoservice.domain.usecase.VeiculoUseCase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class VeiculoConfig() {

    @Bean
    fun veiculoUseCase(IVeiculoRepository: IVeiculoRepository): VeiculoUseCase {
        return VeiculoUseCaseImpl(IVeiculoRepository)
    }

}