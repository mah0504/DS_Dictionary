import java.util.InputMismatchException;
import java.util.Scanner;

/**
     * Classe principale du programme pour l'interaction avec l'utilisateur.
     * Permet de chercher des mots dans le dictionnaire, afficher les définitions
     * ou les traductions, ainsi que gérer l'historique des recherches.
     * 
     * @author: Maha Amhaouch 20272882
*/

public class Dictionnaire {
    public static void main(String[] args) {

        Recherche def = new Recherche("dictiona.csv");
        Histo historique= new Histo();
        Test testPrefixe = new Test(def); 

    
            // Affichage du menu des options
            System.out.println( " What are you looking for today ?\n"  +
            " Click 1 for the definition of the word \n"
            + " Click 2 for the search history\n " +
            "Click 3 for the french translation\n" + 
            " Click 4 to test the code !\n"+ 
            "Enter 0 to leave "); 

            System.out.print("Enter your choice! ");
            // Utilisation de l'objet scanner pour obtenir l'entrée de l'utilisateur
            
            try {
            Scanner scan = new Scanner(System.in);
            Integer affichage = scan.nextInt();
            
            

            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the search word: ");
            String motCherche = scanner.nextLine();


            switch (affichage){
                case 0: 
                    // ne pas afficher enter search word
                    System.out.println("Fermeture...");
                    scan.close();
                    System.exit(0);
                    break;
                case 1: 
                    def.chercherMot(motCherche.toLowerCase());
                    break;
                
                case 2:
                    // ne pas afficher enter search word
                    historique.afficherHisto();
                    break;

                case 3:
                    def.chercherTrad(motCherche.toLowerCase()); 
                    break;
            
                case 4:
                    testPrefixe.chercherSousChaines("testPrefix.txt"); 
                    testPrefixe.resultatTest("testPrefixResult.txt");
                
                default:
                    System.out.println( " Please enter the correct number ! ");
            } 

            // Ajout du mot recherché à l'historique des recherches
  
            
            historique.construireHisto(motCherche);

            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number!   ");
            }

        

    }

} 
