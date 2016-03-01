/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.certicom.scolas.monitorvideos.iu;

/**
 *
 * @author Carlos
 */
import javax.swing.*;
import java.awt.*;
import java.awt.font.LineMetrics;
import java.util.Map;
import java.awt.font.TextAttribute;
import java.net.URL;
import pe.com.certicom.scolas.model.beans.Marquesina;

public class MarquesinaComponente extends JLabel {

    private Hilo hilo;
    private int x = 0;
    private int anchoInicial = 0;
    private int y = 0;
    private String texto = "";
    private JPanel pnlMarquesina = null;
    private java.util.List<Marquesina> marquesinas = null;
    private int indiceMarquesinaActual = 0;
    private Marquesina marquesinaActual;
    private String rutaImagen = "";
    private String alineacionImagen = "";
    private String desTipoFuente = "";
    private int numTamanioFuente = 0;
    private ImageIcon imagen = null;
    private int anchoImagen = 0;

    public MarquesinaComponente(String texto) {
        super(texto);
        hilo = new Hilo(this);
        setOpaque(true);
        setText(texto);
    }



    public MarquesinaComponente() {
        this("");
    }

    public void setConfiguracionImagen(String rutaImagen, String alineacion) {
        
    }

    public void comenzar() {
        hilo.comenzar();
    }

    public void setText(String text) {
        texto = text;
    }

    public String getText() {
        return texto;
    }

    public void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(getForeground());
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setFont(getFont());
        int anchoString = SwingUtilities.computeStringWidth(g.getFontMetrics(), texto);

        int limite = 0;
        if("I".equals(alineacionImagen)){
            limite = x+anchoString+anchoImagen+10;
            g2.drawImage(imagen.getImage(), x, 10, null);
            g2.drawString(texto, x+anchoImagen+10, y);
        }
        else if("D".equals(alineacionImagen)){
            limite = x+anchoString+anchoImagen+10;
            g2.drawString(texto, x, y);
            g2.drawImage(imagen.getImage(), x+anchoString+10, 10, null);
        }
        else if("A".equals(alineacionImagen)){
            limite = x+anchoString+anchoImagen+10+anchoImagen;
            g2.drawImage(imagen.getImage(), x, 10, null);
            g2.drawString(texto, x+anchoImagen+10, y);
            g2.drawImage(imagen.getImage(), x+anchoImagen+10+anchoString+10, 10, null);
        }
        else{
            limite = x+anchoString+10;
            g2.drawString(texto, x, y);
        }

        if (limite <= 0) {
            x = anchoInicial;
            derecha = true;
            indiceMarquesinaActual++;
            if(indiceMarquesinaActual==marquesinas.size()){
                indiceMarquesinaActual = 0;
            }
            marquesinaActual = marquesinas.get(indiceMarquesinaActual);
            cambiarMarquesina();
        }
    }

    public void cambiarMarquesina() {
        this.setText(marquesinaActual.getDesMensaje());
        this.setForeground(new Color(marquesinaActual.getNumColorTextoR(), marquesinaActual.getNumColorTextoG(), marquesinaActual.getNumColorTextoB()));
        this.setBackground(new Color(marquesinaActual.getNumColorFondoR(), marquesinaActual.getNumColorFondoG(), marquesinaActual.getNumColorFondoB()));
    }

    public void setPanelContenedor(JPanel pnlMarquesina){
        this.pnlMarquesina=pnlMarquesina;
    }


    public void establecerTamaño(int anchoMarquesina, int altoMarquesina) {
        super.setPreferredSize(new Dimension(anchoMarquesina, Short.MAX_VALUE));
        super.setMaximumSize(new Dimension(anchoMarquesina, Short.MAX_VALUE));
        super.setVerticalAlignment(javax.swing.SwingConstants.CENTER);
        anchoInicial = anchoMarquesina;
        this.x = anchoMarquesina;
        marquesinaActual = marquesinas.get(0);//verificar que al menos haya una marquesina... para que no dispare indexofboundexception...
        cambiarMarquesina();
        Font fuente = getFont();
        Map m = fuente.getAttributes();
        m.put(TextAttribute.SIZE, new Float((float) this.numTamanioFuente));
        m.put(TextAttribute.FAMILY, this.desTipoFuente);
        setFont(new Font(m));

        FontMetrics fm = this.getFontMetrics(getFont());

        int excedente = altoMarquesina - this.numTamanioFuente;
        if(excedente>0){
            this.y = (int) (altoMarquesina - (excedente) * (2 / 3.0));
        }else{
            this.y = altoMarquesina;
        }
    }
    boolean derecha = true;

    /**
     * @return the marquesinas
     */
    public java.util.List<Marquesina> getMarquesinas() {
        return marquesinas;
    }

    /**
     * @param marquesinas the marquesinas to set
     */
    public void setMarquesinas(java.util.List<Marquesina> marquesinas) {
        this.marquesinas = marquesinas;

        if(this.marquesinas.size()>0){
            Marquesina m = this.marquesinas.get(0);//al menos debe de haber una marquesina.
            this.rutaImagen = m.getDesRutaImagen();
            this.alineacionImagen = m.getIndAlineacionImagen();

            this.desTipoFuente = m.getDesTipoFuente();
            this.numTamanioFuente = m.getNumTamanioFuente();

            //colocamos la fuente y tl tamaño

            //Si la alineacion es diferente a ninguna entonces creamos la imagen que vamos a pintar
            if(!"N".equals(alineacionImagen)){
                try{
                    imagen = new ImageIcon(new URL("file:" + this.rutaImagen));
                    anchoImagen = imagen.getIconWidth();
                    System.out.println("Ancho en pixeles del dibujo:"+anchoImagen);
                }catch(Exception e){
                    e.printStackTrace();
                    System.out.println("Ocurrio un error al obtener la imagen:"+e.getMessage());
                }
            }
        }
    }

    /**
     * @return the desTipoFuente
     */
    public String getDesTipoFuente() {
        return desTipoFuente;
    }

    /**
     * @param desTipoFuente the desTipoFuente to set
     */
    public void setDesTipoFuente(String desTipoFuente) {
        this.desTipoFuente = desTipoFuente;
    }

    /**
     * @return the numTamanioFuente
     */
    public int getNumTamanioFuente() {
        return numTamanioFuente;
    }

    /**
     * @param numTamanioFuente the numTamanioFuente to set
     */
    public void setNumTamanioFuente(int numTamanioFuente) {
        this.numTamanioFuente = numTamanioFuente;
    }

    private class Hilo extends Thread {

        MarquesinaComponente m;

        public Hilo(MarquesinaComponente m) {
            this.m = m;
        }

        public void comenzar() {
            this.start();
        }

        public void run() {
            try {
                while (true) {
                    x -= 2;
                    sleep(marquesinaActual.getNumVelocidad());
                    m.repaint();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
