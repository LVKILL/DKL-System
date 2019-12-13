package br.com.stand.artilharia.controller;

import br.com.stand.artilharia.model.Credenciais;
import br.com.stand.artilharia.service.CredenciaisService;
import java.net.URI;
import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@AllArgsConstructor
@RestController
public class CredentialController {

  private CredenciaisService service;

  @PostMapping("api/registrar")
  public ResponseEntity<Object> register(@RequestBody Credenciais credenciais) {
    Credenciais salvo = service.register(credenciais);
    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(salvo.getId())
        .toUri();
    return ResponseEntity.created(location).build();
  }
}
