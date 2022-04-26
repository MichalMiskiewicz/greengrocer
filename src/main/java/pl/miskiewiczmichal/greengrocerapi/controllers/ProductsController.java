package pl.miskiewiczmichal.greengrocerapi.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.miskiewiczmichal.greengrocerapi.DTOs.AddProductDTO;
import pl.miskiewiczmichal.greengrocerapi.DTOs.CategoryDTO;
import pl.miskiewiczmichal.greengrocerapi.DTOs.ProductDTO;
import pl.miskiewiczmichal.greengrocerapi.services.ProductService;

import java.util.List;

@CrossOrigin
@AllArgsConstructor
@RequestMapping("/products")
@RestController
public class ProductsController {

    private final ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<List<ProductDTO>> getProductsList(){
        return ResponseEntity.ok().body(productService.getAllProducts());
    }

    @GetMapping("/categories/all")
    public ResponseEntity<List<CategoryDTO>> getCategoriesList() {
        return ResponseEntity.ok().body(productService.getAllCategories());
    }

    @PostMapping("/add")
    public ResponseEntity<ProductDTO> addProduct(@RequestBody AddProductDTO productDTO){

        return ResponseEntity.ok().body(productService.addNewProduct(productDTO));
    }
}
