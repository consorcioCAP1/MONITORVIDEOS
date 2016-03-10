/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.com.certicom.scolas.monitorvideos.iu;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.RenderingHints;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import pe.com.certicom.scolas.model.beans.GrillaLlamados;

/**
 *
 * @author ccaciquey
 */
public class RoundedPanel extends JPanel {

    /** Stroke size. it is recommended to set it to 1 for better view */
    protected int strokeSize = 1;
    /** Color of shadow */
    protected Color shadowColor = Color.black;
    /** Sets if it drops shadow */
    protected boolean shady = true;
    /** Sets if it has an High Quality view */
    protected boolean highQuality = true;
    /** Double values for Horizontal and Vertical radius of corner arcs */
    protected Dimension arcs = new Dimension(20, 20);
    /** Distance between shadow border and opaque panel border */
    protected int shadowGap = 5;
    /** The offset of shadow.  */
    protected int shadowOffset = 4;
    /** The transparency value of shadow. ( 0 - 255) */
    protected int shadowAlpha = 150;
    private char[] flecha = new char[1];

    private String flecha2 = "  ->  ";

    private JLabel[] etiquetas = null;

    private GrillaLlamados configuracionGrilla;

    private BlinkingListener blinkingListener;

    private String codigoImpresionReal;

    private int cantidadRellamadosTicket = 0;

    public RoundedPanel() {
        super();
        //setLayout(new java.awt.GridLayout(1,3));
        setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();

        setOpaque(false);
        flecha[0] = (char) (61672);
        etiquetas = new JLabel[3];


        //Inicializo las 3 etiquetas..
        etiquetas[0] = new JLabel();
        etiquetas[0].setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        etiquetas[0].setHorizontalAlignment(SwingConstants.CENTER);
        etiquetas[0].setVerticalTextPosition(javax.swing.SwingConstants.CENTER);
        etiquetas[0].setVerticalAlignment(SwingConstants.CENTER);

        etiquetas[1] = new JLabel();
        etiquetas[1].setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        etiquetas[1].setHorizontalAlignment(SwingConstants.CENTER);
        etiquetas[1].setVerticalTextPosition(javax.swing.SwingConstants.CENTER);
        etiquetas[1].setVerticalAlignment(SwingConstants.CENTER);
        

        etiquetas[2] = new JLabel();
        etiquetas[2].setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        etiquetas[2].setHorizontalAlignment(SwingConstants.CENTER);
        etiquetas[2].setVerticalTextPosition(javax.swing.SwingConstants.CENTER);
        etiquetas[2].setVerticalAlignment(SwingConstants.CENTER);

        constraints.gridx = 0; // El primer componente empieza en la columna cero.
        constraints.gridy = 0; // El primer componente empieza en la fila cero
        constraints.gridwidth = 2; // El primer componente ocupa dos columnas.
        constraints.gridheight = 1; // El primer componente ocupa 1 filas.
        //add(areaTexto, constraints);
        add(etiquetas[0],constraints);

        constraints.gridx = 2; // El segundo componente empieza en la columna dos.
        constraints.gridwidth = 1; // El segundo componente ocupa dos columnas.
        add(etiquetas[1],constraints);

        constraints.gridx = 3;
        constraints.gridwidth = 2;
        add(etiquetas[2],constraints);
    }

    public String getCodigoImpresion(){
        return etiquetas[1].getText();
    }

    public void setCodigoImpresion(String codigoImpresion){
        etiquetas[1].setText(codigoImpresion);
    }

    public void setMensaje(String codigoImpresion,String ventanilla, boolean llamadoPorPrecola){

        if(codigoImpresion.equals(this.getCodigoImpresionReal())){
            cantidadRellamadosTicket++;
        }else{
            cantidadRellamadosTicket=0;
        }

        setCodigoImpresionReal(codigoImpresion);
        
        //si el llamado es por precola solo debería de mostrarse el código del ticket unicamente en forma centrada:
        if(llamadoPorPrecola){
            etiquetas[0].setText("");
            etiquetas[1].setText(codigoImpresion);
            etiquetas[2].setText("");
        }else{
            etiquetas[0].setText(codigoImpresion);

            if(configuracionGrilla.isFontSupported()){
                //Si la flecha puede visualizarse ..
                etiquetas[1].setText("  "+new String(flecha)+"  ");
            }else{
                //Sino solo un guion y la flecha
                etiquetas[1].setText(flecha2);
            }
            etiquetas[2].setText(ventanilla);
        }
        setFuente(llamadoPorPrecola);
    }

    public void setConfiguracionGrilla(GrillaLlamados configuracionGrilla){
        this.configuracionGrilla = configuracionGrilla;
    }

    public void setFuente(boolean llamadoPorPrecola){
        setColor(llamadoPorPrecola);

        //El color de fondo y la fuente son comunes a el codigo de impresion la flecha y la ventanilla
        if(llamadoPorPrecola){
            for(int i=0;i<3;i++){
                etiquetas[i].setBackground(new Color(configuracionGrilla.getNumColorFondoR(),configuracionGrilla.getNumColorFondoG(),configuracionGrilla.getNumColorFondoB()));
                if(i==1)etiquetas[i].setFont(new java.awt.Font(configuracionGrilla.getDesTipoFuente(), 1, configuracionGrilla.getNumTamanioFuente())); // NOI18N
                else etiquetas[i].setFont(new java.awt.Font(configuracionGrilla.getDesTipoFuente(), 1, configuracionGrilla.getNumTamanioFuente())); // NOI18N
            }
        }else{
            for(int i=0;i<3;i++){
                etiquetas[i].setBackground(new Color(configuracionGrilla.getNumColorFondoR(),configuracionGrilla.getNumColorFondoG(),configuracionGrilla.getNumColorFondoB()));
                if(i==1)etiquetas[i].setFont(new java.awt.Font(configuracionGrilla.getDesTipoFuente(), 1, configuracionGrilla.getNumTamanioFuente()/2)); // NOI18N
                else etiquetas[i].setFont(new java.awt.Font(configuracionGrilla.getDesTipoFuente(), 1, configuracionGrilla.getNumTamanioFuente())); // NOI18N
            }
        }
    }

    public void setColor(boolean llamadoPorPrecola){
        if(llamadoPorPrecola){
            etiquetas[0].setForeground(new Color(configuracionGrilla.getNumColorCodigoImpresionR(),configuracionGrilla.getNumColorCodigoImpresionG(),configuracionGrilla.getNumColorCodigoImpresionB()));
            etiquetas[1].setForeground(new Color(configuracionGrilla.getNumColorCodigoImpresionR(),configuracionGrilla.getNumColorCodigoImpresionG(),configuracionGrilla.getNumColorCodigoImpresionB()));
            etiquetas[2].setForeground(new Color(configuracionGrilla.getNumColorCodigoImpresionR(),configuracionGrilla.getNumColorCodigoImpresionG(),configuracionGrilla.getNumColorCodigoImpresionB()));
        }else{
            etiquetas[0].setForeground(new Color(configuracionGrilla.getNumColorCodigoImpresionR(),configuracionGrilla.getNumColorCodigoImpresionG(),configuracionGrilla.getNumColorCodigoImpresionB()));
            etiquetas[1].setForeground(new Color(configuracionGrilla.getNumColorFlechaR(),configuracionGrilla.getNumColorFlechaG(),configuracionGrilla.getNumColorFlechaB()));
            etiquetas[2].setForeground(new Color(configuracionGrilla.getNumColorVentanillaR(),configuracionGrilla.getNumColorVentanillaG(),configuracionGrilla.getNumColorVentanillaB()));
        }
    }

    public void setColorResaltado(GrillaLlamados configuracionGrilla){
        etiquetas[0].setForeground(new Color(configuracionGrilla.getNumColorResaltadoR(),configuracionGrilla.getNumColorResaltadoG(),configuracionGrilla.getNumColorResaltadoB()));
        etiquetas[1].setForeground(new Color(configuracionGrilla.getNumColorResaltadoR(),configuracionGrilla.getNumColorResaltadoG(),configuracionGrilla.getNumColorResaltadoB()));
        etiquetas[2].setForeground(new Color(configuracionGrilla.getNumColorResaltadoR(),configuracionGrilla.getNumColorResaltadoG(),configuracionGrilla.getNumColorResaltadoB()));
    }


     @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();
        int shadowGap = this.shadowGap;
        Color shadowColorA = new Color(shadowColor.getRed(),
	shadowColor.getGreen(), shadowColor.getBlue(), shadowAlpha);
        Graphics2D graphics = (Graphics2D) g;

        //Sets antialiasing if HQ.
        if (highQuality) {
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
			RenderingHints.VALUE_ANTIALIAS_ON);
        }

        //Draws shadow borders if any.
        if (shady) {
            graphics.setColor(shadowColorA);
            graphics.fillRoundRect(
                    shadowOffset,// X position
                    shadowOffset,// Y position
                    width - strokeSize - shadowOffset, // width
                    height - strokeSize - shadowOffset, // height
                    arcs.width, arcs.height);// arc Dimension
        } else {
            shadowGap = 1;
        }

        //Draws the rounded opaque panel with borders.
        graphics.setColor(getBackground());
        graphics.fillRoundRect(0, 0, width - shadowGap,
		height - shadowGap, arcs.width, arcs.height);
        graphics.setColor(getForeground());
        graphics.setStroke(new BasicStroke(strokeSize));
        graphics.drawRoundRect(0, 0, width - shadowGap,
		height - shadowGap, arcs.width, arcs.height);

        //Sets strokes to default, is better.
        graphics.setStroke(new BasicStroke());
    }

    /**
     * @return the blinkingListener
     */
    public BlinkingListener getBlinkingListener() {
        return blinkingListener;
    }

    /**
     * @param blinkingListener the blinkingListener to set
     */
    public void setBlinkingListener(BlinkingListener blinkingListener) {
        this.blinkingListener = blinkingListener;
    }

    /**
     * @return the codigoImpresionReal
     */
    public String getCodigoImpresionReal() {
        return codigoImpresionReal;
    }

    /**
     * @param codigoImpresionReal the codigoImpresionReal to set
     */
    public void setCodigoImpresionReal(String codigoImpresionReal) {
        this.codigoImpresionReal = codigoImpresionReal;
    }

    /**
     * @return the cantidadRellamadosTicket
     */
    public int getCantidadRellamadosTicket() {
        return cantidadRellamadosTicket;
    }

    /**
     * @param cantidadRellamadosTicket the cantidadRellamadosTicket to set
     */
    public void setCantidadRellamadosTicket(int cantidadRellamadosTicket) {
        this.cantidadRellamadosTicket = cantidadRellamadosTicket;
    }

}
