package br.com.stand.artilharia.controller;

import br.com.stand.artilharia.dto.ReservaDTO;
import br.com.stand.artilharia.dto.ReservaListarDTO;
import br.com.stand.artilharia.model.Reserva;
import br.com.stand.artilharia.service.ReservaService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reservas")
@AllArgsConstructor
public class ReservaController {

  private ReservaService service;

  @PostMapping
  public ResponseEntity<Reserva> post(@RequestBody ReservaDTO dto) {
    return ResponseEntity.ok().body(service.salvar(dto, null));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Reserva> post(@RequestBody ReservaDTO dto, @PathVariable Long id) {
    return ResponseEntity.ok().body(service.salvar(dto, id));
  }

  @GetMapping("/inativar")
  public ResponseEntity<Reserva> inativar(@RequestParam Long id) {
    service.inativar(id);
    return ResponseEntity.ok().build();
  }

  @GetMapping
  public ResponseEntity<Page<ReservaListarDTO>> getAll(@RequestParam("pagina") int pagina,
      @RequestParam("tamanho") int tamanho, String busca) {
    return ResponseEntity.ok()
        .body(service.buscarTodos(busca, (PageRequest.of(pagina, tamanho, Sort.by("id").descending()))));
  }

  @GetMapping("{id}")
  public ResponseEntity<ReservaDTO> get(@PathVariable("id") Long id) {
    return ResponseEntity.ok().body(service.buscarReservaPorId(id));
  }
}
