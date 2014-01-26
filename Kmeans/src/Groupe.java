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
	private Color color;
	
	public Groupe(Point center){
		this.center = center;
		this.members = new ArrayList<Point>();
		this.members.add(center);
	}
	
	public Groupe(Point center, Color color){
		this.center = center;
		this.members = new ArrayList<Point>();
		this.members.add(center);
		this.color = color;
	}

	public void remplasserParGroupe (Groupe groupeACopier){
		this.center = new Point(groupeACopier.getCenter().getX(), groupeACopier.getCenter().getY());
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
	public Color getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}
	
	@Override
    public void dessinerObjet(Graphics g) {
		int tailleQuadrillage = 8;
		this.cercle(g, (int)(this.center.getX()*tailleQuadrillage), (int)(this.center.getY()*tailleQuadrillage), tailleQuadrillage);
		for (int i=0; i<this.members.size(); i++){
			this.croix(g, (int)(this.members.get(i).getX()*tailleQuadrillage), (int)(this.members.get(i).getY()*tailleQuadrillage), tailleQuadrillage);
		}
    }


	@Override
    public void colorerObjet(Graphics g) {
    	g.setColor(this.color);
    }
	
	
}
