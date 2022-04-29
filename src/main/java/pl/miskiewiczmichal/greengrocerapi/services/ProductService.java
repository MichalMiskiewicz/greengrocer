package pl.miskiewiczmichal.greengrocerapi.services;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.miskiewiczmichal.greengrocerapi.DTOs.AddProductDTO;
import pl.miskiewiczmichal.greengrocerapi.DTOs.CategoryDTO;
import pl.miskiewiczmichal.greengrocerapi.DTOs.ProductDTO;
import pl.miskiewiczmichal.greengrocerapi.entities.Category;
import pl.miskiewiczmichal.greengrocerapi.entities.MeasureType;
import pl.miskiewiczmichal.greengrocerapi.entities.Product;
import pl.miskiewiczmichal.greengrocerapi.mappers.CategoryMapper;
import pl.miskiewiczmichal.greengrocerapi.mappers.ProductMapper;
import pl.miskiewiczmichal.greengrocerapi.repositories.CategoryRepository;
import pl.miskiewiczmichal.greengrocerapi.repositories.MeasureTypeRepository;
import pl.miskiewiczmichal.greengrocerapi.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;
    private final MeasureTypeRepository measureTypeRepository;

    public List<ProductDTO> getAllProducts(){
        List<Product> products = (List<Product>) productRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));

        return products.stream().map(productMapper::mapProductToProductDTO).collect(Collectors.toList());
    }

    public ProductDTO addNewProduct(AddProductDTO productDTO) throws Exception {

        Category category = getCategory(productDTO.category);
        Optional<MeasureType> optionalMeasureType = Optional.ofNullable(measureTypeRepository.getByName(productDTO.measureType));
        if(optionalMeasureType.isEmpty()){
            throw new Exception("Measure type not found!");
        }

        Product product = Product.builder().name(productDTO.name)
                .description(productDTO.description)
                .category(category)
                .price(productDTO.price)
                .amount(productDTO.amount)
                .imgFileSrc(productDTO.imgFileSrc)
                .measureType(optionalMeasureType.get())
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

    public List<CategoryDTO> getAllCategories(){
        List<Category> categories = categoryRepository.findAll();
        System.out.println(categories);
        return categories.stream().map(categoryMapper::mapCategoryToCategoryDTO).collect(Collectors.toList());
    }
}
