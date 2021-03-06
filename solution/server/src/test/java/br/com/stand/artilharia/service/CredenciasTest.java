package br.com.stand.artilharia.service;

import br.com.stand.artilharia.AbstractIntegrationTestsNamosca;
import br.com.stand.artilharia.model.Credenciais;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.Assert.assertNotNull;

public class CredenciasTest extends AbstractIntegrationTestsNamosca {

    @Autowired
    CredenciaisService credenciaisService;

    @Test
    @Sql({"/dataset/truncate.sql"})
    public void deveRegistrarCredencial() {
        Credenciais credenciais = credenciaisService.register(Credenciais.builder().email("email@email.com").password("123").build());
        assertNotNull(credenciais);
    }

}
