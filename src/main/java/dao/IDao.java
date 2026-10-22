package dao;

/**
 * Interface IDao - Contrat d'accès aux données E-Commerce.
 * Définit la méthode d'extraction du montant d'une commande / panier d'achat.
 * Respecte le principe OCP (Open-Closed Principle) : le code métier dépend exclusivement de cette abstraction.
 */
public interface IDao {
    /**
     * Récupère le montant brut hors taxes (HT) d'une commande ou d'un panier d'achat.
     * @return montant en devise de référence (€ / DH)
     */
    double getValue();
}
