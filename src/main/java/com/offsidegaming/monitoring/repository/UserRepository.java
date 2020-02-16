package com.offsidegaming.monitoring.repository;

import com.offsidegaming.monitoring.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.NotNull;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(@NotNull Long userId);

    boolean existsById(@NotNull Long userId);
}
