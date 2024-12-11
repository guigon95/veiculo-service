
@Configuration
class VeiculoConfig() {

    @Bean
    fun veiculoUseCase(veiculoGateway: VeiculoGateway): VeiculoUseCase {
        return VeiculoUseCase(veiculoGateway)
    }

}