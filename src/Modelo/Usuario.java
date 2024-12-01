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
public class Usuario {
    private int codigo;
    private String nombre;
    private String apellido;
    private String nickName;
    private String clave;
    private int nivel;
    public Usuario()
    {
        
    }

    public Usuario(int codigo, String nombre, String apellido, String nickName, String clave, int nivel) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.nickName = nickName;
        this.clave = clave;
        this.nivel = nivel;
    }

   

    public Usuario(String nombre, String apellido, String NickName, String Clave, int nivel) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.nickName = NickName;
        this.clave = Clave;
        this.nivel = nivel;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String NickName) {
        this.nickName = NickName;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String Clave) {
        this.clave = Clave;
    }
    
    
    
    
    
    
}
