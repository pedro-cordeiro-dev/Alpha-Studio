package com.example.Alpha_Studio.Repository;

import com.example.Alpha_Studio.Model.UsuarioAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioAdminRepository extends JpaRepository<UsuarioAdmin, Long> {

    Optional<UsuarioAdmin> findByEmail(String email);
}
