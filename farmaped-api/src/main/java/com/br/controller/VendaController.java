package com.br.controller;

import com.br.model.ItemVenda;
import com.br.model.Venda;
import com.br.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/vendas")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    // POST - Efetuar venda
    @PostMapping
    public ResponseEntity<Venda> efetuarVenda(@RequestBody Map<String, Object> request) {
        Long clienteId = Long.valueOf(request.get("clienteId").toString());
        List<ItemVenda> itens = (List<ItemVenda>) request.get("itens");
        Double desconto = request.get("desconto") != null ?
                Double.valueOf(request.get("desconto").toString()) : 0.0;
        String formaPagamento = request.get("formaPagamento").toString();

        Venda venda = vendaService.efetuarVenda(clienteId, itens, desconto, formaPagamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(venda);
    }

    // GET - Listar todas
    @GetMapping
    public ResponseEntity<List<Venda>> listarTodas() {
        return ResponseEntity.ok(vendaService.listarTodas());
    }

    // GET - Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<Venda> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(vendaService.buscarPorId(id));
    }

    // GET - Buscar por período
    @GetMapping("/periodo")
    public ResponseEntity<List<Venda>> buscarPorPeriodo(
            @RequestParam LocalDateTime inicio,
            @RequestParam LocalDateTime fim) {
        return ResponseEntity.ok(vendaService.buscarPorData(inicio, fim));
    }

    // GET - Buscar por cliente
    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<Venda>> buscarPorCliente(@PathVariable Long clienteId) {
        return ResponseEntity.ok(vendaService.buscarPorCliente(clienteId));
    }

    // GET - Total vendido no período
    @GetMapping("/total-periodo")
    public ResponseEntity<Double> calcularTotalPeriodo(
            @RequestParam LocalDateTime inicio,
            @RequestParam LocalDateTime fim) {
        return ResponseEntity.ok(vendaService.calcularTotalVendas(inicio, fim));
    }
}