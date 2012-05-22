/**
 * 
 */
package org.lims.gui.util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Muralidhar Yaragalla
 *
 */
public class GuiUtil {
	
	private static int currentX;
    private static int currentY;
	
	
	/**
     * Given component width and height it returns cords which 
     * can set the component at the mid of desktop.
     *  
     * @param width
     * @param height
     * @return
     */
    public static CompCenterCords getCompCenterCords(int width,int height){
            CompCenterCords cords=new CompCenterCords();
            Toolkit toolkit=Toolkit.getDefaultToolkit();
            Dimension dim=toolkit.getScreenSize();
            int screenHeight=dim.height;
            int screenWidth=dim.width;
            int heightMidPoint=screenHeight/2;
            int widthMidPoint=screenWidth/2;
            
            int compX=widthMidPoint-(width/2);
            int finalCompX=compX+currentX;
            
            int compY=heightMidPoint-(height/2);
            int finalCompY=compY-currentY;          
            
            if(finalCompY==0 ||finalCompY<0){
                    currentY=0;
                    currentX=currentX+30;
                    finalCompY=compY;
                    finalCompX=finalCompX+currentX;
            }
            if((finalCompX+width)==screenWidth ||(finalCompX+width)>screenWidth){
                    currentX=0;
                    finalCompX=compX;
            }
            cords.setX(finalCompX);
            cords.setY(finalCompY);
            currentY=currentY+30;
            return cords;
    }
    
    /**
     * It holds desktop center cords.
     * 
     * @author Muralidhar Yaragalla.
     *
     */
    public static class CompCenterCords{
            private int x;
            private int y;
            
            
            public int getX() {
                    return x;
            }
            public void setX(int x) {
                    this.x = x;
            }
            public int getY() {
                    return y;
            }
            public void setY(int y) {
                    this.y = y;
            }               
    }
    
    /**
     * This creates a JPanel with the given success message.
     * @param msg
     * @return JPanel
     */
    public static JPanel getSuccessMsgPanel(String msg){
    	JPanel panel=new JPanel();
    	panel.setLayout(new GridLayout(1,0));
    	JLabel label=new JLabel(msg);
    	label.setForeground(Color.GREEN);
    	panel.add(label);
    	return panel;
    }

}
