package ru.mikhailin.poolapi.clientapi.manager;

import org.apache.http.HttpResponse;
import org.apache.http.message.BasicHttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.mikhailin.poolapi.clientapi.manager.entity.ClientEntity;
import ru.mikhailin.poolapi.clientapi.manager.repository.ClientRepo;

import java.util.List;
import java.util.Optional;

@Component
public class ClientManagerImpl implements ClientManager {
    @Autowired
    private ClientRepo clientRepo;

    @Override
    public List<ClientEntity> getClients() {
        return (List<ClientEntity>) clientRepo.findAll();
    }

    @Override
    public Optional<ClientEntity> getClient(ClientEntity clientEntity) {
        return clientRepo.findById(clientEntity.getId());
    }

    @Override
    public void addClient(ClientEntity clientEntity) {
        clientRepo.save(clientEntity);
    }

    @Override
    public void updateClient(ClientEntity clientEntity) {
        clientRepo.save(clientEntity);
    }
}
