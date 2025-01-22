package edu.unideh.GymPull.controllers;


import edu.unideh.GymPull.entity.Carrito;

import edu.unideh.GymPull.services.CarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carritos")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    @GetMapping
    public List<Carrito> obtenerTodos() {
        return carritoService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carrito> obtenerPorId(@PathVariable Long id) {
        return carritoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Carrito crearCarrito(@RequestBody Carrito carrito) {
        carrito.setTotal(carrito.getCantidad() * carrito.getPrecio());
        return carritoService.crearCarrito(carrito);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Carrito> actualizarCarrito(@PathVariable Long id, @RequestBody Carrito carritoDetalles) {
        try {
            Carrito carritoActualizado = carritoService.actualizarCarrito(id, carritoDetalles);
            return ResponseEntity.ok(carritoActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCarrito(@PathVariable Long id) {
        carritoService.eliminarCarrito(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/sacar")
    public ResponseEntity<String> sacarCarro(@RequestParam Long idCliente, @RequestBody List<Carrito> productos) {

        if (productos == null || productos.isEmpty()) {
            return ResponseEntity.badRequest().body("El carrito está vacío.");
        }

        for (Carrito producto : productos) {
            producto.setIdCliente(idCliente);

            carritoService.crearCarrito(producto);
        }

        return ResponseEntity.ok("Carro procesado exitosamente para el usuario ID: " + idCliente);
    }
}
