# Reporte de Mejoras

---

## Base de Datos y Persistencia

### Mejora 1: Evita que se registren productos con el mismo nombre

- **Ubicación:** Base de datos (Schema), `InventarioService` y `Main`.
- **Descripción del cambio:** En la base de datos se utilizó la palabra reservada `UNIQUE` para asegurar que los nombres de los productos sean únicos. En `InventarioService` se agregó una función que valida que no existan nombres repetidos antes de registrar un nuevo producto, y se creó una función en `Main` para gestionar la adición de productos.
- **Justificación:** Es crucial evitar productos con nombres duplicados para prevenir confusiones tanto en el código como en la base de datos.

---

### Mejora 2: Registra la fecha y hora de cada pedido

- **Ubicación:** Base de datos, `Pedido.java`.
- **Descripción del cambio:** Se añadió el tipo de dato `DATETIME` a la tabla de pedidos en la base de datos. En `Pedido.java`, se implementó la lógica para registrar la fecha y hora de creación de cada pedido.
- **Justificación:** Permite llevar un control preciso de los pedidos, mejorando la productividad y la gestión del negocio.

---

## Validaciones y Seguridad

### Mejora 5: Valida que el stock nunca sea negativo

- **Ubicación:** `Producto.java`.
- **Descripción del cambio:** Se modificó la función de `reducirStock` para incluir una validación. Ahora, el método verifica que haya suficiente inventario disponible antes de disminuir el stock.
- **Justificación:** Previene la venta de productos que ya no están disponibles, lo que mejora la precisión del inventario y evita problemas operativos.

---

### Mejora 6: Valida que los precios y cantidades ingresados sean positivos

- **Ubicación:** `Main.java`, Constructor de `Producto`.
- **Descripción del cambio:** Se añadió validación en el constructor de `Producto` para asegurar que los precios y la cantidad de stock inicial sean valores positivos.
- **Justificación:** Es una validación esencial para evitar datos ilógicos en el sistema, como precios o stock negativos, garantizando la integridad de los datos.

---

## EXTRA

### Mejora 10: Agrega comentarios y documentación básica

- **Ubicación:** Código general del proyecto.
- **Descripción del cambio:** Se incluyeron comentarios explicativos en todo el código para hacerlo más didáctico y fácil de entender.
- **Justificación:** La documentación básica y los comentarios mejoran la legibilidad y el mantenimiento del código, facilitando su comprensión para otros desarrolladores o para uno mismo en el futuro.
