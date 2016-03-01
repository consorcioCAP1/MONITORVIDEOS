/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.com.certicom.scolas.monitor.service;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.xml.namespace.QName;
import org.springframework.beans.factory.InitializingBean;
import pe.com.certicom.scolas.model.beans.ConfiguracionMonitor;
import pe.com.certicom.scolas.model.beans.GrillaLlamados;
import pe.com.certicom.scolas.model.beans.Marquesina;
import pe.com.certicom.scolas.model.beans.Video;
import pe.com.certicom.scolas.model.dao.ConfiguracionMonitorDAO;
import pe.com.certicom.scolas.model.dao.GrillaLlamadosDAO;
import pe.com.certicom.scolas.model.dao.MarquesinaDAO;
import pe.com.certicom.scolas.model.dao.VideoDAO;
import pe.com.certicom.scolas.monitor.clientws.MonitorWSService;

/**
 *
 * @author Carlos
 */
public class MonitorServiceImpl implements MonitorService, InitializingBean {

    private MarquesinaDAO marquesinaDAO;
    private VideoDAO videoDAO;
    private GrillaLlamadosDAO grillaLlamadosDAO;
    private ConfiguracionMonitorDAO configuracionMonitorDAO;

    private String ipServidor;
    private String puertoServidor;
    private String idConfiguracion;
    private String rellamadoMismoCasillero;
    private String fontsSupported;
    private String sonidoHabilitadoEnVideos;


    public List<Marquesina> listarMarquesinas() {
        return marquesinaDAO.listarMarquesinas(new Integer(getIdConfiguracion()));
    }

    public List<Video> listarVideos() {
        return videoDAO.listarVideos(new Integer(getIdConfiguracion()));
    }

    public GrillaLlamados getConfiguracionGrilla() {
        GrillaLlamados grillaLlamados = grillaLlamadosDAO.getConfiguracionGrilla(new Integer(getIdConfiguracion()));;
        if(grillaLlamados!=null){
            grillaLlamados.setFontsSupported(fontsSupported);
        }
        return grillaLlamados;
    }

    public ConfiguracionMonitor getConfiguracionMonitor() {
        return getConfiguracionMonitorDAO().getConfiguracionMonitor(new Integer(getIdConfiguracion()));
    }
    
    /**
     * @return the marquesinaDAO
     */
    public MarquesinaDAO getMarquesinaDAO() {
        return marquesinaDAO;
    }

    /**
     * @param marquesinaDAO the marquesinaDAO to set
     */
    public void setMarquesinaDAO(MarquesinaDAO marquesinaDAO) {
        this.marquesinaDAO = marquesinaDAO;
    }

    /**
     * @return the videoDAO
     */
    public VideoDAO getVideoDAO() {
        return videoDAO;
    }

    /**
     * @param videoDAO the videoDAO to set
     */
    public void setVideoDAO(VideoDAO videoDAO) {
        this.videoDAO = videoDAO;
    }

    /**
     * @return the grillaLlamadosDAO
     */
    public GrillaLlamadosDAO getGrillaLlamadosDAO() {
        return grillaLlamadosDAO;
    }

    /**
     * @param grillaLlamadosDAO the grillaLlamadosDAO to set
     */
    public void setGrillaLlamadosDAO(GrillaLlamadosDAO grillaLlamadosDAO) {
        this.grillaLlamadosDAO = grillaLlamadosDAO;
    }


    /**
     * @return the ipServidor
     */
    public String getIpServidor() {
        return ipServidor;
    }

    /**
     * @param ipServidor the ipServidor to set
     */
    public void setIpServidor(String ipServidor) {
        this.ipServidor = ipServidor;
    }


    /**
     * @return the configuracionMonitorDAO
     */
    public ConfiguracionMonitorDAO getConfiguracionMonitorDAO() {
        return configuracionMonitorDAO;
    }

    /**
     * @param configuracionMonitorDAO the configuracionMonitorDAO to set
     */
    public void setConfiguracionMonitorDAO(ConfiguracionMonitorDAO configuracionMonitorDAO) {
        this.configuracionMonitorDAO = configuracionMonitorDAO;
    }

    /**
     * @return the idConfiguracion
     */
    public String getIdConfiguracion() {
        return idConfiguracion;
    }

    /**
     * @param idConfiguracion the idConfiguracion to set
     */
    public void setIdConfiguracion(String idConfiguracion) {
        this.idConfiguracion = idConfiguracion;
    }

    public void afterPropertiesSet() throws Exception {
        //lanzo una excepcion si no existe la configuracion
        try{
            Integer idConfiguracion = new Integer(getIdConfiguracion());
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "El ID de configuración del monitor no es válido, encontrado:"+getIdConfiguracion(), "Error",
                    JOptionPane.ERROR_MESSAGE);
            throw new Exception("Debe de colocar una identificación válida:"+e.getMessage());
        }
        ConfiguracionMonitor cm = getConfiguracionMonitor();
        if(cm==null){
            JOptionPane.showMessageDialog(null, "El ID de configuración del monitor no existe en BD, encontrado:"+getIdConfiguracion(), "Error",
                    JOptionPane.ERROR_MESSAGE);
            throw new Exception("El ID de configuración del monitor no existe en BD, encontrado:"+getIdConfiguracion());
        }

        //Verificamos que no esté conectado este monitor,
        if("1".equals(cm.getIndConectado())){
            JOptionPane.showMessageDialog(null, "Una instancia del monitor con ID:"+getIdConfiguracion()+" ya se encuentra conectado.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            throw new Exception("Una instancia del monitor con ID:"+getIdConfiguracion()+" ya se encuentra conectado.");
        }

        //Si llega hasta aqui marco el monitor como conectado...
        System.out.println("Iniciando conexion del monitor...");
        configuracionMonitorDAO.conectarMonitor(new Integer(getIdConfiguracion()));
        System.out.println("Monitor marcado como conectado.");
    }

    public void desconectarMonitor() {
        System.out.println("Desconectando monitor..");
        configuracionMonitorDAO.desconectarMonitor(new Integer(getIdConfiguracion()));
        System.out.println("Monitor desconectado.");
    }

    /**
     * @return the fontsSupported
     */
    public String getFontsSupported() {
        return fontsSupported;
    }

    /**
     * @param fontsSupported the fontsSupported to set
     */
    public void setFontsSupported(String fontsSupported) {
        this.fontsSupported = fontsSupported;
    }

    /**
     * @return the rellamadoMismoCasillero
     */
    public String getRellamadoMismoCasillero() {
        return rellamadoMismoCasillero;
    }

    /**
     * @param rellamadoMismoCasillero the rellamadoMismoCasillero to set
     */
    public void setRellamadoMismoCasillero(String rellamadoMismoCasillero) {
        this.rellamadoMismoCasillero = rellamadoMismoCasillero;
    }

    public boolean publicarMensajeEnTopic(String mensaje) {
        System.out.println("Publicando mensaje en topic:"+mensaje);
	boolean mensajePublicado = false;
	try{
            URL url =  new URL("http://"+ipServidor+":"+getPuertoServidor()+"/monitor/MonitorWS?wsdl");
            MonitorWSService monitorWSService =	new MonitorWSService(url,new QName("http://service.scolas.certicom.com.pe/", "MonitorWSService"));
            monitorWSService.getMonitorWSPort().publicar(mensaje);
            mensajePublicado = true;
	} catch (MalformedURLException ex) {
            ex.printStackTrace();
            mensajePublicado = false;
	} catch (Exception ex) {
            ex.printStackTrace();
            mensajePublicado = false;
	}
        System.out.println("Mensaje publicado en el topic:"+mensajePublicado);
	return mensajePublicado;
    }

    /**
     * @return the puertoServidor
     */
    public String getPuertoServidor() {
        return puertoServidor;
    }

    /**
     * @param puertoServidor the puertoServidor to set
     */
    public void setPuertoServidor(String puertoServidor) {
        this.puertoServidor = puertoServidor;
    }

    /**
     * @return the sonidoHabilitadoEnVideos
     */
    public String getSonidoHabilitadoEnVideos() {
        return sonidoHabilitadoEnVideos;
    }

    /**
     * @param sonidoHabilitadoEnVideos the sonidoHabilitadoEnVideos to set
     */
    public void setSonidoHabilitadoEnVideos(String sonidoHabilitadoEnVideos) {
        this.sonidoHabilitadoEnVideos = sonidoHabilitadoEnVideos;
    }

}
