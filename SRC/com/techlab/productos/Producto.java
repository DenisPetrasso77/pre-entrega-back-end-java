package com.techlab.productos;

public class Producto {
    
        private static int contadorId = 1;
        public String categoria;
        public String nombre;
        public double precio;
        public int stock;
        public int id;

        // VARIABLE ESTÁTICA: pertenece a la clase, no a cada producto
        public static int contadorProductos = 0;

        // Getters
        public double getPrecio() {
            return this.precio;
        }

        public int getStock() {
            return this.stock;
        }

        // Setters con validación
        public void setStock(int cantidad) {

            if (cantidad >= 0) {
                this.stock = cantidad;
            } else {
                System.out.println("Error: No se puede asignar un stock negativo (" + cantidad + "). Se mantiene el valor actual.");
            }
        }

        public void setPrecio(double precio){

            if (precio >0) {
                this.precio = precio;
            } else {
                System.out.println("Error: No se puede asignar un valor negativo. Se mantiene el valor actual.");
            }
        }

        // Constructor
        public Producto( String nombre, Double precio, int stock, String categoria) {
            this.id = contadorId++;
            this.categoria = categoria;
            this.nombre = nombre;
            this.precio = precio;
            this.stock = stock;
            
            
            contadorProductos++;
        }

        //Reescribo el metodo ToString de fabrica para esta clase 
        @Override
        public String toString() {
        return "ID: " + id + "| Nombre: " + nombre + " | Precio: $" + precio + " |  Stock: " + stock + " | Categoria: " + categoria ;
        }
        
    } 

