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
public class KMeans {
	
	private ArrayList<Point> donnees;
	private Affichage monDessin;
	
	public KMeans(ArrayList<Point> donnees, Affichage dessin){
		this.monDessin = dessin;
		this.donnees = donnees;
	}

	/**
	 * 
	 */
	public void solve(int K, String methodeSimilarite){
		// choix aléatoire des centres
		ArrayList<Point> centers = ChoixCentres(K, donnees);
		
		// création des groupes
		ArrayList<Groupe> groupes = new ArrayList<Groupe>();
		for (int i=0; i<centers.size(); i++){
			groupes.add(new Groupe(centers.get(i), monDessin.colorPossibilities.get(i)));
		}
		
		// choix d'une méthode de similarite
		Similarite similarite = new Similarite(methodeSimilarite);
		
		// remplissage intial des groupes
		allocationInitialeDonneesAGroupe(donnees, groupes, similarite);
		
		// affichage des groupes initiaux
        Util.dessiner(monDessin, groupes);
        monDessin.setVisible(true);
        try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// calcul des nouveaux centres de chaque groupe
		boolean reallocationCentre = calculCentresGroupes(groupes);
		if (!reallocationCentre){
			System.out.println("Big luck, aleatories centers are the real ones !");
		}
		
		// itération (réallocation groupe, calcul nouveaux centres, affichage) jusqu'à stabilisation
		int nombreIteration = 0;
		boolean reallocation = true;
		 while(reallocation){
			 reallocation = reallocationDonneesAGroupe(groupes, similarite);
			 calculCentresGroupes(groupes);
			 nombreIteration++;
			 //affichage
			 Util.dessiner(monDessin, groupes);
	         monDessin.repaint();
	         try {
	 			Thread.sleep(1000);
	 		} catch (InterruptedException e) {
	 			// TODO Auto-generated catch block
	 			e.printStackTrace();
	 		}
		 }
		 System.out.println("L'alrgorithme du Kmeans s'est stabilisé en " + nombreIteration + " itérations.");
	}
	
	/**
	 * choix aléatoire initial des centres
	 * @param K nombre de groupes
	 * @param donnees
	 */
	
	public ArrayList<Point> ChoixCentres(int K, ArrayList<Point> donnees){
		ArrayList<Point> centers = new ArrayList<Point>();
		for (int i=0; i<K; i++){
			int random = (int)(Math.random()*(donnees.size()-1));
			centers.add(donnees.get(random));
		}
		return centers;
	}
	
	/**
	 * allocation initiale des données dans un groupe
	 * @param liste des donnees
	 * @param liste des groupes
	 * @param méthode de similarité
	 */
	public void allocationInitialeDonneesAGroupe(ArrayList<Point> donnees, ArrayList<Groupe> groupes, Similarite similarite){
		for (int i=0; i<donnees.size(); i++){
			Point donneeEtudiee = donnees.get(i);
			int groupeNumber = 0;
			double distance = similarite.mesureSimilarite(donneeEtudiee, groupes.get(0).getCenter());
			for (int j=1; j<groupes.size(); j++){
				if(distance>similarite.mesureSimilarite(donneeEtudiee, groupes.get(j).getCenter())){
					distance = similarite.mesureSimilarite(donneeEtudiee, groupes.get(j).getCenter());
					groupeNumber = j;
				}
			}
			groupes.get(groupeNumber).addMember(donneeEtudiee);	
		}
	}
	
	/**
	 * calcul des nouveaux centres dans un groupe
	 * @param groupes
	 * @return true si il n'y a pas eu de réallocation de centres
	 */
	
	public boolean calculCentresGroupes(ArrayList<Groupe> groupes){
		boolean reallocationCentre = false;
		for (int i=0; i<groupes.size(); i++){
			ArrayList<Point> members = groupes.get(i).getMembers();
			int totalX=0;
			int totalY=0;
			for(int j=0; j< members.size(); j++){
				  totalX = totalX + members.get(j).getX();
				  totalY = totalY + members.get(j).getY();
			}
			int coordonneeX = totalX/(members.size());
			int coordonneeY = totalY/(members.size());
			Point center = new Point(coordonneeX, coordonneeY);
			if (!(groupes.get(i).getCenter().getX()== center.getX() & groupes.get(i).getCenter().getY()== center.getY())){
				reallocationCentre = true;
				groupes.get(i).setCenter(center);
			}
		}
		return reallocationCentre;
	}
	
	/**
	 * Réallocation des données à un autre groupe si le centre d'un autre groupe est plus proche
	 * @param liste de groupes
	 * @param méthode de similarite
	 * @return true si il y a eu une lou des réallocations
	 */
	public boolean reallocationDonneesAGroupe(ArrayList<Groupe> groupes, Similarite similarite){
		boolean reallocation = false;
		boolean reallocationinter = false;
		
		// création de nouveaux groupes
		ArrayList<Groupe> groupesRealloués = new ArrayList<Groupe>(groupes.size());
		for (int i=0; i<groupes.size(); i++){
			groupesRealloués.add(new Groupe(new Point(0,0)));
		}
		
		//parcours des groupes
		for (int i=0; i<groupes.size(); i++){
			// création du groupe copie de même centre
			groupesRealloués.get(i).setCenter((groupes.get(i).getCenter())); 
			
			//parcours des membres du groupe de référence
			for (int j=0; j<groupes.get(i).getMembers().size(); j++){
				Point donneeEtudiee = groupes.get(i).getMembers().get(j);
				
				//distance par rapport au centre de son groupe
				double distance = similarite.mesureSimilarite(donneeEtudiee, groupes.get(i).getCenter());;
				
				
				// parcours des distances aux autres centres
				int newGroupeNumber = -1;
				for (int k=0; k<groupes.size(); k++){
					// changement si distance inférieure (si distance égale reste dans le groupe d'origine)
					if (distance>similarite.mesureSimilarite(donneeEtudiee, groupes.get(k).getCenter())){
						System.out.println(distance);
						System.out.println(similarite.mesureSimilarite(donneeEtudiee, groupes.get(k).getCenter()));
						distance = similarite.mesureSimilarite(donneeEtudiee, groupes.get(k).getCenter());
						reallocationinter = true;
						reallocation = true;
						newGroupeNumber = k;
					}
				}
				if (reallocationinter){
					groupesRealloués.get(newGroupeNumber).addMember(donneeEtudiee);
					reallocationinter = false;
				} else {
					groupesRealloués.get(i).addMember(donneeEtudiee);
				}
			}
			groupes.get(i).remplasserParGroupe(groupesRealloués.get(i));
		}
		return reallocation;
	}
}
