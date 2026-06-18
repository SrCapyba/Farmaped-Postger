package com.br.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "itens_venda")
public class ItemVenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "medicamento_id", nullable = false)
    private Medicamento medicamento;

    @Min(value = 1, message = "Quantidade deve ser maior que zero")
    @Column(nullable = false)
    private int quantidade;

    @Column(nullable = false)
    private Double precoUnitario;

    // Construtor padrão
    public ItemVenda() {}

    public ItemVenda(Medicamento medicamento, int quantidade) {
        this.medicamento = medicamento;
        this.quantidade = quantidade;
        this.precoUnitario = medicamento.getPreco();
    }

    public Double calcularSubtotal() {
        return quantidade * precoUnitario;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Medicamento getMedicamento() { return medicamento; }
    public void setMedicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
        if (medicamento != null) {
            this.precoUnitario = medicamento.getPreco();
        }
    }
    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }
    public Double getPrecoUnitario() { return precoUnitario; }
    public void setPrecoUnitario(Double precoUnitario) { this.precoUnitario = precoUnitario; }
}