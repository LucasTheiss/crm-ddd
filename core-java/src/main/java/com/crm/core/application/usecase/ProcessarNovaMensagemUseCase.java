package com.crm.core.application.usecase;

import com.crm.core.application.dto.MensagemRecebidaDTO;
import com.crm.core.application.port.out.AIAgentPort;
import com.crm.core.domain.model.Lead;
import com.crm.core.domain.enums.EnumIntencaoLead;
import com.crm.core.domain.repository.LeadRepository;

public class ProcessarNovaMensagemUseCase {
    
    private final LeadRepository leadRepository;
    private final AIAgentPort aiAgent;

    public ProcessarNovaMensagemUseCase(LeadRepository leadRepository, AIAgentPort aiAgent) {
        this.leadRepository = leadRepository;
        this.aiAgent = aiAgent;
    }

    public void executar(MensagemRecebidaDTO dto) {
        // 1. Recupera ou cria
        Lead lead = leadRepository.buscarPorTelefone(dto.telefone())
                .orElseGet(() -> new Lead(dto.telefone(), "Desconhecido"));

        // 2. Aciona o mundo externo pela porta
        String intencao = aiAgent.analisarIntencao(dto.textoMensagem());

        // 3. Modifica o domínio baseado na regra de negócio
        if (EnumIntencaoLead.COMPRA.name().equalsIgnoreCase(intencao)) {
            lead.moverParaNegociacao();
        }

        // 4. Salva o estado
        leadRepository.salvar(lead);
    }
}