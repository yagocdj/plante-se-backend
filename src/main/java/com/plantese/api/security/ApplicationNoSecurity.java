package com.plantese.api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;

/**
 * Classe que "desativa" o Spring security. É importante salientar que esta classe
 * deve ser removida no futuro para que uma implementação segura possa ser realizada.
 * O único motivo que justifica a existência dessa classe é a falta de tempo para
 * implementar o restante do projeto a tempo.
 * @author yago
 */

@Configuration
public class ApplicationNoSecurity {

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().anyRequest();
    }
}
