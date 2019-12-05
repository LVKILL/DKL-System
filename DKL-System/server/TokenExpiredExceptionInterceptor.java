package br.com.stand.artilharia.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class TokenExpiredExceptionInterceptor extends ResponseEntityExceptionHandler {

  @ResponseBody
  @ExceptionHandler(value = { TokenExpiredException.class })
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public CustomExceptionSchema handleConflict(TokenExpiredException ex) {
    CustomExceptionSchema error = new CustomExceptionSchema(ex.getError());
    return error;
  }
}