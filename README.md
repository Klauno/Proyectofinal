# Proyecto Final - Sistema de Gestión E-commerce "Avios Textiles K"

## Descripción

Este proyecto es una aplicación web para la gestión de productos, pedidos y usuarios en un sistema de e-commerce, desarrollada con **Spring Boot** en el backend y un frontend estático en **HTML, CSS y JavaScript**.  
Permite gestionar productos, categorías, usuarios, realizar pedidos, controlar stock y visualizar un carrito de compras interactivo.

## Tecnologías utilizadas

- **Backend:**  
  - Java 21  
  - Spring Boot 3.5.3  
  - Spring Data JPA  
  - H2 Database en memoria  
  - Spring Security  
  - Maven  

- **Frontend:**  
  - HTML5, CSS3 (responsivo)  
  - JavaScript (Fetch API)  
  - FontAwesome  
  - Google Fonts (Roboto)  
  - Bootstrap 4 (componentes JS)  

## Estructura del proyecto
## 📁 Estructura del proyecto

```
Proyectofinal/
├── src/
│ └── main/
│ ├── java/
│ │ └── techlab/
│ │ └── Proyectofinal/ # Código backend (controladores, servicios, entidades, repositorios)
│ └── resources/
│ ├── static/ # Frontend estático servido por Spring Boot
│ │ ├── css/
│ │ │ └── styles.css
│ │ ├── js/
│ │ │ ├── app.js
│ │ │ └── script.js
│ │ ├── imagenes/
│ │ │ └── (imágenes del proyecto)
│ │ ├── index.html # Página principal
│ │ └── pages/
│ │ ├── productos.html
│ │ ├── carrito.html
│ │ ├── contacto.html
│ │ ├── reseñas.html
│ │ ├── ubicacion.html
│ │ ├── terminos-y-condiciones.html
│ │ └── politica-de-privacidad.html
│ └── application.properties # Configuración backend
├── pom.xml # Configuración Maven
└── README.md
```

## Funcionalidades principales

- Gestión completa de productos y categorías  
- Registro y gestión de usuarios  
- Creación y confirmación de pedidos con control de stock  
- Carrito de compras interactivo en frontend  
- Seguridad básica con Spring Security  
- Base de datos en memoria H2 para pruebas  

## Cómo ejecutar el proyecto

### Requisitos previos

- Java 21  
- Maven  

### Pasos para ejecutar

1. Clona o descarga el repositorio.  
2. Abre una terminal en la raíz del proyecto.  
3. Ejecuta:
mvn spring-boot:run

4. Abre en el navegador:

http://localhost:8080/index.html


5. Navega por el sitio y prueba funcionalidades.  
6. Para acceder a la consola H2 (base de datos):

http://localhost:8080/h2-console

- JDBC URL: `jdbc:h2:mem:productos`  
- Usuario: `sa`  
- Contraseña: (vacía)  

## API REST principales

| Método | Endpoint                  | Descripción                      |
|--------|---------------------------|---------------------------------|
| GET    | `/producto/list`           | Listar productos                |
| POST   | `/producto/`               | Crear producto                 |
| GET    | `/producto/find?nombre=`   | Buscar producto por nombre      |
| GET    | `/producto/{id}`           | Obtener producto por ID         |
| PUT    | `/producto/{id}`           | Editar producto                |
| DELETE | `/producto/{id}`           | Eliminar producto              |
| POST   | `/usuario/`                | Registrar usuario              |
| POST   | `/pedido/`                 | Crear pedido                  |
| GET    | `/usuario/{id}/pedidos`    | Listar pedidos por usuario      |

## Notas importantes

- El frontend estático se sirve directamente desde Spring Boot.  
- La base de datos es en memoria; los datos se pierden al detener la app.  
- La seguridad permite acceso público a frontend y API necesarias.  
- Para producción, se recomienda usar base de datos persistente y mejorar seguridad.

## Autor

- Claudia Oliverio  
- Email: oliverioclau@gmail.com  
- GitHub: https://github.com/Klauno/Proyectofinal

---

¡Gracias por revisar el proyecto! Para dudas o colaboración, no dudes en contactarme.

