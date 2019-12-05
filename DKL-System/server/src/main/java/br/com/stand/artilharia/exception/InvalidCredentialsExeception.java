package br.com.stand.artilharia.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;
import lombok.Setter;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
@Getter
@Setter
public class InvalidCredentialsExeception extends CustomException {
    private static final long serialVersionUID = -4079610358760664887L;

    public InvalidCredentialsExeception(String message, String hint, String error) {
        super(message, hint, error);
    }

}