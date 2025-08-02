package umg.edu.gt;

import com.negocio.models.Cliente;
import com.negocio.models.Pedido;
import com.negocio.models.Producto;
import com.negocio.services.InventarioService;
import com.negocio.services.PedidoService;
import com.negocio.db.DatabaseManager;

import java.util.Scanner;

/**
 * Clase principal que contiene el menú y la lógica de interacción con el usuario para FoodNet.
 */
public class Main {
    private static InventarioService inventarioService;
    private static PedidoService pedidoService;
    private static Scanner scanner;

    /**
     * Método principal. Inicia la aplicación y muestra el menú principal.
     */
    public static void main(String[] args) {
        System.out.println("=== FOODNET - Simulador de Negocio de Comida Rápida ===");

        // Inicializar servicios
        inventarioService = new InventarioService();
        pedidoService = new PedidoService(inventarioService);
        scanner = new Scanner(System.in);

        // Menú principal
        boolean continuar = true;
        while (continuar) {
            mostrarMenu();
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    mostrarInventario();
                    break;
                case 2:
                    crearNuevoPedido();
                    break;
                case 3:
                    agregarProductoAPedido();
                    break;
                case 4:
                    mostrarPedidos();
                    break;
                case 5:
                    mostrarIngresos();
                    break;
                case 6:
                    aplicarDescuentoAPedido();
                    break;
                case 7:
                    agregarProducto();
                    break;
                case 8:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }

        DatabaseManager.cerrarConexion();
        scanner.close();
        System.out.println("¡Gracias por usar FoodNet!");
    }

    /**
     * Muestra el menú principal por consola.
     */
    private static void mostrarMenu() {
        System.out.println("\n--- MENÚ PRINCIPAL ---");
        System.out.println("1. Ver inventario");
        System.out.println("2. Crear nuevo pedido");
        System.out.println("3. Agregar producto a pedido");
        System.out.println("4. Ver pedidos");
        System.out.println("5. Ver ingresos totales");
        System.out.println("6. Aplicar descuento a pedido");
        System.out.println("7. Agregar nuevo producto al inventario");
        System.out.println("8. Salir");
        System.out.print("Seleccione una opción: ");
    }

    /**
     * Muestra el inventario de productos disponibles.
     */
    private static void mostrarInventario() {
        System.out.println("\n--- INVENTARIO ---");
        for (Producto producto : inventarioService.obtenerProductosDisponibles()) {
            System.out.println(producto);
        }
    }

    /**
     * Crea un nuevo pedido solicitando los datos del cliente.
     */
    private static void crearNuevoPedido() {
        System.out.print("Nombre del cliente: ");
        String nombre = scanner.nextLine();
        System.out.print("Teléfono del cliente: ");
        String telefono = scanner.nextLine();

        Cliente cliente = new Cliente(1, nombre, telefono);
        Pedido pedido = pedidoService.crearPedido(cliente);

        System.out.println("Pedido creado con ID: " + pedido.getId());
    }

    /**
     * Agrega un producto a un pedido existente.
     */
    private static void agregarProductoAPedido() {
        System.out.print("ID del pedido: ");
        int pedidoId = scanner.nextInt();
        System.out.print("ID del producto: ");
        int productoId = scanner.nextInt();
        System.out.print("Cantidad: ");
        int cantidad = scanner.nextInt();

        if (pedidoService.agregarProductoAPedido(pedidoId, productoId, cantidad)) {
            System.out.println("Producto agregado exitosamente");
        } else {
            System.out.println("Error al agregar producto");
        }
    }

    /**
     * Muestra todos los pedidos registrados.
     */
    private static void mostrarPedidos() {
        System.out.println("\n--- PEDIDOS ---");
        pedidoService.mostrarPedidos();
    }

    /**
     * Muestra los ingresos totales generados por los pedidos.
     */
    private static void mostrarIngresos() {
        double ingresos = pedidoService.calcularIngresosTotales();
        System.out.println("Ingresos totales: Q" + ingresos);
    }

    /**
     * Aplica un descuento a un pedido (funcionalidad en desarrollo).
     */
    private static void aplicarDescuentoAPedido() {
        System.out.print("ID del pedido: ");
        int pedidoId = scanner.nextInt();
        System.out.print("Porcentaje de descuento: ");
        double descuento = scanner.nextDouble();

        // Buscar pedido y aplicar descuento (simplificado para el ejemplo)
        System.out.println("Función de descuento en desarrollo...");
    }

    /**
     * Agrega un nuevo producto al inventario solicitando los datos al usuario.
     */
    private static void agregarProducto() {
            System.out.print("Nombre del producto: ");
            String nombre = scanner.nextLine();

            System.out.print("Precio del producto: ");
            double precio = scanner.nextDouble();
            if (precio <= 0) {
                System.out.println("El precio debe ser mayor que cero.");
                scanner.nextLine();
                return;
            }

            System.out.print("Stock del producto: ");
            int stock = scanner.nextInt();
            if (stock < 0) {
                System.out.println("El stock no puede ser negativo.");
                scanner.nextLine();
                return;
            }
            scanner.nextLine();

            Producto nuevoProducto = new Producto(0, nombre, precio, stock);
            if (inventarioService.agregarProducto(nuevoProducto)) {
                System.out.println("Producto agregado exitosamente");
            } else {
                System.out.println("Error al agregar producto");
            }
    }
}
