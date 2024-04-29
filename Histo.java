import java.io.*;

    /**
         * Conçue pour gérer un historique de mots recherchés
     */


public class Histo {
    
    // Variable pour stocker le contenu du fichier
    StringBuilder fileContent = new StringBuilder();


      /**  
            * Ajoute le mot passé en paramètre au fichier d'historique.
            * @param mot   Le mot à ajouter à l'historique
      */

    public void construireHisto(String mot) {
         
        try {
            // Lecture du contenu existant du fichier

            FileReader fr = new FileReader("history.txt");
            BufferedReader reader = new BufferedReader(fr);
            String line;
            while ((line = reader.readLine()) != null) {
                fileContent.append(line).append("\n");
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Ajout du nouveau mot au contenu existant
        fileContent.append(mot).append("\n");

        // Écriture du contenu mis à jour dans le fichier
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("history.txt"))) {
            writer.write(fileContent.toString());
            writer.close();

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    /**
        * Affiche le contenu du fichier d'historique sur la console.
     */

    public void afficherHisto() {

        try {
            // Lecture du contenu actuel du fichier

            FileReader fr = new FileReader("history.txt");
            BufferedReader reader = new BufferedReader(fr);
            String line;

            while ((line = reader.readLine()) != null) {
                // afficher le contenu d l'historique de recherche à la console
                System.out.println(line);

            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}