package com.techlab.productos;

 // ---CLASE BEBIDA ---
    public  class Bebida extends Producto implements Descontable {
    private double volumenEnLitros;

    public Bebida(String nombre, double precio, int stock, double volumen) {
        super(nombre, precio, stock);
        this.volumenEnLitros = volumen;
    }

    public double getVol() {
        return this.volumenEnLitros;
    }

    public void setVol(double volumenEnLitros) {
    if (volumenEnLitros > 0 && volumenEnLitros < 10) { // Ejemplo: validás que no sea negativo ni una locura
        this.volumenEnLitros = volumenEnLitros;
    } else {
        System.out.println("Error: Volumen no válido. Se asigna 0 por defecto.");
        this.volumenEnLitros = 0;
    }
}

        @Override 
        public double calcularPrecioFinal() { 
            // Aplicamos el 5% de descuento sobre el cálculo base
            return (this.precio * this.stock) * 0.95; 
        }

        // OBLIGACIÓN Viene de la Interfaz Descontable
        @Override
        public double aplicarDescuento(double porcentaje) {
            // Lógica específica: por ejemplo, las bebidas descuentan un poco más
            return this.precio - (this.precio * (porcentaje / 100));
        }
        // SOBREESCRIBIMOS el toString para Bebida
        @Override
        public String toString() {
            // Usamos super.toString() para traer lo que el padre ya muestra (nombre, precio)
            // y le pegamos lo nuevo (litros)
            return super.toString() + " | Volumen: " + volumenEnLitros + "L";
        }
}  