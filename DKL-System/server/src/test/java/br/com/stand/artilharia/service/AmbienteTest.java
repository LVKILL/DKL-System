package br.com.stand.artilharia.service;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.jdbc.Sql;

import br.com.stand.artilharia.AbstractIntegrationTests;
import br.com.stand.artilharia.model.Ambiente;

public class AmbienteTest extends AbstractIntegrationTests {

  @Autowired
  AmbienteService ambienteService;

  @Test
  @Sql({ "/dataset/truncate.sql", "/dataset/ambientes.sql" })
  public void deveBuscarTodosAmbientes() {
    Page<Ambiente> ambientes = ambienteService.getAll("", PageRequest.of(0, 100));
    assertThat(ambientes.getContent(), hasSize(2));
  }

  @Test
  @Sql({ "/dataset/truncate.sql", "/dataset/ambientes.sql" })
  public void deveSalvarAmbiente() {
    Ambiente ambiente = Ambiente.builder().descricao("area 3").area(12).alvo(true).build();

    ambienteService.save(ambiente);
    assertNotNull(ambiente);
    assertNotNull(ambiente.getId());
  }

  @Test
  @Sql({ "/dataset/truncate.sql", "/dataset/ambientes.sql" })
  public void deveBuscarUmAmbiente() {
    Ambiente ambiente = ambienteService.findOne(1001L);
    assertNotNull(ambiente);
    assertNotNull(ambiente.getId());
  }

  @Test
  @Sql({ "/dataset/truncate.sql", "/dataset/ambientes.sql" })
  public void deveAtualizarAmbiente() {
    Ambiente ambiente = Ambiente.builder().descricao("area 51").area(12).alvo(true).build();

    ambienteService.update(1001L, ambiente);
    assertThat(ambiente.getId(), equalTo(1001L));
    assertEquals("area 51", ambiente.getDescricao());

  }
}
