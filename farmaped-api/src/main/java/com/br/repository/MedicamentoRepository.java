package com.br.repository;

import com.br.model.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {
    List<Medicamento> findByNomeContainingIgnoreCase(String nome);
    List<Medicamento> findByCategoriaIgnoreCase(String categoria);
    List<Medicamento> findByPrecoBetween(Double precoMin, Double precoMax);
    List<Medicamento> findByControlado(boolean controlado);
    List<Medicamento> findByQuantidadeEstoqueLessThan(int limite);
}