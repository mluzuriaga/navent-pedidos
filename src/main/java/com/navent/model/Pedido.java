package com.navent.model;

import com.navent.model.dto.PedidoDTO;

import javax.persistence.*;

@Entity
@Table(name = "Pedido")
public class Pedido {

    @Id
    @GeneratedValue
    @Column(name = "idPedido")
    private Integer idPedido;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "monto")
    private double monto;

    @Column(name = "descuento")
    private int descuento;

    public Pedido() {
        
    }

    public Pedido(PedidoDTO pedidoDTO) {

        this.nombre = pedidoDTO.getNombre();
        this.monto = pedidoDTO.getMonto();
        this.descuento = pedidoDTO.getDescuento();

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
