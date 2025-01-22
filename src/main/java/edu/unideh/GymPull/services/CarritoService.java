package edu.unideh.GymPull.services;

import edu.unideh.GymPull.entity.Carrito;
import edu.unideh.GymPull.repositories.CarritoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;

    public List<Carrito> obtenerTodos() {
        return carritoRepository.findAll();
    }

    public Optional<Carrito> obtenerPorId(Long id) {
        return carritoRepository.findById(id);
    }

    public Carrito crearCarrito(Carrito carrito) {
        return carritoRepository.save(carrito);
    }

    public Carrito actualizarCarrito(Long id, Carrito carritoDetalles) {
        return carritoRepository.findById(id).map(carrito -> {
            carrito.setIdCliente(carritoDetalles.getIdCliente());
            carrito.setIdProducto(carritoDetalles.getIdProducto());
            carrito.setCantidad(carritoDetalles.getCantidad());
            carrito.setPrecio(carritoDetalles.getPrecio());
            carrito.setTotal(carritoDetalles.getCantidad() * carritoDetalles.getPrecio());
            carrito.setFecha(carritoDetalles.getFecha());
            return carritoRepository.save(carrito);
        }).orElseThrow(() -> new RuntimeException("Carrito no encontrado"));
    }

    public void eliminarCarrito(Long id) {
        carritoRepository.deleteById(id);
    }
}