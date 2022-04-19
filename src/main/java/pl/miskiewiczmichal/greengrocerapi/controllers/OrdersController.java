package pl.miskiewiczmichal.greengrocerapi.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

}
