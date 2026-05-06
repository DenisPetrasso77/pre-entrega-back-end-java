package com.techlab.productos;


// --- CLASE COMIDA ---
    public  class Comida extends Producto { 
    public String fechaVencimiento; 
 
    public Comida(String nombre, double precio, int cantidadEnStock, 
    String fechaVencimiento) { 
       super(nombre, precio, cantidadEnStock); 
       this.fechaVencimiento = fechaVencimiento; 
   }
    public String getFechaVencimiento() {
            return this.fechaVencimiento;
        }

    public void setFecha(String fechaVencimiento) {
    // Aquí podés meter el .matches que tenías antes
    if (fechaVencimiento.matches("[0-9/]+")) {
        this.fechaVencimiento = fechaVencimiento;
    } else {
        System.out.println("Error: Formato de fecha incorrecto.");
        this.fechaVencimiento = "00/00/0000";
    }
}
   
   
    // Dentro de la clase Comida
    @Override 
    public double calcularPrecioFinal() { 
    // Aplicamos el 5% de descuento sobre el cálculo base
    return (this.precio * this.stock) * 0.85; 
    }

    // SOBREESCRIBIMOS el toString para Comida
    @Override
        public String toString() {
    // Usamos super.toString() para traer lo que el padre ya muestra (nombre, precio)
    // y le pegamos lo nuevo (litros)
    return super.toString() + " | Volumen: " + fechaVencimiento + "L";
    }

}