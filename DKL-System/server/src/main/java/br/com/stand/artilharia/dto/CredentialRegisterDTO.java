package br.com.stand.artilharia.dto;

import br.com.stand.artilharia.model.Credenciais;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CredentialRegisterDTO {

  private String email;
  private String password;

  public Credenciais toCredential() {
    return Credenciais.builder().password(password).email(email).enabled(true).build();
  }
}
