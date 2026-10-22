package config;

import dao.DaoApi;
import dao.IDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

/**
 * Configuration par Alias de @Bean.
 * Illustre la stratégie de redirection d'un Bean vers une implémentation sans modifier le code métier.
 * Activée sous le profil 'alias'.
 */
@Configuration
@Profile("alias")
public class DaoAliasConfig {

    @Bean
    @Primary
    public IDao daoAlias() {
        System.out.println("[CONFIG ALIAS] Activation de l'alias de bean pointant vers Stripe API (DaoApi)");
        return new DaoApi();
    }
}