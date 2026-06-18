package com.br.repository;

import com.br.model.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
    Optional<Fornecedor> findByCnpj(String cnpj);
    Optional<Fornecedor> findByNomeContainingIgnoreCase(String nome);
}