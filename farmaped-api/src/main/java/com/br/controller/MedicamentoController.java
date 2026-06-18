package com.br.controller;

import com.br.model.Medicamento;
import com.br.service.MedicamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/medicamentos")
public class MedicamentoController {

    @Autowired
    private MedicamentoService medicamentoService;

    // POST - Cadastrar
    @PostMapping
    public ResponseEntity<Medicamento> cadastrar(@Valid @RequestBody Medicamento medicamento) {
        Medicamento novo = medicamentoService.cadastrar(medicamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(novo);
    }

    // GET - Listar todos
    @GetMapping
    public ResponseEntity<List<Medicamento>> listarTodos() {
        return ResponseEntity.ok(medicamentoService.listarTodos());
    }

    // GET - Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<Medicamento> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(medicamentoService.buscarPorId(id));
    }

    // GET - Buscar por Nome
    @GetMapping("/nome")
    public ResponseEntity<List<Medicamento>> buscarPorNome(@RequestParam String nome) {
        return ResponseEntity.ok(medicamentoService.buscarPorNome(nome));
    }

    // GET - Buscar por Categoria
    @GetMapping("/categoria")
    public ResponseEntity<List<Medicamento>> buscarPorCategoria(@RequestParam String categoria) {
        return ResponseEntity.ok(medicamentoService.buscarPorCategoria(categoria));
    }

    // GET - Buscar por faixa de preço
    @GetMapping("/preco")
    public ResponseEntity<List<Medicamento>> buscarPorFaixaPreco(
            @RequestParam Double precoMin,
            @RequestParam Double precoMax) {
        return ResponseEntity.ok(medicamentoService.buscarPorFaixaPreco(precoMin, precoMax));
    }

    // GET - Buscar por controlado
    @GetMapping("/controlado")
    public ResponseEntity<List<Medicamento>> buscarPorControlado(@RequestParam boolean controlado) {
        return ResponseEntity.ok(medicamentoService.buscarPorControlado(controlado));
    }

    // GET - Estoque baixo
    @GetMapping("/estoque-baixo")
    public ResponseEntity<List<Medicamento>> estoqueBaixo(@RequestParam(defaultValue = "10") int limite) {
        return ResponseEntity.ok(medicamentoService.buscarEstoqueBaixo(limite));
    }

    // PUT - Atualizar
    @PutMapping("/{id}")
    public ResponseEntity<Medicamento> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody Medicamento medicamento) {
        return ResponseEntity.ok(medicamentoService.atualizar(id, medicamento));
    }

    // DELETE - Remover (soft delete)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        medicamentoService.remover(id);
        return ResponseEntity.noContent().build();
    }
}