/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Modelo.Mantenimiento.Producto;
import Vista.Mantenimiento;
import Vista.Movimientos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jhonas
 */
public class MovimientosDao {
    
    public MovimientosDao(){}
    BDatos bd = new BDatos();
    
    public int cantidadStock(int id)
    {
        int cantidad = 0;
        try {
            JTable tabla = new Movimientos().tblProductoMovimientos;
            int fila = tabla.getSelectedRow();
            
            
            Connection con = bd.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            
            String query = "SELECT CANTIDAD FROM PRODUCTO WHERE ID_PRODUCTO = ?";
            
            ps = con.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while(rs.next())
            {
                cantidad = rs.getInt(1);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(MovimientosDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return cantidad;
    }
    
    
    public void ingresoProducto(int ingreso, JTable tabla)
    {
        
        try {
            
            int fila = tabla.getSelectedRow();
            int id = (int)tabla.getValueAt(fila, 0);
            
            if(fila >= 0)
            {
                Connection con = bd.getConnection();
                PreparedStatement ps = null;

                String query = "UPDATE `producto` set `CANTIDAD`= CANTIDAD + ? WHERE ID_PRODUCTO = ?";

                ps = con.prepareStatement(query);
                ps.setInt(1, ingreso);
                ps.setInt(2, id);
                ps.execute();
                JOptionPane.showMessageDialog(null, "Entrada registrada con exito", "Aviso", 1);
            }else
            {
                  JOptionPane.showMessageDialog(null, "Debe de seleccionar una fila primero", "Aviso", 1);

            }
            
        } catch (SQLException ex) {
            Logger.getLogger(MovimientosDao.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
   
    public void salidaProducto(int salida, JTable tabla)
    {
        try {
             int fila = tabla.getSelectedRow();
            int id = (int)tabla.getValueAt(fila, 0);
            //JTable tab = new Movimientos().tblProductoMovimientos;
            if(fila >= 0)
            {
                if(cantidadStock(id) >= salida)
                {
                     Connection con = bd.getConnection();
                        PreparedStatement ps = null;

                        String query = "UPDATE `producto` set `CANTIDAD`= CANTIDAD - ? WHERE ID_PRODUCTO = ?";

                        ps = con.prepareStatement(query);
                        ps.setInt(1, salida);
                        ps.setInt(2, id);
                        ps.execute();
                        JOptionPane.showMessageDialog(null, "Salida registrada con exito", "Aviso", 1);

                }else
                {
                    JOptionPane.showMessageDialog(null, "No hay Stock suficiente", "Aviso", 1);
                }

            }else
            {
                     JOptionPane.showMessageDialog(null, "Debe de seleccionar una fila primero", "Aviso", 1);

            }
                
           
        } catch (SQLException ex) {
            Logger.getLogger(MovimientosDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<Movimiento> listaTablaMovimientos()
    {
        ArrayList<Movimiento> lista = new ArrayList();
        try {
            int id_producto = 0;
            int cantidad = 0;
            String nombre = null;
            String descripcion = null;
            double precio_vent = 0;
            
            Connection con = bd.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            String query = "SELECT ID_PRODUCTO, CANTIDAD, NOMBRE,  DESCRIPCION, PRECIO_VENTA FROM PRODUCTO";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next())
            {
                id_producto = rs.getInt(1);
                cantidad = rs.getInt(2);
                nombre = rs.getString(3);
                descripcion = rs.getString(4);
                precio_vent = rs.getDouble(5);
                Movimiento mov = new Movimiento(id_producto, cantidad, nombre, descripcion, precio_vent);
               lista.add(mov);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(MovimientosDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    
     public void listarTabla(JTable tabla, ArrayList<Movimiento> u)
    {
      DefaultTableModel modeloT = new DefaultTableModel(){
    public boolean isCellEditable(int rowIndex,int columnIndex){return false;}
    };
      
      //modeloT = (DefaultTableModel)tabla.getModel();
      tabla.setModel(modeloT);
      
      modeloT.addColumn("CODIGO");
      modeloT.addColumn("CANTIDAD");
      modeloT.addColumn("NOMBRE");
      modeloT.addColumn("DESCRIPCION");
      modeloT.addColumn("PRECIO");
      
      Object[] columna = new Object[5];
      
      int numeroRegistros = u.size();
     
      for(int x = 0; x < numeroRegistros; x++)
      {
          columna[0] = u.get(x).getId_producto();
          columna[1] = u.get(x).getCantidad();
          columna[2] = u.get(x).getNombre();
          columna[3] = u.get(x).getDescripcion();
          columna[4] = u.get(x).getPrecio_venta();
         
          modeloT.addRow(columna);
          
      }
        
    }
}
