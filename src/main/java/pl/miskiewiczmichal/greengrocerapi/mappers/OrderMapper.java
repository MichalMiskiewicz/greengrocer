package pl.miskiewiczmichal.greengrocerapi.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import pl.miskiewiczmichal.greengrocerapi.DTOs.OrderDTO;
import pl.miskiewiczmichal.greengrocerapi.entities.Order;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mappings({
            @Mapping(source = "order.id", target = "orderId"),
            @Mapping(source = "order.creationDate", target = "creationDate"),
            @Mapping(source = "order.createdBy", target = "createdBy"),
            @Mapping(source = "order.description", target = "description"),
            @Mapping(source = "order.driver", target = "driver"),
            @Mapping(source = "order.status", target = "status"),
            @Mapping(source = "order.warnings", target = "warnings"),
            @Mapping(source = "order.paymentType", target = "payment")
    })
    OrderDTO mapOrderToOrderDTO(Order order);
}