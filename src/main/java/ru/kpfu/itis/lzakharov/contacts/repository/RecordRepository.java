package ru.kpfu.itis.lzakharov.contacts.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.lzakharov.contacts.model.Client;
import ru.kpfu.itis.lzakharov.contacts.model.Record;

import java.util.List;

@Repository
public interface RecordRepository extends CrudRepository<Record, Long> {
    List<Record> findByClient(Client client);
}
