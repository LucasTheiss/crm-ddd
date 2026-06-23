package com.crm.core.domain.model;

import com.crm.core.domain.enums.EnumStatusFunil;
import com.crm.core.domain.enums.EnumIntencaoLead;
import com.crm.core.domain.exception.LeadInvalidoException;
import java.time.LocalDateTime;

public class Lead {
    private String telefone;
    private String nome;
    private EnumStatusFunil EnumStatusFunil;
    private LocalDateTime criadoEm;
    private LocalDateTime atualizadoEm;
    
    // Construtor garante que um Lead nunca nasça com estado inválido
    public Lead(String telefone, String nome) {
        if (telefone == null || telefone.isBlank()) {
            throw new LeadInvalidoException("O telefone é obrigatório.");
        }
        this.telefone = telefone;
        this.nome = nome;
        this.EnumStatusFunil = EnumStatusFunil.NOVO;
        this.criadoEm = LocalDateTime.now();
        this.atualizadoEm = LocalDateTime.now();
    }

    public void moverParaNegociacao() {
        if (this.EnumStatusFunil.equals(EnumStatusFunil.FECHADO)) {
            throw new IllegalStateException("Não é possível reabrir um lead fechado diretamente.");
        }
        this.EnumStatusFunil = EnumStatusFunil.NEGOCIACAO;
    }

    public String getNome() { return nome; }
    public String setNome(String nome) { this.nome = nome; return nome;}
    public String getTelefone() { return telefone; }
    public String setTelefone(String telefone) { this.telefone = telefone; return telefone; }
    public EnumStatusFunil getEnumStatusFunil() { return EnumStatusFunil; }
    public EnumStatusFunil setEnumStatusFunil(EnumStatusFunil EnumStatusFunil) { this.EnumStatusFunil = EnumStatusFunil; return EnumStatusFunil; }
    public LocalDateTime getCriadoEm() { return criadoEm; }
    public LocalDateTime getAtualizadoEm() { return atualizadoEm; }
    public LocalDateTime setAtualizadoEm(LocalDateTime atualizadoEm) { this.atualizadoEm = atualizadoEm; return atualizadoEm; }
}