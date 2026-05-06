package com.techlab.pedidos;
import com.techlab.productos.Producto; 

// UN TICKET CON PRODUCTO Y CANTIDAD
public class LineaPedido {

    public Producto producto;
    public int cantidad;

    // El nombre acá tiene que ser IDÉNTICO al de la clase arriba
    public LineaPedido(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public double calcularSubtotal() {
        // Usamos .precio porque en tu Producto es público
        return this.producto.precio * this.cantidad;
    }
}