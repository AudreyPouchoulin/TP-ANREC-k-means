/**
 * Project: TP-ANREC
 * Creation date: 17 janv. 2014
 * Author: Audrey
 */

/**
 * @author Audrey
 *
 */
public class Point {

	private float x;
	private float y; 

	// constructeur d'un point
	public Point(){
		this.x = 0;
		this.y = 0;
	}
	
	public Point(float a, float b){
		this.x = a;
		this.y = b;
	}	

	// getter de l'abscisse
	public float getX(){
		return this.x;
	}
	
	// setter de l'abscisse	
	public void setX(float x){
		this.x = x;
	}

	// getter de l'ordonne
	public float getY(){
		return this.y;
	}
	
	// setter de l'ordonnée	
	public void setY(float y){
		this.y = y;
	}
	
	//affichage des caractéristiques
	public void afficheCaract() {
		System.out.println(this.getX()+","+this.getY());
	}
}
