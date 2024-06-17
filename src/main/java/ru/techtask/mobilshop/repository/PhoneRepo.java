package ru.techtask.mobilshop.repository;

import org.springframework.data.repository.CrudRepository;
import ru.techtask.mobilshop.entity.Phone;

public interface PhoneRepo extends CrudRepository<Phone, Integer> {
    Phone findByName(String name);
}
