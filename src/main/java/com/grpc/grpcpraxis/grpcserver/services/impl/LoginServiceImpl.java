package com.grpc.grpcpraxis.grpcserver.services.impl;

import com.grpc.grpcpraxis.grpcserver.entities.impl.User;
import com.grpc.grpcpraxis.grpcserver.repositories.UserRepository;
import com.grpc.grpcpraxis.grpcserver.services.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

    private final UserRepository userRepository;

    @Autowired
    public LoginServiceImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValidUser(String userName, String password) {
        try {
            List<User> users = userRepository.findByUserNameAndPassword(userName, password);
            return Objects.nonNull(users) && !users.isEmpty();
        } catch (Exception e) {
            log.error("DB exception :{}", e);
            System.out.println(e);
        }
        return false;
    }
}