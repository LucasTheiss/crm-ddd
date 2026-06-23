package com.crm.core.domain.repository;

import com.crm.core.domain.model.Lead;
import java.util.Optional;

// O domínio avisa que precisa disso, mas não sabe como será salvo
public interface LeadRepository {
    Optional<Lead> buscarPorTelefone(String telefone);
    void salvar(Lead lead);
}