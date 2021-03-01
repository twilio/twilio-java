package com.twilio.twiml;

import lombok.ToString;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@SuppressWarnings("checkstyle:abbreviationaswordinname")
@ToString
public abstract class TwiML {
    private final String tagName;
    private final Builder builder;
    private static final Map<String, String> attrNameMapper = Collections.singletonMap("for_", "for");

    /**
     * @param tagName Element tag name
     * @param builder Builder data for the element
     */
    protected TwiML(String tagName, Builder builder) {
        this.tagName = tagName;
        this.builder = builder;
    }

    /**
     * The body of the TwiML element
     *
     * @return Element body as a string if present else null
     */
    protected String getElementBody() {
        return null;
    }

    /**
     * Attributes to set on the generated XML element
     *
     * @return A Map of attribute keys to values
     */
    protected Map<String, String> getElementAttributes() {
        return new HashMap<>();
    }

    /**
     * Get tag name of this TwiML Element.
     */
    public String getTagName() {
        return this.tagName;
    }

    /**
     * Get children elements of this TwiML element.
     */
    public List<TwiML> getChildren() {
        return this.builder.children;
    }

    /**
     * Get additional options set on this TwiML's generated XML.
     */
    public Map<String, String> getOptions() {
        return this.builder.options;
    }

    /**
     * Get transformed attribute name for this Twiml element.
     */
    private String getTransformedAttrName(final String attrName) {
        return attrNameMapper.containsKey(attrName) ? attrNameMapper.get(attrName) : attrName;
    }

    /**
     * @param parentDoc Root XML Document
     */
    protected Node buildXmlElement(Document parentDoc) {
        Element node = parentDoc.createElement(this.getTagName());

        String body = this.getElementBody();
        if (body != null) {
            node.appendChild(parentDoc.createTextNode(body));
        }

        for (Map.Entry<String, String> attr : this.getElementAttributes().entrySet()) {
            node.setAttribute(getTransformedAttrName(attr.getKey()), attr.getValue());
        }

        for (TwiML child : this.getChildren()) {
            node.appendChild(child.buildXmlElement(parentDoc));
        }

        for (Map.Entry<String, String> option : this.getOptions().entrySet()) {
            node.setAttribute(option.getKey(), option.getValue());
        }
        return node;
    }

    /**
     * Convert TwiML object to XML.
     *
     * @return XML string of TwiML object
     * @throws TwiMLException if cannot generate XML
     */
    public String toXml() throws TwiMLException {
        try {
            Document doc = DocumentBuilderFactory
                .newInstance()
                .newDocumentBuilder()
                .newDocument();
            doc.setXmlStandalone(true);
            doc.appendChild(this.buildXmlElement(doc));

            TransformerFactory tFact = TransformerFactory.newInstance();
            tFact.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
            tFact.setAttribute(XMLConstants.ACCESS_EXTERNAL_STYLESHEET, "");
            Transformer transformer = tFact.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "no");
            DOMSource source = new DOMSource(doc);
            StreamResult output = new StreamResult(new StringWriter());
            transformer.transform(source, output);
            return output.getWriter().toString().trim();
        } catch (TransformerException te) {
            throw new TwiMLException("Exception serializing TwiML: " + te.getMessage());
        } catch (Exception e) {
            throw new TwiMLException("Unhandled exception: " + e.getMessage());
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TwiML twiml = (TwiML) o;
        return Objects.equals(this.getTagName(), twiml.getTagName()) &&
            Objects.equals(this.getElementBody(), twiml.getElementBody()) &&
            Objects.equals(this.getElementAttributes(), twiml.getElementAttributes()) &&
            Objects.equals(this.getOptions(), twiml.getOptions()) &&
            Objects.equals(this.getChildren(), twiml.getChildren());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            this.getTagName(),
            this.getElementBody(),
            this.getElementAttributes(),
            this.getChildren(),
            this.getOptions()
        );
    }

    /**
     * Create a new {@code TwiML} node
     */
    public static class Builder<T extends Builder<T>> {
        protected Map<String, String> options = new HashMap<>();
        protected List<TwiML> children = new ArrayList<>();

        /**
         * Set additional attributes on this TwiML element that will appear in generated
         * XML.
         */
        public T option(String key, String value) {
            this.options.put(key, value);
            return (T) this;
        }

        /**
         * Add a text node as a child element
         */
        public T addText(String text) {
            this.children.add(new Text(text));
            return (T) this;
        }

        /**
         * @return TwiML object
         */
        public T addChild(GenericNode node) {
            this.children.add(node);
            return (T) this;
        }
    }
}
