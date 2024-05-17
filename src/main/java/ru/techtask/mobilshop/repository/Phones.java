package ru.techtask.mobilshop.repository;

import ru.techtask.mobilshop.model.Phone;

import java.util.List;

public interface Phones {
    List<String> listPhoneNames();
    Integer addPhone(Phone newPhone);
}
