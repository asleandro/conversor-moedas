package com.leandro.conversor.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.leandro.conversor.dto.ListaDeMoedasDTO;
import com.leandro.conversor.dto.MoedaDTO;
import com.leandro.conversor.model.CotacaoResponse;
import com.leandro.conversor.model.ListaDeMoedasResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class BancoCentralClient {

    private final RestTemplate restTemplate = new RestTemplate();

    public CotacaoResponse buscarCotacao(String moeda, LocalDate data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        String dataFormatada = data.format(formatter);

        String url = String.format(
                "https://olinda.bcb.gov.br/olinda/servico/PTAX/versao/v1/odata/" +
                        "CotacaoMoedaDia(moeda='%s',dataCotacao='%s')?$format=json",
                moeda, dataFormatada);

        ResponseEntity<JsonNode> response = restTemplate.getForEntity(url, JsonNode.class);

        JsonNode valueNode = response.getBody().get("value");
        if (valueNode.isEmpty()) {
            throw new RuntimeException(("Cotação não encontrada para a data " + data));
        }

        JsonNode cotacao = valueNode.get(0);
        return new CotacaoResponse(
                cotacao.get("cotacaoCompra").asDouble(),
                cotacao.get("cotacaoVenda").asDouble(),
                cotacao.get("dataHoraCotacao").asText()
        );
    }

    public ListaDeMoedasResponse listarMoedas() {
        String url = "https://olinda.bcb.gov.br/olinda/servico/PTAX/versao/v1/odata/Moedas";
        ResponseEntity<JsonNode> response = restTemplate.getForEntity(url, JsonNode.class);
        JsonNode body = response.getBody();
        List<MoedaDTO> moedas = new ArrayList<>();

        if (body != null && body.has("value")) {
            for (JsonNode item : body.get("value")) {
                String simbolo = item.get("simbolo").asText();
                String nomeFormatado = item.get("nomeFormatado").asText();
                String tipoMoeda = item.get("tipoMoeda").asText();
                MoedaDTO moeda = new MoedaDTO(simbolo, nomeFormatado, tipoMoeda);

                moedas.add(moeda);
                // TODO: Adicionar lógica para filtrar tipoMoeda = "A"
            }
        }
        return new ListaDeMoedasResponse(moedas);
    }

}
