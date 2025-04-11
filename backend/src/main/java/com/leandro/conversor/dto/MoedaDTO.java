package com.leandro.conversor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MoedaDTO {
    private String simbolo;
    private String nomeFormatado;
    private String tipoMoeda;
}
