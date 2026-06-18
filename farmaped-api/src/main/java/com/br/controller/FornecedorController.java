package com.br.controller;

import com.br.model.Fornecedor;
import com.br.service.FornecedorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/fornecedores")
public class FornecedorController {

    @Autowired
    private FornecedorService fornecedorService;

    // POST - Cadastrar
    @PostMapping
    public ResponseEntity<Fornecedor> cadastrar(@Valid @RequestBody Fornecedor fornecedor) {
        Fornecedor novo = fornecedorService.cadastrar(fornecedor);
        return ResponseEntity.status(HttpStatus.CREATED).body(novo);
    }

    // GET - Listar todos
    @GetMapping
    public ResponseEntity<List<Fornecedor>> listarTodos() {
        return ResponseEntity.ok(fornecedorService.listarTodos());
    }

    // GET - Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<Fornecedor> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(fornecedorService.buscarPorId(id));
    }

    // GET - Buscar por CNPJ
    @GetMapping("/cnpj/{cnpj}")
    public ResponseEntity<Fornecedor> buscarPorCnpj(@PathVariable String cnpj) {
        return ResponseEntity.ok(fornecedorService.buscarPorCnpj(cnpj));
    }

    // PUT - Atualizar
    @PutMapping("/{id}")
    public ResponseEntity<Fornecedor> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody Fornecedor fornecedor) {
        return ResponseEntity.ok(fornecedorService.atualizar(id, fornecedor));
    }

    // PUT - Inativar
    @PutMapping("/{id}/inativar")
    public ResponseEntity<Void> inativar(@PathVariable Long id) {
        fornecedorService.inativar(id);
        return ResponseEntity.noContent().build();
    }
}