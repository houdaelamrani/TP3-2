package dao;

import org.springframework.stereotype.Component;

/**
 * Implémentation DAO accédant à la base de données relationnelle PostgreSQL (Production).
 * Représente la source de données principale pour les commandes e-commerce en production.
 */
@Component("dao")
public class DaoImpl implements IDao {
    @Override
    public double getValue() {
        System.out.println("[E-COMMERCE DAO - SQL PROD] Lecture du panier client #CMD-10492 depuis la base PostgreSQL (Production)");
        return 100.0;
    }
}