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
    double precioBase = this.producto.precio;
    
    // Aplicamos los descuentos aquí mismo para que el objeto sea "inteligente"
    if (this.producto.categoria.equals("Bebida")) {
        precioBase *= 0.95; // 5% descuento
    } else if (this.producto.categoria.equals("Comida")) {
        precioBase *= 0.85; // 15% descuento
    }

    return precioBase * this.cantidad;
}
}