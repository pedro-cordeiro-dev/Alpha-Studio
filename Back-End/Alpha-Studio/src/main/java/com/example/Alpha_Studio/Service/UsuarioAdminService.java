package com.example.Alpha_Studio.Service;

import com.example.Alpha_Studio.Model.UsuarioAdmin;
import com.example.Alpha_Studio.Repository.UsuarioAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioAdminService {

    @Autowired
    private UsuarioAdminRepository repository;

    public boolean autenticar(String email, String senha) {
        Optional<UsuarioAdmin> usuarioOpt = repository.findByEmail(email);

        if (usuarioOpt.isPresent()) {
            UsuarioAdmin usuario = usuarioOpt.get();
            return usuario.getSenha().equals(senha);
        }

        return false;
    }

}
