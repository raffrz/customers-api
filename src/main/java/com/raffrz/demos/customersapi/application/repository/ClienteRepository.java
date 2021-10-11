package com.raffrz.demos.customersapi.application.repository;

import java.util.List;

import com.raffrz.demos.customersapi.domain.model.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    List<Cliente> findByEmail(String email);
    List<Cliente> findByNome(String nome);
    List<Cliente> findByTelefone(Long telefone);
    List<Cliente> findByUf(String uf);
    List<Cliente> findByCidade(String cidade);
    List<Cliente> findByUfAndCidadeIgnoreCase(String uf, String cidade);
    
}
