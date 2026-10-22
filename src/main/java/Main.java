import config.AppConfig;
import metier.IMetier;
import metier.MetierMultiple;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Classe Principale de Demonstration - ShopMaster E-Commerce
 * Illustre en direct toutes les strategies d'injection Spring et le respect du principe OCP :
 * 1. Configuration dynamique externe (app.properties)
 * 2. Gestion des Profils Spring (prod vs dev)
 * 3. Configuration par Alias (@Bean)
 * 4. Agregation multi-sources avec List<IDao> et Map<String, IDao>
 */
public class Main {

    public static void main(String[] args) {
        afficherBanniere();

        // =========================================================================
        // 1. STRATEGIE PAR DEFAUT : CONFIGURATION DYNAMIQUE EXTERNE (app.properties)
        // =========================================================================
        System.out.println("\n+-------------------------------------------------------------------------+");
        System.out.println("|  DEMO 1 : SELECTION DYNAMIQUE EXTERNE VIA app.properties (OCP TOTAL)    |");
        System.out.println("+-------------------------------------------------------------------------+");
        
        AnnotationConfigApplicationContext contextDefaut = 
                new AnnotationConfigApplicationContext(AppConfig.class);
        
        IMetier metierDefaut = contextDefaut.getBean(IMetier.class);
        double totalDefaut = metierDefaut.calcul();
        System.out.printf(">>> [RESULTAT DEMO 1] Facturation calculee : %.2f EUR%n%n", totalDefaut);
        contextDefaut.close();

        // =========================================================================
        // 2. STRATEGIE PROFILS SPRING : ENVIRONNEMENT PROD VS DEV (@Profile)
        // =========================================================================
        System.out.println("+-------------------------------------------------------------------------+");
        System.out.println("|  DEMO 2 : PROFILS D'ENVIRONNEMENT SPRING (prod vs dev)                  |");
        System.out.println("+-------------------------------------------------------------------------+");

        // Cas A : Profil Production
        System.out.println("--- [A] Activation du Profil 'prod' ---");
        AnnotationConfigApplicationContext contextProd = new AnnotationConfigApplicationContext();
        contextProd.getEnvironment().setActiveProfiles("prod");
        contextProd.register(AppConfig.class);
        contextProd.refresh();
        
        IMetier metierProd = contextProd.getBean(IMetier.class);
        double totalProd = metierProd.calcul();
        System.out.printf(">>> [RESULTAT PROD] Facturation Production : %.2f EUR%n%n", totalProd);
        contextProd.close();

        // Cas B : Profil Developpement
        System.out.println("--- [B] Activation du Profil 'dev' ---");
        AnnotationConfigApplicationContext contextDev = new AnnotationConfigApplicationContext();
        contextDev.getEnvironment().setActiveProfiles("dev");
        contextDev.register(AppConfig.class);
        contextDev.refresh();

        IMetier metierDev = contextDev.getBean(IMetier.class);
        double totalDev = metierDev.calcul();
        System.out.printf(">>> [RESULTAT DEV] Facturation Developpement : %.2f EUR%n%n", totalDev);
        contextDev.close();

        // =========================================================================
        // 3. STRATEGIE ALIAS : REDIRECTION VERS L'API STRIPE (@Bean Alias)
        // =========================================================================
        System.out.println("+-------------------------------------------------------------------------+");
        System.out.println("|  DEMO 3 : STRATEGIE PAR ALIAS DE @BEAN (Profil 'alias' -> Stripe API)   |");
        System.out.println("+-------------------------------------------------------------------------+");

        AnnotationConfigApplicationContext contextAlias = new AnnotationConfigApplicationContext();
        contextAlias.getEnvironment().setActiveProfiles("alias");
        contextAlias.register(AppConfig.class);
        contextAlias.refresh();

        IMetier metierAlias = contextAlias.getBean(IMetier.class);
        double totalAlias = metierAlias.calcul();
        System.out.printf(">>> [RESULTAT ALIAS] Facturation via Stripe API : %.2f EUR%n%n", totalAlias);
        contextAlias.close();

        // =========================================================================
        // 4. EXTENSION : INJECTION MULTIPLE DE DAOS (List<IDao> et Map<String, IDao>)
        // =========================================================================
        System.out.println("+-------------------------------------------------------------------------+");
        System.out.println("|  DEMO 4 : CONSOLIDATION OMNICANALE (Injection List<IDao> & Map)         |");
        System.out.println("+-------------------------------------------------------------------------+");

        AnnotationConfigApplicationContext contextMulti = 
                new AnnotationConfigApplicationContext(AppConfig.class);
        
        MetierMultiple metierMultiple = contextMulti.getBean(MetierMultiple.class);
        double totalOmnicanal = metierMultiple.calcul();
        System.out.printf(">>> [RESULTAT OMNICANAL] Total global des ventes consolidees : %.2f EUR%n%n", totalOmnicanal);
        contextMulti.close();

        afficherConclusion();
    }

    private static void afficherBanniere() {
        System.out.println("=========================================================================");
        System.out.println("   ____  _                 __  __           _            ");
        System.out.println("  / ___|| |__   ___  _ __ |  \\/  | __ _ ___| |_ ___ _ __ ");
        System.out.println("  \\___ \\| '_ \\ / _ \\| '_ \\| |\\/| |/ _` / __| __/ _ \\ '__|");
        System.out.println("   ___) | | | | (_) | |_) | |  | | (_| \\__ \\ ||  __/ |   ");
        System.out.println("  |____/|_| |_|\\___/| .__/|_|  |_|\\__,_|___/\\__\\___|_|   ");
        System.out.println("                    |_|                                  ");
        System.out.println("  TP3 - Architecture Spring IoC & Respect du Principe OCP");
        System.out.println("  Theme : E-Commerce & Calcul de Panier / Remises Omnicanales");
        System.out.println("=========================================================================");
    }

    private static void afficherConclusion() {
        System.out.println("=========================================================================");
        System.out.println("BILAN ARCHITECTURAL :");
        System.out.println("  [OK] Respect strict du principe OCP : MetierImpl n'a JAMAIS ete modifie.");
        System.out.println("  [OK] Decouplage maximal grace a l'interface IDao et l'injection Spring.");
        System.out.println("  [OK] Changement de source de donnees a chaud via app.properties & profils.");
        System.out.println("  [OK] Support de l'injection multiple (List & Map) pour l'agregation.");
        System.out.println("=========================================================================");
    }
}