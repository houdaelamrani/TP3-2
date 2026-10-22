package config;

import dao.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

/**
 * Configuration dynamique du DAO selon le fichier de propriétés externe 'app.properties'.
 * Permet de basculer la source de données sans recompiler l'application (respect strict du principe OCP).
 * Active par défaut lorsque aucun profil d'environnement spécifique (prod/dev/alias) n'est forcé.
 */
@Configuration
@Profile("!prod & !dev & !alias")
public class DynamicDaoConfig {

    @Value("${dao.target:dao}")
    private String daoTarget;

    @Bean
    @Primary
    public IDao dynamicDao() {
        System.out.println("[CONFIG DYNAMIQUE] Selection du DAO d'apres 'dao.target=" + daoTarget + "'");
        switch (daoTarget) {
            case "dao": 
                return new DaoImpl();
            case "dao2": 
                return new DaoImpl2();
            case "daoFile": 
                return new DaoFile();
            case "daoApi": 
                return new DaoApi();
            default: 
                throw new IllegalArgumentException("Cible DAO e-commerce inconnue : " + daoTarget);
        }
    }
}