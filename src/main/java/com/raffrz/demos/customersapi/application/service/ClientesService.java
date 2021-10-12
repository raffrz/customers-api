package com.raffrz.demos.customersapi.application.service;

import java.util.List;

import com.raffrz.demos.customersapi.application.repository.ClienteRepository;
import com.raffrz.demos.customersapi.domain.model.Cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Classe Service que controla as operacoes sobre a entidade Cliente
 */
@Service
public class ClientesService {

    @Autowired
    private ClienteRepository repository;

    /**
     * 
     * @param pageable
     * @return
     */
    public Page<Cliente> listarClientes(Pageable pageable) {
        return repository.findAll(pageable);
    }

    /**
     * 
     * @param nome
     * @return
     */
    public List<Cliente> buscarPorNome(String nome) {
        return repository.findByNome(nome);
    }

    /**
     * 
     * @param telefone
     * @return
     */
    public List<Cliente> buscarPorTelefone(Long telefone) {
        return repository.findByTelefone(telefone);
    }

    /**
     * 
     * @param cidade
     * @return
     */
    public List<Cliente> buscarPorCidade(String cidade) {
        return repository.findByCidade(cidade);
    }

    /**
     * 
     * @param uf
     * @return
     */
    public List<Cliente> buscarPorUf(String uf) {
        return repository.findByUf(uf);
    }

    /**
     * 
     * @param uf
     * @param idadeMinima
     * @return
     */
    public List<Cliente> buscarPorUfEIdadeMaiorQue(String uf, Integer idadeMinima) {
        return repository.findByUfAndIdadeGreaterThan(uf, idadeMinima);
    }

    /**
     * 
     * @param uf
     * @param idadeMaxima
     * @return
     */
    public List<Cliente> buscarPorUfEIdadeMenorQue(String uf, Integer idadeMaxima) {
        return repository.findByUfAndIdadeLowerThan(uf, idadeMaxima);
    }

    /**
     * 
     * @param uf
     * @param idadeMinima
     * @param idadeMaxima
     * @return
     */
    public List<Cliente> buscarPorUfEIdadeEntre(String uf, Integer idadeMinima, Integer idadeMaxima) {
        return repository.findByUfAndIdadeBetween(uf, idadeMinima, idadeMaxima);
    }

    /**
     * 
     * @param nome
     * @param email
     * @param telefone
     * @param idade
     * @param uf
     * @param cidade
     */
    public void criarCliente(String nome, String email, Long telefone, Integer idade, String uf, String cidade) {
        Cliente c = new Cliente(null, nome, email, telefone, uf, cidade, idade);
        repository.save(c);
    }

    /**
     * altera o cliente pelo id.
     * se não houver registro cria um novo
     * 
     * @param id
     * @param nome
     * @param email
     * @param telefone
     * @param idade
     * @param uf
     * @param cidade
     */
    public void alterarCliente(Long id, String nome, String email, Long telefone, Integer idade, String uf,
            String cidade) {
        Cliente c = repository.getById(id);
        if (c == null) {
            c = new Cliente();
            c.setNome(nome);
            c.setEmail(email);
            c.setTelefone(telefone);
            c.setUf(uf);
            c.setCidade(cidade);
            c.setIdade(idade);
        }
        repository.save(c);
    }

    /**
     * exclui o cliente
     * 
     * @param id
     * @return true se excluido false se não excluído
     */
    public void excluirCliente(Long id) {
        Cliente c = repository.getById(id);
        if (c != null) {
            repository.delete(c);
        }
    }

}
