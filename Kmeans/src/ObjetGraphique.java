/**
 * Project: TP-ANREC
 * Creation date: 17 janv. 2014
 * Author: Audrey
 */

/**
 * @author Audrey
 *
 */

import java.awt.*;

abstract class ObjetGraphique {

    public ObjetGraphique() {
        // do nothing
    }

    abstract public void dessinerObjet(Graphics g);
    abstract public void colorerObjet(Graphics g);


    public void ligne(Graphics g, int x1, int y1, int x2, int y2) {
        g.drawLine(x1, y1, x2, y2);
    }

    public void croix(Graphics g, int x, int y, int largeur) {
        this.ligne(g, x - largeur / 2, y, x + largeur / 2, y);
        this.ligne(g, x, y - largeur / 2, x, y + largeur / 2);
    }
}
