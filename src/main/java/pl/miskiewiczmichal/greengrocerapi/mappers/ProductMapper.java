package pl.miskiewiczmichal.greengrocerapi.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import pl.miskiewiczmichal.greengrocerapi.DTOs.ProductDTO;
import pl.miskiewiczmichal.greengrocerapi.DTOs.ProductNameDTO;
import pl.miskiewiczmichal.greengrocerapi.entities.Product;
import pl.miskiewiczmichal.greengrocerapi.entities.ProductName;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mappings({
            @Mapping(source = "product.id", target = "productId"),
            @Mapping(source = "product.name", target = "name"),
            @Mapping(source = "product.category.name", target = "category"),
            @Mapping(source = "product.description", target = "description"),
            @Mapping(source = "product.price", target = "price"),
            @Mapping(source = "product.amount", target = "amount"),
            @Mapping(source = "product.imgFileSrc", target = "imgFileSrc"),
            @Mapping(source = "product.measureType", target = "measureType")
    })
    ProductDTO mapProductToProductDTO(Product product);

    @Mappings({
            @Mapping(source = "productName.name", target = "productName")
    })
    ProductNameDTO mapProductNameToProductNameDto(ProductName productName);
}