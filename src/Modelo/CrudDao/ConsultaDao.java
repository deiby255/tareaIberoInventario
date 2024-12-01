/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.CrudDao;

import Modelo.BDatos;
import Modelo.BDatosDao;
import Modelo.Mantenimiento.Cliente;
import Modelo.Mantenimiento.ConsultaProductos;
import Modelo.Mantenimiento.ConsultaProveedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jhonas
 */
public class ConsultaDao {
    ArrayList<ConsultaProductos> listaConsulta;
    ArrayList<ConsultaProveedor> listaProveedor;
    public ConsultaDao()
    {
        listaConsulta = new ArrayList();
        listaProveedor = new ArrayList();
    }
    
    
    public ArrayList<ConsultaProductos> buscar(JTextField j)
    {
        String nombre = null;
        String descripcion = null;
        String categoria = null;
        String proveedor = null;
        int cantidad = 0;
        try {
            Connection con = new BDatos().getConnection();
            PreparedStatement ps;
            ResultSet rs;
            
            String query = "select producto.NOMBRE, producto.DESCRIPCION, categoria.NOMBRE as CATEGORIA, proveedor.NOMBRE as PROVEEDOR, producto.CANTIDAD from producto "
                    + "inner join categoria on categoria.ID_CATEGORIA = producto.ID_CATEGORIA inner JOIN proveedor on proveedor.ID_PROVEEDOR = producto.ID_PROVEEDOR"
                    + "where nombre like %?% or descripcion like %?% or categoria like %?% "
                    + "or proveedor like %?% or cantidad like %?%";
            ps = con.prepareStatement(query);
            ps.setString(1, j.getText());
            ps.setString(2, j.getText());
            ps.setString(3, j.getText());
            ps.setString(4, j.getText());
            rs = ps.executeQuery();
            
            while(rs.next())
            {
                nombre = rs.getString(1);
                descripcion = rs.getString(2);
                categoria = rs.getString(3);
                proveedor = rs.getString(4);
                cantidad = rs.getInt(5);
                ConsultaProductos consul = new ConsultaProductos(nombre, descripcion, categoria, proveedor, cantidad);
                listaConsulta.add(consul);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaConsulta;
    }  
    
    public ArrayList<ConsultaProductos> listar()
    {
        String nombre = null;
        String descripcion = null;
        String categoria = null;
        String proveedor = null;
        int cantidad = 0;
        try {
            Connection con = new BDatos().getConnection();
            PreparedStatement ps;
            ResultSet rs;
            
            String query = "select producto.NOMBRE, producto.DESCRIPCION, categoria.NOMBRE as CATEGORIA, proveedor.NOMBRE as PROVEEDOR, producto.CANTIDAD from producto "
                    + "inner join categoria on categoria.ID_CATEGORIA = producto.ID_CATEGORIA inner JOIN proveedor on proveedor.ID_PROVEEDOR = producto.ID_PROVEEDOR";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            
            while(rs.next())
            {
                nombre = rs.getString(1);
                descripcion = rs.getString(2);
                categoria = rs.getString(3);
                proveedor = rs.getString(4);
                cantidad = rs.getInt(5);
                ConsultaProductos consul = new ConsultaProductos(nombre, descripcion, categoria, proveedor, cantidad);
                listaConsulta.add(consul);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaConsulta;
    }  
    
    
     public void listarTabla(JTable tabla, ArrayList<ConsultaProductos> u)
    {
      DefaultTableModel modeloT = new DefaultTableModel(){
    public boolean isCellEditable(int rowIndex,int columnIndex){return false;}
    };
      
      //modeloT = (DefaultTableModel)tabla.getModel();
      tabla.setModel(modeloT);
      
      modeloT.addColumn("NOMBRE");
      modeloT.addColumn("DESCRIPCION");
      modeloT.addColumn("CATEGORIA");
      modeloT.addColumn("PROVEEDOR");
      modeloT.addColumn("CANTIDAD");
     
      
      Object[] columna = new Object[5];
      
      int numeroRegistros = u.size();
     
      for(int x = 0; x < numeroRegistros; x++)
      {
          columna[0] = u.get(x).getNombre();
          columna[1] = u.get(x).getDescripcion();
          columna[2] = u.get(x).getCategoria();
          columna[3] = u.get(x).getProveedor();
          columna[4] = u.get(x).getCantidad();
          
          modeloT.addRow(columna);
          
      }
        
    }
    
     
     
      public ArrayList<ConsultaProveedor> listarProveedor()
    {
        String nombre = null;
        String telefono = null;
        String email = null;
        String direccion = null;
        
        try {
            Connection con = new BDatos().getConnection();
            PreparedStatement ps;
            ResultSet rs;
            
            String query = "SELECT `NOMBRE`, `TELEFONO`, `EMAIL`, `DIRECCION` FROM `proveedor`";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            
            while(rs.next())
            {
                nombre = rs.getString(1);
                telefono = rs.getString(2);
                email = rs.getString(3);
                direccion = rs.getString(4);
                
                ConsultaProveedor consul = new ConsultaProveedor(nombre, telefono, email, direccion);
                listaProveedor.add(consul);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaProveedor;
    }  
    
    
     public void listarProveedor(JTable tabla, ArrayList<ConsultaProveedor> u)
    {
      DefaultTableModel modeloT = new DefaultTableModel(){
    public boolean isCellEditable(int rowIndex,int columnIndex){return false;}
    };
      
      //modeloT = (DefaultTableModel)tabla.getModel();
      tabla.setModel(modeloT);
      
      modeloT.addColumn("NOMBRE");
      modeloT.addColumn("TELEFONO");
      modeloT.addColumn("EMAIL");
      modeloT.addColumn("DIRECCION");
      
     
      
      Object[] columna = new Object[4];
      
      int numeroRegistros = u.size();
     
      for(int x = 0; x < numeroRegistros; x++)
      {
          columna[0] = u.get(x).getNombre();
          columna[1] = u.get(x).getTelefono();
          columna[2] = u.get(x).getEmail();
          columna[3] = u.get(x).getDireccion();
          modeloT.addRow(columna);
          
      }
        
    }
    
}
