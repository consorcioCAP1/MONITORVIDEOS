/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.certicom.scolas.monitorvideos.iu;

import java.awt.BorderLayout;
import java.awt.Component;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import javax.media.ControllerEvent;
import javax.media.ControllerListener;
import javax.media.EndOfMediaEvent;
import javax.media.GainControl;
import javax.media.Manager;
import javax.media.NoPlayerException;
import javax.media.Player;
import javax.media.PrefetchCompleteEvent;
import javax.media.RealizeCompleteEvent;
import javax.media.Time;
import javax.media.protocol.DataSource;
import javax.swing.JPanel;
import pe.com.certicom.scolas.model.beans.Video;

/**
 *
 * @author Carlos
 */
public class VideoPlayer implements ControllerListener{

    private JPanel panelContenedor = null;
    private Player player = null;
    private List<Video> videos = null;
    private DataSource[] fuentesVideo = null;
    private int indiceVideoActivo = 0;
    private Component componenteVisual;
    private boolean play = false;

    private boolean sonidoHabilitado = false;

    private Player soundPlayer = null;


    public VideoPlayer(JPanel panel) {
        Manager.setHint(Manager.LIGHTWEIGHT_RENDERER, true);
        panelContenedor = panel;
    }

    public void setListaVideos(List<Video> videos) {
        try {
            this.videos = videos;
            fuentesVideo = new DataSource[videos.size()];
            int i = 0;
            for (Video v : videos) {
                fuentesVideo[i] = Manager.createDataSource(new URL("file:" + v.getDesRutaVideo()));
                i++;
            }
        } catch (Exception e) {
            System.out.println("Error al setear medios:" + e.getMessage());
            e.printStackTrace();
        }

    }


    public void play() {
	if(player == null) {
            try {
		player = Manager.createPlayer(fuentesVideo[indiceVideoActivo]);
		player.addControllerListener(this);
		player.realize();
            }
            catch(MalformedURLException ex) {
                System.out.println("MalformedURLException-->"+ex.getMessage());
                next();
            }
            catch(NoPlayerException ex) {
                System.out.println("NoPlayerException-->"+ex.getMessage());
                next();
            }
            catch(IOException ex) {
                System.out.println("IOException-->"+ex.getMessage());
                next();
            }
	}
	else {
            try{
                player.start();
            }catch(Exception e){
                System.out.println("Exception-->"+e.getMessage());
                next();
            }
	}
    }

    /**
     * Detiene el player y su hilo asociado...
     */
    public void stop() {
	if(player != null) {
            player.removeControllerListener(this);
            player.stop();
            player.close();
            player.deallocate();
            player = null;
	}
    }

    public void reiniciar(){
        indiceVideoActivo = 0;
        stop();
        play();
    }
	
    private void next() {
	indiceVideoActivo++;
        if(indiceVideoActivo >= fuentesVideo.length) {
            indiceVideoActivo = 0;
            stop();
            
            //si no se a reproducido ninfun video de la lista, porque ninguno es valido entonces terminamos
            //la reproducción
            if(!play)return;

            play();
            return;
	}
        if(player != null)stop();
	play();
    }


    public void controllerUpdate(ControllerEvent ev) {
	if(ev instanceof RealizeCompleteEvent) {
            
            Component video = player.getVisualComponent();
            GainControl controlVolumen = player.getGainControl();
            if(controlVolumen!=null)controlVolumen.setMute(!sonidoHabilitado);

            if (video != null) {
                panelContenedor.removeAll();
                panelContenedor.add(video, BorderLayout.CENTER); //add video al centro
                //panelContenedor.revalidate();
            }

            if (video != null) {
               while (!(video.isVisible())) {
                try {
                    Thread.sleep(500L);
                }
                catch (InterruptedException ie){}
               }
            }
            panelContenedor.revalidate();
            System.out.println("Antes de realizar el prefetch...");
            player.prefetch();
	}

        if(ev instanceof PrefetchCompleteEvent) {
            System.out.println("Prefetch del video con id:"+indiceVideoActivo+", completo.");
            player.setMediaTime(new Time(0));
            try{
                player.start();
            }catch(Exception e){
                System.out.println("Exception-->"+e.getMessage());
                next();
            }
	}
	if(ev instanceof EndOfMediaEvent) {
            play = true;
            System.out.println("Fin de reproducción del video con id:"+indiceVideoActivo+", completo.");
            player.removeControllerListener(this);
            player.stop();
            player.close();
            player = null;
            next();
	}
    }

    /**
     * @return the sonidoHabilitado
     */
    public boolean isSonidoHabilitado() {
        return sonidoHabilitado;
    }

    /**
     * @param sonidoHabilitado the sonidoHabilitado to set
     */
    public void setSonidoHabilitado(boolean sonidoHabilitado) {
        this.sonidoHabilitado = sonidoHabilitado;
    }
}
