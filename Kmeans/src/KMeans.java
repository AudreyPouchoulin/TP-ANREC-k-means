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
	
	public KMeans(ArrayList<Point> donnees){
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
			groupes.add(new Groupe(centers.get(i)));
		}
		
		// choix d'une méthode de similarite
		Similarite similarite = new Similarite(methodeSimilarite);
		
		// remplissage intial des groupes
		allocationInitialeDonneesAGroupe(donnees, centers, groupes, similarite);
		
		// affichage des groupes initiaux
		
		// calcul nouveaux centres
		centers.clear();
		centers = calculCentresGroupes(groupes);
		
		// itération (réallocation groupe, calcul nouveaux centres, affichage) jusqu'à stabilisation
		 while(reallocationDonneesAGroupe(centers, groupes, similarite)){
			 centers = calculCentresGroupes(groupes);
			 //TO DO: affichage
		 }
	}
	
	/**
	 * choix aléatoire initial des centres
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
	 * @param K nombre de centres
	 * @param donnees
	 * @param centers liste des centres
	 */
	public void allocationInitialeDonneesAGroupe(ArrayList<Point> donnees, ArrayList<Point> centers, ArrayList<Groupe> groupes, Similarite similarite){
		for (int i=0; i<donnees.size(); i++){
			int groupeCategorie = 0;
			Point donneeEtudiee = donnees.get(i);
			double distance = similarite.mesureSimilarite(donneeEtudiee, centers.get(0));
			for (int j=1; j<centers.size(); j++){
				if(distance>similarite.mesureSimilarite(donneeEtudiee, centers.get(j))){
					distance = similarite.mesureSimilarite(donneeEtudiee, centers.get(j));
					groupeCategorie = j;
				}
			}
			groupes.get(groupeCategorie).addMember(donneeEtudiee);	
		}
	}
	
	/**
	 * 
	 * @param groupes
	 * @return
	 */
	
	public ArrayList<Point> calculCentresGroupes(ArrayList<Groupe> groupes){
		ArrayList<Point> newCenters = new ArrayList<Point>();
		for (int i=0; i<groupes.size(); i++){
			ArrayList<Point> members = groupes.get(i).getMembers();
			float totalX=0;
			float totalY=0;
			for(int j=0; j< members.size(); j++){
				  totalX = totalX + members.get(j).getX();
				  totalY = totalY + members.get(j).getY();
			}
			float coordonneeX = totalX/(members.size());
			float coordonneeY = totalY/(members.size());
			Point center = new Point(coordonneeX, coordonneeY);
			groupes.get(i).setCenter(center);
			newCenters.add(center);
		}
		return newCenters;
	}
	
	/**
	 * Réallocation des données à un autre groupe si centre d'un autre groupe plus proche
	 * @param centers
	 * @param groupes
	 * @param similarite
	 * @return
	 */
	public boolean reallocationDonneesAGroupe(ArrayList<Point> centers, ArrayList<Groupe> groupes, Similarite similarite){
		boolean reallocation = false;
		boolean reallocationinter = false;
		
		ArrayList<Groupe> groupesRealloués = new ArrayList<Groupe>(groupes.size());
		for (int i=0; i<groupes.size(); i++){
			groupesRealloués.add(new Groupe(new Point(0,0)));
		}
		System.out.println("groupes réalloués :" + groupesRealloués.size());
		System.out.println("groupes :" + groupes.size());
		
		//parcours des groupes
		for (int i=0; i<groupes.size(); i++){
			// création du groupe copie de même centre
			groupesRealloués.get(i).setCenter((groupes.get(i).getCenter())); 
			
			//parcours des membres du groupe de référence
			for (int j=0; j<groupes.get(i).getMembers().size(); j++){
				Point donneeEtudiee = groupes.get(i).getMembers().get(j);
				
				//distance par rapport au centre de son groupe
				double distance = similarite.mesureSimilarite(donneeEtudiee, centers.get(0));;
				
				// parcours des distances aux autres centres
				int newGroupeNumber = -1;
				for (int k=0; k<centers.size(); k++){
					// changement si distance inférieure
					if (distance>similarite.mesureSimilarite(donneeEtudiee, centers.get(k))){
						distance = similarite.mesureSimilarite(donneeEtudiee, centers.get(k));
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
