package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Classe de Configuration Principale Spring IoC.
 * - Scanne les composants des packages 'dao', 'metier' et 'config'.
 * - Charge le fichier de configuration externe 'app.properties'.
 */
@Configuration
@ComponentScan(basePackages = {"dao", "metier", "config"})
@PropertySource("classpath:app.properties")
public class AppConfig {
}