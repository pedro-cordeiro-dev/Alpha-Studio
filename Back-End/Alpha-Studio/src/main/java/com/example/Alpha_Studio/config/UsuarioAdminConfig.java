package com.example.Alpha_Studio.config;

import com.example.Alpha_Studio.Model.UsuarioAdmin;
import com.example.Alpha_Studio.Repository.UsuarioAdminRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UsuarioAdminConfig {

    @Bean("UsuarioAdminConfig")
    CommandLineRunner commandLineRunner(UsuarioAdminRepository repository) {
        return args -> {
            String email = "admin@alphastudio.com";
            String senha = "alphaAdmin123456";

            if (repository.findByEmail(email).isEmpty()) {
                UsuarioAdmin admin = new UsuarioAdmin(null, email, senha);
                repository.save(admin);
                System.out.println("✅ Admin criado com sucesso.");
            } else {
                System.out.println("ℹ️ Admin já cadastrado.");
            }
        };
    }

}
