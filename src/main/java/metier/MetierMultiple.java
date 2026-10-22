package metier;

import dao.IDao;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

/**
 * Service Métier E-Commerce Omnicanal (Consolidation Multi-Sources).
 * Démontre une fonctionnalité avancée de Spring IoC : l'injection de collections (List et Map).
 * Spring injecte automatiquement l'ensemble des beans implémentant IDao dans la liste,
 * et les associe par leur identifiant de bean dans la map.
 */
@Service
public class MetierMultiple implements IMetier {
    private final List<IDao> daos;
    private final Map<String, IDao> daoMap;

    /**
     * Injection automatique de toutes les sources de commandes disponibles.
     * @param daos liste de tous les beans IDao
     * @param daoMap map associant l'identifiant du bean à son instance IDao
     */
    public MetierMultiple(List<IDao> daos, Map<String, IDao> daoMap) {
        this.daos = daos;
        this.daoMap = daoMap;
    }

    @Override
    public double calcul() {
        System.out.println("\n--- [E-COMMERCE OMNICANAL] Detail des ventes par canal ---");
        daoMap.forEach((nomBean, daoBean) -> {
            System.out.printf("  • Canal '%-12s' (Classe: %s) -> Montant brut : %.2f €%n",
                    nomBean, daoBean.getClass().getSimpleName(), daoBean.getValue());
        });

        // Somme de toutes les valeurs collectées sur l'ensemble des canaux
        double chiffreAffairesTotal = daos.stream().mapToDouble(IDao::getValue).sum();
        System.out.printf("--- [E-COMMERCE OMNICANAL] Chiffre d'affaires global consolide : %.2f € ---\n%n",
                chiffreAffairesTotal);
        return chiffreAffairesTotal;
    }

    public List<IDao> getDaos() {
        return daos;
    }

    public Map<String, IDao> getDaoMap() {
        return daoMap;
    }
}
