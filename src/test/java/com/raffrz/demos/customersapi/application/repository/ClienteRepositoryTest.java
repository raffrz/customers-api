package com.raffrz.demos.customersapi.application.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import com.raffrz.demos.customersapi.domain.model.Cliente;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ClienteRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ClienteRepository clienteRepository;

    @BeforeEach
    public void setUp() {
        Cliente jose = new Cliente(null, "José da Silva", "jose.silva@gmail.com", 551155443322l, "SP", "Barueri", 22);
        Cliente pedro = new Cliente(null, "Pedro Lima", "pedro.lima@gmail.com", 551344558877l, "SP", "Santos", 26);
        Cliente juca = new Cliente(null, "Juca Navarro", "juca.navarro@gmail.com", 559033554477l, "AM", "Manaus", 36);
        Cliente suellen = new Cliente(null, "Suellen Souza", "suellen.souza@gmail.com", 551955443322l, "SP", "São Paulo", 30);
        Cliente julia = new Cliente(null, "Júlia Alves", "julia.alves@gmail.com", 551155443322l, "SP", "São Paulo", 19);
        entityManager.persist(jose);
        entityManager.persist(pedro);
        entityManager.persist(juca);
        entityManager.persist(suellen);
        entityManager.persist(julia);
        entityManager.flush();
    }

    @AfterEach
    public void tearDown() {
        clienteRepository.findAll().forEach(entityManager::remove);
        entityManager.flush();
    }

    @Test
    public void findAllPageable() {
        Page<Cliente> clientsPage = clienteRepository.findAll(PageRequest.of(0, 2));
        
        assertEquals(2, clientsPage.getNumberOfElements());
        assertEquals(1, clientsPage.get()
            .filter(c -> c.getNome().equals("José da Silva"))
            .count());
        assertEquals(1, clientsPage.get()
            .filter(c -> c.getNome().equals("Pedro Lima"))
            .count());
    }

    @Test
    public void testFindClienteByNome() {
        List<Cliente> clientes = clienteRepository.findByNome("José");

        assertNotNull(clientes);
        assertFalse(clientes.isEmpty());
        assertTrue(clientes.size() == 1);
        assertEquals(clientes.get(0).getNome(), "José da Silva");
    }

    @Test
    public void testFindClienteByEmail() {
        List<Cliente> clientes = clienteRepository.findByEmail("pedro.lima@gmail.com");

        assertNotNull(clientes);
        assertFalse(clientes.isEmpty());
        assertTrue(clientes.size() == 1);
        assertEquals(clientes.get(0).getEmail(), "pedro.lima@gmail.com");
    }

    @Test
    public void testFindClienteByUf() {
        List<Cliente> clientes = clienteRepository.findByUf("AM");

        assertNotNull(clientes);
        assertFalse(clientes.isEmpty());
        assertTrue(clientes.size() == 1);
        assertEquals(clientes.get(0).getNome(), "Juca Navarro");
        assertEquals(clientes.get(0).getUf(), "AM");
    }

    @Test
    public void testFindClienteByCidade() {
        List<Cliente> clientes = clienteRepository.findByCidade("São Paulo");

        assertNotNull(clientes);
        assertFalse(clientes.isEmpty());
        assertTrue(clientes.size() == 2);
        clientes.forEach(c -> assertEquals("São Paulo", c.getCidade()));
    }

    @Test
    public void testFindClienteByUfAndIdade() {
        List<Cliente> query1 = clienteRepository.findByUfAndIdadeGreaterThan("SP", 25);
        List<Cliente> query2 = clienteRepository.findByUfAndIdadeLowerThan("SP", 25);
        List<Cliente> query3 = clienteRepository.findByUfAndIdadeBetween("SP", 20, 25);
        
        assertNotNull(query1);
        assertFalse(query1.isEmpty());
        assertTrue(query1.size() == 2);
        assertEquals(query1.get(0).getNome(), "Pedro Lima");
        assertEquals(query1.get(1).getNome(), "Suellen Souza");
        assertNotNull(query2);
        assertFalse(query2.isEmpty());
        assertEquals(2, query2.size());
        assertEquals("José da Silva", query2.get(0).getNome());
        assertEquals("Júlia Alves", query2.get(1).getNome());
        assertNotNull(query2);
        assertFalse(query3.isEmpty());
        assertEquals(1, query3.size());
        assertEquals("José da Silva", query3.get(0).getNome());
    }
    
}
