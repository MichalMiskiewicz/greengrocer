package pl.miskiewiczmichal.greengrocerapi.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.miskiewiczmichal.greengrocerapi.DTOs.OrderDTO;
import pl.miskiewiczmichal.greengrocerapi.entities.Order;
import pl.miskiewiczmichal.greengrocerapi.mappers.OrderMapper;
import pl.miskiewiczmichal.greengrocerapi.repositories.OrderRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public List<OrderDTO> getAllOrders(){
        List<Order> orders = orderRepository.findAll();

        return orders.stream().map(orderMapper::mapOrderToOrderDTO).collect(Collectors.toList());
    }

    public List<OrderDTO> getAllOrdersByClientId(UUID uuid){
        List<Order> orders = orderRepository.getAllByCreatedBy_Id(uuid);

        return orders.stream().map(orderMapper::mapOrderToOrderDTO).collect(Collectors.toList());
    }

    public List<OrderDTO> getAllOrdersByDriverId(UUID uuid){
        List<Order> orders = orderRepository.getAllByDriver_Id(uuid);

        return orders.stream().map(orderMapper::mapOrderToOrderDTO).collect(Collectors.toList());
    }


}
