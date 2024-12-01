/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Vista.Menu;
import Vista.MenuInvitado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Jhonas
 */
public class BDatosDao {
    Connection con;
    Statement st;
    ResultSet rs;
    PreparedStatement ps;
    int nivel = 0;
    
    
    
    public int sesion(UsuarioLogin datos, JFrame panel)
    {
        
        try {
            con = new BDatos().getConnection();
           
            String query = "Select nivel from usuarios where NICKNAME = ? and clave =? ";
            String user = datos.getUsuario();
            String pass = datos.getClave();
            
           // if(user == null || pass == null)
           // {
            //    JOptionPane.showMessageDialog(null, "Campos vacios");
           // }else
           // {
                ps = con.prepareStatement(query);
                ps.setString(1,user);
                ps.setString(2,pass);
                rs = ps.executeQuery();
                
            //}
            
            while(rs.next())
            {
                nivel = rs.getInt(1);
            }
            if(nivel == 0)
            {
                JOptionPane.showMessageDialog(null, "Uusario inexistente o campos vacios", "Mensaje", 0);
            }else if(nivel == 1)
            {
                 //JOptionPane.showMessageDialog(null, "Bienvenido SUPER USUARIO", "Mensaje", 1);
                 panel.dispose();
                 new Menu().setVisible(true);
            }else if(nivel == 2)
            {
                 //JOptionPane.showMessageDialog(null, "Bienvenido INVITADO", "Mensaje", 1);
                 panel.dispose();
                 
                 
                 new MenuInvitado().setVisible(true);
                /* new Menu().btnMantenimiento.setEnabled(false);
                 new Menu().btnConfiguracion.setEnabled(false);
                 new Menu().btnReporte.setEnabled(false);*/
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(BDatosDao.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        return nivel;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
    
    
    
}
