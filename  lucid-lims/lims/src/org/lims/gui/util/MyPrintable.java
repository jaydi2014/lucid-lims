/**
 * 
 */
package org.lims.gui.util;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.print.PageFormat;
import java.awt.print.Printable;

import javax.swing.JTextPane;

/**
 * @author Muralidhar Yaragalla
 *
 */


public class MyPrintable implements Printable {
    JTextPane str;
    public MyPrintable(JTextPane getStr)
    {
        str = getStr;
    }
    public int print(Graphics g, PageFormat pf, int pageIndex) {
        if (pageIndex != 0)
          return NO_SUCH_PAGE;
    Graphics2D g2 = (Graphics2D) g;
    g2.translate(pf.getImageableX()+20, pf.getImageableY()+20);
    Rectangle componentBounds = str.getBounds(null);
    g2.translate(-componentBounds.x, -componentBounds.y);
    g2.scale(1, 1);
    boolean wasBuffered = str.isDoubleBuffered();
    str.paint(g2);
    str.setDoubleBuffered(wasBuffered);
    return PAGE_EXISTS;
    }
    
  }

