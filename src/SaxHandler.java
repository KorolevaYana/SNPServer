import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.management.Attribute;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;

/**
 * Created by fox on 7/28/16.
 */


public class SaxHandler extends DefaultHandler {

    SNP currentSNP;
    boolean isObserved = false;
    boolean hadObserved = false;
    Connection connection;

    StringBuffer chars = new StringBuffer();

    @Override
    public void startElement(String namespaceURI,
                             String localName,
                             String qName,
                             Attributes atts) throws SAXException {
        if (qName.equalsIgnoreCase("Rs")) {
            currentSNP = new SNP();
            currentSNP.setId(Integer.valueOf(atts.getValue("rsId")));
        } else if (qName.equalsIgnoreCase("Observed") && !hadObserved) {
            isObserved = true;
        }
    }

    @Override
    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("Observed") && isObserved) {
            String tmp = chars.toString();
            String[] variants = chars.toString().trim().split("/");

            currentSNP.setVariants(variants);
            isObserved = false;
            hadObserved = true;
        } else if (qName.equalsIgnoreCase("Rs")) {
            hadObserved = false;
            SqLite.install(currentSNP, "snp_ch21_v2.db", connection);
            //System.out.println(currentSNP.getId() + " " + currentSNP.getVariants()[0] + "/" + currentSNP.getVariants()[1]);
        }
        chars.setLength(0);
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        chars.append(new String(ch, start, length));
    }

    @Override
    public void endDocument() {
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}

