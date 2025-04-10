package com.leandro.conversor.controller;

import com.leandro.conversor.dto.ConversaoDTO;
import com.leandro.conversor.service.ConversorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/conversao")
public class ConversorController {

    @Autowired
    private ConversorService service;

    @GetMapping
    public ResponseEntity<ConversaoDTO> converter(
            @RequestParam String moeda,
            @RequestParam Double valor){

        ConversaoDTO resultado = service.converterMoeda(moeda.toUpperCase(), valor);
        return ResponseEntity.ok(resultado);
    }
}
