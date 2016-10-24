package ru.kpfu.itis.lzakharov.contacts.service;

import ru.kpfu.itis.lzakharov.contacts.model.Client;

public interface ClientService {
    Client create(Client client);
    Client find(String username);
    Client find(String username, String password);
}
