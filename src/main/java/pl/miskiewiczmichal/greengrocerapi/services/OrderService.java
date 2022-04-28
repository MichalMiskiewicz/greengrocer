package pl.miskiewiczmichal.greengrocerapi.services;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
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

    public OrderDTO addNewOrder(AddOrderDTO addOrderDTO, UUID uuid) throws Exception {
        java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());

        //pobranie usera dla orderu
        Optional<User> optionalUser = userRepository.findById(uuid);
        if(optionalUser.isEmpty())
            throw new Exception("User not found!");


        //obiekt z tabeli payment
        Optional<PaymentType> optionalPaymentType = paymentRepository.getByName(addOrderDTO.payment.getName());
        if(optionalPaymentType.isEmpty())
            throw new Exception("Payment type not found!");

        //wywołanie metody
        List<OrderWithProducts> products = addOrderDTO.products.stream().map(x -> {
            try{
               return this.getOrderWithProducts(x);
            }catch (Exception e){
                e.printStackTrace();
                return null;
            }
        }).collect(Collectors.toList());

        products.forEach(x -> {
            updateProductAmount(x.getAmount().intValue(), x.getProduct());
        });

        //tworzenie orderu i dodanie
        Order order = Order.builder().creationDate(date)
                .warnings(addOrderDTO.warnings)
                .createdBy(optionalUser.get())
                .paymentType(optionalPaymentType.get())
                .products(products)
                .build();

        orderRepository.save(order);


        return orderMapper.mapOrderToOrderDTO(order);
    }

    public void updateProductAmount(Integer amount, Product product) {
        Integer productAmount = product.getAmount();
        product.setAmount(productAmount - amount);
        productRepository.save(product);
    }

    private OrderWithProducts getOrderWithProducts(OrderWithProducts product) throws Exception {
        //jeden z zakupionych produktów
        Optional<Product> optionalProduct = productRepository.findById(product.getId());

        if(optionalProduct.isEmpty())
            throw new Exception("Product not found!");

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

    public OrderDTO setDriver(UUID orderId, UUID driverId) throws Exception {
        Order order = new Order();
        User driver = new User();
        if(orderRepository.findById(orderId).isPresent()) {
            order = orderRepository.findById(orderId).get();
        }else {
            throw new Exception("Order not found!");
        }
        if(userRepository.findById(driverId).isPresent()) {
            driver = userRepository.findById(driverId).get();
        }else {
            throw new Exception("Driver not found!");
        }
        order.setDriver(driver);
        orderRepository.save(order);
        return orderMapper.mapOrderToOrderDTO(order);
    }

}
