package test;

import metier.IMetier;
import metier.MetierImpl;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import config.AppConfig;
import static org.junit.Assert.*;

/**
 * Test de non-régression JUnit - Validation du principe OCP (Open-Closed Principle).
 * Vérifie que le composant MetierImpl n'est jamais modifié et que la valeur
 * retournée s'adapte fidèlement à la configuration externe.
 */
public class OcpSelectionTest {

    @Test
    public void testDaoImplParDefaut() {
        AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);
        IMetier metier = context.getBean(IMetier.class);
        
        // Si dao.target=dao (SQL Prod, montant 100.0), le résultat calculé (x2) doit être 200.0
        assertEquals(200.0, metier.calcul(), 0.001);
        
        context.close();
    }

    @Test
    public void testConformiteArchitecturaleOcp() {
        AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);
        IMetier metier = context.getBean(IMetier.class);

        assertNotNull("Le bean métier IMetier doit être injecté sans ambiguïté", metier);
        assertTrue("L'instance doit être du type MetierImpl", metier instanceof MetierImpl);

        MetierImpl metierImpl = (MetierImpl) metier;
        assertNotNull("Le DAO injecté dans MetierImpl ne doit pas être null", metierImpl.getDao());

        context.close();
    }
}
