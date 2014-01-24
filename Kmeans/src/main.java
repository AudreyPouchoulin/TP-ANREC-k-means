import java.util.ArrayList;

/**
 * Project: TP-ANREC
 * Creation date: 15 janv. 2014
 * Author: Audrey
 */

/**
 * @author Audrey
 *
 */

public class main {
	
	public static void main(String[] args){
		// lecture des données
		ArrayList<Point> donnees = Lecture.lire("exemple2.txt");
		
		// choix du nombre de groupe
		int K = 3;
		
		// choix de la méthode de similarité
		String methodeSimilarite = "distance euclidienne";
		
		// résolution du K-means
		KMeans algorithmeKMeans = new KMeans(donnees);
		algorithmeKMeans.solve(K, methodeSimilarite);
	}

}


