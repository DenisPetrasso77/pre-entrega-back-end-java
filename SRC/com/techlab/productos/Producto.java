package com.techlab.productos;
import com.techlab.excepciones.StockInsuficienteException;

public class Producto {
        private static int contadorId = 1;
        public String nombre;
        public double precio;
        public int stock;
        public int id;

        // VARIABLE ESTÁTICA: pertenece a la clase, no a cada producto
        public static int contadorProductos = 0;

        // El Getter
         public double getPrecio() {
        return this.precio;
        }

        public int getStock() {
        return this.stock;
        }

        // El Setter con validación
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

        // constructor
        public Producto(String nombre, Double precio, int stock) {
            this.id = contadorId++;
            this.nombre = nombre;
            this.precio = precio;
            this.stock = stock;
            
            
            contadorProductos++;
        }

        public static double calcularDescuentoGeneral(double precioOriginal) {
            return precioOriginal * 0.90;
        }

        public double calcularPrecioFinal() { 
        // Ahora usamos las variables de la propia instancia (this)
        return this.precio * this.stock; 
        }

        public void mostrarInformacion() {
        System.out.println("Producto: " + nombre + " | Stock actual: " + stock);
        }

        // Metodo para vender con manejo de error
        public void realizarVenta(int cantidadAVender) throws StockInsuficienteException {
            
            // validación
            if (cantidadAVender > this.stock) {
                throw new StockInsuficienteException("Error: No hay stock suficiente de " + this.nombre + 
                ". Solicitado: " + cantidadAVender + 
                " | Disponible: " + this.stock);
            }
            // si hay stock vendo
            this.stock -= cantidadAVender;
            System.out.println("Venta exitosa: " + cantidadAVender + " unidad(es) de " + this.nombre);

            }
            //Re escribimo el metodo ToString de fabrica para esta clase 
                @Override
            public String toString() {
                return "ID: " + id + " | Nombre: " + nombre + " | Precio: $" + precio + " | Stock: " + stock;
        }
            
        public void actualizarDatos(String nuevoNombre, double nuevoPrecio, int nuevoStock) {
            this.nombre = nuevoNombre;
            this.setPrecio(nuevoPrecio);
            this.setStock(nuevoStock);
            System.out.println("Producto Actualizado: " + this.nombre);
        }
        
    } 

