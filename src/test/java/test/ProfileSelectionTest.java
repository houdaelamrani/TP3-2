package test;

import metier.IMetier;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import config.AppConfig;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Tests Unitaires JUnit - Validation de la gestion des Profils Spring (@Profile).
 * Démontre l'activation contextuelle des beans selon l'environnement d'exécution (prod vs dev).
 */
public class ProfileSelectionTest {

    @Test
    public void testProfilProduction() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // Activation programmatique du profil 'prod'
        context.getEnvironment().setActiveProfiles("prod");
        context.register(AppConfig.class);
        context.refresh();

        IMetier metier = context.getBean(IMetier.class);
        assertNotNull("Le bean IMetier doit exister pour le profil prod", metier);

        // Sous le profil 'prod', DaoImplProd fournit 100.0 -> calcul = 100.0 * 2.0 = 200.0
        assertEquals(200.0, metier.calcul(), 0.001);

        context.close();
    }

    @Test
    public void testProfilDeveloppement() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // Activation programmatique du profil 'dev'
        context.getEnvironment().setActiveProfiles("dev");
        context.register(AppConfig.class);
        context.refresh();

        IMetier metier = context.getBean(IMetier.class);
        assertNotNull("Le bean IMetier doit exister pour le profil dev", metier);

        // Sous le profil 'dev', DaoImplDev fournit 150.0 -> calcul = 150.0 * 2.0 = 300.0
        assertEquals(300.0, metier.calcul(), 0.001);

        context.close();
    }
}
