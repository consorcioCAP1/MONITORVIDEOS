/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.com.certicom.scolas.model.listener;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.QueueConnection;
import javax.jms.QueueSession;
import javax.jms.ServerSession;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 *
 * @author Carlos
 */
public class DefaultServerSession implements ServerSession
{
    private final QueueConnection queueConnection;
    private       QueueSession    queueSession;

    DefaultServerSession(QueueConnection queueConnection)
    {
       this.queueConnection = queueConnection;
    }

    // get or create the session for this server session
    // when creating a session a message listener is set
    public synchronized Session getSession() throws JMSException{
       if (queueSession == null) {
          queueSession = queueConnection.createQueueSession(false,Session.AUTO_ACKNOWLEDGE);
          MessageListener listener;
          listener = new MyMessageListener(queueSession);
          queueSession.setMessageListener(listener);
       }
       return queueSession;
    }

    public void start() throws JMSException{
       Thread t = new Thread(queueSession);
       t.start();
    }

    // a simple message listener that counts 100 messages
    static class MyMessageListener implements MessageListener
    {
       private final QueueSession queueSession;

       MyMessageListener(QueueSession queueSession){
          this.queueSession = queueSession;
       }

       // must be thread-safe
       public void onMessage(Message msg)
       {
           //=======================================
           System.out.println("Recibiendo mensaje2:"+msg);
           TextMessage theTextMessage = null;
           if (msg instanceof TextMessage) {
               theTextMessage = (TextMessage) msg;
               try {
                    String[] mensaje = theTextMessage.getText().split(",");
                    System.out.println("Mensaje recibido2:"+mensaje);
               } catch (JMSException ex) {
                    Logger.getLogger(DefaultServerSession.class.getName()).log(Level.SEVERE, null, ex);
               }
               //app.setMensaje(mensaje[0], mensaje[1]);
               System.out.println("Mensaje es de texto2...");
          } else {
               System.out.println("Mensaje no es de texto2...");
          }
          //=======================================
       }
    }
}