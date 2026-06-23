package com.crm.core.infrastructure.adapter.inbound.rest;

import com.crm.core.application.dto.MensagemRecebidaDTO;
import com.crm.core.application.usecase.ProcessarNovaMensagemUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/external/leads")
public class PublicLeadController {

    // A dependência para o nosso Maestro (UseCase)
    private final ProcessarNovaMensagemUseCase useCase;

    // Injeção de dependência via construtor
    public PublicLeadController(ProcessarNovaMensagemUseCase useCase) {
        this.useCase = useCase;
    }

    /**
     * Endpoint público para sistemas de terceiros (ex: n8n, Typeform, etc)
     * Rota: POST /api/v1/external/leads
     */
    @PostMapping
    public ResponseEntity<String> receberLeadExterno(
            @RequestHeader(value = "X-API-KEY", required = false) String apiKey,
            @RequestBody ExternalLeadPayload payload) {

        // 1. Validação de Segurança Básica (Apenas quem tem a chave pode postar)
        // Em um sistema real, essa chave viria das variáveis de ambiente ou banco
        if (apiKey == null || !apiKey.equals("chave_secreta_crm_123")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("API Key inválida ou ausente.");
        }

        // 2. Validação de estrutura (evitar payloads vazios)
        if (payload.telefone() == null) {
            return ResponseEntity.badRequest().body("Telefone é obrigatórios.");
        }

        // 3. Tradução: O Controller traduz o JSON externo para o formato que o nosso UseCase entende
        MensagemRecebidaDTO dto = new MensagemRecebidaDTO(
                payload.telefone(), 
                payload.mensagemInicial()
        );

        // 4. Execução: Aciona a regra de negócio central (A mágica da reutilização!)
        useCase.executar(dto);

        // 5. Resposta: Devolve o status 201 (Created)
        return ResponseEntity.status(HttpStatus.CREATED).body("Lead processado e inserido no funil com sucesso.");
    }

    /**
     * DTO interno do Controller. 
     * Define a estrutura exata do JSON que o sistema externo deve enviar:
     * {
     * "telefone": "5541999999999",
     * "mensagemInicial": "Gostaria de um orçamento"
     * }
     */
    public record ExternalLeadPayload(String telefone, String mensagemInicial) {}
}