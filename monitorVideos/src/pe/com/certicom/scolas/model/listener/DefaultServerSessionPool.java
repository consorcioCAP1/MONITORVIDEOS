/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.com.certicom.scolas.model.listener;

import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.QueueConnection;
import javax.jms.ServerSession;
import javax.jms.ServerSessionPool;

/**
 *
 * @author Carlos
 */
public class DefaultServerSessionPool implements ServerSessionPool, ExceptionListener{

    private QueueConnection queueConnection = null;

    public DefaultServerSessionPool(QueueConnection queueConnection){
        this.queueConnection = queueConnection;
    }

    public ServerSession getServerSession() throws JMSException {
        return new DefaultServerSession(queueConnection);
    }

    public void onException(JMSException jmse) {
        jmse.printStackTrace();
    }

    /**
     * @return the queueConnection
     */
    public QueueConnection getQueueConnection() {
        return queueConnection;
    }

    /**
     * @param queueConnection the queueConnection to set
     */
    public void setQueueConnection(QueueConnection queueConnection) {
        this.queueConnection = queueConnection;
    }

}
