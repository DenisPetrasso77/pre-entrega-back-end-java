package com.techlab.pedidos;
import com.techlab.clientes.Cliente;
import com.techlab.excepciones.StockInsuficienteException;
import com.techlab.productos.Producto;
import com.techlab.productos.ProductoService;
import com.techlab.util.ConsolaUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Pedido {


    private final Cliente cliente;
    public List<LineaPedido> lineas = new ArrayList<>(); //Traigo linea de pedido y armo un array
    private final List<String> historialVentas = new ArrayList<>(); //creo un ArrayList
    int contadorVentas = 0;

    // el constructor para que el pedido SIEMPRE necesite un cliente al nacer
    public Pedido(Cliente cliente) {
        this.cliente = cliente;
    }

    public void crearPedidoMenu(Scanner leer, List<Producto> inventario) {

    System.out.println("\n--- Nuevo Pedido para: " + this.cliente.getNombre() + " ---");
    System.out.print("Ingrese el nombre: ");
    String criterio = ConsolaUtils.leerTexto(leer);    
    Producto p = ProductoService.buscarProducto(inventario, criterio);

    if (p != null) {
        System.out.println("Producto encontrado: " + p.nombre + " (Stock: " + p.stock + ")");
        System.out.print("Cantidad a llevar: ");
        int cant = leer.nextInt();
        leer.nextLine();
        try {

            // Validamos el stock
            if (cant > p.getStock()) {
                // Lanzamos la excepción personalizada si falla algo
                throw new StockInsuficienteException("Error: Stock insuficiente. " +
                        "Disponible: " + p.getStock() + " | Solicitado: " + cant);
            }
            // Si no falla anda entra aca, hay stock. Procedemos:
            // 1. Empaquetamos el producto y la cantidad
            LineaPedido item = new LineaPedido(p, cant);

            // 2. Lo guardamos en la lista que declaraste arriba
            this.lineas.add(item);

            System.out.println("Producto: " + p.nombre + " Cantidad Pedida: " + cant);
            System.out.println("\nProducto añadido al pedido con éxito.");
            System.out.println("\nPresione Enter para continuar...");
            leer.nextLine();
            
        } catch (StockInsuficienteException e) {

            // Atrapamos el error  del throw y mostramos el mensaje específico
            System.err.println(e.getMessage());

        }
        } 
        else 
        {
        System.out.println("[X] Error: El producto no existe.");
        }
    }
    
    public void realizarVenta(Scanner leer) {
    System.out.println("\n========================================");
    System.out.println("           RESUMEN DEL PEDIDO           ");
    System.out.println("========================================");
    System.out.println("Cliente: " + this.cliente.getNombre());
    System.out.println("========================================");

    if (lineas.isEmpty()) {
        System.out.println("El pedido no tiene productos todavía.");
        return; // Salimos temprano si no hay nada que vender
    }
    // Aplico los decuentos
    double totalGeneral = 0;
    for (LineaPedido lp : lineas) {
        double subtotal = lp.calcularSubtotal();    
        totalGeneral += subtotal;
                // Imprimo el subtotal ya rebajado

        System.out.println("Producto: " + lp.producto.nombre + 
                        " | Cantidad: " + lp.cantidad + 
                        " | Subtotal (c/desc): $" + String.format("%.2f", subtotal));
    }
        System.out.println("TOTAL A PAGAR: $" + String.format("%.2f", totalGeneral));
        System.out.println("\nNOTA: Las Bebidas tienen un 5% y las Comidas un 15% de descuento (aplicado en subtotal)");
        System.out.print("\n¿Desea confirmar la venta? (S/N): ");
        String respuesta = ConsolaUtils.leerTexto(leer);

    if (respuesta.equalsIgnoreCase("s")) {
        try {
            // PROCESAMOS EL STOCK USANDO EL SERVICE
            for (LineaPedido lp : lineas) {
                // Llamamos al método estático del Service
                ProductoService.realizarVenta(lp.producto, lp.cantidad);
            }

            // Si llegamos acá, es porque todas las ventas fueron exitosas (no saltó el catch)
            contadorVentas++;
            String registro = "Venta #" + contadorVentas + " | Cliente: " + cliente.getNombre() + 
                              " | Items: " + lineas.size() + " | Total: $" + String.format("%.2f", totalGeneral);

            historialVentas.add(registro);
            lineas.clear(); // Limpiamos el carrito después del éxito

            System.out.println("\n¡Venta Realizada con Éxito!");
            
        } catch (StockInsuficienteException e) {
            // Si algún producto se quedó sin stock justo antes de confirmar
            System.err.println("\n[ERROR CRÍTICO] La venta falló: " + e.getMessage());
            System.out.println("El pedido se mantendrá pendiente para revisión.");
        }
    } else {
        lineas.clear();
        System.out.println("Venta cancelada. Se ha vaciado el pedido.");
    }

    System.out.println("\nPresione Enter para continuar...");
    leer.nextLine();
    System.out.println("========================================\n");
}

    public void mostrarPedidosRealizados() {
        System.out.println("\n========== HISTORIAL DE VENTAS ==========");
        if (historialVentas.isEmpty()) {
            System.out.println("No se han realizado ventas aún.");
        } else {
            for (String v : historialVentas) {
                System.out.println(v);
            }
            System.out.println("Total de ventas procesadas: " + contadorVentas);
        }
        System.out.println("=========================================");
    }
}