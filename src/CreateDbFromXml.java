import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by fox on 7/29/16.
 */
public class CreateDbFromXml {
    public static void main(String[] args) {
        Connection connection = SqLite.create("snp_ch21_v2.db");
        try {
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            SAXParser saxParser = null;
            SaxHandler handler = new SaxHandler();
            handler.setConnection(connection);

            saxParser = saxParserFactory.newSAXParser();

            XMLReader xmlReader = saxParser.getXMLReader();
            xmlReader.setContentHandler(handler);

            xmlReader.parse(new InputSource(new InputStreamReader(new FileInputStream("../ds_ch21.xml"), "UTF-8")));
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        /*Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM SNP;" );
            while ( rs.next() ) {
                int id = rs.getInt("id");
                String nucl0 = rs.getString("nucl0");
                String nucl1 = rs.getString("nucl1");

                System.out.println( "ID = " + id );
                System.out.println( "NUCL0 = " + nucl0 );
                System.out.println( "NUCL1 = " + nucl1 );
                System.out.println();
            }
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Operation done successfully");
*/
    }
}
