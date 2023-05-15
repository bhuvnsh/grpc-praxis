package com.grpc.grpcpraxis.grpcserver.repositories;

import com.grpc.grpcpraxis.grpcserver.entities.impl.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByUserNameAndPassword(String userName, String passwordHash);
}
