package com.ponto.api.service.security;

import com.ponto.api.entity.Usuario;
import com.ponto.api.entity.dto.security.LoginDTO;
import com.ponto.api.entity.dto.security.UsuarioLoginDTO;
import com.ponto.api.exceptions.ExpiredTokenException;
import com.ponto.api.exceptions.InvalidLoginException;
import com.ponto.api.exceptions.InvalidTokenException;
import com.ponto.api.repository.FuncionarioRepository;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthService {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public UsuarioLoginDTO authenticate(LoginDTO usuarioLogin, String token) {
        Usuario user = funcionarioRepository.findByEmail(usuarioLogin.getEmail());
        if (usuarioLogin.getSenha().equals(user.getSenha()) && !token.isEmpty() && validate(token)) {
            return new UsuarioLoginDTO(user.getCodigo(), user.getEmail(), user.getSenha(), token);
        } else {
            throw new InvalidLoginException();
        }
    }

    private boolean validate(String token) {
        try {
            String tokenTratado = token.replace("Bearer ", "");
            Claims claims = tokenService.decodeToken(tokenTratado);

            System.out.println(claims.getIssuer());
            System.out.println(claims.getIssuedAt());
            //Verifica se o token está expirado
            if (claims.getExpiration().before(new Date(System.currentTimeMillis()))) throw new ExpiredTokenException();
            System.out.println(claims.getExpiration());
            return true;
        } catch (ExpiredTokenException et) {
            et.printStackTrace();
            throw et;
        } catch (Exception e) {
            e.printStackTrace();
            throw new InvalidTokenException();
        }

    }

}
