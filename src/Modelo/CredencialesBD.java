/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Jhonas
 */
public class CredencialesBD {
    private String nombreUsuarioBD = "root";
    private String claveBD = "";
    private String URLBD = "jdbc:mysql://localhost:3306/inventario";
    private String DriverBD = "com.mysql.jdbc.Driver";

   

    
    
    
    public String getNombreUsuarioBD() {
        return nombreUsuarioBD;
    }

    public void setNombreUsuarioBD(String nombreUsuarioBD) {
        this.nombreUsuarioBD = nombreUsuarioBD;
    }

    public String getClaveBD() {
        return claveBD;
    }

    public void setClaveBD(String claveBD) {
        this.claveBD = claveBD;
    }

    public String getURLBD() {
        return URLBD;
    }

    public void setURLBD(String URLBD) {
        this.URLBD = URLBD;
    }

    public String getDriverBD() {
        return DriverBD;
    }

    public void setDriverBD(String DriverBD) {
        this.DriverBD = DriverBD;
    }
    
    
}
