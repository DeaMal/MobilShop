package ru.techtask.mobilshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.techtask.mobilshop.entity.Phone;
import ru.techtask.mobilshop.exception.ItemAlreadyExistException;
import ru.techtask.mobilshop.exception.ItemNotFoundException;
import ru.techtask.mobilshop.model.PhoneModel;
import ru.techtask.mobilshop.repository.PhoneRepo;

import java.util.Objects;

@Service
public class PhoneService {

    @Autowired
    private PhoneRepo phoneRepo;

    public Phone addNewPhone(Phone newPhone) throws ItemAlreadyExistException {
        if (Objects.nonNull(phoneRepo.findByName(newPhone.getName()))) {
            throw new ItemAlreadyExistException("Phone already exist");
        }
        return phoneRepo.save(newPhone);
    }

    public PhoneModel getPhoneById(Integer phoneId) {
        return PhoneModel.toModel(phoneRepo.findById(phoneId).orElseThrow(() -> new ItemNotFoundException("Phone not found")));
    }

    public Integer deletePhoneById(Integer phoneId) {
        phoneRepo.deleteById(phoneId);
        return phoneId;
    }
}
