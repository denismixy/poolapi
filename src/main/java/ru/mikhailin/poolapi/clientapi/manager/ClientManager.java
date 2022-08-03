package ru.mikhailin.poolapi.clientapi.manager;

import org.apache.http.HttpResponse;
import ru.mikhailin.poolapi.clientapi.manager.entity.ClientEntity;

import java.util.List;
import java.util.Optional;

public interface ClientManager {
    List<ClientEntity> getClients();
    Optional<ClientEntity> getClient(ClientEntity clientEntity);
    void addClient(ClientEntity clientEntity);
    void updateClient(ClientEntity clientEntity);
}
