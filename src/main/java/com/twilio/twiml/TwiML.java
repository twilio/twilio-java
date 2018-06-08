package com.twilio.twiml;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

@SuppressWarnings("checkstyle:abbreviationaswordinname")
public abstract class TwiML {
    private final String tagName;
    private final List<TwiML> children;
    private final Map<String, String> options;

    /**
     * @param tagName Element tag name
     * @param children Children elements of this tag
     * @param options Additional attributes to set on this element
     */
    protected TwiML(String tagName, List<TwiML> children, Map<String, String> options) {
        this.tagName = tagName;
        this.children = children;
        this.options = options;
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
        return this.children;
    }

    /**
     * Get additional options set on this TwiML's generated XML.
     */
    public Map<String, String> getOptions() {
        return this.options;
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

        for (Map.Entry<String,String> attr : this.getElementAttributes().entrySet()) {
            node.setAttribute(attr.getKey(), attr.getValue());
        }

        for (TwiML child : this.getChildren()) {
            node.appendChild(child.buildXmlElement(parentDoc));
        }

        for (Map.Entry<String, String> option : this.options.entrySet()) {
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

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
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
        return Objects.equal(this.getTagName(), twiml.getTagName()) &&
            Objects.equal(this.getElementBody(), twiml.getElementBody()) &&
            Objects.equal(this.getElementAttributes(), twiml.getElementAttributes()) &&
            Objects.equal(this.getOptions(), twiml.getOptions()) &&
            Objects.equal(this.getChildren(), twiml.getChildren());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(
            this.getTagName(),
            this.getElementBody(),
            this.getElementAttributes(),
            this.getChildren(),
            this.getOptions()
        );
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("Body", this.getElementBody())
            .add("Attributes", this.getElementAttributes())
            .add("Children", this.getChildren())
            .add("Options", this.getOptions())
            .toString();
    }
}
