/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.com.certicom.scolas.monitorvideos.iu;

import java.awt.GraphicsEnvironment;

/**
 *
 * @author Carlos
 */
public class ListFonts {
	public static void main(String args[]){
		GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
		for(String font:e.getAvailableFontFamilyNames()){
			System.out.println(font);
		}
	}
}