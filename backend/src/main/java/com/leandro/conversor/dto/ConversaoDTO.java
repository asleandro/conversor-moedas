package com.leandro.conversor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ConversaoDTO {
    private String moeda;
    private Double valorOriginal;
    private Double cotacao;
    private String dataCotacao;
    private Double valorConvertido;
}
