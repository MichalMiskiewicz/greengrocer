package pl.miskiewiczmichal.greengrocerapi.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.miskiewiczmichal.greengrocerapi.DTOs.AddOrderDTO;
import pl.miskiewiczmichal.greengrocerapi.DTOs.OrderDTO;
import pl.miskiewiczmichal.greengrocerapi.entities.*;
import pl.miskiewiczmichal.greengrocerapi.mappers.OrderMapper;
import pl.miskiewiczmichal.greengrocerapi.repositories.*;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@Transactional
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final UserRepository userRepository;
    private final PaymentRepository paymentRepository;
    private final OrderWithProductsRepository orderWithProductsRepository;
    private final ProductRepository productRepository;

    public List<OrderDTO> getAllOrders(){
        List<Order> orders = orderRepository.findAll();

        return orders.stream().map(orderMapper::mapOrderToOrderDTO).collect(Collectors.toList());
    }

    public List<OrderDTO> getAllOrdersByClientId(UUID uuid){
        List<Order> orders = orderRepository.getAllByCreatedBy_IdOrderByCreationDateDesc(uuid);

        return orders.stream().map(orderMapper::mapOrderToOrderDTO).collect(Collectors.toList());
    }

    public List<OrderDTO> getAllOrdersByDriverId(UUID uuid){
        List<Order> orders = orderRepository.getAllByDriver_IdOrderByCreationDateDesc(uuid);

        return orders.stream().map(orderMapper::mapOrderToOrderDTO).collect(Collectors.toList());
    }

    public OrderDTO addNewOrder(AddOrderDTO addOrderDTO, UUID uuid){
        java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());

        //pobranie usera dla orderu
        Optional<User> optionalUser = userRepository.findById(uuid);

        //obiekt z tabeli payment
        Optional<PaymentType> optionalPaymentType = paymentRepository.getByName(addOrderDTO.payment.getName());

        //wywołanie metody
        List<OrderWithProducts> ord = addOrderDTO.products.stream().map(this::getOrderWithProducts).collect(Collectors.toList());
        //productRepository.

        ord.stream().forEach(x -> {
            updateProductAmount(x.getAmount().intValue(), x.getProduct());
        });

        //tworzenie orderu i dodanie
        Order order = Order.builder().creationDate(date)
                .description(addOrderDTO.description)
                .warnings(addOrderDTO.warnings)
                .createdBy(optionalUser.get())
                .paymentType(optionalPaymentType.get())
                .products(ord)
                .build();
        orderRepository.save(order);

        return orderMapper.mapOrderToOrderDTO(order);
    }

    public void updateProductAmount(Integer amount, Product product) {
        Integer productAmount = product.getAmount();
        product.setAmount(productAmount - amount);
        productRepository.save(product);
    }

    private OrderWithProducts getOrderWithProducts(OrderWithProducts product) {
        //jeden z zakupionych produktów
        Optional<Product> optionalProduct = productRepository.findById(product.getId());

        //zapisanie do tabeli order_products_tb produktu i ilosc
        OrderWithProducts orderWithProducts = OrderWithProducts.builder().amount(product.getAmount())
                .product(optionalProduct.get())
                .build();
        orderWithProductsRepository.save(orderWithProducts);
        return orderWithProducts;
    }

    public OrderDTO updateStatus(AddOrderDTO orderDTO, UUID  uuid){
        Order order = orderRepository.getById(uuid);
        order.setStatus(orderDTO.status);
        orderRepository.save(order);
        return orderMapper.mapOrderToOrderDTO(order);
    }

    public OrderDTO setDriver(UUID orderId, UUID driverId){
        Order order = orderRepository.findById(orderId).get();
        User driver = userRepository.findById(driverId).get();
        order.setDriver(driver);
        orderRepository.save(order);
        return orderMapper.mapOrderToOrderDTO(order);
    }

}
