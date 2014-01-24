import java.awt.Color;
import java.awt.Graphics;
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
public class Groupe extends ObjetGraphique {

	private Point center;
	private ArrayList<Point> members;
	private String color;
	
	public Groupe(Point center){
		this.center = center;
		this.members = new ArrayList<Point>();
		this.members.add(center);
	}
	
	public Groupe(Point center, String color){
		this.center = center;
		this.members = new ArrayList<Point>();
		this.members.add(center);
		this.color = color;
	}

	public void remplasserParGroupe (Groupe groupeACopier){
		this.center = groupeACopier.getCenter();
		this.members = groupeACopier.getMembers();
	}
	
	/**
	 * @return the center
	 */
	public Point getCenter() {
		return center;
	}
	
	/**
	 * @param center the center to set
	 */
	public void setCenter(Point center) {
		this.center = center;
	}
	
	/**
	 * @return the members
	 */
	public ArrayList<Point> getMembers() {
		return members;
	}
	
	/**
	 * @param members the members to set
	 */
	public void setMembers(ArrayList<Point> members) {
		this.members = members;
	}
	
	/**
	 * @param member the member to add
	 */
	public void addMember(Point member) {
		this.members.add(member);
	}
	
	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}
	
	@Override
    public void dessinerObjet(Graphics g) {
		int tailleQuadrillage = 20;
		this.croix(g, (int)(this.center.getX()*2*tailleQuadrillage), (int)(this.center.getY()*2*tailleQuadrillage), tailleQuadrillage);
		for (int i=0; i<this.members.size(); i++){
			this.croix(g, (int)(this.members.get(i).getX()*tailleQuadrillage), (int)(this.members.get(i).getY()*tailleQuadrillage), tailleQuadrillage);
		}    
    }

    @Override
    public void colorerObjet(Graphics g) {
    	if (this.color.equals("blue")){
    		g.setColor(Color.blue);
    	} else if (this.color.equals("red")){
    		g.setColor(Color.red);
    	} else if (this.color.equals("green")){
    		g.setColor(Color.green);
    	} else if (this.color.equals("orange")){
    		g.setColor(Color.orange);
    	} else {
    		g.setColor(Color.black);
    	}
    }
	
	
}
