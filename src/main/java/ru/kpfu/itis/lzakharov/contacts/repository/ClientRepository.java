package ru.kpfu.itis.lzakharov.contacts.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.lzakharov.contacts.model.Client;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {
    Client findByUsernameAndPassword(String username, String password);
}
