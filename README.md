Sistema de Gestión Hotelera

Aplicación de escritorio estilo ERP desarrollada en Java utilizando el gestor de dependencias Maven. El sistema implementa una arquitectura limpia en capas (**MVC + DAO**) y cuenta con un sólido control de transacciones de base de datos para garantizar la integridad de la información.

Requisitos del Proyecto

Para ejecutar y modificar este sistema, se requiere contar con las siguientes herramientas instaladas:

* **Java JDK:** Versión 11 o superior.
* **IDE de Desarrollo:** NetBeans (recomendado), Eclipse o VS Code.
* **Gestor de Dependencias:** Maven (integrado en NetBeans).
* **Base de Datos:** MySQL Server 8.0 o XAMPP (MariaDB 10.4+).
* **Driver JDBC:** MySQL Connector/J (gestionado automáticamente por `pom.xml`).

---

## 📂 Estructura de Carpetas

El proyecto sigue una estructura modular estricta dividida por responsabilidades:

```text
SistemaGestionHotelera/
├── database/
│   └── Dump20260707.sql          # Script de respaldo completo de la BD
├── src/
│   └── main/
│       └── java/
│           └── com/mycompany/sistemagestionhotelera/
│               ├── Main.java     # Punto de entrada de la aplicación
│               ├── database/     # Conexión base de datos (Patrón Singleton)
│               ├── modelo/       # Clases de entidad (Huesped, Habitacion, Reserva)
│               ├── dao/          # Acceso a Datos (Consultas y Stored Procedures)
│               ├── controlador/  # Lógica de negocio e intermediario MVC
│               └── vista/        # Formularios e Interfaces Gráficas (UI Swing)
└── pom.xml                       # Configuración de dependencias de Maven
```
Indicaciones sobre la Base de Datos
El sistema se conecta a un esquema local en MySQL estructurado para soportar lógica transaccional mediante procedimientos almacenados (Stored Procedures).

Nombre de la Base de Datos: db_hotel_gestion

Puerto de Conexión: 3307 (Configurado por defecto para entornos XAMPP. Puede cambiarse a 3306 en ConexionDB.java si el servidor local lo requiere).
