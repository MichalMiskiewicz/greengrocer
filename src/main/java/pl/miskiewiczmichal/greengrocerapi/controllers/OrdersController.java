package pl.miskiewiczmichal.greengrocerapi.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.miskiewiczmichal.greengrocerapi.DTOs.AddOrderDTO;
import pl.miskiewiczmichal.greengrocerapi.DTOs.OrderDTO;
import pl.miskiewiczmichal.greengrocerapi.services.OrderService;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RequestMapping("/orders")
@RestController
public class OrdersController {
    private final OrderService orderService;

    @GetMapping("/all")
    public ResponseEntity<List<OrderDTO>> getAllOrders(){

        return ResponseEntity.ok().body(orderService.getAllOrders());
    }

    @GetMapping("/client/{user_id}/all")
    public ResponseEntity<List<OrderDTO>> getAllOrdersById(@PathVariable("user_id") UUID uuid) {

        return ResponseEntity.ok().body(orderService.getAllOrdersByClientId(uuid));
    }

    @GetMapping("/driver/{driver_id}/all")
    public ResponseEntity<List<OrderDTO>> getAllOrdersByDriverId(@PathVariable("driver_id") UUID uuid) {

        return ResponseEntity.ok().body(orderService.getAllOrdersByDriverId(uuid));
    }

    @PostMapping("/add/{user_id}")
    public ResponseEntity<OrderDTO> addNewOrder(@RequestBody AddOrderDTO orderDTO, @PathVariable("user_id") UUID uuid){

        return ResponseEntity.ok().body(orderService.addNewOrder(orderDTO, uuid));
    }

    @PatchMapping("/{order_id}/status-change")
    public ResponseEntity<OrderDTO> updateOrdersStatus(@RequestBody AddOrderDTO orderDTO, @PathVariable("order_id") UUID uuid){

        return ResponseEntity.ok().body(orderService.updateStatus(orderDTO, uuid));
    }

    @PatchMapping("/{order_id}/driver-set/{driver_id}")
    public ResponseEntity<OrderDTO> setDriverToOrder(@PathVariable("order_id") UUID uuid, @PathVariable("driver_id") UUID uuid2){

        return ResponseEntity.ok().body(orderService.setDriver(uuid, uuid2));
    }

}
