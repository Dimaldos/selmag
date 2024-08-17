package ru.dimaldos.selmag.managerapp;

import org.springframework.data.repository.CrudRepository;
import ru.dimaldos.selmag.managerapp.entity.SelmagUser;

import java.util.Optional;

public interface SelmagUserRepository extends CrudRepository<SelmagUser, Integer> {

    Optional<SelmagUser> findByUsername(String username);
}
