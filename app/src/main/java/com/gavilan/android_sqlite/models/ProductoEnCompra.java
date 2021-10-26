package com.gavilan.android_sqlite.models;

public class ProductoEnCompra {
    private Compra compra;
    private Producto producto;
    private int cantidad;

    public ProductoEnCompra() {
    }

    public ProductoEnCompra(Compra compra, Producto producto, int cantidad) {
        this.compra = compra;
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
