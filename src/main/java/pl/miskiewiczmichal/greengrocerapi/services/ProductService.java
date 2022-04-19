package pl.miskiewiczmichal.greengrocerapi.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.miskiewiczmichal.greengrocerapi.DTOs.AddProductDTO;
import pl.miskiewiczmichal.greengrocerapi.DTOs.ProductDTO;
import pl.miskiewiczmichal.greengrocerapi.entities.Category;
import pl.miskiewiczmichal.greengrocerapi.entities.Product;
import pl.miskiewiczmichal.greengrocerapi.mappers.ProductMapper;
import pl.miskiewiczmichal.greengrocerapi.repositories.CategoryRepository;
import pl.miskiewiczmichal.greengrocerapi.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;

    public List<ProductDTO> getAllProducts(){
        List<Product> products = productRepository.findAll();
       // List<Product> productsd = productRepository.getAllByCategory("jablka");

        return products.stream().map(productMapper::mapProductToProductDTO).collect(Collectors.toList());
    }

    public ProductDTO addNewProduct(AddProductDTO productDTO){

        Category category = getCategory(productDTO.category);

        Product product = Product.builder().name(productDTO.name)
                .description(productDTO.description)
                .category(category)
                .price(productDTO.price)
                .build();
        productRepository.save(product);

        return productMapper.mapProductToProductDTO(product);
    }

    private Category getCategory(String categoryName) {
        Optional<Category> optionalCategory = categoryRepository.getCategoryByName(categoryName);
        Category category;

        if (optionalCategory.isEmpty()) {
            category = Category.builder().name(categoryName).build();
            categoryRepository.save(category);
        } else {
            category = optionalCategory.get();
        }
        return category;
    }
}
