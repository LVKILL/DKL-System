package br.com.stand.artilharia;

import java.util.Locale;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Getter;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class AbstractIntegrationTests {
  @Getter
  @Value("http://localhost:${local.server.port}")
  String baseUrl;
  /*-------------------------------------------------------------------
   *                           ATTRIBUTES
   *-------------------------------------------------------------------*/

  /*-------------------------------------------------------------------
   * 		 					CONSTRUCTORS
   *-------------------------------------------------------------------*/

  /*-------------------------------------------------------------------
   *                           BEHAVIORS
   *-------------------------------------------------------------------*/
  /**
   *
   */
  @Before
  public void beforeTest() {
    Locale.setDefault(new Locale("pt"));
  }
}
