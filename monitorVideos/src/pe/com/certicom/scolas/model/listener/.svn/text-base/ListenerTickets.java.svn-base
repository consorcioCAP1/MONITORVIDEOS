/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.certicom.scolas.model.listener;

import java.util.Properties;
import javax.jms.ConnectionConsumer;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import org.apache.log4j.Logger;
import pe.com.certicom.scolas.monitorvideos.iu.MonitorVideosJFrame;
import pe.com.certicom.scolas.util.FechasUtil;

/**
 *
 * @author Carlos
 */
public class ListenerTickets extends Thread {

    protected final Logger logger = Logger.getLogger(getClass());
    private final String CONNECTION_FACTORY = "ConnectionFactory";
    private final String JNDI_COLA = "queue/serverQueue";
    private final int THREAD_RELIEF_INTERVAL = 100;
    private static Context contextJNDI = null;
    private String sQueueName = null;
    private QueueConnectionFactory queueConnFactory = null;
    private QueueConnection queueConnection = null;
    private QueueSession queueSession = null;
    private Queue queue = null;
    private QueueReceiver queueReceiver = null;
    private int mMessageCount = 0;
    private boolean mbBreakConnection = false;

    private String ipServidor = null;
    private int idEspacioAtencion = -1;
    private MonitorVideosJFrame app = null;

    private boolean hiloEnEjecucion = false;

    public ListenerTickets() {
        super();
        //this.setDaemon(true);
    }

    public void setApp(MonitorVideosJFrame app){
        this.app = app;
    }

    public void setIpServidor(String ipServidor){
        this.ipServidor = ipServidor;
    }
    

    public void conectar() throws Exception {
        // Realizo la conexion a jboss con el connection factory y la cola:
        System.out.println("Conectando a la cola....");
        try {
            Object oEntity = jndiLookup(CONNECTION_FACTORY);
            if (oEntity == null) {
                System.out.println("Falló la búsqueda del jndi para el connection factory.");
                return;
            }

            //Creamos la conexion al pool de conexiones:
            queueConnFactory = (QueueConnectionFactory) oEntity;
            queueConnection = queueConnFactory.createQueueConnection();
            queueSession = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
           
            //Creamos la conexion a la cola como un receptor:
            Object oQueue = jndiLookup(JNDI_COLA);
            if (oQueue == null) {
                System.out.println("Falló la búsqueda del jndi para la cola.....");
                return;
            } else {
                queue = (javax.jms.Queue) oQueue;
            }

            String selector = "idEspacioAtencion = "+idEspacioAtencion;
            queueReceiver = queueSession.createReceiver(queue,selector);

            queueConnection.start();
            hiloEnEjecucion = true;
            System.out.println("Servidor conectado a la cola, con SELECTOR--> "+selector);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("No se pudo inicializar la cola.....");
            return;
        }
    }

    public void run(){
        int iNextThreadRelief = THREAD_RELIEF_INTERVAL;

        // Inicializo el hilo propiamente dicho:
        while (hiloEnEjecucion) {
            try {
                System.out.println("Esperando mensaje de la cola...");
                if (mbBreakConnection) {
                    break;
                }
                Message theMessage = queueReceiver.receive();
                System.out.println("Mensaje recibido...");
                TextMessage theTextMessage = null;
                if (theMessage instanceof TextMessage) {
                    theTextMessage = (TextMessage) theMessage;
                    String[] mensaje = theTextMessage.getText().split(",");

                    System.out.println("Llegando mensaje desde el servidor ticket:"+mensaje[0]);

                    //Si la fecha de generación del ticket, es la misma que la fecha del sistema.
                    String fechaActual = FechasUtil.getFechaActualStringSinHora();
                    String fechaServidor = theMessage.getStringProperty("fechaGeneracionTicket");
                    if(fechaActual.equals(fechaServidor)){
                        app.setMensajeDesdeCola(
                                mensaje[0], mensaje[1],theMessage.getBooleanProperty("llamadoPorPrecola"));

                        //duermo 2 segundos este hilo...
                        Thread.sleep(2000);
                    }
                } else {
                    continue;
                }
                /*mMessageCount++;
                Thread.yield();
                if (mMessageCount == iNextThreadRelief) {
                    Thread.sleep(500);
                    iNextThreadRelief += THREAD_RELIEF_INTERVAL;
                }*/
            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("Ocurrio un error al procesar el mensaje:"+ex.getMessage());
                return;
            }
        }

        // Al terminar cierro la conexion de la cola:
        if (queueConnection != null) {
            try {
                queueConnection.close();
                queueConnection = null;
            } catch (Exception ex) {
            }
        }
    }

    public void cerrarListener(){
        // Al terminar cierro la conexion de la cola:
        hiloEnEjecucion = false;
        if (queueConnection != null) {
            try {
                queueConnection.close();
                queueConnection = null;
            } catch (Exception ex) {
            }
        }
    }

    private Object jndiLookup(String contexto) {
        Object oEntity = null;
        if (contextJNDI == null) {
            try {
                Properties props = new Properties();
                props.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
                props.put(Context.PROVIDER_URL, "jnp://"+ipServidor+":1099");
                contextJNDI = new InitialContext(props);
            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("No se pudo conectar al contexto JNDI del jboss.");
                return null;
            }
        }
        try {
            oEntity = contextJNDI.lookup(contexto);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Falló el lookup para el contexto...");
            return null;
        }
        return oEntity;
    }

    String getStatus() {
        if (queueConnection == null) {
            return "no connection";
        } else {
            return "Conectado a la cola " + sQueueName + " con "
                    + mMessageCount + " mensajes recibidos.";
        }
    }

    /**
     * @return the idEspacioAtencion
     */
    public int getIdEspacioAtencion() {
        return idEspacioAtencion;
    }

    /**
     * @param idEspacioAtencion the idEspacioAtencion to set
     */
    public void setIdEspacioAtencion(int idEspacioAtencion) {
        this.idEspacioAtencion = idEspacioAtencion;
    }
}
