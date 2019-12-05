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

import br.com.stand.artilharia.model.Arma;
import br.com.stand.artilharia.service.ArmaService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class ArmaController extends DefaultController<Arma> {

    private ArmaService service;

    @Override
    @GetMapping("/armas")
    public ResponseEntity<Page<Arma>> getAll(@RequestParam("pagina") int pagina,
            @RequestParam("tamanho") int tamanho, String busca) {
        return ResponseEntity.ok()
                .body(service.getAll(busca, (PageRequest.of(pagina, tamanho, Sort.by("id").descending()))));
    }

    @Override
    @GetMapping("/armas/{id}")
    public ResponseEntity<Arma> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(service.findOne(id));
    }

    @Override
    @PostMapping("/armas")
    public ResponseEntity<Arma> post(@Valid @RequestBody Arma obj) {
        return ResponseEntity.ok().body(service.save(obj));
    }

    @Override
    @PutMapping("/armas/{id}")
    public ResponseEntity<Arma> put(@Valid @PathVariable Long id, @RequestBody Arma obj) {
        return ResponseEntity.ok().body(service.update(id, obj));
    }

    @Override
    @DeleteMapping("/armas/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.delete(id));
    }
}