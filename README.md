# Desafío Alura Challenge Forohub
Bienvenido a Challenge Forohub, un foro creado para gestionar tópicos, usuarios y respuestas usando Java Spring Boot y Spring Security. 

## Configuración
1. Crea una base de datos en MySQL llamada `forohub`.
2. Configura las siguientes variables de entorno en path dentro de tu sistema:
    - `${DB_HOST}`: Por defecto `localhost`
    - `${DB_NAME}`: `forohub`
    - `${DB_USER}`: Usuario de tu MySQL
    - `${DB_PASS}`: Contraseña de tu MySQL
      
## Funcionalidades de la API
- **Crear un nuevo tópico**: Permite crear nuevos tópicos.
- **Mostrar todos los tópicos creados**: Lista todos los tópicos.
- **Mostrar un tópico específico**: Muestra detalles de un tópico por ID.
- **Actualizar un tópico**: Actualiza la información de un tópico existente.
-  **Eliminar un tópico**: Realiza una eliminación lógica del tópico.

## Tecnologías Utilizadas
- Java 17
- Spring Boot, Security y Data JPA
- Validation JWT
- MySQL
  **IDE´s**
- IntelliJ IDEA
- Insomnia



## Instalación
1. Configura la base de datos, actualiza las credenciales en `application.properties` y además en el pom debes tener las dependencias para que meven te ayude.
2. Ejecuta la aplicación.
