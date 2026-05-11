# Pre Entrega - CRUD Sistema de Gestión de Productos - Back-end / Java

**Comisión:** 26138 | **Año:** 2026  
**Alumno:** Franco Denis Petrasso - [Denispetrasso77@gmail.com](mailto:Denispetrasso77@gmail.com)  
**Instructor:** Miguel Nefle - [miguel.nefle@bue.edu.ar](mailto:miguel.nefle@bue.edu.ar)  
**Tutora:** Natalia Themtham - [natalia.themtham@bue.edu.ar](mailto:natalia.themtham@bue.edu.ar)

---

### Descripción del Proyecto
Este proyecto es una aplicación de consola desarrollada en Java que implementa un sistema integral de gestión de **Clientes** (Más adelante), **productos** (Alta, Baja, Modificación y Consulta) y **pedidos** (Creación). La arquitectura está basada en paquetes para asegurar un código limpio, modular y escalable.

---

### Estructura del Proyecto
El sistema está organizado bajo el paquete raíz `com.techlab` y se divide en los siguientes módulos:

* **clientes:** Para la Gestión de la entidad Cliente más adelante en el desarrollo.
* **productos:** Administración del inventario de productos.
* **pedidos:** El núcleo de la aplicación. Contiene la lógica del Carrito que usaremos más adelante, el detalle de cada **LineaPedido** y la gestión del Pedido final.
* **excepciones:** Sistema de control de errores personalizado, incluyendo:
  * `ProductoNoEncontradoException`
  * `StockInsuficienteException`
* **principal:** Punto de entrada de la aplicación (**Main**).

---

### Características Principales
* **Menu de Inicio:** Tendremos un Menú de inicio para navegar por el sistema eligiendo las opciones correspondiente.
* **Gestión de Clientes:** Tendremos un Registro y manejo de datos de clientes mas adelante.
* **Gestión de Productos:** Gestión integra de los produsctos del sistema: Alta, baja modificación y consulta.
* **Gestión de Pedidos:** Creación de pedidos de diferentes productos para la Venta.
* **Gestión de Ventas:** Realización de las ventas de los pedidos hechos anteriormente.
* **Validación de Stock:** El sistema controla automáticamente si hay existencias disponibles antes de procesar una compra.
* **Reporte de Ventas:** El sistema guarda las ventas realizacas para su posterior visualización.


---

### Tecnologías y Conceptos Aplicados
* **Lenguaje:** Java.
* **Programación Orientada a Objetos (POO):** Uso de encapsulamiento, abstracción y asociación entre clases.
* **Manejo de Excepciones:** Estructura de `try-catch` con excepciones personalizadas.
* **Modularidad:** Separación clara de responsabilidades por medio de paquetes.

---

### Cómo ejecutarlo
1. **Bajar o Clonar el repositorio.**
2. **Abre el proyecto** en Visual Studio Code o cualquier IDE de Java.
3. **Asegúrate** de tener configurado el JDK 11 o superior.
4. **Ejecuta** la clase `Main.java` ubicada en `src/com/techlab/principal/`.
5. **Presionar el boton "Run Java" del Visual Studio Code**

