/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Mantenimiento;

import java.sql.Date;

/**
 *
 * @author Jhonas
 */
public class Producto {
    private int codigo;
    private String nombre;
    private String descripcion;
    private String categoria;
    private String proveedor;
    private double precio_venta;
    private double precio_compra;
    private int cantidad;
    private Date fecha;

    public Producto(int codigo, String nombre, String descripcion, String categoria, String proveedor, double precio_venta, double precio_compra, int cantidad, Date fecha) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.proveedor = proveedor;
        this.precio_venta = precio_venta;
        this.precio_compra = precio_compra;
        this.cantidad = cantidad;
        this.fecha = fecha;
    }

    public Producto(String nombre, String descripcion, String categoria, String proveedor, double precio_venta, double precio_compra, int cantidad, Date fecha) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.proveedor = proveedor;
        this.precio_venta = precio_venta;
        this.precio_compra = precio_compra;
        this.cantidad = cantidad;
        this.fecha = fecha;
    }

    public Producto() {
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public double getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(double precio_venta) {
        this.precio_venta = precio_venta;
    }

    public double getPrecio_compra() {
        return precio_compra;
    }

    public void setPrecio_compra(double precio_compra) {
        this.precio_compra = precio_compra;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    
    
}
