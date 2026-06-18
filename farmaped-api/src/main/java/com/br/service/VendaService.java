package com.br.service;

import com.br.model.Cliente;
import com.br.model.ItemVenda;
import com.br.model.Medicamento;
import com.br.model.Venda;
import com.br.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private MedicamentoService medicamentoService;

    @Autowired
    private ClienteService clienteService;

    @Transactional
    public Venda efetuarVenda(Long clienteId, List<ItemVenda> itens, Double desconto, String formaPagamento) {
        // Buscar cliente
        Cliente cliente = clienteService.buscarPorId(clienteId);

        // Validar itens e estoque
        for (ItemVenda item : itens) {
            Medicamento medicamento = medicamentoService.buscarPorId(item.getMedicamento().getId());

            // Verificar estoque
            if (medicamento.getQuantidadeEstoque() < item.getQuantidade()) {
                throw new RuntimeException("Estoque insuficiente para o medicamento: " + medicamento.getNome());
            }

            // Verificar se é controlado (a validação de receita seria feita no frontend)
            if (medicamento.isControlado()) {
                System.out.println("⚠️ ATENÇÃO: Medicamento controlado. Verifique a receita!");
            }
        }

        // Criar venda
        Venda venda = new Venda(cliente, itens, desconto, formaPagamento);

        // Dar baixa no estoque
        for (ItemVenda item : itens) {
            Medicamento medicamento = medicamentoService.buscarPorId(item.getMedicamento().getId());
            int novaQuantidade = medicamento.getQuantidadeEstoque() - item.getQuantidade();
            medicamento.setQuantidadeEstoque(novaQuantidade);
            medicamentoService.atualizar(medicamento.getId(), medicamento);
        }

        return vendaRepository.save(venda);
    }

    public List<Venda> listarTodas() {
        return vendaRepository.findAll();
    }

    public Venda buscarPorId(Long id) {
        return vendaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venda não encontrada!"));
    }

    public List<Venda> buscarPorData(LocalDateTime inicio, LocalDateTime fim) {
        return vendaRepository.findByDataBetween(inicio, fim);
    }

    public List<Venda> buscarPorCliente(Long clienteId) {
        return vendaRepository.findByClienteId(clienteId);
    }

    public Double calcularTotalVendas(LocalDateTime inicio, LocalDateTime fim) {
        return vendaRepository.findByDataBetween(inicio, fim).stream()
                .mapToDouble(Venda::getValorTotal)
                .sum();
    }
}