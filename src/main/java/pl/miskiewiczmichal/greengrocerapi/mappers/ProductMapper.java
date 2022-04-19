package pl.miskiewiczmichal.greengrocerapi.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import pl.miskiewiczmichal.greengrocerapi.DTOs.ProductDTO;
import pl.miskiewiczmichal.greengrocerapi.entities.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mappings({
            @Mapping(source = "product.id", target = "productId"),
            @Mapping(source = "product.name", target = "name"),
            @Mapping(source = "product.category.name", target = "category"),
            @Mapping(source = "product.description", target = "description"),
            @Mapping(source = "product.price", target = "price")
    })
    ProductDTO mapProductToProductDTO(Product product);
}