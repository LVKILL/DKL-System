package br.com.stand.artilharia.service;

import br.com.stand.artilharia.dto.ReservaDTO;
import br.com.stand.artilharia.dto.ReservaListarDTO;
import br.com.stand.artilharia.model.Ambiente;
import br.com.stand.artilharia.model.ArmaLocada;
import br.com.stand.artilharia.model.Cliente;
import br.com.stand.artilharia.model.Reserva;
import br.com.stand.artilharia.repository.ReservaRepository;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReservaService {

  private ReservaRepository reservaRepository;
  private ClienteService clienteService;
  private AmbienteService ambienteService;

  private ArmaLocadaService armaLocadaService;

  public Reserva salvar(ReservaDTO dto, Long id) {
    Cliente cliente = clienteService.findOne(dto.getClienteSelecionado());
    Ambiente ambiente = ambienteService.findOne(dto.getAmbienteSelecionado());
    Reserva reserva = Reserva.builder().id(id).ativa(dto.getAtiva()).cliente(cliente).ambiente(ambiente)
        .inicioDaLocacao(dto.getInicioDaLocacao()).fimDaLocacao(dto.getFimDaLocacao()).build();

    if (id == null) {
      reservaRepository.save(reserva);
    }

    Set<ArmaLocada> armasLocadas = armaLocadaService.salvaArmasLocadas(dto.getArmasLocadas(), reserva);
    reserva.setArmaLocadas(armasLocadas);

    return reservaRepository.save(reserva);
  }

  public Page<ReservaListarDTO> buscarTodos(String busca, PageRequest pageRequest) {
    Page<Reserva> reservas = reservaRepository.findAll(pageRequest);
    return new PageImpl<>(converterParaDto(reservas.getContent()), reservas.getPageable(), reservas.getTotalElements());
  }

  private List<ReservaListarDTO> converterParaDto(List<Reserva> reservas) {
    return reservas.stream().map(ReservaListarDTO::converter).collect(Collectors.toList());
  }

  public ReservaDTO buscarReservaPorId(Long id) {
    Reserva reserva = reservaRepository.getOne(id);
    return reserva.converter();
  }

  @Transactional
  public void inativar(Long id) {
    reservaRepository.inativar(id);
  }
}
