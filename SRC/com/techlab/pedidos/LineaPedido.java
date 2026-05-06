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
    // Ahora LineaPedido usa la lógica interna del producto
    return this.producto.calcularPrecioFinal() * this.cantidad;

    }
}