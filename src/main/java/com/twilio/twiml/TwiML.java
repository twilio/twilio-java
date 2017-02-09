package com.twilio.twiml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * TwiML object.
 */
@SuppressWarnings("checkstyle:abbreviationaswordinname")
@XmlTransient
public abstract class TwiML {

    /**
     * Convert TwiML object to XML.
     *
     * @return XML string of TwiML object
     * @throws TwiMLException if cannot generate XML
     */
    public String toXml() throws TwiMLException {
        try {
            JAXBContext context = JAXBContext.newInstance(this.getClass());
            StringWriter writer = new StringWriter();

            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
            marshaller.marshal(this, writer);

            return writer.toString();
        } catch (JAXBException e) {
            throw new TwiMLException(e.getMessage());
        }
    }

    /**
     * Convert TwiML object to URL.
     *
     * @return URL string of TwiML object
     * @throws TwiMLException if cannot generate URL
     */
    public String toUrl() throws TwiMLException {
        try {
            return URLEncoder.encode(toXml(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new TwiMLException(e.getMessage());
        }
    }

    public static class ToStringAdapter extends XmlAdapter<String, Object> {
        @Override
        public String marshal( Object v ) throws Exception {
            return v == null ? null : v.toString();
        }

        @Override
        public Object unmarshal( String v ) throws Exception {
            throw new UnsupportedOperationException("Not supported. Converts objects using toString().");
        }
    }
}
