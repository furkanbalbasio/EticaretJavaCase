package com.balbasio.EticaretJavaCase.repository;

import com.balbasio.EticaretJavaCase.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findOptionalById(Long id);

    Optional<User> findOptionalByUsernameAndPassword(String username, String password);
}
