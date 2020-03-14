package com.navent.controllers;

import com.navent.exceptions.PedidoPersistenceException;
import com.navent.model.dto.PedidoDTO;
import com.navent.service.PedidosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.ForkJoinPool;

/**
 * Controlador que expone los endpoints para trabajar con los pedidos.
 * Se usara DeferredResult para manejar la concurrencia de consultas por los usuarios
 * (Non-Blocking REST)
 * Alternativas:
 * - Configurar el thread pool size del tomcat embebido
 * - Usar reactive programming con spring web flux
 */
@RestController
@RequestMapping("/pedidos")
public class PedidosController {

    @Autowired
    private PedidosService pedidosService;

    /**
     * Da de alta un nuevo pedido
     *
     * @param pedidoDTO - pedido a dar de alta
     * @return -
     */
    @PostMapping
    public DeferredResult<ResponseEntity<?>> newPedido(@RequestBody PedidoDTO pedidoDTO) {

        DeferredResult<ResponseEntity<?>> response = new DeferredResult<>();

        ForkJoinPool.commonPool().submit(() -> {

            try {

                Integer idPedido = this.pedidosService.newPedido(pedidoDTO);
                response.setResult(new ResponseEntity<Integer>(idPedido, HttpStatus.OK));

            } catch (PedidoPersistenceException e) {

                e.printStackTrace();
                response.setResult(new ResponseEntity<String>("Error al persistir el pedido.", HttpStatus.INTERNAL_SERVER_ERROR));

            }

        });

        return response;

    }

    /**
     * Modifica un pedido
     *
     * @param idPedido  - id del pedido a modificar
     * @param pedidoDTO - pedido con los datos a modificar
     * @return -
     */
    @PutMapping("/{idPedido}")
    public DeferredResult<ResponseEntity<?>> modificarPedido(@PathVariable("idPedido") Integer idPedido, @RequestBody PedidoDTO pedidoDTO) {

        DeferredResult<ResponseEntity<?>> response = new DeferredResult<>();

        ForkJoinPool.commonPool().submit(() -> {

            try {

                this.pedidosService.modificarPedido(idPedido, pedidoDTO);
                response.setResult(new ResponseEntity<String>("Pedido modificado con exito.", HttpStatus.OK));

            } catch (PedidoPersistenceException e) {

                e.printStackTrace();
                response.setResult(new ResponseEntity<String>("Error al modificar el pedido.", HttpStatus.INTERNAL_SERVER_ERROR));

            }

        });

        return response;

    }

    /**
     * Obtiene el pedido por id
     *
     * @param idPedido - id del pedido a obtener
     * @return - retorna el pedido solicitado
     */
    @GetMapping("/{idPedido}")
    public DeferredResult<ResponseEntity<?>> getPedidoPorId(@PathVariable("idPedido") Integer idPedido) {

        DeferredResult<ResponseEntity<?>> response = new DeferredResult<>();

        ForkJoinPool.commonPool().submit(() -> {

            try {

                PedidoDTO pedidoDTO = this.pedidosService.getPedidoPorId(idPedido);
                response.setResult(new ResponseEntity<>(pedidoDTO, HttpStatus.OK));

            } catch (PedidoPersistenceException e) {

                e.printStackTrace();
                response.setResult(new ResponseEntity<String>("Error al obtener el pedido.", HttpStatus.INTERNAL_SERVER_ERROR));

            }

        });

        return response;

    }

    /**
     * Elimina un pedido por id
     *
     * @return -
     */
    @DeleteMapping("/{idPedido}")
    public DeferredResult<ResponseEntity<?>> eliminarPedido(@PathVariable("idPedido") Integer idPedido) {

        DeferredResult<ResponseEntity<?>> response = new DeferredResult<>();

        ForkJoinPool.commonPool().submit(() -> {

            try {

                this.pedidosService.eliminarPedido(idPedido);
                response.setResult(new ResponseEntity<String>("Pedido eliminado con exito.", HttpStatus.OK));

            } catch (PedidoPersistenceException e) {

                e.printStackTrace();
                response.setResult(new ResponseEntity<String>("Error al eliminar el pedido.", HttpStatus.INTERNAL_SERVER_ERROR));

            }

        });

        return response;

    }

}
