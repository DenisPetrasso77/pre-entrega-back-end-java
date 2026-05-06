Pre Entrega - CRUD Sistema de Gestión de Productos - Back-end / Java

Comision:26138
Año:2026
Alumno: Franco Denis Petrasso - Denispetrasso77@gmail.com
Instructor: Miguel Nefle - miguel.nefle@bue.edu.ar
Tutora: Natalia Themtham - natalia.themtham@bue.edu.ar


Este proyecto es una aplicación de consola desarrollada en Java que implementa un sistema integral de gestión de Clientes (Mas adelante), productos (Alta, Baja, Modificación y Consulta) y pedidos (Creación). La arquitectura está basada en paquetes para asegurar un código limpio, modular y escalable.

Estructura del Proyecto
El sistema está organizado bajo el paquete raíz com.techlab y se divide en los siguientes módulos:

clientes: Para la Gestión de la entidad Cliente mas adelante en el desarrollo.

productos: Administración del inventario de productos.

pedidos: El núcleo de la aplicación. Contiene la lógica del Carrito que usaremos mas adelante, el detalle de cada LineaPedido y la gestión del Pedido final.

excepciones: Sistema de control de errores personalizado, incluyendo:

ProductoNoEncontradoException

StockInsuficienteException

principal: Punto de entrada de la aplicación (Main).

Características Principales

Gestión de Clientes: Tendremos un Registro y manejo de datos de clientes.

Validación de Stock: El sistema controla automáticamente si hay existencias disponibles antes de procesar una compra.

Control de Errores Robusto: Implementación de excepciones propias para manejar casos de productos inexistentes o falta de stock, mejorando la experiencia del usuario y la estabilidad del código.

Tecnologías y Conceptos Aplicados
Lenguaje: Java.

Programación Orientada a Objetos (POO): Uso de encapsulamiento, abstracción y asociación entre clases.

Collections API: Uso de estructuras de datos para gestionar listas de productos y pedidos.

Manejo de Excepciones: Estructura de try-catch con excepciones personalizadas.

Modularidad: Separación clara de responsabilidades por medio de paquetes.

Cómo ejecutarlo
Clona el repositorio.

Abre el proyecto en Visual Studio Code o cualquier IDE de Java.

Asegúrate de tener configurado el JDK 11 o superior.

Ejecuta la clase Main.java ubicada en src/com/techlab/principal/.

