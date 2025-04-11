package com.leandro.conversor.service;

import com.leandro.conversor.client.BancoCentralClient;
import com.leandro.conversor.dto.ConversaoDTO;
import com.leandro.conversor.dto.ListaDeMoedasDTO;
import com.leandro.conversor.dto.MoedaDTO;
import com.leandro.conversor.model.CotacaoResponse;
import com.leandro.conversor.model.ListaDeMoedasResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ConversorService {

    @Autowired
    private BancoCentralClient bcClient;

    public ConversaoDTO converterMoeda(String moeda, Double valor) {
        CotacaoResponse cotacao = bcClient.buscarCotacao(moeda, LocalDate.now());

        Double valorConvertido = valor * cotacao.getCotacaoVenda();

        return new ConversaoDTO(
                moeda,
                valor,
                cotacao.getCotacaoVenda(),
                cotacao.getDataHoraCotacao(),
                valorConvertido
        );
    }

    public ListaDeMoedasDTO listarMoedas(){
        ListaDeMoedasResponse moedas = bcClient.listarMoedas();

        ListaDeMoedasDTO listaDeMoedas = new ListaDeMoedasDTO(moedas.getMoedas());

        return listaDeMoedas;
    }
}
