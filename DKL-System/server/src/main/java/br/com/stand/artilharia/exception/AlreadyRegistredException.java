package br.com.stand.artilharia.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlreadyRegistredException extends CustomException {
    private static final long serialVersionUID = 4099874611502515942L;

    public AlreadyRegistredException(String message, String hint, String error) {
        super(message, hint, error);
    }

}