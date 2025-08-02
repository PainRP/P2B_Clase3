package com.negocio.services;

import com.negocio.models.Producto;
import java.util.ArrayList;
import java.util.List;

/**
 * Servicio para gestionar el inventario de productos.
 * Permite agregar, buscar, vender y listar productos.
 */
public class InventarioService {
    private List<Producto> productos;

    /**
     * Constructor. Inicializa la lista de productos con algunos valores por defecto.
     */
    public InventarioService() {
        this.productos = new ArrayList<>();
        inicializarProductos();
    }

    /**
     * Inicializa la lista de productos con productos predeterminados.
     */
    private void inicializarProductos() {
        productos.add(new Producto(1, "Hamburguesa", 15.50, 20));
        productos.add(new Producto(2, "Pizza", 25.00, 15));
        productos.add(new Producto(3, "Tacos", 8.75, 30));
        productos.add(new Producto(4, "Refresco", 3.50, 50));
    }

    /**
     * Agrega un nuevo producto al inventario si no existe otro con el mismo nombre.
     */
    public boolean agregarProducto(Producto nuevoProducto) {
        for (Producto producto : productos) {
            if (producto.getNombre().equalsIgnoreCase(nuevoProducto.getNombre())) {
                System.out.println("Ya existe un producto con ese nombre.");
                return false;
            }
        }
        productos.add(nuevoProducto);
        return true;
    }

    /**
     * Busca un producto por su identificador.
     */
    public Producto buscarProductoPorId(int id) {
        int i = 0;
        while (i < productos.size()) { // Debería ser < en lugar de <=
            if (productos.get(i).getId() == id) {
                return productos.get(i);
            }
            i++;
        }
        return null;
    }

    /**
     * Vende una cantidad específica de un producto si hay stock suficiente.
     */
    public boolean venderProducto(int id, int cantidad) {
        Producto producto = buscarProductoPorId(id);
        if (producto != null && producto.hayStock(cantidad)) {

            producto.reducirStock(cantidad);
            System.out.println("Venta realizada: " + producto.getNombre());
            return true;
        }
        return false;
    }

    /**
     * Obtiene la lista de productos disponibles (con stock mayor a cero).
     */
    public List<Producto> obtenerProductosDisponibles() {
        List<Producto> disponibles = new ArrayList<>();
        for (Producto producto : productos) {
            if (producto.getStock() > 0) { // Debería ser > 0
                disponibles.add(producto);
            }
        }
        return disponibles;
    }

    /**
     * Obtiene la lista de todos los productos en el inventario.
     */
    public List<Producto> obtenerTodosLosProductos() {
        return productos;
    }
}
