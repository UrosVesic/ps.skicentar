/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.broker;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import server.konstante.ServerskeKonstante;

/**
 *
 * @author UrosVesic
 */
public class DbFabrikaKonekcije {

    private Connection konekcija;
    private static DbFabrikaKonekcije instanca;

    private DbFabrikaKonekcije() {
    }

    public static DbFabrikaKonekcije getInstanca() {
        if (instanca == null) {
            instanca = new DbFabrikaKonekcije();
        }
        return instanca;
    }

    public Connection getKonekcija() throws SQLException, IOException {

        if (konekcija == null || konekcija.isClosed()) {
            try {
                //Class.forName("com.mysql.cj.jdbc.Driver");
                Properties properties = new Properties();
                properties.load(new FileInputStream(ServerskeKonstante.DB_CONFIG_PATH));
                String url = properties.getProperty(ServerskeKonstante.DB_CONFIG_URL);
                String user = properties.getProperty(ServerskeKonstante.DB_CONFIG_USERNAME);
                String password = properties.getProperty(ServerskeKonstante.DB_CONFIG_PASSWORD);
                /*String url = "jdbc:mysql://localhost:3306/PSprojekat";
                String user = "root";
                String password = "root";*/
                konekcija = DriverManager.getConnection(url, user, password);
                konekcija.setAutoCommit(false);
            } catch (SQLException ex) {
                System.out.println("Neuspesno uspostavljanje konekcije!\n" + ex.getMessage());
                throw ex;
            }
        }
        return konekcija;
    }

}
