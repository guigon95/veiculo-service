package com.guigon95.VeiculoService.external.infrastructure.repository.jpa

import com.guigon95.VeiculoService.external.infrastructure.entity.VeiculoEntity
import org.springframework.data.jpa.repository.JpaRepository

interface VeiculoRepository: JpaRepository<VeiculoEntity, Long> {
}