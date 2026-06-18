package com.br.service;

import com.br.model.Medicamento;
import com.br.repository.MedicamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class MedicamentoService {

    @Autowired
    private MedicamentoRepository medicamentoRepository;

    @Transactional
    public Medicamento cadastrar(Medicamento medicamento) {
        // Validações
        if (medicamento.getNome() == null || medicamento.getNome().trim().isEmpty()) {
            throw new RuntimeException("Nome do medicamento é obrigatório!");
        }
        if (medicamento.getPreco() == null || medicamento.getPreco() < 0) {
            throw new RuntimeException("Preço deve ser maior que zero!");
        }
        if (medicamento.getQuantidadeEstoque() < 0) {
            throw new RuntimeException("Quantidade em estoque não pode ser negativa!");
        }

        return medicamentoRepository.save(medicamento);
    }

    public List<Medicamento> listarTodos() {
        return medicamentoRepository.findAll();
    }

    public Medicamento buscarPorId(Long id) {
        return medicamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medicamento não encontrado!"));
    }

    public List<Medicamento> buscarPorNome(String nome) {
        return medicamentoRepository.findByNomeContainingIgnoreCase(nome);
    }

    public List<Medicamento> buscarPorCategoria(String categoria) {
        return medicamentoRepository.findByCategoriaIgnoreCase(categoria);
    }

    public List<Medicamento> buscarPorFaixaPreco(Double precoMin, Double precoMax) {
        return medicamentoRepository.findByPrecoBetween(precoMin, precoMax);
    }

    public List<Medicamento> buscarPorControlado(boolean controlado) {
        return medicamentoRepository.findByControlado(controlado);
    }

    public List<Medicamento> buscarEstoqueBaixo(int limite) {
        return medicamentoRepository.findByQuantidadeEstoqueLessThan(limite);
    }

    @Transactional
    public Medicamento atualizar(Long id, Medicamento medicamentoAtualizado) {
        Medicamento medicamento = buscarPorId(id);

        medicamento.setNome(medicamentoAtualizado.getNome());
        medicamento.setDescricao(medicamentoAtualizado.getDescricao());
        medicamento.setFabricante(medicamentoAtualizado.getFabricante());
        medicamento.setCategoria(medicamentoAtualizado.getCategoria());
        medicamento.setPreco(medicamentoAtualizado.getPreco());
        medicamento.setQuantidadeEstoque(medicamentoAtualizado.getQuantidadeEstoque());
        medicamento.setCodigoBarras(medicamentoAtualizado.getCodigoBarras());
        medicamento.setControlado(medicamentoAtualizado.isControlado());
        medicamento.setAtivo(medicamentoAtualizado.isAtivo());

        return medicamentoRepository.save(medicamento);
    }

    @Transactional
    public void remover(Long id) {
        Medicamento medicamento = buscarPorId(id);
        medicamento.setAtivo(false); // Soft delete
        medicamentoRepository.save(medicamento);
    }
}