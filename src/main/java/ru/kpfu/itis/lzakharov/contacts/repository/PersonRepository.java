package ru.kpfu.itis.lzakharov.contacts.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.lzakharov.contacts.model.Person;

@Repository
public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {
}
