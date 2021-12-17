package com.ponto.api.rest;

import java.util.List;

import javax.persistence.NoResultException;

import com.ponto.api.entity.dto.LocalizacaoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ponto.api.entity.dto.RegistroPontoDTO;
import com.ponto.api.entity.dto.RegistroPontoUsuarioDTO;
import com.ponto.api.service.RegistroPontoService;

@RestController
@RequestMapping("/registro")
public class RESTPonto extends RESTUtils {

    private RegistroPontoService service;

    @Autowired
    public RESTPonto(RegistroPontoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<RegistroPontoDTO> registrarPonto(@RequestBody LocalizacaoDTO localizacaoDTO) {

        try {
            var registrado = service.registrarPonto(getUsuarioLogado(), localizacaoDTO);
            return ResponseEntity.status(HttpStatus.OK).body(registrado);
        } catch (NoResultException unee) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @GetMapping
    @RequestMapping("/v1")
    public ResponseEntity<List<RegistroPontoUsuarioDTO>> buscarRegistrosV1() {
        try {
            var registros = service.buscarRegistrosV1();
            return ResponseEntity.status(HttpStatus.OK).body(registros);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping
    @RequestMapping("/v2")
    public ResponseEntity<List<RegistroPontoUsuarioDTO>> buscarRegistrosV2() {
        try {
            var registros = service.buscarRegistrosv2(getUsuarioLogado().getEmail());
            return ResponseEntity.status(HttpStatus.OK).body(registros);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
