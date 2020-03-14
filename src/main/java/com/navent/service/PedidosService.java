package com.navent.service;

import com.navent.exceptions.PedidoPersistenceException;
import com.navent.model.dto.PedidoDTO;

public interface PedidosService {

    Integer newPedido(PedidoDTO pedidoDTO) throws PedidoPersistenceException;

    void modificarPedido(Integer idPedido, PedidoDTO pedidoDTO) throws PedidoPersistenceException;

    PedidoDTO getPedidoPorId(Integer idPedido) throws PedidoPersistenceException;

    void eliminarPedido(Integer idPedido) throws PedidoPersistenceException;

}
