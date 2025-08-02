package com.negocio.models;

/**
 * Representa un producto del inventario.
 */
public class Producto {
    private int id;
    private String nombre;
    private double precio;
    private int stock;

    /**
     * Crea un nuevo producto con los datos especificados.
     */
    public Producto(int id, String nombre, double precio, int stock) {
        this.id = id;
        this.nombre = nombre;
        if (stock < 0 || precio < 0)  {
            throw new IllegalArgumentException("El stock o el precio no puede ser negativo");
        }
            this.precio = precio;
            this.stock = stock; // No valida si el stock es negativo
    }

    /**
     * Reduce el stock del producto en la cantidad indicada.
     */
    public void reducirStock(int cantidad) {
        if (cantidad < 0) {
            throw new IllegalArgumentException("El cantidad no puede ser negativo");
        }
        if (cantidad > stock) {
            throw new IllegalArgumentException("No hay suficiente stock para reducir");
        }
        this.stock = this.stock - cantidad; // No verifica si hay suficiente stock
    }

    /**
     * Verifica si hay suficiente stock para una cantidad dada.
     */
    public boolean hayStock(int cantidad) {
        return stock >= cantidad; // Debería ser >= para permitir exactamente la cantidad
    }

    /**
     * Obtiene el precio del producto.
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Obtiene el stock disponible.
     */
    public int getStock() {
        return stock;
    }

    /**
     * Obtiene el identificador del producto.
     */
    public int getId() {
        return id;
    }

    /**
     * Obtiene el nombre del producto.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Devuelve una representación en cadena del producto.
     */
    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", stock=" + stock +
                '}';
    }
}
