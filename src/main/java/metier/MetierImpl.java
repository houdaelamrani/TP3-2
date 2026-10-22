package metier;

import dao.IDao;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * Implémentation principale du service métier de tarification de commande (Order Pricing Service).
 * Démontre le respect absolu du principe OCP (Open-Closed Principle) :
 * - FERMÉ à la modification : cette classe ne changera jamais, quel que soit le DAO branché.
 * - OUVERT à l'extension : peut collaborer avec toute nouvelle implémentation de IDao (SQL, API, Fichier, Cloud...).
 */
@Service
@Primary
public class MetierImpl implements IMetier {
    private final IDao dao;

    /**
     * Injection de dépendance par constructeur (recommandée par Spring).
     * @param dao l'instance du fournisseur de données sélectionnée par le conteneur IoC
     */
    public MetierImpl(IDao dao) {
        this.dao = dao;
    }

    @Override
    public double calcul() {
        double montantBrut = dao.getValue();
        // Règle métier e-commerce : coefficient de marge et facturation TTC (x2.0)
        double montantFinal = montantBrut * 2.0;
        System.out.printf("[E-COMMERCE SERVICE METIER] Montant brut = %.2f € -> Montant final TTC calcule = %.2f €%n",
                montantBrut, montantFinal);
        return montantFinal;
    }

    /**
     * Getter permettant d'inspecter l'instance injectée (utile pour les tests et le diagnostic).
     */
    public IDao getDao() {
        return dao;
    }
}
