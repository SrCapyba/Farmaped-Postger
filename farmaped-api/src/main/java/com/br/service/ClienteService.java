package com.br.service;

import com.br.model.Cliente;
import com.br.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional
    public Cliente cadastrar(Cliente cliente) {
        // Validação: CPF já existe?
        if (clienteRepository.findByCpf(cliente.getCpf()).isPresent()) {
            throw new RuntimeException("CPF já cadastrado!");
        }

        // Validação: campos obrigatórios
        if (cliente.getNome() == null || cliente.getNome().trim().isEmpty()) {
            throw new RuntimeException("Nome é obrigatório!");
        }

        return clienteRepository.save(cliente);
    }

    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));
    }

    public Cliente buscarPorCpf(String cpf) {
        return clienteRepository.findByCpf(cpf)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));
    }

    public List<Cliente> buscarPorNome(String nome) {
        return clienteRepository.findByNomeContainingIgnoreCase(nome);
    }

    public List<Cliente> buscarPorFaixaEtaria(int idadeMin, int idadeMax) {
        return clienteRepository.findByIdadeBetween(idadeMin, idadeMax);
    }

    @Transactional
    public Cliente atualizar(Long id, Cliente clienteAtualizado) {
        Cliente cliente = buscarPorId(id);

        cliente.setNome(clienteAtualizado.getNome());
        cliente.setSexo(clienteAtualizado.getSexo());
        cliente.setIdade(clienteAtualizado.getIdade());
        cliente.setDataNascimento(clienteAtualizado.getDataNascimento());
        cliente.setTelefone(clienteAtualizado.getTelefone());
        cliente.setEstado(clienteAtualizado.getEstado());
        cliente.setCidade(clienteAtualizado.getCidade());
        cliente.setBairro(clienteAtualizado.getBairro());
        cliente.setLogradouro(clienteAtualizado.getLogradouro());
        cliente.setNumeroResidencia(clienteAtualizado.getNumeroResidencia());
        cliente.setCep(clienteAtualizado.getCep());
        cliente.setEmail(clienteAtualizado.getEmail());
        cliente.setNomeAlergia(clienteAtualizado.getNomeAlergia());
        cliente.setNomeRemedioControlado(clienteAtualizado.getNomeRemedioControlado());

        return clienteRepository.save(cliente);
    }

    @Transactional
    public void remover(Long id) {
        Cliente cliente = buscarPorId(id);
        clienteRepository.delete(cliente);
    }
}