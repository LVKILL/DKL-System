package br.com.stand.artilharia.security;

import br.com.stand.artilharia.exception.TokenExpiredException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import java.util.Collections;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Log4j2
class TokenAuthenticationService {

  // EXPIRATION_TIME = 60 min
  static final long EXPIRATION_TIME = 3600000;
  static final String SECRET = "MySecret";
  static final String HEADER_STRING = "Authorization";

  static void addAuthentication(HttpServletResponse response, String username) {
    String JWT = Jwts.builder().setSubject(username)
        .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
        .signWith(SignatureAlgorithm.HS512, SECRET).compact();

    response.addHeader(HEADER_STRING, JWT);
  }

  static Authentication getAuthentication(HttpServletRequest request) {
    String token = request.getHeader(HEADER_STRING);

    try {
      if (!Strings.isEmpty(token)) {
        String user = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody()
            .getSubject();

        if (user != null) {
          return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
        }
      }
      } catch (SignatureException ex) {
      log.info("Invalid JWT Signature");
      } catch (MalformedJwtException ex) {
      log.info("Invalid JWT token");
      } catch (ExpiredJwtException ex) {
      log.info("Expired JWT token");
        new TokenExpiredException("expired");
      } catch (UnsupportedJwtException ex) {
      log.info("Unsupported JWT exception");
      } catch (IllegalArgumentException ex) {
        log.info("Jwt claims string is empty");
      }

      return null;
    }

}
