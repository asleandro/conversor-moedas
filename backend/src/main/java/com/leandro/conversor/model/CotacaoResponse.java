package com.leandro.conversor.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CotacaoResponse {
    private Double cotacaoCompra;
    private Double cotacaoVenda;
    private String dataHoraCotacao;
}
