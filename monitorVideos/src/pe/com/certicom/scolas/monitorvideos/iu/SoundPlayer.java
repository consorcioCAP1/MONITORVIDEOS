/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.com.certicom.scolas.monitorvideos.iu;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
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

/**
 *
 * @author Carlos
 */
public class SoundPlayer implements ControllerListener{

    private Player player = null;   
    private DataSource fuenteSonido = null;
 


    public SoundPlayer() {
        //Manager.setHint(Manager.LIGHTWEIGHT_RENDERER, true);
    }

    public SoundPlayer(String rutaSonido) {
        try {
            System.out.println("Fuente de sonido:"+rutaSonido);
            fuenteSonido = Manager.createDataSource(new URL("file:" + rutaSonido));
        } catch (Exception e) {
            System.out.println("Error al setear medio de sonido:" + e.getMessage());
            e.printStackTrace();
        }
    }

    public void setRutaSonido(String rutaSonido) {
        try {
            fuenteSonido = Manager.createDataSource(new URL("file:" + rutaSonido));
        } catch (Exception e) {
            System.out.println("Error al setear medio de sonido:" + e.getMessage());
            e.printStackTrace();
        }
    }


    public void play() {
	if(player == null) {
            try {
                System.out.println("Fuente de sonido-->"+fuenteSonido.getLocator().getURL().toString());
		player = Manager.createPlayer(fuenteSonido);
		player.addControllerListener(this);
		player.realize();
                player.start();
            }
            catch(MalformedURLException ex) {
                System.out.println("MalformedURLException-->"+ex.getMessage());
            }
            catch(NoPlayerException ex) {
                System.out.println("NoPlayerException-->"+ex.getMessage());
            }
            catch(IOException ex) {
                System.out.println("IOException-->"+ex.getMessage());
            }
	}
	else {
            try{
                player.start();
            }catch(Exception e){
                System.out.println("Exception-->"+e.getMessage());
            }
	}
    }

    public void reiniciar(){
        stop();
        play();
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

    public void play2() {
	if(player == null) {
            try {
		player = Manager.createPlayer(fuenteSonido);
		player.addControllerListener(this);
		player.realize();
                player.start();
            }
            catch(MalformedURLException ex) {
                System.out.println("MalformedURLException-->"+ex.getMessage());
            }
            catch(NoPlayerException ex) {
                System.out.println("NoPlayerException-->"+ex.getMessage());
            }
            catch(IOException ex) {
                System.out.println("IOException-->"+ex.getMessage());
            }
	}
    }


    public static void llamar(String rutaSonidos){
        try{
            SoundPlayer s = new SoundPlayer(rutaSonidos);
            s.play2();
            Thread.sleep(200);
            //s.stop();
        }catch(Exception e){
            e.printStackTrace();
            System.out.println(e);
        }

    }


    public static void pronunciarTicket(String rutaSonidos, String codigoTicket){
        try{

            char[] alfanumerico = codigoTicket.toCharArray();
            for(char caracter:alfanumerico){
                String file = rutaSonidos+caracter+".wav";
                SoundPlayer s = new SoundPlayer(file);
                s.play2();
                Thread.sleep(700);
                //s.stop();
            }
        }catch(Exception e){
            e.printStackTrace();
            System.out.println(e);
        }

    }


    public void controllerUpdate(ControllerEvent ev) {
	if(ev instanceof RealizeCompleteEvent) {
            System.out.println("Antes de realizar el prefetch del sonido...");
            GainControl controlVolumen = player.getGainControl();
            if(controlVolumen!=null)controlVolumen.setMute(false);
            player.prefetch();
	}

        if(ev instanceof PrefetchCompleteEvent) {
            System.out.println("Prefetch del sonido completado...");
            try{
                player.start();
            }catch(Exception e){
                System.out.println("Exception-->"+e.getMessage());
            }
	}
	if(ev instanceof EndOfMediaEvent) {
            System.out.println("Finalizado la reproducción del sonido...");
            //player.setMediaTime(new Time(0));
            player.removeControllerListener(this);
            player.stop();
	}
    }
}
