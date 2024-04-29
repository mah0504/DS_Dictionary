import java.io.*;
import java.util.*;

/**
 *  Permet d'utiliser la classe  Recherche pour rechercher des mots dans un fichier et stocker 
 * le nombre de correspondances pour chaque mot dans un autre fichier. 
 * La classe Test  évalue les fonctionnalités de la classe Recherche
 */

public class Test {

    Recherche mot; 

    // nbr de mots ayant le même préfixe pour chaque mot du fichier
    ArrayList<Integer> correspondances; 
    // StringBuilder pour construire le contenu à écrire dans le fichier de résultats
    StringBuilder file= new StringBuilder();


/**
 *  constructeur de la classe Test 
 * @param dictio  L'objet Recherche à utiliser pour les tests.
 */

    public Test(Recherche dictio){
    mot = dictio;
    correspondances = new ArrayList<>();

    }

    /**
     * Cherche chaque mot du fichier dans le dictionnaire et affiche le nombre de correspondances 
     * avec ce mot/ le préfixe tiré de ce dernier 
     * @param fichier Le fichier contenant les mots à rechercher.
     */

    public void chercherSousChaines(String fichier) {
       try{ 
            FileReader fr = new FileReader(fichier);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
             while (line!= null){

                int w= mot.chercherMot(line.toLowerCase());  

                // ajouter le nbr de mots avec le prefixe trv dans le dictionnaire à l'Arraylist
                correspondances.add(w);

                line = reader.readLine();
            }
            reader.close(); 

        }catch ( IOException e){
            System.out.println( "Erreur à l'ouverture du fichier ");
        }  
    }

/**
 * Affiche chaque élément de l'arraylist correspondances en une ligne 
 * dans un autre fichier, 
 * @param fichier  fichier dans lequel écrire les résultats de chercherSousChaines(String fichier)
 */
    public void resultatTest(String fichier){ 
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fichier))) {
            
            for(int i=0; i<correspondances.size(); i++){

                // Ajoute chaque nombre de correspondances à la chaîne de résultat, suivi d'un saut de ligne
                file.append(correspondances.get(i)).append("\n");
            }
            
            writer.write(file.toString());
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        } 
        
    }

}
