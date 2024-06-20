package ru.techtask.mobilshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.techtask.mobilshop.entity.Phone;
import ru.techtask.mobilshop.exception.ItemAlreadyExistException;
import ru.techtask.mobilshop.exception.ItemNotFoundException;
import ru.techtask.mobilshop.model.PhoneModel;
import ru.techtask.mobilshop.repository.PhoneRepo;

import java.util.Objects;
import java.util.Optional;

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

    public Phone updatePhone(Phone phone, Integer id) throws ItemAlreadyExistException {
        phoneRepo.findById(id).orElseThrow(() -> new ItemNotFoundException("Phone not found"));
        Integer phoneIdUniqueName = Optional.ofNullable(phoneRepo.findByName(phone.getName())).map(Phone::getId).orElse(id);
        if (!Objects.equals(phoneIdUniqueName, id)) {
            throw new ItemAlreadyExistException("Phone with this name already exist");
        }
        phone.setId(id);
        return phoneRepo.save(phone);
    }
}