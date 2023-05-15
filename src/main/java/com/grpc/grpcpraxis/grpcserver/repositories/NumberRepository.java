package com.grpc.grpcpraxis.grpcserver.repositories;

import com.grpc.grpcpraxis.grpcserver.entities.NumberFactorResult;
import com.grpc.grpcpraxis.grpcserver.entities.impl.Number;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NumberRepository extends JpaRepository<Number, Long> {
    @Query("SELECT n.value as value, f.value as factorValue, n.value * f.value as result " +
            "FROM Number n JOIN Factor f ON n.value = f.value")
    List<NumberFactorResult> getValue();

}

