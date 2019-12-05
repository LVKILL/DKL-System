package br.com.stand.artilharia.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CustomExceptionSchema {

  private String message;
  private String hint;
  private String error;

  protected CustomExceptionSchema() {
  }

  public CustomExceptionSchema(String error) {
    this.error = error;
  }

}