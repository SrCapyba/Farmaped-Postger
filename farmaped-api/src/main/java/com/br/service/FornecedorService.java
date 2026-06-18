package com.br.service;

import com.br.model.Fornecedor;
import com.br.repository.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class FornecedorService {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Transactional
    public Fornecedor cadastrar(Fornecedor fornecedor) {
        // Validação: CNPJ já existe?
        if (fornecedorRepository.findByCnpj(fornecedor.getCnpj()).isPresent()) {
            throw new RuntimeException("CNPJ já cadastrado!");
        }

        // Validação: campos obrigatórios
        if (fornecedor.getNome() == null || fornecedor.getNome().trim().isEmpty()) {
            throw new RuntimeException("Nome é obrigatório!");
        }
        if (fornecedor.getCnpj() == null || fornecedor.getCnpj().trim().isEmpty()) {
            throw new RuntimeException("CNPJ é obrigatório!");
        }

        return fornecedorRepository.save(fornecedor);
    }

    public List<Fornecedor> listarTodos() {
        return fornecedorRepository.findAll();
    }

    public Fornecedor buscarPorId(Long id) {
        return fornecedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado!"));
    }

    public Fornecedor buscarPorCnpj(String cnpj) {
        return fornecedorRepository.findByCnpj(cnpj)
                .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado!"));
    }

    @Transactional
    public Fornecedor atualizar(Long id, Fornecedor fornecedorAtualizado) {
        Fornecedor fornecedor = buscarPorId(id);

        fornecedor.setNome(fornecedorAtualizado.getNome());
        fornecedor.setCnpj(fornecedorAtualizado.getCnpj());
        fornecedor.setTelefone(fornecedorAtualizado.getTelefone());
        fornecedor.setEmail(fornecedorAtualizado.getEmail());
        fornecedor.setEndereco(fornecedorAtualizado.getEndereco());

        return fornecedorRepository.save(fornecedor);
    }

    @Transactional
    public void inativar(Long id) {
        Fornecedor fornecedor = buscarPorId(id);
        fornecedor.setAtivo(false);
        fornecedorRepository.save(fornecedor);
    }
}