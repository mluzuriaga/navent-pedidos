package com.navent.service.impl;

import com.navent.dao.PedidosDAO;
import com.navent.exceptions.PedidoPersistenceException;
import com.navent.model.Pedido;
import com.navent.model.dto.PedidoDTO;
import com.navent.service.PedidosService;
import com.navent.utils.BumexMemcached;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidosServiceImpl implements PedidosService {

    @Autowired
    private PedidosDAO pedidosDAO;

    /**
     * Da de alta un nuevo pedido
     *
     * @param pedidoDTO - informacion del pedido a actualizar
     * @return -
     * @throws PedidoPersistenceException -
     */
    @Override
    public Integer newPedido(PedidoDTO pedidoDTO) throws PedidoPersistenceException {

        // Creo y persisto el pedido a partir de la informacion recibida
        Pedido pedido = new Pedido(pedidoDTO);
        return this.pedidosDAO.insertOrUpdate(pedido);

    }

    /**
     * Modifica un pedido
     *
     * @param idPedido  - id del pedido a modificar
     * @param pedidoDTO - informacion del pedido a modificar
     * @throws PedidoPersistenceException -
     */
    @Override
    public void modificarPedido(Integer idPedido, PedidoDTO pedidoDTO) throws PedidoPersistenceException {

        // Busco el pedido en la base de datos
        Pedido persistedPedido = this.pedidosDAO.select(idPedido);

        // Actualizo la informacion del pedido
        persistedPedido.setNombre(pedidoDTO.getNombre());
        persistedPedido.setDescuento(pedidoDTO.getDescuento());
        persistedPedido.setMonto(pedidoDTO.getMonto());

        // Actualizo el pedido en la base de datos
        this.pedidosDAO.insertOrUpdate(persistedPedido);

        // Consulto si el pedido esta en la cache
        Pedido pedidoCacheado = (Pedido) BumexMemcached.getInstance().get(idPedido.toString());
        if (pedidoCacheado != null) {
            // Si el pedido existe en la cache lo elimino y lo cargo actualizado
            BumexMemcached.getInstance().delete(idPedido.toString());
            BumexMemcached.getInstance().set(idPedido.toString(), persistedPedido);
        }

    }

    /**
     * Obtengo un pedido por id
     *
     * @param idPedido - id del pedido
     * @return -
     * @throws PedidoPersistenceException -
     */
    @Override
    public PedidoDTO getPedidoPorId(Integer idPedido) throws PedidoPersistenceException {

        // Primero busco el pedido en la cache a partir de su id
        Pedido pedidoCacheado = (Pedido) BumexMemcached.getInstance().get(idPedido.toString());
        if (pedidoCacheado != null)
            // Si el pedido existe entonces lo retorno
            return new PedidoDTO(pedidoCacheado);

        // Si no esta en la cache lo busco en la base de datos
        Pedido pedido = this.pedidosDAO.select(idPedido);
        // Luego lo cargo en la cache y lo retorno
        BumexMemcached.getInstance().set(pedido.getIdPedido().toString(), pedido);
        return new PedidoDTO(pedido);

    }

    /**
     * Elimino el pedido
     *
     * @param idPedido - id del pedido a eliminar
     * @throws PedidoPersistenceException -
     */
    @Override
    public void eliminarPedido(Integer idPedido) throws PedidoPersistenceException {

        // Elimino el pedido de la cache, si no existe no hara nada
        BumexMemcached.getInstance().delete(idPedido.toString());

        // Elimino el pedido de la base de datos
        Pedido pedido = this.pedidosDAO.select(idPedido);
        this.pedidosDAO.delete(pedido);

    }

}
