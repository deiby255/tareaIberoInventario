/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.CrudDao;

import Modelo.BDatos;
import Modelo.Mantenimiento.Categoria;
import Modelo.Mantenimiento.Proveedor;
import Modelo.MantenimientoDao;
import Modelo.Usuario;
import static java.awt.image.ImageObserver.HEIGHT;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jhonas
 */
public class CategoriaDao {
    
    Connection con;
    PreparedStatement ps;
    ArrayList<Categoria> lista;
    Statement st;
    ResultSet rs;
    
   
    
    public CategoriaDao()
    {
        lista = new ArrayList();
    }
    
    
    public void agregarCategoria(Categoria us)
    {
        
        try {
            /* if(us.getNombre().length() < 0| us.getDescripcion().length() < 0|| us.getCantidad() <= 0 || us.getPrecio_compra()<= 0 || us.getPrecio_venta() <= 0)
        {
            JOptionPane.showMessageDialog(null, "Campos vacios", "Aviso", HEIGHT);
        }else{*/
            
        
    //String nombre, String descripcion, String categoria, String proveedor, double precio_venta,
    //double precio_compra, int cantidad, Date fecha)        
            String query = "INSERT INTO `categoria`(`NOMBRE`) VALUES (?)";
                    
               
            con = new BDatos().getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, us.getNombre());
            
            ps.execute();
           
            JOptionPane.showMessageDialog(null, "La CATEGORIA "+us.getNombre()+" fue agregada con exito", "Aviso", 1);
           // }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: "+ex, "Aviso", 2);
            Logger.getLogger(MantenimientoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
    
    
    public void eliminarCategoria(JTable tabla)
        {
        try {
            DefaultTableModel modelo =(DefaultTableModel)tabla.getModel();
            int fila = tabla.getSelectedRow();
            
            
            con = new BDatos().getConnection();
            
            String query = "delete from categoria where id_categoria = ?";
            if(fila == -1)
            {
                JOptionPane.showMessageDialog(null, "Debe de elegir un proveedor primero");
            }else{
                int indice = (int) tabla.getValueAt(fila, 0);
                int opcion = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar la categoria seleccionada?", "Confirmar Eliminacion" ,JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
                if(opcion == 0)
                {
                    ps = con.prepareStatement(query);
                    ps.setInt(1, indice);
                    ps.execute();
                    JOptionPane.showMessageDialog(null, "Categoria eliminada correctamente");
                    //modelo.removeRow(fila);
                }else if(opcion == 1 || opcion == -1)
                {

                }
            
            }
            
            
            } catch (SQLException ex) {
                Logger.getLogger(MantenimientoDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
    public ArrayList<Categoria> listarCategoria()
    {
       // = new ArrayList();
        int id = 0;
        String nombre = null;
        String telefono = null;
        String email = null;
        String direccion = null;
        
//Cliente(int codigo, String nombre, String telefono, String email, String direccion        
        try {
            con = new BDatos().getConnection();
            st = con.createStatement();

            rs = st.executeQuery("SELECT *FROM CATEGORIA");
            while(rs.next())
            {
               id = rs.getInt(1);
               nombre = rs.getString(2);
               
               Categoria client = new Categoria(id, nombre);
               lista.add(client);
            }
                 
                 
            
        } catch (SQLException ex) {
            Logger.getLogger(MantenimientoDao.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error: "+ex, "Error", 2);
        }
        return lista;
    }
    
    
     public void listarTabla(JTable tabla, ArrayList<Categoria> u)
    {
      DefaultTableModel modeloT = new DefaultTableModel(){
    public boolean isCellEditable(int rowIndex,int columnIndex){return false;}
    };
      
      //modeloT = (DefaultTableModel)tabla.getModel();
      tabla.setModel(modeloT);
      
      modeloT.addColumn("#");
      modeloT.addColumn("NOMBRE");
      
      
      Object[] columna = new Object[2];
      
      int numeroRegistros = u.size();
     
      for(int x = 0; x < numeroRegistros; x++)
      {
          columna[0] = u.get(x).getCodigo();
          columna[1] = u.get(x).getNombre();
          
          
          modeloT.addRow(columna);
          
      }
        
    }
     
     
    
}
