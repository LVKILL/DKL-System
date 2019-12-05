package br.com.stand.artilharia.exception;

import org.springframework.core.annotation.Order;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CustomExceptionInterceptor extends ResponseEntityExceptionHandler {

  @ResponseBody
  @ExceptionHandler(value = { BadCredentialsException.class })
  @ResponseStatus(HttpStatus.FORBIDDEN)
  public CustomExceptionSchema handleBadCredentials(BadCredentialsException ex) {
    CustomExceptionSchema error = new CustomExceptionSchema(ex.getMessage());
    return error;
  }

  @ResponseBody
  @ExceptionHandler(value = { CustomException.class })
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public CustomExceptionSchema handleConflict(CustomException ex) {
    CustomExceptionSchema error = new CustomExceptionSchema(ex.getMessage(), ex.getHint(), ex.getError());
    return error;
  }

  @ResponseBody
  @ExceptionHandler(value = { NotFoundException.class })
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public CustomExceptionSchema handleNotFound(CustomException ex) {
    CustomExceptionSchema error = new CustomExceptionSchema(ex.getMessage(), ex.getHint(), ex.getError());
    return error;
  }
}