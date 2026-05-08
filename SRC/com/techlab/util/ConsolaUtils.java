package com.techlab.util;

import java.util.Scanner;

public class ConsolaUtils {

public static String leerTexto(Scanner leer) { 
    String resultado;
    do {
        resultado = leer.nextLine();

        if (resultado.trim().isEmpty()) {
            // En lugar de un mensaje genérico, damos una instrucción clara
            System.out.println("[!] El campo anterior es obligatorio.");
            System.out.print("Por favor, ingréselo nuevamente: "); 
        }
    } while (resultado.trim().isEmpty());

    resultado = resultado.trim();
    return resultado.substring(0, 1).toUpperCase() + resultado.substring(1).toLowerCase();
}
}