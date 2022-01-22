/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.broker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
        if(instanca== null){
            instanca = new DbFabrikaKonekcije();
        }
        return instanca;
    }
    
    public Connection getKonekcija() throws SQLException{
       
        if (konekcija == null || konekcija.isClosed()) {
            try {
                //Class.forName("com.mysql.cj.jdbc.Driver");
                String url = "jdbc:mysql://localhost:3306/PSprojekat";
                String user = "root";
                String password = "root";
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
