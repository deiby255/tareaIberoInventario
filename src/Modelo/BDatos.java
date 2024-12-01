/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Jhonas
 */
public class BDatos {
        CredencialesBD cbd = new CredencialesBD();
        Connection con;
        public Connection getConnection()
        {
            try {
                Class.forName(cbd.getDriverBD());
                con = DriverManager.getConnection(cbd.getURLBD(),cbd.getNombreUsuarioBD(), cbd.getClaveBD());
                System.out.print("Conexion Exitosa");
                
                //JOptionPane.showMessageDialog(null, "Exitosa", "Mensaje", 0);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(BDatos.class.getName()).log(Level.SEVERE, null, ex);
                 System.out.print("Error: "+ex);
                  //JOptionPane.showMessageDialog(null, "Error: "+ex, "Mensaje", 0);
            }
            return con;
        }
}
