package com.raffrz.demos.customersapi.application.repository;

import java.util.List;

import com.raffrz.demos.customersapi.domain.model.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    List<Cliente> findByEmail(String email);

    @Query("from Cliente c where c.nome LIKE %:nome%")
    List<Cliente> findByNome(String nome);

    List<Cliente> findByTelefone(Long telefone);
    List<Cliente> findByUf(String uf);
    List<Cliente> findByCidade(String cidade);

    @Query("from Cliente c where UPPER(c.uf) = UPPER(:uf) and c.idade > :idadeMin and c.idade < :idadeMax")
    List<Cliente> findByUfAndIdadeBetween(String uf, Integer idadeMin, Integer idadeMax);

    @Query("from Cliente c where UPPER(c.uf) = UPPER(:uf) and c.idade > :idadeMin")
    List<Cliente> findByUfAndIdadeGreaterThan(String uf, Integer idadeMin);

    @Query("from Cliente c where UPPER(c.uf) = UPPER(:uf) and c.idade < :idadeMax")
    List<Cliente> findByUfAndIdadeLowerThan(String uf, Integer idadeMax);
    
}
