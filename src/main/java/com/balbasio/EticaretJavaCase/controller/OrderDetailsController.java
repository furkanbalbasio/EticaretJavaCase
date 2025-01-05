package com.balbasio.EticaretJavaCase.controller;

import com.balbasio.EticaretJavaCase.service.OrderDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.balbasio.EticaretJavaCase.constant.RestApiUrls.*;

@RestController
@RequestMapping(ORDERDETAILS)
@RequiredArgsConstructor
public class OrderDetailsController {
    private final OrderDetailsService orderDetailsService;
}
