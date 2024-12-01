/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.CrudDao;

import Modelo.BDatos;
import Modelo.Mantenimiento.Cliente;
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
public class ProveedorDao {
    Connection con;
    PreparedStatement ps;
    ArrayList<Proveedor> lista;
    Statement st;
    ResultSet rs;
    
   
    
    public ProveedorDao()
    {
        lista = new ArrayList();
    }
    
    
    public void agregarProveedor(Proveedor us)
    {
        
        try {
            /* if(us.getNombre().length() < 0| us.getDescripcion().length() < 0|| us.getCantidad() <= 0 || us.getPrecio_compra()<= 0 || us.getPrecio_venta() <= 0)
        {
            JOptionPane.showMessageDialog(null, "Campos vacios", "Aviso", HEIGHT);
        }else{*/
            
        
    //String nombre, String descripcion, String categoria, String proveedor, double precio_venta,
    //double precio_compra, int cantidad, Date fecha)        
            String query = "INSERT INTO `proveedor`(`NOMBRE`, `TELEFONO`, `EMAIL`, `DIRECCION`) VALUES (?,?,?,?)";
                    
               
            con = new BDatos().getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, us.getNombre());
            ps.setString(2, us.getTelefono());
            ps.setString(3, us.getEmail());
            ps.setString(4,us.getDireccion());
            ps.execute();
           
            JOptionPane.showMessageDialog(null, "El Proveedor "+us.getNombre()+" fue registrado con exito", "Aviso", 1);
           // }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: "+ex, "Aviso", 2);
            Logger.getLogger(MantenimientoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
    
    
    public void eliminarProveedor(JTable tabla)
        {
        try {
            DefaultTableModel modelo =(DefaultTableModel)tabla.getModel();
            int fila = tabla.getSelectedRow();
            
            
            con = new BDatos().getConnection();
            
            String query = "delete from proveedor where id_proveedor = ?";
            if(fila == -1)
            {
                JOptionPane.showMessageDialog(null, "Debe de elegir un proveedor primero");
            }else{
                int indice = (int) tabla.getValueAt(fila, 0);
                int opcion = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar el proveedor seleccionado?", "Confirmar Eliminacion" ,JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
                if(opcion == 0)
                {
                    ps = con.prepareStatement(query);
                    ps.setInt(1, indice);
                    ps.execute();
                    JOptionPane.showMessageDialog(null, "Proveedor eliminado correctamente");
                    //modelo.removeRow(fila);
                }else if(opcion == 1 || opcion == -1)
                {

                }
            
            }
            
            
            } catch (SQLException ex) {
                Logger.getLogger(MantenimientoDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
    public ArrayList<Proveedor> listarProveedor()
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

            rs = st.executeQuery("SELECT `ID_PROVEEDOR`, `NOMBRE`, `TELEFONO`, `EMAIL`, `DIRECCION` FROM `proveedor`");
            while(rs.next())
            {
               id = rs.getInt(1);
               nombre = rs.getString(2);
               telefono = rs.getString(3);
               email = rs.getString(4);
               direccion = rs.getString(5);
               Proveedor client = new Proveedor(id, nombre, telefono, email, direccion);
               lista.add(client);
            }
                 
                 
            
        } catch (SQLException ex) {
            Logger.getLogger(MantenimientoDao.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error: "+ex, "Error", 2);
        }
        return lista;
    }
    
    
     public void listarTabla(JTable tabla, ArrayList<Proveedor> u)
    {
      DefaultTableModel modeloT = new DefaultTableModel(){
    public boolean isCellEditable(int rowIndex,int columnIndex){return false;}
    };
      
      //modeloT = (DefaultTableModel)tabla.getModel();
      tabla.setModel(modeloT);
      
      modeloT.addColumn("CODIGO");
      modeloT.addColumn("NOMBRE");
      modeloT.addColumn("EMAIL");
      modeloT.addColumn("DIRECCION");
      modeloT.addColumn("TELEFONO");
     
      
      Object[] columna = new Object[5];
      
      int numeroRegistros = u.size();
     
      for(int x = 0; x < numeroRegistros; x++)
      {
          columna[0] = u.get(x).getCodigo();
          columna[1] = u.get(x).getNombre();
          columna[2] = u.get(x).getEmail();
          columna[3] = u.get(x).getDireccion();
          columna[4] = u.get(x).getTelefono();
          
          modeloT.addRow(columna);
          
      }
        
    }
     
     public void editarUsuario(JTable tabla, JTextField codigo2, JTextField nick2, JComboBox<String> nivel2, JTextField apellido2, JPasswordField clave2, JTextField nombre2)
        {
            Usuario user = null;
        //try {
            DefaultTableModel modelo = new DefaultTableModel();
            tabla.setModel(modelo);
            int fila = tabla.getSelectedRow();
          
            
            con = new BDatos().getConnection();
            String query = "Select * from usuario where id = ?";
            //int opcion = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar el usuario seleccionado?", "Confirmar Eliminacion" ,JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
            if(fila == -1)
            {
                JOptionPane.showMessageDialog(null, "Debe de seleccionar un usuario de la lista primero");
            }else
            {
                String codigo = (String) tabla.getValueAt(fila, 0);
                codigo2.setText(codigo);
                String nombre = (String) tabla.getValueAt(fila, 1);
                nombre2.setText(nombre);
                String apellido = (String) tabla.getValueAt(fila, 2);
                apellido2.setText(apellido);
                String nickname = (String) tabla.getValueAt(fila, 3);
                nick2.setText(nickname);
                String clave = (String) tabla.getValueAt(fila, 4);
                clave2.setText(clave);
                int nivel = (int) tabla.getValueAt(fila, 5);
                nivel2.setSelectedIndex(nivel+1);
               /* ps = con.prepareStatement(query);
                ps.setInt(1, indice);
                rs =  ps.executeQuery();
                int id =0, nivel = 0;*/
                /*String nick = null, clave = null, nombre = null, apellido = null;
                while(rs.next())
                {
                    id =rs.getInt(1);
                    nick = rs.getString(2);
                    
                    clave = rs.getString(3);
                    
                    nombre = rs.getString(4);
                    
                    apellido = rs.getString(5);
                    nivel = rs.getInt(6);*/
                }
          //int codigo, String nombre, String apellido, String nickName, String clave, int nivel)
            /* codigo2.setText(String.valueOf(id));
             nick2.setText(nick);
             nivel2.setSelectedIndex(nivel+1);
             apellido2.setText(apellido);
             nombre2.setText(nombre);
             new EditarUsuario().setVisible(true);*/
            }
            
            
           /* } catch (SQLException ex) {
                Logger.getLogger(MantenimientoDao.class.getName()).log(Level.SEVERE, null, ex);
            }*/
        
        
    
     public void actualizarUsuario(Usuario user)
     {
        try {
             if(user.getNombre().equals("NOMBRE") || user.getApellido().equals("APELLIDO") || user.getNickName().equals("NOMBRE DE USUARIO") || user.getClave().equals("CLAVE"))
        {
            JOptionPane.showMessageDialog(null, "Campos vacios", "Aviso", HEIGHT);
        }else
             {
                 int opcion = JOptionPane.showConfirmDialog(null, "¿Seguro que desea editar el usuario seleccionado?", "Aviso", JOptionPane.YES_NO_OPTION,  JOptionPane.WARNING_MESSAGE);
                if(opcion == 0)
                {
                    con = new BDatos().getConnection();
                    String sql = "UPDATE usuarios set nickname = ?, clave = ?, nombre = ?, apellido = ?, nivel = ? where id = ?";
                    ps = con.prepareStatement(sql);
                    ps.setString(1, user.getNickName());
                    ps.setString(2, user.getClave());
                    ps.setString(3, user.getNombre());
                    ps.setString(4, user.getApellido());
                    ps.setInt(5, user.getNivel());
                    ps.setInt(6, user.getCodigo());
                    ps.execute();
                    JOptionPane.showMessageDialog(null, "Informacion del usuario actualizada");
                    }else
                    {

                    }
             }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(MantenimientoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
    
    
}
