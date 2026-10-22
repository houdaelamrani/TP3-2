package test;

import dao.IDao;
import metier.MetierMultiple;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import config.AppConfig;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.*;

/**
 * Tests Unitaires JUnit - Extension Injection Multiple (List<IDao> et Map<String, IDao>).
 * Démontre la capacité de Spring IoC à injecter toutes les implémentations disponibles
 * pour des logiques d'agrégation omnicanale.
 */
public class MetierMultipleTest {

    @Test
    public void testInjectionCollectionsDaos() {
        AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);
        MetierMultiple metierMultiple = context.getBean(MetierMultiple.class);

        assertNotNull("Le bean MetierMultiple doit être instancié par Spring", metierMultiple);

        List<IDao> daos = metierMultiple.getDaos();
        Map<String, IDao> daoMap = metierMultiple.getDaoMap();

        assertNotNull("La liste des DAOs ne doit pas être nulle", daos);
        assertNotNull("La map des DAOs ne doit pas être nulle", daoMap);
        assertTrue("Au moins 4 DAOs doivent être enregistrés", daos.size() >= 4);

        // Vérification de la présence des sources clés
        assertTrue("La map doit contenir le canal 'dao'", daoMap.containsKey("dao"));
        assertTrue("La map doit contenir le canal 'dao2'", daoMap.containsKey("dao2"));
        assertTrue("La map doit contenir le canal 'daoApi'", daoMap.containsKey("daoApi"));
        assertTrue("La map doit contenir le canal 'daoFile'", daoMap.containsKey("daoFile"));

        // Calcul de la somme globale
        double somme = metierMultiple.calcul();
        assertTrue("La somme omnicanale doit être positive", somme > 0);

        context.close();
    }
}
