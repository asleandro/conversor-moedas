package com.leandro.conversor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class ListaDeMoedasDTO {
    private List<MoedaDTO> moedas = new ArrayList<>();
}
