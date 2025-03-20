# Jurassic Bark Vet - Sistema Web para Veterinaria

## Descripción
Jurassic Bark Vet es una aplicación web desarrollada con **Spring Boot** y **Thymeleaf** para gestionar los servicios de una veterinaria especializada en perros y gatos.

## Tecnologías Utilizadas
- **Backend:** Spring Boot, Maven, H2 Database
- **Frontend:** HTML, CSS, JavaScript, Thymeleaf
- **Gestión de Dependencias:** Maven
- **Base de Datos:** H2 (archivo `petsDataBase.mv.db`)

## Características Principales
- Gestión de clientes y sus mascotas.
- Reservas en línea para consultas y servicios.
- Sistema de autenticación y formularios de inicio de sesión.
- Interfaz moderna y responsive.

## Estructura del Proyecto
```
vetproject/
├── src/
│   ├── main/
│   │   ├── java/com/example/vetproject/  # Código fuente backend
│   │   ├── resources/
│   │   │   ├── templates/  # Archivos HTML con Thymeleaf
│   │   │   ├── static/
│   │   │   │   ├── styles/  # Archivos CSS
│   │   │   │   ├── Functions/  # Archivos JavaScript
│   │   │   ├── application.properties  # Configuración del proyecto
├── pom.xml  # Dependencias y configuración de Maven
├── petsDataBase.mv.db  # Base de datos H2
```

## Instalación y Ejecución
1. Clonar el repositorio:
   ```bash
   git clone https://github.com/tu-repo/vetproject.git
   ```
2. Navegar al directorio del proyecto:
   ```bash
   cd vetproject
   ```
3. Compilar y ejecutar con Maven:
   ```bash
   ./mvnw spring-boot:run  # Linux/Mac
   mvnw.cmd spring-boot:run  # Windows
   ```
4. Acceder a la aplicación en el navegador:
   ```
   http://localhost:8080
   ```

## Contacto
Para más información, contacta al equipo de desarrollo a través de [correo@example.com](mailto:correo@example.com).

