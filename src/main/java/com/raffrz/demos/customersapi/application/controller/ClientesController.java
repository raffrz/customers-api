package com.raffrz.demos.customersapi.application.controller;

import java.util.List;

import com.raffrz.demos.customersapi.application.repository.ClienteRepository;
import com.raffrz.demos.customersapi.domain.model.Cliente;

import org.springframework.beans.factory.annotation.Autowired;
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
    private ClienteRepository repository;

    @GetMapping
    public @ResponseBody List<Cliente> listarClientes() {
        return repository.findAll();
    }

    @PostMapping
    public @ResponseBody String criarCliente(@RequestParam String nome, @RequestParam String email, 
      @RequestParam(required = false) Long telefone, @RequestParam(required = false) Integer idade,
      @RequestParam String uf, @RequestParam String cidade)  {
        Cliente c = new Cliente(null, nome, email, telefone, uf, cidade, idade);
        repository.save(c);
        return "created";
    }

    @PutMapping
    public @ResponseBody String alterarCliente(@RequestParam Long id, @RequestParam String nome, @RequestParam String email, 
      @RequestParam(required = false) Long telefone, @RequestParam(required = false) Integer idade,
      @RequestParam String uf, @RequestParam String cidade)  {
        Cliente c = repository.getById(id);
        if (c != null) {
            c.setNome(nome);
            c.setEmail(email);
            c.setTelefone(telefone);
            c.setUf(uf);
            c.setCidade(cidade);
            c.setIdade(idade);
            repository.save(c);
        }
        repository.save(c);
        return "updated";
    }

    @DeleteMapping("/{id}")
    public @ResponseBody String excluirCliente(@PathVariable("id") Long id)  {
        Cliente c = repository.getById(id);
        if (c != null) {
            repository.delete(c);
        }
        
        return "delete";
    }
    
}
