/**
 * Project: TP-ANREC
 * Creation date: 17 janv. 2014
 * Author: Audrey
 */

/**
 * @author Audrey
 *
 */

import java.awt.Graphics;
import java.util.Vector;

import javax.swing.JFrame;

public class Affichage extends JFrame{

    private Vector<ObjetGraphique> lesObjetsG; // les objets à tracer

    public Affichage(int largeur, int longueur, int tailleCase) {
        lesObjetsG = new Vector<>();
        setSize(tailleCase * (largeur + 2), tailleCase * (longueur + 2));
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
