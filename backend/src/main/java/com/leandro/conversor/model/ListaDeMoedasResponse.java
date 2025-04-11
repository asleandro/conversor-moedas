package com.leandro.conversor.model;

import com.leandro.conversor.dto.MoedaDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class ListaDeMoedasResponse {
    private List<MoedaDTO> moedas = new ArrayList<>();
}
