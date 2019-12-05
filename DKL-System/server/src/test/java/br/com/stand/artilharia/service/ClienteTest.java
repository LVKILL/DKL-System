package br.com.stand.artilharia.service;

import br.com.stand.artilharia.AbstractIntegrationTests;
import br.com.stand.artilharia.model.Cliente;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;

public class ClienteTest extends AbstractIntegrationTests {

  @Autowired
  ClienteService clienteService;

  @Test
  @Sql({ "/dataset/truncate.sql", "/dataset/clientes.sql" })
  public void deveBuscarTodasOsClientes() {
    Page<Cliente> Clientes = clienteService.getAll("", PageRequest.of(0, 100));
    assertThat(Clientes.getContent(), hasSize(1));
  }

  @Test
  @Sql({ "/dataset/truncate.sql", "/dataset/clientes.sql" })
  public void deveSalvarCliente() {
    Cliente cliente = Cliente.builder().primeiroNome("Joao").ultimoNome("Ninguém").cpf(4452148942L).rg(103587746L)
            .telefone(55455445L).dataNascimento(LocalDate.of(2004, 11, 6)).build();

    clienteService.save(cliente);
    assertNotNull(cliente);
    assertNotNull(cliente.getId());
  }

   @Test
   @Sql({ "/dataset/truncate.sql", "/dataset/clientes.sql" })
   public void deveBuscarUmCliente() {
   Cliente cliente = clienteService.findOne(1001L);
   assertNotNull(cliente);
   assertNotNull(cliente.getId());
   }

   @Test
   @Sql({ "/dataset/truncate.sql", "/dataset/clientes.sql" })
   public void deveAtualizarCliente() {
   Cliente cliente = Cliente.builder().primeiroNome("Pedro").ultimoNome("Ninguém").cpf(4452148942L).rg(103587746L)
           .telefone(55455445L).dataNascimento(LocalDate.of(2004, 11, 6)).build();

   clienteService.update(1001L, cliente);
   assertThat(cliente.getId(), equalTo(1001L));
   assertEquals("Pedro", cliente.getPrimeiroNome());

   }
}
