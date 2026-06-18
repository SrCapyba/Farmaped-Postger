package com.br.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    @Column(nullable = false)
    private String nome;

    private String sexo;

    @Min(value = 0, message = "Idade deve ser maior que 0")
    private int idade;

    @NotBlank(message = "CPF é obrigatório")
    @Column(unique = true, nullable = false)
    private String cpf;

    private LocalDate dataNascimento;
    private String telefone;
    private String estado;
    private String cidade;
    private String bairro;
    private String logradouro;
    private int numeroResidencia;
    private String cep;

    @Email(message = "Email inválido")
    private String email;

    private boolean allergy;

    @ElementCollection
    private List<String> nomeAlergia = new ArrayList<>();

    private boolean remedioControlado;

    @ElementCollection
    private List<String> nomeRemedioControlado = new ArrayList<>();

    // Construtor padrão (obrigatório para JPA)
    public Cliente() {}

    // Construtor completo
    public Cliente(Long id, String nome, String sexo, int idade, String cpf,
                   LocalDate dataNascimento, String telefone, String estado,
                   String cidade, String bairro, String logradouro, int numeroResidencia,
                   String cep, String email, boolean allergy, List<String> nomeAlergia,
                   boolean remedioControlado, List<String> nomeRemedioControlado) {
        this.id = id;
        this.nome = nome;
        this.sexo = sexo;
        this.idade = idade;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.estado = estado;
        this.cidade = cidade;
        this.bairro = bairro;
        this.logradouro = logradouro;
        this.numeroResidencia = numeroResidencia;
        this.cep = cep;
        this.email = email;
        this.allergy = allergy;
        this.nomeAlergia = nomeAlergia != null ? new ArrayList<>(nomeAlergia) : new ArrayList<>();
        this.remedioControlado = remedioControlado;
        this.nomeRemedioControlado = nomeRemedioControlado != null ? new ArrayList<>(nomeRemedioControlado) : new ArrayList<>();
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }
    public int getIdade() { return idade; }
    public void setIdade(int idade) { this.idade = idade; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public LocalDate getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public String getCidade() { return cidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }
    public String getBairro() { return bairro; }
    public void setBairro(String bairro) { this.bairro = bairro; }
    public String getLogradouro() { return logradouro; }
    public void setLogradouro(String logradouro) { this.logradouro = logradouro; }
    public int getNumeroResidencia() { return numeroResidencia; }
    public void setNumeroResidencia(int numeroResidencia) { this.numeroResidencia = numeroResidencia; }
    public String getCep() { return cep; }
    public void setCep(String cep) { this.cep = cep; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public boolean isAllergy() { return allergy; }
    public void setAllergy(boolean allergy) { this.allergy = allergy; }
    public List<String> getNomeAlergia() { return nomeAlergia; }
    public void setNomeAlergia(List<String> nomeAlergia) {
        this.nomeAlergia = new ArrayList<>(nomeAlergia);
        this.allergy = !nomeAlergia.isEmpty();
    }
    public boolean isRemedioControlado() { return remedioControlado; }
    public void setRemedioControlado(boolean remedioControlado) { this.remedioControlado = remedioControlado; }
    public List<String> getNomeRemedioControlado() { return nomeRemedioControlado; }
    public void setNomeRemedioControlado(List<String> nomeRemedioControlado) {
        this.nomeRemedioControlado = new ArrayList<>(nomeRemedioControlado);
        this.remedioControlado = !nomeRemedioControlado.isEmpty();
    }
}