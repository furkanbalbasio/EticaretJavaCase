package com.balbasio.EticaretJavaCase.controller;

import com.balbasio.EticaretJavaCase.dto.request.AddOrderRequestDto;
import com.balbasio.EticaretJavaCase.dto.response.GetOrderResponseDto;
import com.balbasio.EticaretJavaCase.dto.response.ListOrdersByUserResponseDto;
import com.balbasio.EticaretJavaCase.enums.EOrderStatus;
import com.balbasio.EticaretJavaCase.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static com.balbasio.EticaretJavaCase.constant.RestApiUrls.*;

@RestController
@RequestMapping(ORDER)
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping(ADDORDER)
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<Void> addOrder(String token, @RequestBody AddOrderRequestDto dto){
        orderService.addOrder(token, dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get-order/{orderId}")
    public ResponseEntity<GetOrderResponseDto> getOrder(String token,@PathVariable Long orderId){
        return ResponseEntity.ok(orderService.getOrder(token,orderId));
    }

    @GetMapping("/get-order-by-user/{userId}")
    public ResponseEntity<ListOrdersByUserResponseDto> getOrderByUser(@PathVariable Long userId){
        return ResponseEntity.ok(orderService.getOrderByUser(userId));
    }

    @PutMapping("/update-order-status/{orderId}/{status}")
    @PreAuthorize("hasAnyAuthority('SELLER','ADMIN')")
    public ResponseEntity<Boolean> updateOrderStatus(@PathVariable Long orderId, @PathVariable EOrderStatus status) {
        return ResponseEntity.ok(orderService.updateOrderStatus(orderId, status));
    }

    @DeleteMapping("/delete-order/{orderId}")
    public ResponseEntity<Boolean> deleteOrder(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderService.deleteOrder(orderId));
    }

}
