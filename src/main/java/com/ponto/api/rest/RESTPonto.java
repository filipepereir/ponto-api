package com.ponto.api.rest;


import com.ponto.api.entity.dto.RegistroPontoDTO;
import com.ponto.api.entity.dto.RegistroPontoUsuarioDTO;
import com.ponto.api.entity.dto.UsuarioDTO;
import com.ponto.api.service.RegistroPontoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import java.util.List;


@RestController
@RequestMapping("/registro")
public class RESTPonto {


    private RegistroPontoService service;

    @Autowired
    public RESTPonto(RegistroPontoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<RegistroPontoDTO> registrarPonto(@RequestBody UsuarioDTO usuario) {

        try {
            RegistroPontoDTO registrado = service.registrarPonto(usuario);
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
            var registrado = service.buscarRegistrosV1();
            return ResponseEntity.status(HttpStatus.OK).body(registrado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping
    @RequestMapping("/v2")
    public ResponseEntity<List<RegistroPontoUsuarioDTO>> buscarRegistrosV2() {
        try {
            var registrado = service.buscarRegistrosv2();
            return ResponseEntity.status(HttpStatus.OK).body(registrado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
