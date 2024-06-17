package ru.techtask.mobilshop.repository;

import ru.techtask.mobilshop.model.PhoneModel;

import java.util.List;

public interface Phones {
    List<String> listPhoneNames();

    Integer addPhone(PhoneModel newPhone);

    PhoneModel getPhone(String findPhone);

    Integer updatePhone(PhoneModel updatePhone);

    Integer deletePhone(String findPhone, Boolean cascade);

    List<PhoneModel> listPhones();
}
