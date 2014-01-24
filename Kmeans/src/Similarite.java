/**
 * Project: TP-ANREC
 * Creation date: 17 janv. 2014
 * Author: Audrey
 */

/**
 * @author Audrey
 *
 */
public class Similarite {
	private String name;
	
	public Similarite (String name){
		this.name = name;
	}
	
	public double mesureSimilarite(Point donneeEtudiee, Point center){
		double distance = 0;
		if (name.equals("distance euclidienne")){
			double distanceX = Math.abs(donneeEtudiee.getX()-center.getX());
			double distanceY = Math.abs(donneeEtudiee.getY()-center.getY());
			distance = Math.sqrt(Math.pow(distanceY,2) + Math.pow(distanceX, 2));
		}
		return distance;
	}
}
