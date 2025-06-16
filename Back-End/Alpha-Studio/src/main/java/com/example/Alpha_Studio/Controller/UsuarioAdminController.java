package com.example.Alpha_Studio.Controller;

import com.example.Alpha_Studio.DTO.UsuarioAdminDTO;
import com.example.Alpha_Studio.Service.UsuarioAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class UsuarioAdminController {

    @Autowired
    private UsuarioAdminService service;

    @PostMapping("/login")
    public String login(@RequestBody UsuarioAdminDTO dados) {
        String email = dados.getEmail();
        String senha = dados.getSenha();

        boolean autenticado = service.autenticar(email, senha);

        if (autenticado) {
            return "Login realizado com sucesso!";
        } else {
            return "Email ou senha inválidos!";
        }
    }

}
