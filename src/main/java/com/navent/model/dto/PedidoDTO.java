package com.navent.model.dto;

import com.navent.model.Pedido;

public class PedidoDTO {

    private Integer idPedido;
    private String nombre;
    private double monto;
    private int descuento;

    public PedidoDTO() {

    }

    public PedidoDTO(Pedido pedido) {

        this.idPedido = pedido.getIdPedido();
        this.nombre = pedido.getNombre();
        this.monto = pedido.getMonto();
        this.descuento = pedido.getDescuento();

    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public int getDescuento() {
        return descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

}
