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

    // el constructor para que el pedido siempre necesite un cliente al nacer
    public Pedido(Cliente cliente) {
        this.cliente = cliente;
    }

    public void crearPedidoMenu(Scanner leer, List<Producto> inventario) {

    System.out.println("\n--- Nuevo Pedido para: " + this.cliente.getNombre() + " ---");
    System.out.print("Ingrese el nombre del Producto: ");
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
        System.out.println("\nPresione Enter para continuar...");
        leer.nextLine();
        return; 
    }

    double totalGeneral = 0;
    for (LineaPedido lp : lineas) {
        double subtotal = lp.calcularSubtotal();    
        totalGeneral += subtotal;

        System.out.println("Producto: " + lp.producto.nombre + 
                        " | Cantidad: " + lp.cantidad + 
                        " | Subtotal (c/desc): $" + String.format("%.2f", subtotal));
    }

    System.out.println("TOTAL A PAGAR: $" + String.format("%.2f", totalGeneral));
    System.out.println("\nNOTA: Las Bebidas tienen un 5% y las Comidas un 15% de descuento.");

    // Lógica de repetición
    String respuesta;
    boolean opcionValida = false;

    do {
        System.out.print("\n¿Desea confirmar la venta? (S/N): ");
        respuesta = ConsolaUtils.leerTexto(leer);

        // Validamos que no sea un número
        if (respuesta.matches(".*[0-9].*")) {
            System.err.println("[!] Error: No se permiten números. Ingrese S o N.");
            continue; // Salta el resto del do y vuelve a preguntar
        }

        if (respuesta.equalsIgnoreCase("s")) {
            opcionValida = true; // Opción correcta, saldrá del bucle
            try {
                for (LineaPedido lp : lineas) {
                    ProductoService.descontarStock(lp.producto, lp.cantidad);
                }

                contadorVentas++;
                String registro = "Venta #" + contadorVentas + " | Cliente: " + cliente.getNombre() + 
                                  " | Items: " + lineas.size() + " | Total: $" + String.format("%.2f", totalGeneral);

                historialVentas.add(registro);
                lineas.clear(); 
                System.out.println("\n¡Venta Realizada con Éxito!");
                
            } catch (StockInsuficienteException e) {
                System.err.println("\n[ERROR CRÍTICO] La venta falló: " + e.getMessage());
                System.out.println("El pedido se mantendrá pendiente para revisión.");
            }
        } 
        else if (respuesta.equalsIgnoreCase("n")) {
            opcionValida = true; // Opción correcta, saldrá del bucle
            lineas.clear();
            System.out.println("Venta cancelada. Se ha vaciado el pedido.");
        } 
        else {
            System.out.println("[!] Opción no reconocida. Por favor, escriba 'S' para Sí o 'N' para No.");
        }

    } while (!opcionValida); 

    System.out.println("\nPresione Enter para continuar...");
    leer.nextLine();
}

    public void mostrarPedidosRealizados(Scanner leer) {
        System.out.println("\n========== HISTORIAL DE VENTAS ==========");
        if (historialVentas.isEmpty()) {
            System.out.println("No se han realizado ventas aún.");
        } else {
            for (String v : historialVentas) {
                System.out.println(v);
            }
            System.out.println("Total de ventas procesadas: " + contadorVentas);
            
        }
            System.out.println("\nPresione Enter para continuar...");
            leer.nextLine();
        System.out.println("=========================================");
    }
}