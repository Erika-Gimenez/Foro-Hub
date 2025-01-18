<p align="center"> 
  <img src="http://img.shields.io/static/v1?label=STATUS&message=EN%20DESARROLLO&color=ORANGE&style=for-the-badge" /> 
  <img src="https://img.shields.io/badge/language-Java-007396?style=for-the-badge"/> 
  <img src="https://img.shields.io/badge/framework-Spring%20Boot-6DB33F?style=for-the-badge"/> 
  <img src="https://img.shields.io/badge/database-MySQL-4479A1?style=for-the-badge"/> 
</p>

## Descripción del Proyecto
ForoHub es una plataforma de preguntas y respuestas desarrollada como parte del desafío final del programa Oracle Next Education, ofrecido por Alura LATAM. 
El desafío consistió en la creación de una API REST que permite a los usuarios realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre temas y preguntas. 
La plataforma implementa un sistema de autenticación y autorización para restringir el acceso a la información, y utiliza una base de datos para la persistencia de los datos, 
todo siguiendo las mejores prácticas del modelo REST.

## 🔧 Tecnologías Utilizadas
- **Lenguaje de programación:** Java 17 ☕
- **Framework:** Spring Boot 3 🌱
- **Base de Datos:** MySQL 8.0 🐬
- **Seguridad:** JSON Web Tokens (JWT) 🔐
- **Gestor de Dependencias:** Maven 4.0.0 📦
- **Dependencias:** Spring Boot DevTools, Spring Web, Spring Data JPA, Spring Security, MySQL Driver, Validation, Lombok, Flyway Migration, Auth0, SpringDocs

## 🚀 Instalación
### 📋 Pre-requisitos
1. Java JDK 17 o superior  [Descarga](https://www.oracle.com/ar/java/technologies/downloads/#java17)
2. MySQL 8.0: Descarga y configurar tu base de datos.
3. Clona este repositorio en tu ordenador:
````
https://github.com/Erika-Gimenez/Foro-Hub.git

````
- Navega hasta la carpeta del proyecto.
````
cd Foro-Hub

````
4. Configurar los datos en tu application.properties.
````
DATASOURCE_URL=jdbc:mysql://localhost:tu_base_de_datos
DATASOURCE_USERNAME=tu_nombre_de_usuario
DATASOURCE_PASSWORD=tu_contraseña
api.security.secret=tu_clave_secreta
````
5. Ejecuta el proyecto en tu IDE favorito (IntelliJ IDEA, Eclipse, etc.).

6. Acceder a la documentación Swagger en con el puerto que este configurado:

[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui/index.html)

## 🛠️ Pruebas

- **Herramientas recomendadas:** Postman o Insomnia.
- **Formato de solicitudes:** JSON.

Ejemplo de Solicitud POST para Crear un Tópico
````
{
  "titulo": "string",
  "mensaje": "string",
  "curso": "string",
  "autorId": 9007199254740991
}

````
## 📂 Funcionalidades

- Autenticación y autorización: Los usuarios se registran, inician sesión y reciben tokens JWT para acceder a los recursos protegidos de la plataforma.

- Gestión de tópicos y respuestas: Los usuarios pueden crear, leer, actualizar y eliminar tópicos, así como responder a preguntas.

- Visualización de tópicos y respuestas: Los usuarios pueden acceder a tópicos y respuestas mediante su ID específico o por las rutas /topicos y /respuestas.

- Reglas de negocio: Se valida que los campos no estén vacíos ni en blanco, que no haya títulos o mensajes duplicados y que cada usuario pueda crear un máximo de 10 tópicos.

## 🙌 Contribuciones
¡Me encantaría tu ayuda para mejorar este proyecto! Puedes contribuir de varias maneras:
* Si encuentras algún error o problema.
* Si tienes ideas para nuevas funcionalidades o mejoras.

## ✒️ Autores 

[<img src="https://github.com/user-attachments/assets/1e99f8e5-f229-4128-837a-554d2844c64c" width=115><br><sub>Gimenez Erika</sub>](https://github.com/Erika-Gimenez)

## 😊 Contacto 

* [Linkedin](https://www.linkedin.com/in/erika-gimenez/).
* [GitHub](https://github.com/Erika-Gimenez).

> [!NOTE]
>  Nuevas opciones en el menú.
>  Experimentar con una interfaz gráfica en el futuro para mejorar la experiencia del usuario.




