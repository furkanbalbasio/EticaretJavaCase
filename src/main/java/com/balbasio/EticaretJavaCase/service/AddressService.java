package com.balbasio.EticaretJavaCase.service;

import com.balbasio.EticaretJavaCase.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;
}
