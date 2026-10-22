package dao;

import org.springframework.stereotype.Component;

/**
 * Implémentation DAO lisant les commandes depuis un fichier local (commandes_offline.csv / batch).
 * Utilisée pour traiter les transactions enregistrées en mode déconnecté.
 */
@Component("daoFile")
public class DaoFile implements IDao {
    @Override
    public double getValue() {
        System.out.println("[E-COMMERCE DAO - FICHIER] Importation du panier différé depuis le fichier local 'commandes_offline.csv'");
        return 180.0;
    }
}