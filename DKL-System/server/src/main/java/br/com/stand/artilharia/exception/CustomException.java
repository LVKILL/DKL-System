package br.com.stand.artilharia.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public abstract class CustomException extends RuntimeException {
    private static final long serialVersionUID = -4079610358760664887L;

    private String message;
    private String hint;
    private String error;
}