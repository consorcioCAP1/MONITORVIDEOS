/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.com.certicom.scolas.model.listener;

import java.util.Properties;
import org.apache.log4j.Logger;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.naming.Context;
import javax.naming.InitialContext;
import pe.com.certicom.scolas.monitorvideos.iu.MonitorVideosJFrame;

/**
 *
 * @author ccaciquey
 */
public class ListenerMonitor extends Thread {

    protected final Logger logger = Logger.getLogger(getClass());
    private final String CONNECTION_FACTORY = "ConnectionFactory";
    private final String JNDI_TOPIC = "topic/monitorTopic";
    private final int THREAD_RELIEF_INTERVAL = 100;
    private static Context contextJNDI = null;


    private String sTopicName = null;
    private TopicConnectionFactory topicConnFactory = null;
    private TopicConnection topicConnection = null;
    private TopicSession topicSession = null;
    private Topic topic = null;
    private TopicSubscriber topicSubscriber = null;
    private int mMessageCount = 0;
    private boolean mbBreakConnection = false;

    private String ipServidor = null;
    private MonitorVideosJFrame app = null;

    public ListenerMonitor() {
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
        System.out.println("Conectando monitor al topic....");
        try {
            Object oEntity = jndiLookup(CONNECTION_FACTORY);
            if (oEntity == null) {
                System.out.println("Falló la búsqueda del jndi para el connection factory.");
                return;
            }

            //Creamos la conexion al pool de conexiones:
            topicConnFactory = (TopicConnectionFactory) oEntity;
            topicConnection = topicConnFactory.createTopicConnection();
            topicSession = topicConnection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);

            //Creamos la conexion a la cola como un receptor:
            Object oTopic = jndiLookup(JNDI_TOPIC);
            if (oTopic == null) {
                System.out.println("Falló la búsqueda del jndi para el topic.....");
                return;
            } else {
                topic = (javax.jms.Topic) oTopic;
            }
            topicSubscriber = topicSession.createSubscriber(topic);
            topicConnection.start();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("No se pudo inicializar la cola.....");
            return;
        }
    }

    public void run(){
        int iNextThreadRelief = THREAD_RELIEF_INTERVAL;

        // Inicializo el hilo propiamente dicho:
        while (true) {
            try {
                System.out.println("Esperando mensaje del topic...");
                if (mbBreakConnection) {
                    break;
                }

                Message theMessage = topicSubscriber.receive();
                TextMessage theTextMessage = null;
                if (theMessage instanceof TextMessage) {
                    theTextMessage = (TextMessage) theMessage;
                    app.setMensajeDesdeTopic(theTextMessage.getText());
                } else { 
                    continue;
                }
                mMessageCount++;
                Thread.yield();
                if (mMessageCount == iNextThreadRelief) {
                    Thread.sleep(500);
                    iNextThreadRelief += THREAD_RELIEF_INTERVAL;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("Ocurrio un error al procesar el mensaje del topic:"+ex.getMessage());
                return;
            }
        }

        // Al terminar cierro la conexion de la cola:
        if (topicConnection != null) {
            try {
                topicConnection.close();
                topicConnection = null;
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
        if (topicConnection == null) {
            return "no connection";
        } else {
            return "Conectado a la cola " + sTopicName + " con "+ mMessageCount + " mensajes recibidos.";
        }
    }
}
