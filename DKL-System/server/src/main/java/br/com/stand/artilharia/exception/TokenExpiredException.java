package br.com.stand.artilharia.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TokenExpiredException extends RuntimeException {
    private static final long serialVersionUID = 2290654584569442859L;

    private String error;

}