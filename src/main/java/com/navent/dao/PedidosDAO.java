package com.navent.dao;

import com.navent.exceptions.PedidoPersistenceException;
import com.navent.model.Pedido;
import org.springframework.stereotype.Component;

@Component
public class PedidosDAO {

    /**
     * Inserta un nuevo pedido en la base de datos, o actualiza uno existente
     *
     * @param pedido - pedido a insertar o modificar
     */
    public Integer insertOrUpdate(Pedido pedido) throws PedidoPersistenceException {

        System.out.println("Insertando o actualizando el pedido " + pedido.getNombre());
        return 1;

    }

    /**
     * Elimina un pedido
     *
     * @param pedido - pedido a eliminar
     */
    public void delete(Pedido pedido) throws PedidoPersistenceException {

        System.out.println("Eliminando pedido " + pedido.getNombre());

    }

    /**
     * Busca un pedido por id
     *
     * @param idPedido - id del pedido a buscar
     * @return -
     * @throws PedidoPersistenceException -
     */
    public Pedido select(Integer idPedido) throws PedidoPersistenceException {

        System.out.println("Buscando pedido con id: " + idPedido);
        return new Pedido();

    }

}
