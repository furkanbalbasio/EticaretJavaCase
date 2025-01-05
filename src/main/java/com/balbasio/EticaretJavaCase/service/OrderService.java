package com.balbasio.EticaretJavaCase.service;

import com.balbasio.EticaretJavaCase.dto.request.AddOrderDetailsRequestDto;
import com.balbasio.EticaretJavaCase.dto.request.AddOrderRequestDto;
import com.balbasio.EticaretJavaCase.dto.response.GetOrderResponseDto;
import com.balbasio.EticaretJavaCase.dto.response.ListOrdersByUserResponseDto;
import com.balbasio.EticaretJavaCase.enums.EOrderStatus;
import com.balbasio.EticaretJavaCase.exception.ErrorType;
import com.balbasio.EticaretJavaCase.exception.EticaretJavaCaseException;
import com.balbasio.EticaretJavaCase.repository.AddressRepository;
import com.balbasio.EticaretJavaCase.repository.OrderDetailsRepository;
import com.balbasio.EticaretJavaCase.repository.OrderRepository;
import com.balbasio.EticaretJavaCase.repository.ProductRepository;
import com.balbasio.EticaretJavaCase.repository.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserService userService;
    private final ProductRepository productRepository;
    private final OrderDetailsRepository orderDetailsRepository;
    private final AddressRepository addressRepository;

    public void addOrder(String token, AddOrderRequestDto dto) {
        Optional<User> user = userService.tokenConvertUser(token);
        List<AddOrderDetailsRequestDto> addOrderDetailsRequestDtos = dto.getOrderDetailsList();
        Order order = Order.builder()
                .orderStatus(EOrderStatus.PENDING)
                .orderDate(LocalDate.now())
                .orderNo(UUID.randomUUID().toString())
                .userId(user.get().getId())
                .addressId(dto.getAddressId())
                .build();
        for(AddOrderDetailsRequestDto orderDetailsList : addOrderDetailsRequestDtos ){
            Optional<Product> product = productRepository.findById(orderDetailsList.getProductId());
            if(orderDetailsList.getQuantity()>product.get().getStock())
                throw new EticaretJavaCaseException(ErrorType.STOK_TUKENDI);
            OrderDetails orderDetails = OrderDetails.builder()
                    .quantity(orderDetailsList.getQuantity())
                    .orderId(order.getId())
                    .productId(orderDetailsList.getProductId())
                    .totalPrice(product.get().getPrice()*orderDetailsList.getQuantity())
                    .build();
            orderDetailsRepository.save(orderDetails);
            product.get().setStock(product.get().getStock()-orderDetailsList.getQuantity());
            order.setTotalPrice(order.getTotalPrice()+orderDetails.getTotalPrice());
        }
        orderRepository.save(order);
    }

    public GetOrderResponseDto getOrder(String token, Long orderId) {
        Optional<User> user = userService.tokenConvertUser(token);
        Optional<Order> order = orderRepository.findById(orderId);
        Optional<Address> address = addressRepository.findById(order.get().getAddressId());
        GetOrderResponseDto dto = GetOrderResponseDto.builder()
                .orderStatus(order.get().getOrderStatus())
                .orderNo(order.get().getOrderNo())
                .orderDate(order.get().getOrderDate())
                .name(user.get().getName())
                .surname(user.get().getSurname())
                .totalPrice(order.get().getTotalPrice())
                .address(address.get().getAddress())
                .addressType(address.get().getAddressType())
                .build();
        return dto;
    }

    public ListOrdersByUserResponseDto getOrderByUser(Long userId) {
        List<Long> userOrderIds = orderRepository.findAllOrderIdByUserId(userId);
       List<Order> order = orderRepository.findAllById(userOrderIds);
       ListOrdersByUserResponseDto dto = ListOrdersByUserResponseDto.builder()
               .getOrderResponseDtos(order)
               .message("Tüm sipariş listeniz getirilmiştir.")
               .build();
       return dto;
    }

    public Boolean updateOrderStatus(Long orderId, EOrderStatus status) {
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isEmpty()) {
            throw new EticaretJavaCaseException(ErrorType.ORDER_BULUNAMADI);
        }
        order.get().setOrderStatus(status);
        orderRepository.save(order.get());
        return true;
    }

    public Boolean deleteOrder(Long orderId){
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isEmpty()) {
            throw new EticaretJavaCaseException(ErrorType.ORDER_BULUNAMADI);
        }
        orderRepository.delete(order.get());
        return true;
    }

}
