package com.twilio.sdk.parser;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.twilio.sdk.TwilioRestResponse;

// TODO: Auto-generated Javadoc
/**
 * The Class XmlResponseParser.
 */
public class XmlResponseParser implements ResponseParser {
	public Map<String, Object> parse(TwilioRestResponse response) {
		Map<String, Object> xmlMap = this.parseXml(response.getResponseText());

		flattenMap(xmlMap, null, null);
		
		return xmlMap;
	}

	/**
	 * Flatten map.
	 *
	 * @param map the map
	 * @param parentMap the parent map
	 * @param parentKey the parent key
	 */
	@SuppressWarnings("unchecked")
	private void flattenMap(Map<String, Object> map,
			Map<String, Object> parentMap, String parentKey) {

		for (String key : map.keySet()) {
			Object data = map.get(key);
			if (data instanceof Map) {
				flattenMap((Map<String, Object>) data, map, key);
			} else if (data instanceof List) {
				parentMap.put(parentKey, data);
			}
		}

	}

	/**
	 * Parses the xml.
	 *
	 * @param xmlString the xml string
	 * @return the map
	 */
	protected Map<String, Object> parseXml(String xmlString) {

		Map<String, Object> ret = new HashMap<String, Object>();

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			Document d = builder.parse(new InputSource(new StringReader(
					xmlString)));

			Node resp = d.getFirstChild(); // TwilioResponse
			NodeList nodes = resp.getChildNodes();

			for (int i = 0; i < nodes.getLength(); i++) {
				Node node = nodes.item(i);
				buildNode(node, ret);
			}

			if (resp.hasChildNodes() && resp.getFirstChild().hasAttributes()) {
				// Setup paging
				NamedNodeMap attrs = resp.getFirstChild().getAttributes();

				for (PagingProperty p : PagingProperty.values()) {
					String property = this.getPagingPropertyKey(p);
					Node n = attrs.getNamedItem(property);
					ret.put(property, n.getNodeValue());
				}
			}
		} catch (ParserConfigurationException e) {
			// TODO log this
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ret;
	}

	/**
	 * Builds the node.
	 *
	 * @param currentNode the current node
	 * @param containerMap the container map
	 */
	@SuppressWarnings("unchecked")
	private void buildNode(Node currentNode, Map<String, Object> containerMap) {
		if (currentNode.hasChildNodes()
				&& currentNode.getChildNodes().getLength() == 1
				&& currentNode.getFirstChild().getNodeType() == Node.TEXT_NODE) {
			Node textNode = currentNode.getFirstChild();

			// this should be a list...
			if (containerMap.containsKey(currentNode.getNodeName())) {
				Object oldObject = containerMap.get(currentNode.getNodeName());
				if (oldObject instanceof List) {
					List<Object> array = (List<Object>) oldObject;
					array.add(textNode.getNodeValue());
				} else {
					List<Object> newList = new ArrayList<Object>();
					newList.add(oldObject);
					newList.add(textNode.getNodeValue());
					containerMap.put(currentNode.getNodeName(), newList);
				}
			} else {
				containerMap.put(currentNode.getNodeName(),
						textNode.getNodeValue());
			}
		} else if (currentNode.hasChildNodes()) {
			NodeList nodes = currentNode.getChildNodes();
			Map<String, Object> newElement = new HashMap<String, Object>();

			if (containerMap.containsKey(currentNode.getNodeName())) {
				Object oldObject = containerMap.get(currentNode.getNodeName());
				if (oldObject instanceof List) {
					List<Object> array = (List<Object>) oldObject;
					array.add(newElement);
				} else {
					List<Object> newList = new ArrayList<Object>();
					newList.add(oldObject);
					newList.add(newElement);
					containerMap.put(currentNode.getNodeName(), newList);
				}
			} else {
				containerMap.put(currentNode.getNodeName(), newElement);
			}

			for (int i = 0; i < nodes.getLength(); i++) {
				Node node = nodes.item(i);
				buildNode(node, newElement);
			}
		}

	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.parser.ResponseParser#getPagingPropertyKey(com.twilio.sdk.parser.ResponseParser.PagingProperty)
	 */
	@Override
	public String getPagingPropertyKey(PagingProperty prop) {
		switch (prop) {
		case NEXT_PAGE_URI_KEY:
			return "nextpageuri";
		case START_KEY:
			return "start";
		case END_KEY:
			return "end";
		case PAGE_KEY:
			return "page";
		case NUM_PAGES_KEY:
			return "numpages";
		case TOTAL_KEY:
			return "total";
		}

		return null;
	}
}
