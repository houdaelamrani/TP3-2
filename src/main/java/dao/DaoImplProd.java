package dao;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * DAO activé exclusivement sous le profil Spring 'prod'.
 * Simule la connexion sécurisée au cluster PostgreSQL de production.
 */
@Component("daoProd")
@Profile("prod")
@Primary
public class DaoImplProd implements IDao {
    @Override
    public double getValue() {
        System.out.println("[E-COMMERCE PROFIL PROD] Connexion sécurisée au cluster PostgreSQL de Production - Transaction validée");
        return 100.0;
    }
}
