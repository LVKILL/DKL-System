package br.com.stand.artilharia.security;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import br.com.stand.artilharia.exception.InvalidCredentialsExeception;
import br.com.stand.artilharia.model.Credenciais;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

  public JWTLoginFilter(String url, AuthenticationManager authManager) {
    super(new AntPathRequestMatcher(url));
    setAuthenticationManager(authManager);
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
      throws JsonParseException, JsonMappingException, IOException {

    Credenciais credentials = new ObjectMapper().readValue(request.getInputStream(), Credenciais.class);
  
    try {
      return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(credentials.getUsername(),
          credentials.getPassword(), Collections.emptyList()));
    } catch (Exception e) {
      log.info(e);
      throw new InvalidCredentialsExeception("Credenciais Inválidas", "Se esta conta te pertencer, tente recuperar",
      "error.credential.credentials.invalid");
    }

  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain, Authentication auth) {

    TokenAuthenticationService.addAuthentication(response, auth.getName());
  }

}
