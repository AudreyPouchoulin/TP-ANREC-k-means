import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Project: TP-ANREC
 * Creation date: 17 janv. 2014
 * Author: Audrey
 */

/**
 * @author François
 *
 */
public class Lecture {

	public int maxX;
	public int maxY;
	
	public Lecture(){
		maxX = 0;
		maxY = 0;
	}


	public ArrayList<Point> lire(String filePath){
		ArrayList<Point> donnees = new ArrayList<Point>();
		        try{         
		           BufferedReader buff = new BufferedReader(new FileReader(filePath));         
		           try { 
		              String line; 
		              // Lire le fichier ligne par ligne 
		              // La boucle se termine quand la méthode affiche "null"           
		              while ((line = buff.readLine()) != null) {
		            	  String[] coord = line.split("\t");
		            	  Point point = new Point();            	 
		            	  point.setX( Integer.parseInt(coord[0]) );            	 
		            	  point.setY( Integer.parseInt(coord[1]) );
		            	  donnees.add(point);
		            	  if (point.getX()>maxX){
		            		  maxX=point.getX();
		            	  }
		            	  if (point.getY()>maxY){
		            		  maxY=point.getY();
		            	  }
		              }     
		              buff.close(); //Lecture fini donc on ferme le flux 
		           }
		           catch (IOException e){ 
		               System.out.println(e.getMessage()); 
		               System.exit(1); 
		            } 
		      
		        } 
		         catch (IOException e) { 
		            System.out.println(e.getMessage()); 
		            System.exit(1); 
		         } 
		return donnees;
	}

}