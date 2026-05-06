package com.techlab.excepciones;

// Heredamos de Exception para que Java nos obligue a manejarla (Checked Exception)
public class StockInsuficienteException extends Exception {
    
    public StockInsuficienteException(String mensaje) {
        super(mensaje);
    }
}