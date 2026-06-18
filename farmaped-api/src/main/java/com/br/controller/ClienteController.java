package com.br.controller;

import com.br.model.Cliente;
import com.br.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    // POST - Cadastrar cliente
    @PostMapping
    public ResponseEntity<Cliente> cadastrar(@Valid @RequestBody Cliente cliente) {
        Cliente novoCliente = clienteService.cadastrar(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoCliente);
    }

    // GET - Listar todos
    @GetMapping
    public ResponseEntity<List<Cliente>> listarTodos() {
        return ResponseEntity.ok(clienteService.listarTodos());
    }

    // GET - Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.buscarPorId(id));
    }

    // GET - Buscar por CPF
    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<Cliente> buscarPorCpf(@PathVariable String cpf) {
        return ResponseEntity.ok(clienteService.buscarPorCpf(cpf));
    }

    // GET - Buscar por Nome
    @GetMapping("/nome")
    public ResponseEntity<List<Cliente>> buscarPorNome(@RequestParam String nome) {
        return ResponseEntity.ok(clienteService.buscarPorNome(nome));
    }

    // GET - Buscar por faixa etária
    @GetMapping("/idade")
    public ResponseEntity<List<Cliente>> buscarPorFaixaEtaria(
            @RequestParam int idadeMin,
            @RequestParam int idadeMax) {
        return ResponseEntity.ok(clienteService.buscarPorFaixaEtaria(idadeMin, idadeMax));
    }

    // PUT - Atualizar cliente
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody Cliente cliente) {
        return ResponseEntity.ok(clienteService.atualizar(id, cliente));
    }

    // DELETE - Remover cliente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        clienteService.remover(id);
        return ResponseEntity.noContent().build();
    }
}