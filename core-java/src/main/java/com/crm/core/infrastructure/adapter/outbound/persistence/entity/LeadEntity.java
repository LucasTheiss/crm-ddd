package com.crm.core.infrastructure.adapter.outbound.persistence.entity;

import com.crm.core.domain.model.StatusKanban;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;

@Entity
@Table(name = "leads")
public class LeadEntity {
    
    @Id
    private String telefone;
    private String nome;

    // Salva a String ("NEGOCIACAO") e não o índice numérico (1)
    @Enumerated(EnumType.STRING)
    private StatusKanban statusKanban;

    // Construtores, Getters e Setters obrigatórios do JPA...
    public LeadEntity() {}
    
    public LeadEntity(String telefone, String nome, StatusKanban statusKanban) {
        this.telefone = telefone;
        this.nome = nome;
        this.statusKanban = statusKanban;
    }
    
    public String getTelefone() { return telefone; }
    public String getNome() { return nome; }
    public StatusKanban getStatusKanban() { return statusKanban; }
}