package ru.mikhailin.poolapi.clientapi.manager.repository;

import org.springframework.data.repository.CrudRepository;
import ru.mikhailin.poolapi.clientapi.manager.entity.ClientEntity;

public interface ClientRepo extends CrudRepository<ClientEntity, Integer> {
}
