package com.techlab.productos;

import java.util.List;
import java.util.Scanner;

public class ProductoService {

    // Agregar producto
    public static void agregarProducto(Scanner leer, List<Producto> lista) {

    System.out.println("\n--- Agregar Nuevo Producto ---");

    String categoria = "";
    boolean categoriaValida = false;

    // VALIDACIÓN INICIAL: No lo dejamos pasar de acá si no es B o C
    while (!categoriaValida) {
        System.out.print("¿Es una Bebida o una Comida? (B/C): ");
        categoria = leer.nextLine().toUpperCase();
        
        if (categoria.equals("B") || categoria.equals("C")) {
            categoriaValida = true;
        } else {
            System.err.println("Error: Categoría no válida. Ingrese 'B' para Bebida o 'C' para Comida.");
            System.out.println("\nPresione Enter para continuar...");
            leer.nextLine();
        }
    }

    System.out.print("Nombre: ");
    String nom = leer.nextLine();

    System.out.print("Precio: ");
        while(!leer.hasNextDouble()) {
            System.err.println("Error: El precio debe ser un numero");
            System.out.print("Reintente el Precio: ");
            leer.nextLine();
        }
    double pre = leer.nextDouble();
    leer.nextLine();

    System.out.print("Stock inicial: ");

    // chequeaamos el número
    while (!leer.hasNextInt()) {
        System.err.println("Error: El Stock debe ser un número entero.");
        System.out.print("Reintente el Stock: ");
        leer.nextLine(); 
    }
    // variable temporal para chequear el valor
    int st = leer.nextInt();

    // Si el número es negativo
    while (st < 0) {
        System.err.println("Error: El Stock no puede ser menor a cero.");
        System.out.print("Ingrese un Stock válido (0 o más): ");
        
        // Repetimos la lógica de validación de entero por si ahora pone letras
        while (!leer.hasNextInt()) {
            System.err.println("Error: Debe ser un número entero.");
            leer.nextLine();
        }
        st = leer.nextInt();
    }

    leer.nextLine(); // Limpieza final

    //Validamos los atributos de cada uno
   if (categoria.equals("B")) {

    System.out.print("Volumen en litros (ej: 0.5): ");
    
    // VALIDACIÓN: Mientras lo que escriba NO sea un número (double)
    while (!leer.hasNextDouble()) {
        System.err.println("Error: El volumen debe ser un número (ej: 0.5).");
        System.out.print("Reintente el Volumen: ");
        leer.nextLine(); // Limpiamos el buffer para que pueda intentar de nuevo
    }
    
    double vol = leer.nextDouble();
    leer.nextLine(); // Limpieza necesaria después de nextDouble()

    // Si además querés validar que no sea negativo, podés sumar este if:
    if (vol <= 0) {
        System.out.println("Cuidado: El volumen debe ser mayor a 0. Se asignará 0.1L por defecto.");
        vol = 0.1;
    }
    
    // Ahora sí, agregamos con el dato seguro
    lista.add(new Bebida(nom, pre, st, vol));
    System.out.println("Bebida '" + nom + "' agregada con éxito.");

} else if (categoria.equals("C")) {
    
      System.out.println("¿El producto tiene Fecha de Vencimiento? (s/n): ");
        String respuesta = leer.nextLine();

        // Comparamos si la respuesta es "s" (ignorando mayúsculas)
        if (respuesta.equalsIgnoreCase("s")) {
            System.out.print("Fecha de vencimiento (dd/mm/aaaa): ");
            String fecha = leer.nextLine();

            while (!fecha.matches("[0-9/]+")) {
                System.out.print("Error. Ingrese solo números y barras: ");
                fecha = leer.nextLine();
            }
            
            // Si tiene fecha, creamos la Comida con esa fecha
            lista.add(new Comida(nom, pre, st, fecha));
            System.out.println("Comida '" + nom + "' agregada con éxito.");

        } else {
            // Si el usuario pone "n" o cualquier otra cosa, "sigue de largo" por aquí
            // Aquí puedes agregar el producto con una fecha por defecto o como producto base
            lista.add(new Comida(nom, pre, st, "Sin fecha")); 
            System.out.println("Producto agregado sin fecha de vencimiento.");
        }
    }
}
    
   public static void listarProductos(Scanner leer, List<Producto> listaProducto) {
    System.out.println("\n========== INVENTARIO DISPONIBLE ==========");
        
    for (Producto p : listaProducto) {
        // 1. Empezamos con los datos comunes que tienen todos
        String info = "ID: " + p.id + " | Nombre: " + p.nombre + " | Precio: $" + p.precio + " | Stock: " + p.stock;

        // 2. Verificamos si es Bebida para sumar el volumen
        if (p instanceof Bebida) {
            Bebida b = (Bebida) p; // Casting a Bebida
            info += " | Volumen: " + b.getVol() + "L";
        } 
        // 3. Verificamos si es Comida para sumar la fecha
        else if (p instanceof Comida) {
            Comida c = (Comida) p; // Casting a Comida
            info += " | Vencimiento: " + c.getFechaVencimiento();
        }

        // 4. Imprimimos la línea completa
        System.out.println(info);
    }

    System.out.println("\nPresione Enter para continuar...");
    leer.nextLine();
}

public static void buscarActualizarProducto(Scanner leer, List<Producto> lista) {
    System.out.print("Nombre del producto a buscar: ");
    String criterio = leer.nextLine();

    Producto encontrado = buscarProducto(lista, criterio);

    if (encontrado != null) {
        System.out.println("\n[!] Producto localizado:");
        System.out.println(encontrado);

        System.out.println("\n¿Qué desea actualizar?");
        System.out.println("1. Precio");
        System.out.println("2. Stock");
        System.out.println("3. Cancelar");
        System.out.print("Elija una opción: ");
        
        String opcion = leer.nextLine();

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

public static Producto buscarProducto(List<Producto> lista, String criterio) {
    for (Producto p : lista) {
        if (p.nombre.equalsIgnoreCase(criterio)) {
            return p;
        }
    }
    return null;
}

   public static void eliminarProducto(List<Producto> listaProducto, Scanner leer) {

    System.out.print("Ingrese nombre del producto a eliminar: ");
    String producto = leer.nextLine();
                        
    for (int i = 0; i < listaProducto.size(); i++) {
        Producto p = listaProducto.get(i);

        if (p.nombre.equalsIgnoreCase(producto)) {
            System.err.println("¿Está seguro que desea eliminar este producto? (S/N)");
            String respuesta = leer.nextLine();

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
}

