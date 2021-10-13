package com.raffrz.demos.customersapi.application.service;

import java.util.List;

import com.raffrz.demos.customersapi.application.repository.ClienteRepository;
import com.raffrz.demos.customersapi.domain.model.Cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
     * salva um cliente novo
     * @param cliente
     */
    public void criarCliente(Cliente cliente) {
        Cliente c = new Cliente(null, cliente.getNome(), cliente.getEmail(), cliente.getTelefone(), cliente.getUf(), cliente.getCidade(), cliente.getIdade());
        repository.save(c);
    }

    /**
     * altera o cliente pelo id.
     * se não houver registro não cria um novo
     * 
     * @param cliente
     */
    @Transactional
    public void alterarCliente(Cliente cliente) {
        Cliente found = repository.getById(cliente.getId());
        if (found != null) {
            found.setNome(cliente.getNome());
            found.setEmail(cliente.getEmail());
            found.setTelefone(cliente.getTelefone());
            found.setUf(cliente.getUf());
            found.setCidade(cliente.getCidade());
            found.setIdade(cliente.getIdade());
            repository.save(found);
        }
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
