package com.br.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "medicamentos")
public class Medicamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    @Column(nullable = false)
    private String nome;

    private String descricao;
    private String fabricante;
    private String categoria;

    @NotNull(message = "Preço é obrigatório")
    @Positive(message = "Preço deve ser positivo")
    @Column(nullable = false)
    private Double preco;

    @NotNull(message = "Quantidade em estoque é obrigatória")
    @Min(value = 0, message = "Quantidade não pode ser negativa")
    @Column(nullable = false)
    private int quantidadeEstoque;

    private String codigoBarras;

    @Column(nullable = false)
    private boolean controlado;

    @Column(nullable = false)
    private boolean ativo = true;

    // Construtor padrão
    public Medicamento() {}

    // Construtor completo
    public Medicamento(Long id, String nome, String descricao, String fabricante,
                       String categoria, Double preco, int quantidadeEstoque,
                       String codigoBarras, boolean controlado, boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.fabricante = fabricante;
        this.categoria = categoria;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
        this.codigoBarras = codigoBarras;
        this.controlado = controlado;
        this.ativo = ativo;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public String getFabricante() { return fabricante; }
    public void setFabricante(String fabricante) { this.fabricante = fabricante; }
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public Double getPreco() { return preco; }
    public void setPreco(Double preco) { this.preco = preco; }
    public int getQuantidadeEstoque() { return quantidadeEstoque; }
    public void setQuantidadeEstoque(int quantidadeEstoque) { this.quantidadeEstoque = quantidadeEstoque; }
    public String getCodigoBarras() { return codigoBarras; }
    public void setCodigoBarras(String codigoBarras) { this.codigoBarras = codigoBarras; }
    public boolean isControlado() { return controlado; }
    public void setControlado(boolean controlado) { this.controlado = controlado; }
    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }
}