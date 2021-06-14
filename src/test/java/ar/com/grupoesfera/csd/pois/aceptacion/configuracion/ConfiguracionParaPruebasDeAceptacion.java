package ar.com.grupoesfera.csd.pois.aceptacion.configuracion;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.sql.DataSource;

@TestConfiguration
public class ConfiguracionParaPruebasDeAceptacion {

    @Autowired
    private WebApplicationContext contexto;
    @Autowired
    private DataSource dataSource;

    @Bean
    public MockMvc mockMvc() {
        return MockMvcBuilders
                .webAppContextSetup(contexto)
                .build();
    }

    @Bean(initMethod = "migrate")
    public void flyway() {

        Flyway flyway = Flyway.configure()
                .dataSource(dataSource)
                .baselineOnMigrate(true)
                .locations("classpath:db/migration")
                .load();

        flyway.migrate();
    }
}
