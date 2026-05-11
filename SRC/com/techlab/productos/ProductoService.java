package com.techlab.productos;

import com.techlab.excepciones.StockInsuficienteException;
import com.techlab.util.ConsolaUtils;
import java.util.List;
import java.util.Scanner;

public class ProductoService {

    // Agregar producto
    public static void agregarProducto(Scanner leer, List<Producto> lista) {
    System.out.println("\n--- Agregar Nuevo Producto ---");

    // 1. Nombre
    System.out.print("Nombre: ");
    String nom = ConsolaUtils.leerTexto(leer);

    // 2. Precio
    System.out.print("Precio: ");
    while (!leer.hasNextDouble()) {
        System.err.print("Error: Ingrese un número: ");
        leer.nextLine();
    }
    double pre = leer.nextDouble();

    // 3. Stock
    System.out.print("Stock inicial: ");
    while (!leer.hasNextInt()) {
        System.err.print("Error: Ingrese un entero: ");
        leer.nextLine();
    }
    int st = leer.nextInt();
    leer.nextLine(); // Limpieza de buffer

    // 4. Menú de Categorías
    System.out.println("\nSeleccione la Categoría:");
    System.out.println("1) Bebida\n2) Comida\n3) Limpieza\n4) Perfumería\n5) Otros");
    System.out.print("Opción: ");
    
    String opcion = ConsolaUtils.leerTexto(leer);
    String cat;

    switch (opcion) {
        case "1" -> cat = "Bebida";
        case "2" -> cat = "Comida";
        case "3" -> cat = "Limpieza";
        case "4" -> cat = "Perfumería";
        default  -> cat = "Otros";
    }


    lista.add(new Producto(nom, pre, st,cat)); 

    System.out.println("Producto '" + nom + "' agregado con éxito como " + cat + ".");
}
    // Ver Prodcutos cargador
    public static void listarProductos(Scanner leer, List<Producto> listaProducto) {
    System.out.println("\n========== INVENTARIO DISPONIBLE ==========");
        
    for (Producto p : listaProducto) {
        // En lugar de escribir toda la tira de texto, llamamos al objeto
        // Java llama automáticamente al toString() cuando hacés esto:
        System.out.println(p); 
    }

    System.out.println("\nPresione Enter para continuar...");
    leer.nextLine();
}
    //Buscar y actualizar productos
    public static void buscarActualizarProducto(Scanner leer, List<Producto> lista) {
    System.out.print("Nombre del producto a buscar: ");
    String criterio = ConsolaUtils.leerTexto(leer);

    Producto encontrado = buscarProducto(lista, criterio);

    if (encontrado != null) {
        System.out.println("\n[!] Producto localizado:");
        System.out.println(encontrado);

        System.out.println("\n¿Qué desea actualizar?");
        System.out.println("1. Precio");
        System.out.println("2. Stock");
        System.out.println("3. Cancelar");
        System.out.print("Elija una opción: ");
        
        String opcion = ConsolaUtils.leerTexto(leer);

        switch (opcion) {
            case "1":
                System.out.print("Nuevo Precio: ");
                while (!leer.hasNextDouble()) {
                    System.out.println("Error: Ingrese un precio válido.");
                    leer.nextLine();
                }
                double nuevoPre = leer.nextDouble();
                leer.nextLine();
                encontrado.setPrecio(nuevoPre); // Usamos el setter
                System.out.println("Precio actualizado con éxito.");
                break;

            case "2":
                System.out.print("Nuevo Stock: ");
                while (!leer.hasNextInt()) {
                    System.out.println("Error: Ingrese un número entero.");
                    leer.nextLine();
                }
                int nuevoSt = leer.nextInt();
                leer.nextLine();
                
                if (nuevoSt >= 0) {
                    encontrado.setStock(nuevoSt); // Usamos el setter
                    System.out.println("Stock actualizado con éxito.");
                } else {
                    System.out.println("Error: El stock no puede ser negativo.");
                }
                break;

            case "3":
                System.out.println("Operación cancelada.");
                break;

            default:
                System.out.println("Opción no válida.");
                break;
        }
    } else {
        System.out.println("\nError: No se encontró el producto.");
        System.out.println("\nPresione Enter para continuar...");
        leer.nextLine();
    }
}
    //Solo buscar Prodcutos
    public static Producto buscarProducto(List<Producto> lista, String criterio) {
    for (Producto p : lista) {
        if (p.nombre.equalsIgnoreCase(criterio)) {
            return p;
        }
    }
    return null;
}
    //Eliminar Productos
    public static void eliminarProducto(List<Producto> listaProducto, Scanner leer) {

    System.out.print("Ingrese nombre del producto a eliminar: ");
    String producto = ConsolaUtils.leerTexto(leer);
                        
    for (int i = 0; i < listaProducto.size(); i++) {
        Producto p = listaProducto.get(i);

        if (p.nombre.equalsIgnoreCase(producto)) {
            System.err.println("¿Está seguro que desea eliminar este producto? (S/N)");
            String respuesta = ConsolaUtils.leerTexto(leer);

            if (respuesta.equalsIgnoreCase("s")) {

            listaProducto.remove(i); 
            System.out.println("\nOperación exitosa: El producto ha sido eliminado.");
            System.out.println("\nPresione Enter para continuar...");
            leer.nextLine();
            return ;
            }

        }
    }  
    System.out.println("\nError: No se encontró ningún producto con ese criterio.");
    System.out.println("\nPresione Enter para continuar...");
    leer.nextLine();
   }
    //Vender los pedidos Soplicitados   
    public static void descontarStock(Producto p, int descontarStock) throws StockInsuficienteException {
            
    // validación
    if (descontarStock > p.getStock()) {
    throw new StockInsuficienteException("Error: No hay stock suficiente de " + p.nombre + 
    ". Solicitado: " + descontarStock + 
                " | Disponible: " + p.getStock());
    }
    // si hay stock vendo
    int stockActual = p.getStock();
    p.setStock(stockActual - descontarStock);
    System.out.println("Venta exitosa: " + descontarStock + " unidad(es) de " + p.nombre);
    }
        
}

