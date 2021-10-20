package com.gavilan.android_sqlite.models;

public class Compra {
    private int id;
    private String fecha;
    private int pagado;
    private Usuario usuario;

    public Compra() {
    }

    public Compra(String fecha, Usuario usuario) {
        this.fecha = fecha;
        this.pagado = 0;
        this.usuario = usuario;
    }

    public Compra(int id, String fecha, int pagado, Usuario usuario) {
        this.id = id;
        this.fecha = fecha;
        this.pagado = pagado;
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getPagado() {
        return pagado;
    }

    public void setPagado(int pagado) {
        this.pagado = pagado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Compra{" +
                "id=" + id +
                ", fecha='" + fecha + '\'' +
                ", pagado=" + pagado +
                ", usuario=" + usuario +
                '}';
    }
}
