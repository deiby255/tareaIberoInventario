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
public class Movimiento {
    
    
     /*ista.add(id_producto);
                lista.add(cantidad);
                lista.add(nombre);
                lista.add(descripcion);
                lista.add(precio_vent);*/
    
    private int id_producto;
    private int cantidad;
    private String nombre;
    private String descripcion;
    private double precio_venta;

    public Movimiento(int id_producto, int cantidad, String nombre, String descripcion, double precio_venta) {
        this.id_producto = id_producto;
        this.cantidad = cantidad;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio_venta = precio_venta;
    }
    
    public Movimiento(){}

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(double precio_venta) {
        this.precio_venta = precio_venta;
    }
    
    
    
    
    
    
}
