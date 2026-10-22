package metier;

/**
 * Interface IMetier - Contrat du service métier E-Commerce.
 * Définit la méthode de calcul de facturation d'un panier / commande.
 * Respecte le principe OCP : les composants de présentation ou de test manipulent cette interface.
 */
public interface IMetier {
    /**
     * Calcule le montant final facturé de la commande (application des règles métier, TVA, marge commerciale).
     * @return montant total calculé TTC
     */
    double calcul();
}
