package com.balbasio.EticaretJavaCase.controller;

import com.balbasio.EticaretJavaCase.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.balbasio.EticaretJavaCase.constant.RestApiUrls.*;

@RestController
@RequestMapping(ADDRESS)
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;
}
