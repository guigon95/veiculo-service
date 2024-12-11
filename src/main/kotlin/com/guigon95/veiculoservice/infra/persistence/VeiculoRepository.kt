package com.guigon95.veiculoservice.infra.persistence

import com.guigon95.veiculoservice.infra.persistence.entity.VeiculoEntity
import org.springframework.data.jpa.repository.JpaRepository

interface VeiculoRepository: JpaRepository<VeiculoEntity, Long> {
}