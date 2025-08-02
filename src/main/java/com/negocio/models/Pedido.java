// src/main/java/com/negocio/models/Pedido.java
package com.negocio.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa un pedido realizado por un cliente.
 * Contiene información sobre los productos, el cliente, la fecha y el total del pedido.
 */
public class Pedido {
    private int id;
    private Cliente cliente;
    private List<ItemPedido> items;
    private LocalDateTime fecha;
    private double total;

    /**
     * Crea un nuevo pedido para un cliente específico.
     */
    public Pedido(int id, Cliente cliente) {
        this.id = id;
        this.cliente = cliente;
        this.items = new ArrayList<>();
        this.fecha = LocalDateTime.now();
        this.total = 0.0;
    }

    /**
     * Agrega un producto al pedido. Si el producto ya existe, incrementa su cantidad.
     */
    public void agregarProducto(Producto producto) {
        for (ItemPedido item : items) {
            if (item.getProducto().equals(producto)) {
                item.incrementarCantidad(1);
                calcularTotal();
                return;
            }
        }
        items.add(new ItemPedido(producto, 1));
        calcularTotal();
    }

    /**
     * Calcula el total del pedido sumando el precio de todos los productos por su cantidad.
     */
    private void calcularTotal() {
        total = 0;
        for (ItemPedido item : items) {
            total += item.getProducto().getPrecio() * item.getCantidad();
        }
    }

    /**
     * Obtiene el primer producto del pedido.
     */
    public Producto obtenerPrimerProducto() {
        if (items.isEmpty()) {
            throw new IllegalStateException("No hay productos en el pedido");
        }
        return items.get(0).getProducto();
    }

    /**
     * Aplica un descuento al total del pedido.
     */
    public double aplicarDescuento(double porcentaje) {
        return total - (total * porcentaje / 100);
    }

    // Getters
    public int getId() { return id; }
    public Cliente getCliente() { return cliente; }
    public List<ItemPedido> getItems() { return items; }
    public LocalDateTime getFecha() { return fecha; }
    public double getTotal() { return total; }

    /**
     * Devuelve una representación en cadena del pedido.
     */
    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", cliente=" + cliente.getNombre() +
                ", items=" + items.size() +
                ", fecha=" + fecha +
                ", total=" + total +
                '}';
    }
}
