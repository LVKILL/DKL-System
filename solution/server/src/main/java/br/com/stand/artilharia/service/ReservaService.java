package br.com.stand.artilharia.service;

import br.com.stand.artilharia.exception.NotFoundException;
import br.com.stand.artilharia.model.Ambiente;
import br.com.stand.artilharia.model.ArmaLocada;
import br.com.stand.artilharia.model.Cliente;
import br.com.stand.artilharia.model.Reserva;
import br.com.stand.artilharia.repository.ReservaRepository;
import java.util.Set;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReservaService {

  private ReservaRepository reservaRepository;
  private ClienteService clienteService;
  private AmbienteService ambienteService;

  private ArmaLocadaService armaLocadaService;

  @Transactional
  public Reserva salvar(Reserva reserva, Long id) {
    Cliente cliente = clienteService.findOne(reserva.getCliente().getId());
    Ambiente ambiente = ambienteService.findOne(reserva.getAmbiente().getId());




    if (id == null) {
      reservaRepository.save(reserva);
      reserva.setCliente(cliente);
      reserva.setAmbiente(ambiente);
      Set<ArmaLocada> armasLocadas = armaLocadaService
              .salvaArmasLocadas(reserva.getArmaLocadas(), reserva);

      reserva.setArmaLocadas(armasLocadas);

    }else{
      Reserva reservaModificada = buscarReservaPorId(id);
      reservaModificada.setAmbiente(ambiente);
      reservaModificada.setCliente(cliente);
      reservaModificada.setAtiva(reserva.getAtiva());
      reservaModificada.setInicioDaLocacao(reserva.getInicioDaLocacao());
      reservaModificada.setFimDaLocacao(reserva.getFimDaLocacao());

      Set<ArmaLocada> armasLocadas = armaLocadaService
              .salvaArmasLocadas(reserva.getArmaLocadas(), reservaModificada);

      reservaModificada.setArmaLocadas(armasLocadas);

      reserva = reservaRepository.save(reservaModificada);
    }


    return reservaRepository.save(reserva);
  }

  public Page<Reserva> buscarTodos(String busca, PageRequest pageRequest) {
    return reservaRepository.findAll(pageRequest);
  }

  public Reserva buscarReservaPorId(Long id) {
    return reservaRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Reserva não encontrada", null, null));
  }

  @Transactional
  public void inativar(Long id) {
    reservaRepository.inativar(id);
  }
}
