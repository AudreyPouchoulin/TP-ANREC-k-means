import java.util.ArrayList;

/**
 * Project: Kmeans
 * Creation date: 26 janv. 2014
 * Author: Audrey
 */

/**
 * @author Audrey
 *
 */
public class Util {

    public static void dessiner(Affichage monDessin, ArrayList<Groupe> groupes) {
    	  monDessin.removeAllObjet();
          for (int i = 0; i < groupes.size(); i++) {
          	monDessin.ajoutObjet(groupes.get(i));
          }
          monDessin.setVisible(true);
      }
    
   
    
}
