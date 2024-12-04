package com.guigon95.VeiculoService.external.infrastructure.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.math.BigDecimal

@Entity
data class VeiculoEntity(
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long?,
        val placa: String,
        val marca: String,
        val modelo: String,
        val ano: Int,
        val cor: String,
        val preco: BigDecimal
)