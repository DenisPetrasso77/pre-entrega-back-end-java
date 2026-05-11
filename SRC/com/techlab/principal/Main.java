package com.techlab.principal;
import com.techlab.clientes.Cliente;
import com.techlab.pedidos.Pedido;
import com.techlab.productos.*;
import com.techlab.util.ConsolaUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {

        // instanciamos los objetos a usar
        Scanner leer = new Scanner(System.in);
        leer.useLocale(Locale.US); 
        List<Producto> inventario = new ArrayList<>();
        Cliente clienteDefault = new Cliente("Miguel", "Miguel@techlab.com"); //cliente hardcodeado
        Pedido miPedido = new Pedido(clienteDefault); 
        int opcion = 0; //variable que usa el switch
        System.out.println("\n");


        do {
            System.out.println("\n================ Bienvenido al SISTEMA DE GESTIÓN ================");
            System.out.println("1) Agregar producto");
            System.out.println("2) Listar productos");
            System.out.println("3) Buscar/Actualizar producto");
            System.out.println("4) Eliminar producto");
            System.out.println("5) Crear un pedido");
            System.out.println("6) Realizar Ventas");
            System.out.println("7) Historial de Pedidos");
            System.out.println("8) Salir");
            System.out.print("\nElija una opción: ");
       
            //Manejo de errores
            try {
                String entrada = ConsolaUtils.leerTexto(leer);

                opcion = Integer.parseInt(entrada);
                } 
            catch (NumberFormatException e) { 
                
                System.out.println("\nError: no es un número válido.");
                System.out.println("Por favor, elija una opción del 1 al 8.");
                opcion = 0;
            }

            switch (opcion) { 

                case 1:
                    ProductoService.agregarProducto(leer, inventario);
                    break;                  
                case 2:
                    ProductoService.listarProductos(leer,inventario);
                    break;
                case 3:
                    ProductoService.buscarActualizarProducto(leer, inventario);
                    break;
                case 4:
                    ProductoService.eliminarProducto(inventario, leer);
                        break;
                case 5:
                    miPedido.crearPedidoMenu(leer, inventario);
                    break;
                case 6:
                    miPedido.realizarVenta(leer);
                    break;
                case 7:
                    miPedido.mostrarPedidosRealizados(ler);
                    break;
                  case 8:
                    System.out.println("Saliendo del sistema... ¡Chau!");
                    break;
                default:
                    System.out.println("Opción no válida.");
                    System.out.println("\nPresione Enter para continuar...");
                    leer.nextLine();                  
            }
        } while (opcion != 8); // Repite mientras no elija 7

    }    
} 





