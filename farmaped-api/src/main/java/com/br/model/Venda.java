package com.br.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "vendas")
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVenda;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "venda_id")
    private List<ItemVenda> itens = new ArrayList<>();

    @Column(nullable = false)
    private LocalDateTime data = LocalDateTime.now();

    @Column(nullable = false)
    private Double valorTotal;

    private Double desconto = 0.0;

    @Column(nullable = false)
    private String formaPagamento;

    // Construtor padrão
    public Venda() {}

    public Venda(Cliente cliente, List<ItemVenda> itens, Double desconto, String formaPagamento) {
        this.cliente = cliente;
        this.itens = new ArrayList<>(itens);
        this.desconto = desconto != null ? desconto : 0.0;
        this.formaPagamento = formaPagamento;
        this.data = LocalDateTime.now();
        calcularValorTotal();
    }

    private void calcularValorTotal() {
        this.valorTotal = itens.stream()
                .mapToDouble(ItemVenda::calcularSubtotal)
                .sum();
        this.valorTotal -= this.desconto;
    }

    // Getters e Setters
    public Long getIdVenda() { return idVenda; }
    public void setIdVenda(Long idVenda) { this.idVenda = idVenda; }
    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
    public List<ItemVenda> getItens() { return itens; }
    public void setItens(List<ItemVenda> itens) {
        this.itens = new ArrayList<>(itens);
        calcularValorTotal();
    }
    public LocalDateTime getData() { return data; }
    public void setData(LocalDateTime data) { this.data = data; }
    public Double getValorTotal() { return valorTotal; }
    public void setValorTotal(Double valorTotal) { this.valorTotal = valorTotal; }
    public Double getDesconto() { return desconto; }
    public void setDesconto(Double desconto) { this.desconto = desconto; }
    public String getFormaPagamento() { return formaPagamento; }
    public void setFormaPagamento(String formaPagamento) { this.formaPagamento = formaPagamento; }
}