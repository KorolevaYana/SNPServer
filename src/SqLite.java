import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Created by fox on 7/28/16.
 */
public class SqLite {

    static public Connection create(String dbName) {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:" + dbName);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "CREATE TABLE SNP " +
                    "(ID INT PRIMARY KEY     NOT NULL," +
                    " NUCL0          TEXT    NOT NULL," +
                    " NUCL1          TEXT    NOT NULL)";
            stmt.executeUpdate(sql);
            stmt.close();
            c.setAutoCommit(false);
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Table created successfully");
        return c;
    }

    static public void install(SNP snp, String dbName, Connection c) {
        try {
            //Class.forName("org.sqlite.JDBC");
            //c = DriverManager.getConnection("jdbc:sqlite:" + dbName);
            //c.setAutoCommit(false);
           // System.out.println("Opened database successfully");

            Statement stmt = c.createStatement();
            String sql = "INSERT INTO SNP (ID,NUCL0,NUCL1) " +
                    "VALUES (" + snp.getId() + ", '" + snp.getVariants()[0] + "', '" + snp.getVariants()[1] + "' );";
            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }
}
