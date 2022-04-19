package pl.miskiewiczmichal.greengrocerapi.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.miskiewiczmichal.greengrocerapi.DTOs.AddOrderDTO;
import pl.miskiewiczmichal.greengrocerapi.DTOs.OrderDTO;
import pl.miskiewiczmichal.greengrocerapi.entities.Order;
import pl.miskiewiczmichal.greengrocerapi.entities.OrderWithProducts;
import pl.miskiewiczmichal.greengrocerapi.entities.PaymentType;
import pl.miskiewiczmichal.greengrocerapi.entities.User;
import pl.miskiewiczmichal.greengrocerapi.mappers.OrderMapper;
import pl.miskiewiczmichal.greengrocerapi.repositories.OrderRepository;
import pl.miskiewiczmichal.greengrocerapi.repositories.OrderWithProductsRepository;
import pl.miskiewiczmichal.greengrocerapi.repositories.PaymentRepository;
import pl.miskiewiczmichal.greengrocerapi.repositories.UserRepository;

import java.sql.Driver;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final UserRepository userRepository;
    private final PaymentRepository paymentRepository;
    private final OrderWithProductsRepository orderWithProductsRepository;

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

    public OrderDTO addNewOrder(AddOrderDTO addOrderDTO, UUID uuid){
        java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());


        Optional<User> optionalUser = userRepository.findById(uuid);
        Optional<PaymentType> optionalPaymentType = paymentRepository.getByName(addOrderDTO.payment.getName());

        Order order = Order.builder().creationDate(date)
                .description(addOrderDTO.description)
                .warnings(addOrderDTO.warnings)
                .createdBy(optionalUser.get())
                .paymentType(optionalPaymentType.get())
                //.products(addOrderDTO.products)
                .build();
        orderRepository.save(order);

        return orderMapper.mapOrderToOrderDTO(order);
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
/*


    public UserDTO addNewUser(AddUserDTO userDTO){

        Address address = getAddress(userDTO.address);
        Optional<UserType> optionalUserType = userTypeRepository.getAllByName(userDTO.userType.getName());

        User user = User.builder().username(userDTO.username)
                .name(userDTO.name)
                .surname(userDTO.surname)
                .emailAddress(userDTO.eMail)
                .telNumber(userDTO.telNumber)
                .address(address)
                .userType(optionalUserType.get())
                .build();
        userRepository.save(user);

        return userMapper.mapUserToUserDTO(user);
    }

    private Address getAddress(Address address) {
        Address newAddress;
        Optional<Address> optionalAddress = addressRepository
                .getAddressByCityAndAndStreetAndHouseNumber(
                        address.getCity(),
                        address.getStreet(),
                        address.getHouseNumber());

        if(optionalAddress.isEmpty()){
            newAddress = Address.builder().city(address.getCity())
                    .houseNumber(address.getHouseNumber())
                    .street(address.getZipCode())
                    .zipCode(address.getZipCode())
                    .build();
            addressRepository.save(newAddress);
        } else{
            newAddress = optionalAddress.get();
        }
        return newAddress;
    }*/
