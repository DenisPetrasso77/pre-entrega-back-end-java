package com.techlab.clientes;


// --- Clase Cliente que voy a usar mas adelante ---
    public class Cliente {

        private static int contadorId = 1;
        private int id;
        public  String nombre;
        public  String email;

        public void setEmail(String email) {
        if (email.contains("@")) {
            this.email = email;
        } else {
            this.email = "inválido";
        }
    }

       // Agregamos esto para que otras clases puedan "pedirle" el nombre
        public String getNombre() {
            return this.nombre;
        }

        public Cliente(String nombre, String email) {
            this.id = contadorId++;
            this.nombre = nombre;
            if (email.contains("@")) {
                this.email = email;
            } else {
                this.email = "Email no válido (falta @)";
                System.out.println("Error: El email de " + nombre + " es incorrecto.");
            }
        }


        public void saludar(String nombre) {

            System.out.println("Hola " + nombre + " Bienvenido al Sistema Techlab!!!" );
        }

        @Override
        public String toString() {
            return "Cliente: " + this.nombre + " | Email: " + this.email;
        }
    }