/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MonitorVideosJFrame.java
 *
 * Created on 09-dic-2011, 11:31:43
 */

package pe.com.certicom.scolas.monitorvideos.iu;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JRootPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pe.com.certicom.scolas.model.beans.ConfiguracionMonitor;
import pe.com.certicom.scolas.model.beans.GrillaLlamados;
import pe.com.certicom.scolas.model.beans.Marquesina;
import pe.com.certicom.scolas.model.beans.Video;
import pe.com.certicom.scolas.model.listener.ListenerMonitor;
import pe.com.certicom.scolas.model.listener.ListenerTickets;
import pe.com.certicom.scolas.monitor.service.MonitorService;

/**
 *
 * @author Carlos
 */
public class MonitorVideosJFrame extends javax.swing.JFrame {

    /** Creates new form MonitorVideosJFrame */
    private ApplicationContext app;
    private VideoPlayer videoPlayer = null;
    private SoundPlayer soundPlayer = null;
    private List<Marquesina> marquesinas = null;
    private List<Video> videos = null;
    private GrillaLlamados configuracionGrilla = null;
    private JLabel[][] etiquetas = null;

    private ListenerTickets listenerTickets = new ListenerTickets();
    private ListenerMonitor listenerMonitor = new ListenerMonitor();

    private List<RoundedPanel> llamados = new ArrayList<RoundedPanel>();
    private int indiceLlamado = 0;
    private ConfiguracionMonitor configuracionMonitor = null;
    private int tiempoBlinking = 0;
    private BlinkingListener blinkingListener = null;
    private Timer temporizador = null;
    private boolean rellamadoMismoCasillero = false;

    /**
     Llamado cada vez que llega un mensaje del servidor...
     */

    public void setMensajeDesdeTopic(String mensaje){
        System.out.println("Mensaje llegado desde el topic por suscripción:"+mensaje);
        //Si el mensaje llegó hasta aquí debemos de inicializar recien
        Integer idListaVideosFromTopic = new Integer(mensaje);
        if(configuracionMonitor.getIdListaVideos().intValue()==idListaVideosFromTopic.intValue()){
            videoPlayer.reiniciar();
        }
    }

    public void setMensajeDesdeCola(String codigoImpresion, String nombreTV, boolean llamadoPorPrecola){
        RoundedPanel caja = getSiguienteCaja(llamadoPorPrecola,codigoImpresion);
        //Aqui está la flecha :D
        caja.setMensaje(codigoImpresion, nombreTV, llamadoPorPrecola);
        resaltarCaja(caja,llamadoPorPrecola);
        soundPlayer.reiniciar();
    }

    //Como podemos hacer que este método se ejecute en forma secuencial...
    private void resaltarCaja(RoundedPanel caja, boolean llamadoPorPrecola){
        synchronized (this){
            if(tiempoBlinking > 0){
                //creamos un nuevo hilo con el tiempo?? o con un temporizador???
                blinkingListener = new BlinkingListener(tiempoBlinking,llamadoPorPrecola);
                blinkingListener.setConfiguracionGrilla(configuracionGrilla);
                blinkingListener.setCajaActiva(caja);
                caja.setBlinkingListener(blinkingListener);
                temporizador = new Timer( 500 , blinkingListener);//El listener es disparado cada segundo hasta cierto tiempo
                blinkingListener.setTemporizador(temporizador);
                temporizador.setInitialDelay(0);
                temporizador.start();
            }
        }
    }



    /**
     * Lo unico que deberìa de cambiar es esto, si el llamado es por precola entonces
     *
     */
    private RoundedPanel getSiguienteCaja(boolean llamadoPorPrecola, String codigoImpresion){
        if(llamadoPorPrecola){
            for(int i=1;i<llamados.size();i++){
                RoundedPanel hacia = llamados.get(i-1);//X[1]
                RoundedPanel desde = llamados.get(i);//X[2]
                copiarContenido(desde,hacia);
            }
            RoundedPanel retorno = llamados.get(llamados.size()-1);
            return retorno;
        }
        else{

            //El rellamado en el mismo casillero solo aplica para el llamado directo o tradicional:
            RoundedPanel retorno = null;
            if(rellamadoMismoCasillero){
                //Busco el ticket en la lista para ver si ya a sido llamado.
                int indiceEncontrado = -1;
                for(int i=0;i<llamados.size();i++){
                    RoundedPanel temporal = llamados.get(i);
                    if(codigoImpresion.equals(temporal.getCodigoImpresionReal())){
                        indiceEncontrado = i;
                        break;
                    }
                }

                if(indiceEncontrado>=0){
                    retorno = llamados.get(indiceEncontrado);
                }else{
                    if(indiceLlamado>=llamados.size())indiceLlamado=0;
                    retorno = llamados.get(indiceLlamado);
                    indiceLlamado++;
                }
            }else{
                if(indiceLlamado>=llamados.size())indiceLlamado=0;
                retorno = llamados.get(indiceLlamado);
                indiceLlamado++;
            }
            return retorno;
        }
    }

    private void copiarContenido(RoundedPanel desde, RoundedPanel hacia){
        hacia.setFuente(true);
        hacia.setCodigoImpresion(desde.getCodigoImpresion());
        if(desde.getBlinkingListener()!=null){
            int resto = (desde.getBlinkingListener().getTiempoTemporizador());
            if(desde.getBlinkingListener().getTiempoTemporizador()>0 && resto>0){
                blinkingListener = new BlinkingListener(tiempoBlinking,true);
                blinkingListener.setTiempoTemporizador(resto);
                blinkingListener.setConfiguracionGrilla(configuracionGrilla);
                blinkingListener.setCajaActiva(hacia);
                hacia.setBlinkingListener(blinkingListener);
                temporizador = new Timer( 500 , blinkingListener);//El listener es disparado cada segundo hasta cierto tiempo
                blinkingListener.setTemporizador(temporizador);
                temporizador.setInitialDelay(0);
                temporizador.start();
            }
        }
    }
  

    public MonitorVideosJFrame(ApplicationContext app) {
        try {
            //Obtengo la informacion de la base de datos:
            this.app = app;
            MonitorService monitorService = (MonitorService) app.getBean("monitorService");
            if("1".equals(monitorService.getRellamadoMismoCasillero()))rellamadoMismoCasillero = true;

            marquesinas = monitorService.listarMarquesinas();
            videos = monitorService.listarVideos();
            configuracionGrilla = monitorService.getConfiguracionGrilla();
            configuracionMonitor = monitorService.getConfiguracionMonitor();

            soundPlayer = new SoundPlayer();
            soundPlayer.setRutaSonido(configuracionGrilla.getDesRutaSonidoLlamado());
            tiempoBlinking = configuracionGrilla.getNumTiempoBlinking();


            boolean sonidoHabilitado = "1".equals(monitorService.getSonidoHabilitadoEnVideos());
            //timer.start();

            //Inicializo los componentes...
            iniciarComponentes();
            videoPlayer = new VideoPlayer(pnlVideos);
            videoPlayer.setListaVideos(videos);
            videoPlayer.setSonidoHabilitado(sonidoHabilitado);

            //Se comenta lo anterior ya que se inicia la reproducción de los vídeos cada vez que el
            //monitor recibe un mensaje desde el topic
            //if(videos.size()>0)videoPlayer.play();

            //Inicializo el listener de monitores;
            listenerMonitor.setApp(this);
            listenerMonitor.setIpServidor(monitorService.getIpServidor());
            listenerMonitor.conectar();
            listenerMonitor.start();

            //Envio un mensaje al topic para que todos los monitores sean advertidos del inicio de este monitor, incluido este monitor.
            monitorService.publicarMensajeEnTopic(String.valueOf(configuracionMonitor.getIdListaVideos()));

            //Inicializo el listener de tickets;
            listenerTickets.setApp(this);
            listenerTickets.setIpServidor(monitorService.getIpServidor());
            listenerTickets.setIdEspacioAtencion(configuracionMonitor.getIdEspacioAtencion());
            listenerTickets.conectar();
            listenerTickets.start();

            this.setLocation(configuracionMonitor.getNumPosAppX(), configuracionMonitor.getNumPosAppY());
            this.setSize(configuracionMonitor.getNumDimAppX(), configuracionMonitor.getNumDimAppY());
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(MonitorVideosJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   

    private void iniciarComponentes(){
        jPanel1 = new javax.swing.JPanel();

        //Si el mouse no está habilitado, entonces lo deshabilito...
        if("0".equals(configuracionMonitor.getIndMouseVisualizado())){
            this.setCursor(this.getToolkit().createCustomCursor(new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB), new Point(0, 0),"null"));
        }

        setUndecorated(true);
        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
     
        pnlVideos = new javax.swing.JPanel();
        pnlVideos.setLayout(new java.awt.GridLayout(1,1));

        pnlLlamados = new javax.swing.JPanel();

        pnlLlamados.setPreferredSize(new java.awt.Dimension(800, 150));
        pnlLlamados.setLayout(new java.awt.GridLayout(configuracionGrilla.getNumFilas(),configuracionGrilla.getNumColumnas()));

        Color color = new Color(configuracionGrilla.getNumColorTextoR(),configuracionGrilla.getNumColorTextoG(),configuracionGrilla.getNumColorTextoB());
        pnlLlamados.setBackground(color);

        for(int i=0;i<configuracionGrilla.getNumFilas();i++){
            for(int j=0;j<configuracionGrilla.getNumColumnas();j++){
                RoundedPanel panel = new RoundedPanel();
                panel.setConfiguracionGrilla(configuracionGrilla);
                //Lo siguiente ahora se coloca por cada ticket???
                //panel.setFuente(configuracionGrilla);
                panel.setBackground(new Color(configuracionGrilla.getNumColorFondoR(),configuracionGrilla.getNumColorFondoG(),configuracionGrilla.getNumColorFondoB()));
                pnlLlamados.add(panel);
                llamados.add(panel);
            }
        }
        pnlLlamados.setOpaque(false);
        pnlMarquesina = new javax.swing.JPanel();
        pnlMarquesina.setLayout(new java.awt.GridLayout(1,1));

        this.setSize(configuracionMonitor.getNumDimAppX(), configuracionMonitor.getNumDimAppY());

        pnlVideos.setBounds(configuracionMonitor.getNumPosVideoX(), configuracionMonitor.getNumPosVideoY(), configuracionMonitor.getNumDimVideoX(), configuracionMonitor.getNumDimVideoY());
        pnlMarquesina.setBounds(configuracionMonitor.getNumPosMarquesinaX(), configuracionMonitor.getNumPosMarquesinaY(), configuracionMonitor.getNumDimMarquesinaX(), configuracionMonitor.getNumDimMarquesinaY());
        pnlLlamados.setBounds(configuracionMonitor.getNumPosGrillaX(), configuracionMonitor.getNumPosGrillaY(), configuracionMonitor.getNumDimGrillaX(), configuracionMonitor.getNumDimGrillaY());
        
        MarquesinaComponente lblMarquesina2 = new MarquesinaComponente();
        lblMarquesina2.setVerticalAlignment(SwingConstants.CENTER);
        lblMarquesina2.setVerticalTextPosition(SwingConstants.CENTER);
        lblMarquesina2.setMarquesinas(marquesinas);

        //Le coloco el mismo tamaño del ancho de su compartimiento

        pnlMarquesina.add(lblMarquesina2);

        if(marquesinas.size()>0){
            int altoMarquesina = configuracionMonitor.getNumPosGrillaY()-configuracionMonitor.getNumPosMarquesinaY();
            lblMarquesina2.establecerTamaño(configuracionMonitor.getNumDimMarquesinaX(),altoMarquesina);
            lblMarquesina2.comenzar();
        }

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        final MonitorService monitorService = (MonitorService) app.getBean("monitorService");
        this.addWindowListener(new WindowListener() {
            public void windowClosed(WindowEvent arg0) {}
            public void windowActivated(WindowEvent arg0) {}
            public void windowClosing(WindowEvent arg0) {
                //Aqui libero el monitor:
                listenerTickets.cerrarListener();
                monitorService.desconectarMonitor();
            }
            public void windowDeactivated(WindowEvent arg0) {}
            public void windowDeiconified(WindowEvent arg0) {}
            public void windowIconified(WindowEvent arg0) {}
            public void windowOpened(WindowEvent arg0) {}
        });

        jPanel1.setMinimumSize(new java.awt.Dimension(800, 600));
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 600));
        jPanel1.setLayout(null);
        
        jPanel1.add(pnlVideos);
        jPanel1.add(pnlMarquesina);
        jPanel1.add(pnlLlamados);


        getContentPane().setLayout(new java.awt.GridLayout(1,1));
        getContentPane().add(jPanel1);
        pack();
    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        pnlVideos = new javax.swing.JPanel();
        pnlMarquesina = new javax.swing.JPanel();
        lblMarquesina = new javax.swing.JLabel();
        pnlLlamados = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.Y_AXIS));

        jPanel1.setMinimumSize(new java.awt.Dimension(800, 600));
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 600));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        pnlVideos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnlVideos.setPreferredSize(new java.awt.Dimension(800, 400));
        pnlVideos.setLayout(new java.awt.BorderLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(pnlVideos, gridBagConstraints);

        pnlMarquesina.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnlMarquesina.setPreferredSize(new java.awt.Dimension(800, 50));

        lblMarquesina.setText("jLabel2");

        javax.swing.GroupLayout pnlMarquesinaLayout = new javax.swing.GroupLayout(pnlMarquesina);
        pnlMarquesina.setLayout(pnlMarquesinaLayout);
        pnlMarquesinaLayout.setHorizontalGroup(
            pnlMarquesinaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblMarquesina, javax.swing.GroupLayout.DEFAULT_SIZE, 850, Short.MAX_VALUE)
        );
        pnlMarquesinaLayout.setVerticalGroup(
            pnlMarquesinaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblMarquesina, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(pnlMarquesina, gridBagConstraints);

        pnlLlamados.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnlLlamados.setPreferredSize(new java.awt.Dimension(800, 150));
        pnlLlamados.setLayout(new java.awt.GridLayout());

        jLabel3.setText("jLabel3");
        pnlLlamados.add(jLabel3);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(pnlLlamados, gridBagConstraints);

        getContentPane().add(jPanel1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        final ApplicationContext app = new ClassPathXmlApplicationContext("monitor-service.xml");
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MonitorVideosJFrame(app).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblMarquesina;
    private javax.swing.JPanel pnlLlamados;
    private javax.swing.JPanel pnlMarquesina;
    private javax.swing.JPanel pnlVideos;
    // End of variables declaration//GEN-END:variables

}
