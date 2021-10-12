package com.raffrz.demos.customersapi.application.controller;

import java.util.List;

import com.raffrz.demos.customersapi.application.service.ClientesService;
import com.raffrz.demos.customersapi.domain.model.Cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/cliente")
public class ClientesController {

    @Autowired
    private ClientesService repository;

    @GetMapping
    public @ResponseBody Page<Cliente> listarClientes(Pageable pageable) {
        return repository.listarClientes(pageable);
    }

    @GetMapping(params = "nome")
    public @ResponseBody List<Cliente> buscarPorNome(@RequestParam String nome) {
        return repository.buscarPorNome(nome);
    }

    @GetMapping(params = "telefone")
    public @ResponseBody List<Cliente> buscarPorTelefone(@RequestParam Long telefone) {
        return repository.buscarPorTelefone(telefone);
    }

    @GetMapping(params = "cidade")
    public @ResponseBody List<Cliente> buscarPorCidade(@RequestParam String cidade) {
        return repository.buscarPorCidade(cidade);
    }

    @GetMapping(params = "uf")
    public @ResponseBody List<Cliente> buscarPorUf(@RequestParam String uf) {
        return repository.buscarPorUf(uf);
    }

    @GetMapping(params = {"uf", "idadeMinima"})
    public @ResponseBody List<Cliente> buscarPorUfEIdadeMaiorQue(@RequestParam String uf, @RequestParam Integer idadeMinima) {
        return repository.buscarPorUfEIdadeMaiorQue(uf, idadeMinima);
    }

    @GetMapping(params = {"uf", "idadeMaxima"})
    public @ResponseBody List<Cliente> buscarPorUfEIdadeMenorQue(@RequestParam String uf, @RequestParam Integer idadeMaxima) {
        return repository.buscarPorUfEIdadeMenorQue(uf, idadeMaxima);
    }

    @GetMapping(params = {"uf", "idadeMinima", "idadeMaxima"})
    public @ResponseBody List<Cliente> buscarPorUfEIdadeEntre(@RequestParam String uf, @RequestParam Integer idadeMinima, @RequestParam Integer idadeMaxima) {
        return repository.buscarPorUfEIdadeEntre(uf, idadeMinima, idadeMaxima);
    }

    @PostMapping
    public @ResponseBody String criarCliente(@RequestParam String nome, @RequestParam String email, 
      @RequestParam(required = false) Long telefone, @RequestParam(required = false) Integer idade,
      @RequestParam String uf, @RequestParam String cidade)  {
        repository.criarCliente(nome, email, telefone, idade, uf, cidade);
        return "created";
    }

    @PutMapping
    public @ResponseBody String alterarCliente(@RequestParam Long id, @RequestParam String nome, @RequestParam String email, 
      @RequestParam(required = false) Long telefone, @RequestParam(required = false) Integer idade,
      @RequestParam String uf, @RequestParam String cidade)  {
        repository.alterarCliente(id, nome, email, telefone, idade, uf, cidade);
        return "updated";
    }

    @DeleteMapping("/{id}")
    public @ResponseBody String excluirCliente(@PathVariable("id") Long id)  {
        repository.excluirCliente(id);  
        return "deleted";
    }
    
}
