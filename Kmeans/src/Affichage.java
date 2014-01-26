/**
 * Project: TP-ANREC
 * Creation date: 17 janv. 2014
 * Author: Audrey
 */

/**
 * @author Audrey
 *
 */

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;

public class Affichage extends JFrame{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Vector<ObjetGraphique> lesObjetsG; // les objets à tracer
	public ArrayList<Color> colorPossibilities = new ArrayList<Color>();

    public Affichage(int largeur, int longueur, int tailleCase) {
        lesObjetsG = new Vector<>();
        getContentPane().setSize(tailleCase * (largeur + 2), tailleCase * (longueur + 2));
    	this.colorPossibilities.add(Color.red);
    	this.colorPossibilities.add(Color.blue);
    	this.colorPossibilities.add(Color.green);
    	this.colorPossibilities.add(Color.orange);
    	this.colorPossibilities.add(Color.cyan);
    	this.colorPossibilities.add(Color.gray);
    	this.colorPossibilities.add(Color.magenta);
    	this.colorPossibilities.add(Color.pink);
    	this.colorPossibilities.add(Color.yellow);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (ObjetGraphique oG : lesObjetsG) {
            oG.colorerObjet(g); // chaque objet graphique a une méthode colorerObjet
            oG.dessinerObjet(g); // chaque objet graphique a une méthode dessinerObjet
        }
    }

    public void ajoutObjet(ObjetGraphique objet) {
        lesObjetsG.add(objet);
    }

    public void removeAllObjet() {
        lesObjetsG.removeAllElements();
    }

}
