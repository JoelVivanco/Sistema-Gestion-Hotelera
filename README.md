# Sistema de Gestión Hotelera

Aplicación de escritorio estilo ERP desarrollada en Java utilizando el gestor de dependencias Maven. El sistema implementa una arquitectura en capas (**MVC + DAO**), aplica 7 patrones de diseño GOF y cuenta con un control de transacciones y seguridad por rol para garantizar la integridad y el acceso controlado a la información.

## Requisitos del Proyecto

* **Java JDK:** Versión 21 (definido en `maven.compiler.source`/`target` del `pom.xml`).
* **IDE de Desarrollo:** NetBeans (recomendado), Eclipse o VS Code.
* **Gestor de Dependencias:** Maven (integrado en NetBeans).
* **Base de Datos:** MySQL Server 8.0 o XAMPP (MariaDB 10.4+).
* **Driver JDBC:** MySQL Connector/J (gestionado automáticamente por `pom.xml`).

---

## 📂 Estructura de Carpetas

El proyecto sigue una estructura modular estricta dividida por responsabilidades y por patrón de diseño aplicado:

```text
SistemaGestionHotelera/
├── script_base_datos.sql             # Script de creación de BD + Stored Procedures
├── src/
│   └── main/
│       └── java/
│           └── sistema/
│               ├── Main.java         # Punto de entrada de la aplicación
│               ├── database/         # Conexión a BD (Patrón Singleton)
│               ├── modelo/           # Entidades (Habitacion, Huesped, Reserva); Reserva usa Patrón Builder
│               │   ├── factory/      # Creación de tipos de huésped (Patrón Factory)
│               │   └── state/        # Estados de habitación (Patrón State)
│               ├── observer/         # Notificaciones ante eventos de reserva (Patrón Observer)
│               ├── dao/              # Acceso a Datos (CallableStatement contra Stored Procedures)
│               ├── controlador/      # Lógica de negocio + Fachada (HotelFacade) + Proxy de seguridad (HuespedControladorProxy)
│               └── formularios/      # Interfaces gráficas (UI Swing); las 3 de gestión (Huésped, Habitación, Reserva) consumen HotelFacade
└── pom.xml                           # Configuración de dependencias de Maven
```

### Patrones de diseño aplicados

| Patrón | Ubicación | Propósito |
|---|---|---|
| Singleton | `database/ConexionDB.java` | Una única conexión a la base de datos |
| Factory | `modelo/factory/HuespedFactory.java` | Crea `HuespedRegular`, `HuespedFrecuente`, `HuespedCorporativo` |
| Builder | `modelo/Reserva.java` | Construcción legible de objetos `Reserva` |
| State | `modelo/state/*` | Transiciones de estado de una habitación (Disponible / Ocupada / Limpieza) |
| Observer | `observer/*` | Notifica eventos (email, estado de habitación) al crear una reserva o hacer check-out |
| Proxy | `controlador/HuespedControladorProxy.java` | Bloquea registrar/modificar/eliminar huéspedes si el rol de sesión no es "Administración" |
| Facade | `controlador/HotelFacade.java` | Único punto de acceso que usan los 3 formularios (Huésped, Habitación, Reserva) |

---

## Indicaciones sobre la Base de Datos

El sistema se conecta a un esquema local en MySQL estructurado para soportar lógica transaccional mediante procedimientos almacenados (Stored Procedures).

* **Nombre de la Base de Datos:** `db_hotel_gestion`
* **Puerto de Conexión:** `3307` (configurado en `ConexionDB.java` para entornos XAMPP donde el puerto 3306 está ocupado por otro proceso). Si tu MySQL corre en el puerto estándar, cámbialo a `3306` en esa misma clase.
* **Procedimientos almacenados:** 9 SP definidos en `script_base_datos.sql` (registrar/listar/modificar/eliminar por módulo + `sp_procesar_checkout`), todos invocados vía `CallableStatement` desde los DAO correspondientes.
* **Transacciones:** `ReservaDAO.registrarReserva` maneja `commit()` / `rollback()` manual.

## Seguridad por rol

El login (`FrmLogin`) permite ingresar como **Personal** o **Administración**. El rol seleccionado se registra en `HuespedControladorProxy`, que bloquea el registro, la modificación y la eliminación de huéspedes si el usuario no tiene rol de Administración. Las 3 vistas de gestión (Huésped, Habitación, Reserva) pasan siempre por `HotelFacade`, que internamente delega en dicho Proxy.
