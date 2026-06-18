package com.br.repository;

import com.br.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {
    List<Venda> findByDataBetween(LocalDateTime inicio, LocalDateTime fim);
    List<Venda> findByClienteId(Long clienteId);
}