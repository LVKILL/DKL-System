package br.com.stand.artilharia.service;

import javax.validation.ValidationException;
import br.com.stand.artilharia.AbstractIntegrationTestsNamosca;
import br.com.stand.artilharia.model.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.jdbc.Sql;

import javax.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;

public class ReservaTest extends AbstractIntegrationTestsNamosca {

    @Autowired
    ReservaService reservaService;

    @Test
    @Sql({"/dataset/truncate.sql", "/dataset/clientes.sql", "/dataset/armas.sql", "/dataset/ambientes.sql", "/dataset/reservas.sql"})
    public void deveBuscarTodasAsReservas() {
        Page<Reserva> reservas = reservaService.buscarTodos("", PageRequest.of(0, 100));
        assertThat(reservas.getContent(), hasSize(1));
    }


    @Test
    @Transactional
    @Sql({"/dataset/truncate.sql", "/dataset/clientes.sql", "/dataset/armas.sql", "/dataset/ambientes.sql", "/dataset/reservas.sql"})
    public void deveBuscarUmaReserva() {
        Reserva reserva = reservaService.buscarReservaPorId(1001L);
        assertNotNull(reserva);
    }

    @Test
    @Sql({"/dataset/truncate.sql", "/dataset/clientes.sql", "/dataset/armas.sql", "/dataset/ambientes.sql", "/dataset/reservas.sql"})
    public void deveSalvarUmaReserva() {
        Reserva reserva = Reserva.builder()
                .ambiente(Ambiente.builder().id(1001L).build())
                .cliente(Cliente.builder().id(1001L).build())
                .inicioDaLocacao(LocalDateTime.now())
                .fimDaLocacao(LocalDateTime.now())
                .armaLocadas(Collections.singleton(ArmaLocada.builder()
                        .arma(Arma.builder().id(1001L).build())
                        .quantidade(1)
                        .build()))
                .ativa(true)
                .build();

        Reserva reservaSalva = reservaService.salvar(reserva, null);
        assertNotNull(reservaSalva);

    }


    @Test
    @Sql({"/dataset/truncate.sql", "/dataset/clientes.sql", "/dataset/armas.sql", "/dataset/ambientes.sql", "/dataset/reservas.sql"})
    public void deveAtualizarReserva() {
        Reserva reserva = Reserva.builder()
                .ambiente(Ambiente.builder().id(1001L).build())
                .cliente(Cliente.builder().id(1001L).build())
                .inicioDaLocacao(LocalDateTime.now())
                .fimDaLocacao(LocalDateTime.now())
                .armaLocadas(Collections.singleton(ArmaLocada.builder()
                        .arma(Arma.builder().id(1001L).build())
                        .quantidade(2)
                        .build()))
                .ativa(true)
                .build();

        Reserva reservaSalva = reservaService.salvar(reserva, 1001L);
        assertNotNull(reservaSalva);
        assertThat(reserva.getId(),equalTo(1001L));
        assertThat(reservaSalva.getArmaLocadas().stream().findFirst().orElse(new ArmaLocada()).getQuantidade(), equalTo(2));
    	}
        
        @Test(expected = ValidationException.class)
        @Sql({"/dataset/truncate.sql", "/dataset/clientes.sql", "/dataset/armas.sql", "/dataset/ambientes.sql", "/dataset/reservas.sql"})
    	public void cadastrarReservaMustFailClienteInexistente() {
    		
    		Reserva reserva = Reserva.builder()
                    .ambiente(Ambiente.builder().id(1001L).build())
                    .cliente(Cliente.builder().id(1002L).build())
                    .inicioDaLocacao(LocalDateTime.now())
                    .fimDaLocacao(LocalDateTime.now())
                    .armaLocadas(Collections.singleton(ArmaLocada.builder()
                            .arma(Arma.builder().id(1001L).build())
                            .quantidade(1)
                            .build()))
                    .ativa(true)
                    .build();

    		 try {
            Reserva reservaSalva = reservaService.salvar(reserva, null);
            assertNotNull(reservaSalva);
    		
    		 } catch (Exception e) {
                 assertTrue(e.getMessage().equals("cliente inexistente"));
             }
           
              
    		
    	}
        
       
    
}
