package com.guigon95.veiculoservice.external.api

import com.guigon95.veiculoservice.adapter.controller.VeiculoController
import com.guigon95.veiculoservice.adapter.dto.VeiculoRequest
import com.guigon95.veiculoservice.adapter.dto.VeiculoResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/veiculo")
@Tag(name = "Veiculos", description = "Acesso ao gerenciamento de veiculos")
class VeiculoApi(
        private val veiculoController: VeiculoController
) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Salvar um veiculo")
    @ApiResponses(value = [
        ApiResponse(responseCode = "201", description = "Veiculo salvo com sucesso"),
        ApiResponse(responseCode = "4xx", description = "Invalid data"),
        ApiResponse(responseCode = "5xx", description = "Internal server error")])
    fun salvar(
            @RequestBody @Valid veiculoRequest: VeiculoRequest
    ): ResponseEntity<VeiculoResponse> {
        return ResponseEntity.status(HttpStatus.CREATED).body(veiculoController.salvarVeiculo(veiculoRequest))
    }

    @PutMapping("/{id}")
    fun atualizarVeiculo(
            @PathVariable @Schema(description = "veiculo id") id: Long,
            @RequestBody @Valid veiculoRequest: VeiculoRequest
    ): ResponseEntity<VeiculoResponse> {
        return ResponseEntity.ok(veiculoController.atualizarVeiculo(id, veiculoRequest))
    }

    @GetMapping
    fun listarVeiculos() : ResponseEntity<List<VeiculoResponse>> {
        return ResponseEntity.ok(veiculoController.listarVeiculosAvenda())
    }

    @GetMapping("/vendido")
    fun listarVeiculosVendidos(): ResponseEntity<List<VeiculoResponse>> {
        return ResponseEntity.ok(veiculoController.listarVeiculosVendidos())
    }



}