package com.balbasio.EticaretJavaCase.repository;

import com.balbasio.EticaretJavaCase.repository.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Long> {
}
