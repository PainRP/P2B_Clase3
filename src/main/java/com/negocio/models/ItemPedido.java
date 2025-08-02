package com.negocio.models;

/**
 * Representa un ítem dentro de un pedido, asociando un producto y la cantidad solicitada.
 */
public class ItemPedido {
        private Producto producto;
        private int cantidad;

        /**
         * Crea un nuevo ítem de pedido con el producto y la cantidad especificados.
         */
        public ItemPedido(Producto producto, int cantidad) {
            this.producto = producto;
            this.cantidad = cantidad;
        }

        /**
         * Obtiene el producto asociado a este ítem.
         */
        public Producto getProducto() { return producto; }

        /**
         * Obtiene la cantidad de productos en este ítem.
         */
        public int getCantidad() { return cantidad; }

        /**
         * Incrementa la cantidad de productos en este ítem.
         */
        public void incrementarCantidad(int cantidad) { this.cantidad += cantidad; }
}
