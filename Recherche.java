import java.io.*;
import java.util.*;

/**
     * Représenter un dictionnaire et à effectuer des opérations de recherche à l'intérieur de ce dernier. 
     * Elle est utilisée pour construire un dictionnaire à partir d'un fichier texte donné, puis pour rechercher des mots, 
     * trouver leur traduction, leur type et leur définition.
 */

public class Recherche {

    // HashMaps pour stocker les mots commençant par différentes lettres de l'alphabet
    private  HashMap<String, String[]> hash ;
    private  HashMap<String, String[]> LettreA;
    private  HashMap<String, String[]> LettreE;
    private  HashMap<String, String[]> LettreF;
    private  HashMap<String, String[]> LettreG;
    private ArrayList<HashMap<String, String[]>> liste;
    

    // Variables pour suivre l'état de la recherche de préfixe
    private boolean prefixeTrouve; 
    private String prefixe;

    /**
         * Constructeur qui instancie les variables bon jsp à revoir
         * @param fichier fichier duquel on crée un dictionnaire
     */

    public Recherche(String fichier) {

        // Initialisation des structures de données
        LettreA = new HashMap<>();
        LettreE = new HashMap<>();
        LettreF = new HashMap<>();
        LettreG = new HashMap<>();
        liste   = new ArrayList<>();
     
        prefixeTrouve = false;

        construireDictionnaire(fichier); // construc du dictio lors de l'ouverture directe 
    } 
    
    /**
         * Permet de construire le dictionnaire où on va effectuer la recherche des mots entrés par
         * l'utilisateur. Le dictionnaire est divisé en plusieurs HashMap et chaque HashMap
         * représente une lettre de l'alphabet ( pour optimiser le temps de recherche des mots). 
         * Ces HashMaps ont comme clé le mot en anglais, et value correspondante un tableau contenant 
         * la traduction française du mot , son type, et sa définition 
         * 
         * @param fichier fichier qu'on veut transformer en dictionnaire 
     */

    public void construireDictionnaire(String fichier) {  

        try {
            // Lecture du fichier 

            FileReader fr = new FileReader(fichier);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();

            // placer les HashMaps dans L'Arraylist pour faciliter la manip
            this.liste.add(LettreA);
            this.liste.add(LettreE);
            this.liste.add(LettreF);
            this.liste.add(LettreG);

            while (line != null) {

                String[] separateur = line.split(",");

                String mot  = separateur[0];
                
                String trad = separateur[1];
                String type = separateur[2];
                String def  = separateur[3];

                String[] tab = { trad, type, def };
    

                // placer les mots dans la hashmap correspondante selon leur 1ère lettre 
                switch (mot.charAt(0)) {
                    case 'A':
                        LettreA.put(mot.toLowerCase(), tab);
                        break;
                    case 'E':
                        LettreE.put(mot.toLowerCase(), tab);
                        break;
                    case 'F':
                        LettreF.put(mot.toLowerCase(), tab);
                        break;
                    case 'G':
                        LettreG.put(mot.toLowerCase(), tab);
                        break;
                    default:
                        break; 
                } 
                // Lire à travers le fichier ligne par ligne 
                line = reader.readLine();

            } 
            reader.close(); // Fermeture du lecteur 

        } catch (IOException ex) {
            System.out.println("Erreur à l’ouverture du fichier");
            
        }
    }

    /**
         * Vérifie si le motCherche se trouve dans le dictionnaire, si oui le code imrpime son type et sa définition 
         * sur la console, sinon vérifie si la première lettre du mot cherché se trouve dans le dictionnaire, si oui 
         * le code invoque alors la méthode chercherPrefixe
         * 
         * @param motCherche Le mot à rechercher
         * @return type int  Nombre de mots avec le préfixe le plus grand issu du motCherche
     */

    public int chercherMot(String motCherche) {
     
        for (int i =0; i<liste.size(); i++){
            hash=null;
            
            // Si le mot est trouvé, affichage de son type et de sa définition
            if(liste.get(i).containsKey(motCherche)){

                System.out.println("The type of the word is: "+ liste.get(i).get(motCherche)[1] +"\n" 
                + "The definition is : "  + liste.get(i).get(motCherche)[2]);

                // mot trouvé dans le dictio, donc le préfixe le plus grand est le mot lui-même 
                 return 1;  
            } 
        }
                
        switch (motCherche.charAt(0)) {
            // vérifie si au moins la première lettre du motCherche se trouve dans le dictio
            case 'a':
                hash =liste.get(0);  
                break;
            case 'e':
                hash = liste.get(1); 
                break;
            case 'f':
                hash = liste.get(2); 
                break;
            case 'g':
                hash = liste.get(3); 
                break;
            default: 
                System.out.println("We're sorry it seems we can't find the word in the dictionary ! ");
                break;

        } 


        if (hash != null) { 

            // retourne le nombre de mots avec le prefixe le plus grand trouvé 
            return chercherPrefixe(motCherche).size();           

        } else {

            System.out.println("Sorry we can't seem to find the word in the provided dictionary ! ");
            return 0; //pas de préfixe trouvé 
            
        }
   
        
    }

    /**
         * Permet de chercher le préfixe le plus long du motCherche dans le dictionnaire s'il est introuvable et 
         * de retourner une Arraylist avec les mots commençant par ce préfixe
         * 
         * @param motCherche  Le mot à rechercher 
         * @return    Une ArrayList contenant tous les mots du dictionnaire commençant par le préfixe trouvé
     */
    
    public ArrayList<String> chercherPrefixe(String motCherche){
       ArrayList<String> maListe= new ArrayList<String>();

        // Boucle pour trouver le préfixe le plus long (incrémenter la taille du préfixe issu du motCherche jusqu'à
        // ne plus trouver une correspondance dans le dictio)
         for (int j = 0; j < motCherche.length(); j++) {

            String mot1 = motCherche.substring(0, j + 1);


            // Recherche du préfixe dans le dictionnaire
            for (String mot : hash.keySet() ) {

                if (mot.startsWith(mot1)) {
                    prefixe = mot1;
                    prefixeTrouve = true;
                    break; // On a trouvé le préfixe dans un mot, pas besoin de continuer la boucle
                }
            }  

             // Si le préfixe n'est pas trouvé, on le marque comme null
             if (!prefixeTrouve) {
                prefixe = null;
            } 
        } 

        // Ajout de tous les mots ayant le préfixe trouvé dans  maListe
            for (String mot : hash.keySet()) {
                if (mot.startsWith(prefixe)){
                    maListe.add(mot);
                } 
            } 
        
        // Si un préfixe est trouvé, on l'affiche et on retourne la liste de mots correspondante
        if (prefixe != null) {
            System.out.println("The longest prefix found is : " + prefixe);
            System.out.println(maListe);
            return maListe; 

        } else {
           System.out.println("We can't find the word you're looking for ! :(");
           return maListe; 
        } 
    }
    /**
         * Cherche la traduction française du motCherche 
         * @param motCherche Le mot cherché
     */

    public  void chercherTrad(String motCherche) {
        for (int i =0; i<liste.size(); i++){

            // Vérification si le mot se trouve dans les clés de la HashMap correspondante
            if (liste.get(i).containsKey(motCherche)) {
                System.out.println("the french translation is: "+ liste.get(i).get(motCherche)[0]);
             } 
            
        } 
        
        
    }
}
