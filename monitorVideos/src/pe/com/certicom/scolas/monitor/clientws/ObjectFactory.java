
package pe.com.certicom.scolas.monitor.clientws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the pe.com.certicom.scolas.service package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _PublicarResponse_QNAME = new QName("http://service.scolas.certicom.com.pe/", "publicarResponse");
    private final static QName _Ping_QNAME = new QName("http://service.scolas.certicom.com.pe/", "ping");
    private final static QName _PingResponse_QNAME = new QName("http://service.scolas.certicom.com.pe/", "pingResponse");
    private final static QName _Publicar_QNAME = new QName("http://service.scolas.certicom.com.pe/", "publicar");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: pe.com.certicom.scolas.service
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Publicar }
     * 
     */
    public Publicar createPublicar() {
        return new Publicar();
    }

    /**
     * Create an instance of {@link Ping }
     * 
     */
    public Ping createPing() {
        return new Ping();
    }

    /**
     * Create an instance of {@link PublicarResponse }
     * 
     */
    public PublicarResponse createPublicarResponse() {
        return new PublicarResponse();
    }

    /**
     * Create an instance of {@link PingResponse }
     * 
     */
    public PingResponse createPingResponse() {
        return new PingResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PublicarResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.scolas.certicom.com.pe/", name = "publicarResponse")
    public JAXBElement<PublicarResponse> createPublicarResponse(PublicarResponse value) {
        return new JAXBElement<PublicarResponse>(_PublicarResponse_QNAME, PublicarResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Ping }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.scolas.certicom.com.pe/", name = "ping")
    public JAXBElement<Ping> createPing(Ping value) {
        return new JAXBElement<Ping>(_Ping_QNAME, Ping.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PingResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.scolas.certicom.com.pe/", name = "pingResponse")
    public JAXBElement<PingResponse> createPingResponse(PingResponse value) {
        return new JAXBElement<PingResponse>(_PingResponse_QNAME, PingResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Publicar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.scolas.certicom.com.pe/", name = "publicar")
    public JAXBElement<Publicar> createPublicar(Publicar value) {
        return new JAXBElement<Publicar>(_Publicar_QNAME, Publicar.class, null, value);
    }

}
