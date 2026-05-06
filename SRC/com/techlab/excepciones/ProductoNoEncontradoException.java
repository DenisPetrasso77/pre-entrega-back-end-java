package com.techlab.excepciones;

    public  class ProductoNoEncontradoException extends Exception {
        
        public ProductoNoEncontradoException(String mensaje) {
        super(mensaje); // Pasa el texto del error a la clase padre Exception
    }
}
