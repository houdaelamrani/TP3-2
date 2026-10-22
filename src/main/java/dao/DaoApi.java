package dao;

import org.springframework.stereotype.Component;

/**
 * Implémentation DAO interrogeant l'API REST de la passerelle de paiement en ligne (Stripe / PayPal).
 * Récupère le montant brut de commande directement depuis l'API distante en temps réel.
 */
@Component("daoApi")
public class DaoApi implements IDao {
    @Override
    public double getValue() {
        System.out.println("[E-COMMERCE DAO - REST API] Appel HTTP GET vers l'API Stripe / Checkout Gateway (Temps réel)");
        return 220.0;
    }
}