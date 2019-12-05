package br.com.stand.artilharia.service;

import br.com.stand.artilharia.AbstractIntegrationTests;
import br.com.stand.artilharia.dto.ArmaLocadaDto;
import br.com.stand.artilharia.dto.ReservaDTO;
import br.com.stand.artilharia.dto.ReservaListarDTO;
import br.com.stand.artilharia.model.Reserva;
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

public class ReservaTest extends AbstractIntegrationTests {

    @Autowired
    ReservaService reservaService;

    @Test
    @Sql({"/dataset/truncate.sql", "/dataset/clientes.sql", "/dataset/armas.sql", "/dataset/ambientes.sql", "/dataset/reservas.sql"})
    public void deveBuscarTodasAsReservas() {
        Page<ReservaListarDTO> reservas = reservaService.buscarTodos("", PageRequest.of(0, 100));
        assertThat(reservas.getContent(), hasSize(1));
    }


    @Test
    @Transactional
    @Sql({"/dataset/truncate.sql", "/dataset/clientes.sql", "/dataset/armas.sql", "/dataset/ambientes.sql", "/dataset/reservas.sql"})
    public void deveBuscarUmaReserva() {
        ReservaDTO reserva = reservaService.buscarReservaPorId(1001L);
        assertNotNull(reserva);
    }

    @Test
    @Sql({"/dataset/truncate.sql", "/dataset/clientes.sql", "/dataset/armas.sql", "/dataset/ambientes.sql", "/dataset/reservas.sql"})
    public void deveSalvarUmaReserva() {
     ReservaDTO reserva = ReservaDTO.builder()
             .ambienteSelecionado(1001L)
             .clienteSelecionado(1001L)
             .inicioDaLocacao(LocalDateTime.now())
             .fimDaLocacao(LocalDateTime.now())
             .armasLocadas(Collections.singleton(ArmaLocadaDto.builder()
                     .id(1001L)
                     .quantidade(1)
                     .build()))
             .ativa(true)
             .build();

        Reserva reservaSalva = reservaService.salvar(reserva,null);
        assertNotNull(reservaSalva);

    }


    @Test
    @Sql({"/dataset/truncate.sql", "/dataset/clientes.sql", "/dataset/armas.sql", "/dataset/ambientes.sql", "/dataset/reservas.sql"})
    public void deveAtualizarReserva() {
        ReservaDTO reserva = ReservaDTO.builder()
                .ambienteSelecionado(1001L)
                .clienteSelecionado(1001L)
                .inicioDaLocacao(LocalDateTime.now())
                .fimDaLocacao(LocalDateTime.now())
                .armasLocadas(Collections.singleton(ArmaLocadaDto.builder()
                        .id(1001L)
                        .quantidade(2)
                        .build()))
                .ativa(true)
                .build();

        Reserva reservaSalva = reservaService.salvar(reserva,1001L);
        assertNotNull(reservaSalva);
        assertThat(reservaSalva.getId(),equalTo(1001L));

    }
}
