package com.ponto.api.rest;

import com.ponto.api.entity.dto.security.LoginDTO;
import com.ponto.api.entity.dto.security.UsuarioLoginDTO;
import com.ponto.api.service.security.FuncionarioDetailsService;
import com.ponto.api.service.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class RESTAuth {

    private AuthenticationManager authenticationManager;
    private TokenService tokenService;
    private FuncionarioDetailsService funcionarioDetailsService;

    @Autowired
    public RESTAuth(AuthenticationManager authenticationManager, TokenService tokenService,
                    FuncionarioDetailsService funcionarioDetailsService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.funcionarioDetailsService = funcionarioDetailsService;
    }


    @PostMapping
    public ResponseEntity<UsuarioLoginDTO> createAuthenticationToken(@RequestBody LoginDTO authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getEmail(), authenticationRequest.getSenha());
        final UserDetails userDetails = funcionarioDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        final String token = tokenService.gerarToken(userDetails);

        return ResponseEntity.ok(new UsuarioLoginDTO(token, userDetails.getUsername()));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
