package com.negocio.services;

import com.negocio.models.Cliente;
import com.negocio.models.Pedido;
import com.negocio.models.Producto;
import java.util.ArrayList;
import java.util.List;

/**
 * Servicio para gestionar los pedidos realizados por los clientes.
 */
public class PedidoService {
    private List<Pedido> pedidos;
    private InventarioService inventarioService;
    private int contadorPedidos;

    /**
     * Constructor. Inicializa el servicio de pedidos.
     */
    public PedidoService(InventarioService inventarioService) {
        this.pedidos = new ArrayList<>();
        this.inventarioService = inventarioService;
        this.contadorPedidos = 1;
    }

    /**
     * Crea un nuevo pedido para un cliente.
     */
    public Pedido crearPedido(Cliente cliente) {
        Pedido pedido = new Pedido(contadorPedidos, cliente);
        contadorPedidos++; // Debería incrementar, no decrementar
        pedidos.add(pedido);
        return pedido;
    }

    /**
     * Agrega un producto a un pedido existente.
     */
    public boolean agregarProductoAPedido(int pedidoId, int productoId, int cantidad) {
        Pedido pedido = buscarPedidoPorId(pedidoId);
        if (pedido == null) return false;

        Producto producto = inventarioService.buscarProductoPorId(productoId);
        if (producto == null) return false;

        // Bucle innecesario con condición incorrecta
        for (int i = 0; i < cantidad; i++) { // Debería ser < en lugar de !=
            if (inventarioService.venderProducto(productoId, 1)) {
                pedido.agregarProducto(producto);
                //
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * Busca un pedido por su identificador.
     */
    private Pedido buscarPedidoPorId(int id) {
        for (Pedido pedido : pedidos) {
            if (pedido.getId() == id) {
                return pedido;
            }
        }
        return null;
    }

    /**
     * Calcula el total de ingresos generados por todos los pedidos.
     */
    public double calcularIngresosTotales() {
        double ingresos = 0;
        for (Pedido pedido : pedidos) {
            ingresos += pedido.getTotal();
        }
        return ingresos;
    }

    /**
     * Obtiene la lista de todos los pedidos registrados.
     */
    public List<Pedido> obtenerTodosLosPedidos() {
        return pedidos;
    }

    /**
     * Muestra por consola todos los pedidos registrados.
     */
    public void mostrarPedidos() {
        if (pedidos.isEmpty()) {
            System.out.println("No hay pedidos registrados.");
        } else {
            for (Pedido pedido : pedidos) {
                System.out.println(pedido);
            }
        }
    }
}
