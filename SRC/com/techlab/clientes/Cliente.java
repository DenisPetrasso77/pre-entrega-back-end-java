package com.techlab.clientes;


// --- Clase Cliente que voy a usar mas adelante ---
    public class Cliente {

        private static int contadorId = 1;
        private int id;
        public  String nombre;
        public  String email;

        public final void setEmail(String email) {

            if (email.contains("@")) {
                this.email = email;
                } else {
                this.email = "inválido";
                }
        }

        //getter
        public String getNombre() {
            return this.nombre;
        }

        //constructor
        public Cliente(String nombre, String email) {

            this.id = contadorId++;
            this.nombre = nombre;
            setEmail(email);
           
        }

        public void saludar(String nombre) {

            System.out.println("Hola " + nombre + " Bienvenido al Sistema Techlab!!!" );
        }
        @Override
        public String toString() {
            return "Cliente: " + this.nombre + " | Email: " + this.email;
        }
    }