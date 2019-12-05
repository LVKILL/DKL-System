package br.com.stand.artilharia.controller;

import javax.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.stand.artilharia.model.Ambiente;
import br.com.stand.artilharia.service.AmbienteService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class AmbienteController extends DefaultController<Ambiente> {

    private AmbienteService service;

    @Override
    @GetMapping("/ambientes")
    public ResponseEntity<Page<Ambiente>> getAll(@RequestParam("pagina") int pagina,
            @RequestParam("tamanho") int tamanho, String busca) {
        return ResponseEntity.ok()
                .body(service.getAll(busca, (PageRequest.of(pagina, tamanho, Sort.by("id").descending()))));
    }

    @Override
    @GetMapping("/ambientes/{id}")
    public ResponseEntity<Ambiente> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(service.findOne(id));
    }

    @Override
    @PostMapping("/ambientes")
    public ResponseEntity<Ambiente> post(@Valid @RequestBody Ambiente obj) {
        return ResponseEntity.ok().body(service.save(obj));
    }

    @Override
    @PutMapping("/ambientes/{id}")
    public ResponseEntity<Ambiente> put(@PathVariable Long id,@Valid @RequestBody Ambiente obj) {
        return ResponseEntity.ok().body(service.update(id, obj));
    }

    @Override
    @DeleteMapping("/ambientes/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.delete(id));
    }
}