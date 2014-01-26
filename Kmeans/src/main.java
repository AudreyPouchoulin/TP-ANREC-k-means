import java.util.ArrayList;
import java.util.Scanner;

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
		ArrayList<Point> donnees;
		String methodeSimilarite ="";
		int K = 1;
		
		// lecture des données
		Lecture lecture = new Lecture();
		donnees = lecture.lire("exemple.txt");
		
		// choix du nombre de groupe
		Scanner sc = new Scanner(System.in);
		System.out.println("Choose the number of group you want (max : 9)");
		try {
			K =Integer.parseInt(sc.nextLine());
		} catch (Exception e){
			System.out.println("Please enter a number !");
		}
		
		// choix de la méthode de similarité
		System.out.println("Choose the similarity methode (\"d\" for distance euclidienne)");
		if (sc.nextLine().equals("d")){
			methodeSimilarite = "distance euclidienne";
		} else {
			System.out.println("Error, please enter a valid choice next time !");
			System.exit(0);
		}
		
		
		// résolution du K-means
		Affichage monDessin = new Affichage(lecture.maxX, lecture.maxY, 1);
		KMeans algorithmeKMeans = new KMeans(donnees, monDessin);
		algorithmeKMeans.solve(K, methodeSimilarite);
	}

}


