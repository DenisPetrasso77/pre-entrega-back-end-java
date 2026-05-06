package com.techlab.pedidos;
import com.techlab.clientes.Cliente;
import com.techlab.excepciones.StockInsuficienteException;
import com.techlab.productos.Producto;
import com.techlab.productos.ProductoService;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Pedido {


    private Cliente cliente;
    public List<LineaPedido> lineas = new ArrayList<>(); //Traigo linea de pedido y armo un array
    private final List<String> historialVentas = new ArrayList<>(); //creo un ArrayList
    int contadorVentas = 1;

    // el constructor para que el pedido SIEMPRE necesite un cliente al nacer
    public Pedido(Cliente cliente) {
        this.cliente = cliente;
    }

    public void agregarAlPedido(Producto p, int cant) {

        if (p.getStock() >= cant) {
            lineas.add(new LineaPedido(p, cant));

            p.setStock(p.getStock() - cant);

            System.out.println("Producto agregado al pedido.");
            
        } else {
            System.out.println("No hay stock suficiente.");
        }
    }

public void crearPedidoMenu(Scanner leer, List<Producto> inventario) {

    System.out.println("\n--- Nuevo Pedido para: " + this.cliente.getNombre() + " ---");
    System.out.print("Ingrese el nombre: ");
    String criterio = leer.nextLine();
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
            this.agregarAlPedido(p, cant); 
            System.out.println("Producto añadido al pedido con éxito.");
            
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
    // USAMOS EL OBJETO CLIENTE AQUÍ:
    System.out.println("Cliente: " + this.cliente.getNombre()); 
    System.out.println("========================================");

    if (lineas.isEmpty()) {
        System.out.println("El pedido no tiene productos todavía.");
    } else {
        double totalGeneral = 0;

        for (LineaPedido lp : lineas) {
            // Mostramos los datos de la línea
            System.out.println("Producto: " + lp.producto.nombre);
            System.out.println("Cantidad: " + lp.cantidad + " | Subtotal: $" + lp.calcularSubtotal());
            System.out.println("----------------------------------------");
            
            // Vamos sumando al total
            totalGeneral += lp.calcularSubtotal();
        }

            System.out.println("TOTAL A PAGAR: $" + totalGeneral);
            System.out.println("Desea realizar la venta? (S/N)");
            String respuesta = leer.nextLine();
            if (respuesta.equals("s")) {

            // HISTORIAL           
            String registro = "Venta #" + contadorVentas + " | Cliente: " + cliente.getNombre() + 
                    " | Items: " + lineas.size() + " | Total: $" + totalGeneral;
            historialVentas.add(registro);

            lineas.clear();

            System.out.println("Venta Realizada con Éxito!");
            System.out.println("\nPresione Enter para continuar...");
            leer.nextLine();

            contadorVentas ++;

            }
    }
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