package pl.miskiewiczmichal.greengrocerapi.controllers;

import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.miskiewiczmichal.greengrocerapi.DTOs.AddProductDTO;
import pl.miskiewiczmichal.greengrocerapi.DTOs.CategoryDTO;
import pl.miskiewiczmichal.greengrocerapi.DTOs.ProductDTO;
import pl.miskiewiczmichal.greengrocerapi.services.ProductService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@CrossOrigin
@AllArgsConstructor
@RequestMapping("/products")
@RestController
public class ProductsController {
    public static String uploadDirectory = "C:\\Users\\mdmis\\Desktop\\INŻYNIERKA\\projekt\\greengrocer-frontend\\src\\assets\\img\\";

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
    public ResponseEntity<ProductDTO> addProduct(@RequestBody AddProductDTO productDTO) throws Exception {

        return ResponseEntity.ok().body(productService.addNewProduct(productDTO));
    }

    @PatchMapping("/amount/{product_id}/{product_amount}")
    public ResponseEntity<ProductDTO> setProductAmount(@PathVariable("product_id") UUID uuid, @PathVariable("product_amount") Integer amount) throws Exception {

        return ResponseEntity.ok().body(productService.updateProductsAmount(uuid, amount));
    }

    @PostMapping(value = "/upload",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE} )
    public ResponseEntity<String> uploadSingleFileExample4(@RequestBody MultipartFile file) throws IOException {
        StringBuilder fileName = new StringBuilder();
        Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
        fileName.append(file.getOriginalFilename());
        Files.write(fileNameAndPath, file.getBytes());

        return ResponseEntity.ok().body(fileNameAndPath + "");
    }

}
