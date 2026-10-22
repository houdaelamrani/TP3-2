package dao;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * DAO activé exclusivement sous le profil Spring 'dev'.
 * Simule les données mockées pour l'environnement de développement.
 */
@Component("daoDev")
@Profile("dev")
@Primary
public class DaoImplDev implements IDao {
    @Override
    public double getValue() {
        System.out.println("[E-COMMERCE PROFIL DEV] Données de test chargées depuis la base en mémoire H2 (Développement)");
        return 150.0;
    }
}
