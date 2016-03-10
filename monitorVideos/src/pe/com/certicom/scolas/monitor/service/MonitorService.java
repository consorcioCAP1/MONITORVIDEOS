/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.com.certicom.scolas.monitor.service;

import java.util.Date;
import java.util.List;
import pe.com.certicom.scolas.model.beans.ConfiguracionMonitor;
import pe.com.certicom.scolas.model.beans.GrillaLlamados;
import pe.com.certicom.scolas.model.beans.Marquesina;
import pe.com.certicom.scolas.model.beans.Video;

/**
 *
 * @author Carlos
 */
public interface MonitorService {

    public List<Marquesina> listarMarquesinas();
    public List<Video> listarVideos();
    public GrillaLlamados getConfiguracionGrilla();
    public ConfiguracionMonitor getConfiguracionMonitor();
    public String getIpServidor();
    public String getFontsSupported();
    public String getRellamadoMismoCasillero();
    public void desconectarMonitor();
    public boolean publicarMensajeEnTopic(String mensaje);
    public String getSonidoHabilitadoEnVideos();
    public String getRellamadoPronunciado();
    public String getRutaSonidos();

    
}
