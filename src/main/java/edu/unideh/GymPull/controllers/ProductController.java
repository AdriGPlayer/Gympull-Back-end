package edu.unideh.GymPull.controllers;

import edu.unideh.GymPull.entity.Product;
import edu.unideh.GymPull.entity.Categoria;
import edu.unideh.GymPull.services.ProductService;
import edu.unideh.GymPull.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/get")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/getByCategory/{categoryId}")
    public ResponseEntity<List<Product>> getProductsByCategoryId(@PathVariable Long categoryId) {
        List<Product> products = productService.getProductsByCategoryId(categoryId);
        if (products.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(products);
    }

    @PostMapping("/post")
    public ResponseEntity<?> addProduct(@RequestBody Product product) {
        Categoria categoria = categoriaService.getCategoriaById(product.getCategoria().getId());
        if (categoria == null)
            return ResponseEntity.badRequest().body("Categoría no encontrada");
        product.setCategoria(categoria);
        Product createdProduct = productService.addProduct(product);
        return ResponseEntity.ok(createdProduct);
    }


}

