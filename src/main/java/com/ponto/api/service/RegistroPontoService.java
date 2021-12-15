package com.ponto.api.service;


import com.ponto.api.entity.RegistroPonto;
import com.ponto.api.entity.Usuario;
import com.ponto.api.entity.dto.RegistroPontoDTO;
import com.ponto.api.entity.dto.RegistroPontoUsuarioDTO;
import com.ponto.api.entity.dto.UsuarioDTO;
import com.ponto.api.enums.StatusRegistroPonto;
import com.ponto.api.repository.FuncionarioRepository;
import com.ponto.api.repository.RegistroPontoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegistroPontoService {

    @Autowired
    private RegistroPontoRepository repository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public RegistroPontoDTO registrarPonto(UsuarioDTO usuario) {
        UsuarioDTO usuarioBuscado = null;
        try {
            usuarioBuscado = funcionarioRepository.buscarUsuarioPorId(usuario.getCodigo());
        } catch (NoResultException nre) {
            throw nre;
        }
        RegistroPonto registroPonto = new RegistroPonto(new Usuario(usuarioBuscado));

        Long quantidadeBatidasDoDia = repository.buscarQuantidadeBatidasTotaisPorDia(usuarioBuscado.getCodigo());
        Long quantidadeBatidasErroDoDia = repository.buscarQuantidadeBatidasErroPorDia(usuarioBuscado.getCodigo());

        if (quantidadeBatidasDoDia < 4) {
            registroPonto.setStatus(StatusRegistroPonto.REGISTRADO);
            registroPonto.setMensagem("Ponto registrado");

            RegistroPonto registrado = repository.save(registroPonto);

            return new RegistroPontoDTO(registrado);

        } else if (quantidadeBatidasErroDoDia == 0) {
            registroPonto.setStatus(StatusRegistroPonto.ERRO);
            registroPonto.setMensagem("Erro o registrar ponto, quantidade maxima de 4 batidas");

            RegistroPonto registrado = repository.save(registroPonto);

            return new RegistroPontoDTO(registrado);
        }
        registroPonto.setStatus(StatusRegistroPonto.ERRO);
        registroPonto.setMensagem(
                "Erro ao registrar ponto, quantidade maxima de 4 batidas e maximo de 2 solicitações com erro, tente novamente amanhã");

        //RegistroPonto registrado = repository.save(registroPonto);

        return new RegistroPontoDTO(registroPonto);

    }

    public List<RegistroPontoUsuarioDTO> buscarRegistros() {

        return repository.findAll().stream().map(RegistroPontoUsuarioDTO::new).collect(Collectors.toList());
    }

    public List<RegistroPontoUsuarioDTO> buscarRegistrosv2() {
        return repository.buscarRegistrosUsuariosV2();
    }
}
