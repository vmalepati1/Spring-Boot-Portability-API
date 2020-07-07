package com.portability.api.utils;

import com.portability.api.model.PortabilityResponse;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;

public class PeerlessXML {

    public static String generatePortabilityXML(String customer, String passCode, String userID, String tn) {
        return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"\n" +
                "xmlns:pub=\"http://publicapi.api.s2.peerless.com/\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "       <pub:portabilityCheck>\n" +
                "       <authentication>\n" +
                "           <customer>" + customer + "</customer>\n" +
                "           <passCode>" + passCode + "</passCode>\n" +
                "           <userId>" + userID + "</userId>\n" +
                "       </authentication>\n" +
                "       <portabilityCheckRequest>\n" +
                "           <tns>\n" +
                "               <tns>" + tn + "</tns>\n" +
                "           </tns>\n" +
                "       </portabilityCheckRequest>\n" +
                "       </pub:portabilityCheck>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
    }

    public static boolean responseReturnedError(String responseXML) {
        return responseXML.contains("S:Fault");
    }

    public static PortabilityResponse extractPortabilityResponse(String responseXML) throws ParserConfigurationException, IOException, SAXException {
        PortabilityResponse result = new PortabilityResponse("", "", false);

        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        InputSource inputSource = new InputSource();
        inputSource.setCharacterStream(new StringReader(responseXML));
        Document doc = documentBuilder.parse(inputSource);

        NodeList dataTag = doc.getElementsByTagName("portPsNumber");

        Element element = (Element) dataTag.item(0);

        NodeList tn = element.getElementsByTagName("number");
        Element line = (Element) tn.item(0);
        result.setTelephoneNumber(getCharacterDataFromElement(line));

        NodeList provider = element.getElementsByTagName("provider");
        line = (Element) provider.item(0);
        result.setServiceProviderName(getCharacterDataFromElement(line));

        NodeList portable = element.getElementsByTagName("portable");
        line = (Element) portable.item(0);
        String portableContent = getCharacterDataFromElement(line);

        if (portableContent.equalsIgnoreCase("Yes")) {
            result.setPortable(true);
        }

        return result;
    }

    private static String getCharacterDataFromElement(Element e) {
        Node child = e.getFirstChild();
        if (child instanceof CharacterData) {
            CharacterData cd = (CharacterData) child;
            return cd.getData();
        }
        return "";
    }

}
