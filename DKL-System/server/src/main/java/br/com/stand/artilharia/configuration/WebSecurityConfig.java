package br.com.stand.artilharia.configuration;

import br.com.stand.artilharia.security.JWTAuthenticationFilter;
import br.com.stand.artilharia.security.JWTLoginFilter;
import br.com.stand.artilharia.service.CustomUserDetailsService;
import lombok.AllArgsConstructor;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  private CustomUserDetailsService service;

  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity.cors().and().csrf().disable().authorizeRequests().antMatchers(HttpMethod.POST, "/api/logar")
        .permitAll().antMatchers(HttpMethod.POST, "/api/registrar").permitAll().anyRequest().authenticated().and()

        // filtra requisições de login
        .addFilterBefore(new JWTLoginFilter("/api/logar", authenticationManager()),
            UsernamePasswordAuthenticationFilter.class)

        // filtra outras requisições para verificar a presença do
        // JWT(token/autencicacao) no header
        .addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(service).passwordEncoder(passwordEncoder());
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  // Configuração para devolver a autorização(token) do header
  @Bean
  public CorsConfigurationSource corsConfigurationSource() {

    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowCredentials(true);
    configuration.setAllowedOrigins(Arrays.asList("*"));
    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
    configuration
        .setAllowedHeaders(Arrays.asList("X-Requested-With", "Origin", "Content-Type", "Accept", "Authorization"));

    // This allow us to expose the headers
    configuration.setExposedHeaders(Arrays.asList("Access-Control-Allow-Headers",
        "Authorization, x-xsrf-token, Access-Control-Allow-Headers, Origin, Accept, X-Requested-With, "
            + "Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers"));

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }
}
