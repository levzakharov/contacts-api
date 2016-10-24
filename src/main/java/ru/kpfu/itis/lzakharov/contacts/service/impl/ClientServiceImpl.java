package ru.kpfu.itis.lzakharov.contacts.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.lzakharov.contacts.model.Client;
import ru.kpfu.itis.lzakharov.contacts.repository.ClientRepository;
import ru.kpfu.itis.lzakharov.contacts.service.ClientService;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    ClientRepository clientRepository;

    @Override
    public Client create(Client client) {
        if (clientRepository.findByUsername(client.getUsername()) == null) {
            return clientRepository.save(client);
        } else {
            // TODO: throw exception
            return null;
        }
    }

    @Override
    public Client find(String username) {
        return clientRepository.findByUsername(username);
    }

    @Override
    public Client find(String username, String password) {
        return clientRepository.findByUsernameAndPassword(username, password);
    }
}
