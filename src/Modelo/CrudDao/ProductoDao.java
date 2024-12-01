/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.CrudDao;

import Modelo.BDatos;
import Modelo.Mantenimiento.Producto;
import Modelo.MantenimientoDao;
import Modelo.Usuario;
import static java.awt.image.ImageObserver.HEIGHT;
import java.sql.Connection;
import java.sql.Date;
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
public class ProductoDao {
    
     Connection con;
    PreparedStatement ps;
    ArrayList<Producto> lista;
    Statement st;
    ResultSet rs;
    
   
    
    public ProductoDao()
    {
        lista = new ArrayList();
    }
    
    public int idProveedor(String proveedor)
    {
        int idProveedor = 0;
         try {
             con = new BDatos().getConnection();
             //String nombre, String descripcion, String categoria, String proveedor, double precio_venta,
             //double precio_compra, int cantidad, Date fecha)
             String query2 = "Select ID_PROVEEDOR from proveedor where nombre =?";
             ps = con.prepareStatement(query2);
             ps.setString(1, proveedor);
             rs = ps.executeQuery();
             while(rs.next())
             {
                 idProveedor = rs.getInt(1);
             }
         } catch (SQLException ex) {
             Logger.getLogger(ProductoDao.class.getName()).log(Level.SEVERE, null, ex);
         }
         return idProveedor;
    }
    
    public int idCategoria(String categoria)
    {
        int idCategoria = 0;
         try {
             con = new BDatos().getConnection();
             //String nombre, String descripcion, String categoria, String proveedor, double precio_venta,
             //double precio_compra, int cantidad, Date fecha)
             String query2 = "Select ID_categoria from categoria where nombre =?";
             ps = con.prepareStatement(query2);
             ps.setString(1, categoria);
             rs = ps.executeQuery();
             while(rs.next())
             {
                 idCategoria = rs.getInt(1);
             }
         } catch (SQLException ex) {
             Logger.getLogger(ProductoDao.class.getName()).log(Level.SEVERE, null, ex);
         }
         return idCategoria;
    }
    
    public void agregarUsuario(Producto us)
    {
        
        try {
           if(us.getNombre() == null| us.getDescripcion() == null|| us.getCantidad() <= 0 || us.getPrecio_compra()<= 0 || us.getPrecio_venta() <= 0)
        {
            JOptionPane.showMessageDialog(null, "Campos vacios", "Aviso", HEIGHT);
        }else{
            
        
            String query = "Insert into Producto (NOMBRE, DESCRIPCION,ID_CATEGORIA, ID_PROVEEDOR, PRECIO_VENTA, PRECIO_COMPRA, CANTIDAD, FECHA) values(?,?,?,?,?,?,?,?)";
            int idprov = idProveedor(us.getProveedor());        
            int idcate = idCategoria(us.getCategoria());   
            con = new BDatos().getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, us.getNombre());
            ps.setString(2, us.getDescripcion());
            ps.setInt(3, idcate);
            ps.setInt(4, idprov);
            ps.setDouble(5, us.getPrecio_venta());
            ps.setDouble(6, us.getPrecio_compra());
            ps.setInt(7, us.getCantidad());
            ps.setDate(8, us.getFecha());
            ps.execute();
           
            JOptionPane.showMessageDialog(null, "El producto "+us.getNombre()+" fue registrado con exito", "Aviso", 1);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: "+ex, "Aviso", 2);
            Logger.getLogger(MantenimientoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
    
    
    public void eliminarProducto(JTable tabla)
        {
        try {
            DefaultTableModel modelo =(DefaultTableModel)tabla.getModel();
            int fila = tabla.getSelectedRow();
            
            
            con = new BDatos().getConnection();
            
            String query = "delete from producto where id_producto = ?";
            if(fila == -1)
            {
                JOptionPane.showMessageDialog(null, "Debe de elegir un producto primero");
            }else{
                int indice = (int) tabla.getValueAt(fila, 0);
                int opcion = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar el producto seleccionado?", "Confirmar Eliminacion" ,JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
                if(opcion == 0)
                {
                    ps = con.prepareStatement(query);
                    ps.setInt(1, indice);
                    ps.execute();
                    JOptionPane.showMessageDialog(null, "Usuario eliminado correctamente");
                    //modelo.removeRow(fila);
                }else if(opcion == 1 || opcion == -1)
                {

                }
            
            }
            
            
            } catch (SQLException ex) {
                Logger.getLogger(MantenimientoDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
    public ArrayList<Producto> listarProducto()
    {
       // = new ArrayList();
        int id = 0;
        String nombre = null;
        String descripcion = null;
        String categoria = null;
        String proveedor = null;
        double precio_venta = 0;
        double precio_compra = 0;
        int cantidad = 0;
        Date fecha = null;
        try {
            con = new BDatos().getConnection();
            st = con.createStatement();
 //(int codigo, String nombre, String descripcion, String categoria, String proveedor,
// double precio_venta, double precio_compra, int cantidad, Date fecha)
            rs = st.executeQuery("select producto.ID_PRODUCTO as codigo, producto.NOMBRE, producto.DESCRIPCION, categoria.NOMBRE as CATEGORIA, proveedor.NOMBRE as proveedor, producto.PRECIO_COMPRA, producto.PRECIO_venta, producto.CANTIDAD,producto.FECHA"
                    + " from producto inner join proveedor on producto.ID_PROVEEDOR = proveedor.ID_PROVEEDOR inner join categoria"
                    + " on categoria.ID_CATEGORIA = producto.ID_CATEGORIA");
            while(rs.next())
            {
                id = rs.getInt(1);
                nombre = rs.getString(2);
                descripcion = rs.getString(3);
                categoria = rs.getString(4);
                proveedor = rs.getString(5);
                precio_venta = rs.getDouble(7);
                precio_compra = rs.getDouble(6);
                cantidad = rs.getInt(8);
                fecha = rs.getDate(9);
          Producto prod = new Producto(id, nombre, descripcion,
          categoria, proveedor, precio_venta, precio_compra,
          cantidad, fecha);    
                lista.add(prod);
            }
                 
                 
            
        } catch (SQLException ex) {
            Logger.getLogger(MantenimientoDao.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error: "+ex, "Error", 2);
        }
        return lista;
    }
    
    
     public void listarTabla(JTable tabla, ArrayList<Producto> u)
    {
      DefaultTableModel modeloT = new DefaultTableModel(){
    public boolean isCellEditable(int rowIndex,int columnIndex){return false;}
    };
      
      //modeloT = (DefaultTableModel)tabla.getModel();
      tabla.setModel(modeloT);
      
      modeloT.addColumn("CODIGO");
      modeloT.addColumn("NOMBRE");
      modeloT.addColumn("DESCRIPCION");
      modeloT.addColumn("CATEGORIA");
      modeloT.addColumn("PROVEEDOR");
      modeloT.addColumn("PRECIO DE COMPRA");
      
      modeloT.addColumn("PRECIO DE VENTA");
      modeloT.addColumn("STOCK");
      modeloT.addColumn("FECHA");
      
      Object[] columna = new Object[9];
      
      int numeroRegistros = u.size();
     
      for(int x = 0; x < numeroRegistros; x++)
      {
          columna[0] = u.get(x).getCodigo();
          columna[1] = u.get(x).getNombre();
          columna[2] = u.get(x).getDescripcion();
          columna[3] = u.get(x).getCategoria();
          columna[4] = u.get(x).getProveedor();
          columna[5] = "RD$"+u.get(x).getPrecio_compra();
          columna[6] = "RD$"+u.get(x).getPrecio_venta();
          columna[7] =u.get(x).getCantidad();
          columna[8] = u.get(x).getFecha();
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
     
     public ArrayList<String> comboProveedor()
     {
         ArrayList<String> provCombo = new ArrayList();
         int id = 0;
         String nombre = null;
         try {
            con = new BDatos().getConnection();
            st = con.createStatement();
 //(int codigo, String nombre, String descripcion, String categoria, String proveedor,
// double precio_venta, double precio_compra, int cantidad, Date fecha)
            rs = st.executeQuery("SELECT `ID_PROVEEDOR`, `NOMBRE` FROM `proveedor`");
            while(rs.next())
            {
                
              id = rs.getInt(1);
              nombre = rs.getString(2);
                provCombo.add(nombre);
            }
                 
                 
            
        } catch (SQLException ex) {
            Logger.getLogger(MantenimientoDao.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error: "+ex, "Error", 2);
        }
         return provCombo;
     }
     
      public ArrayList<String> comboCategoria()
     {
         ArrayList<String> provCombo = new ArrayList();
         int id = 0;
         String nombre = null;
         try {
            con = new BDatos().getConnection();
            st = con.createStatement();
 //(int codigo, String nombre, String descripcion, String categoria, String proveedor,
// double precio_venta, double precio_compra, int cantidad, Date fecha)
            rs = st.executeQuery("SELECT *FROM `categoria`");
            while(rs.next())
            {
                
              id = rs.getInt(1);
              nombre = rs.getString(2);
                provCombo.add(nombre);
            }
                 
                 
            
        } catch (SQLException ex) {
            Logger.getLogger(MantenimientoDao.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error: "+ex, "Error", 2);
        }
         return provCombo;
     }
    
}
