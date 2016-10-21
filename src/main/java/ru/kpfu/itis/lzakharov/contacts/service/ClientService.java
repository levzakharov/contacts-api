package ru.kpfu.itis.lzakharov.contacts.service;

import ru.kpfu.itis.lzakharov.contacts.model.Client;

public interface ClientService {
    Client find(String username, String password);
}
