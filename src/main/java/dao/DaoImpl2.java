package dao;

import org.springframework.stereotype.Component;

/**
 * Implémentation DAO accédant à la base de données locale H2 / Sandbox (Développement).
 * Permet aux développeurs de tester les calculs de commande sans impacter la production.
 */
@Component("dao2")
public class DaoImpl2 implements IDao {
    @Override
    public double getValue() {
        System.out.println("[E-COMMERCE DAO - SQL DEV] Lecture du panier de test depuis la base locale H2 (Développement)");
        return 150.0;
    }
}