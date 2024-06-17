package ru.techtask.mobilshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.techtask.mobilshop.entity.Phone;
import ru.techtask.mobilshop.exception.ItemAlreadyExistException;
import ru.techtask.mobilshop.exception.ItemNotFoundException;
import ru.techtask.mobilshop.repository.TransactionRepo;
import ru.techtask.mobilshop.service.PhoneService;
import ru.techtask.mobilshop.service.ProcessorsService;

@RestController
@RequestMapping("/mobilshop")
public class RestDBController {

    @Autowired
    private PhoneService phoneService;

    @Autowired
    private ProcessorsService processorsService;

    @Autowired
    private TransactionRepo transactionRepo;

    @PostMapping("/phone/add")
    public ResponseEntity addNewPhone(@RequestBody Phone phone) {
        try {
            return ResponseEntity.ok(phoneService.addNewPhone(phone).getName() + " added successfully");
        } catch (ItemAlreadyExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Some error when adding a new phone");
        }
    }

    @GetMapping("/phone")
    public ResponseEntity getPhoneById(@RequestParam Integer id) {
        try {
            return ResponseEntity.ok(phoneService.getPhoneById(id));
        } catch (ItemNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Some error when searching for a phone by id");
        }
    }

    @DeleteMapping("/phone/{id}")
    public ResponseEntity deletePhoneById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(phoneService.deletePhoneById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Some error when deleting phone");
        }
    }

//    @PatchMapping

    @GetMapping("/processor")
    public ResponseEntity getProcessorById(@RequestParam Integer id) {
        try {
            return ResponseEntity.ok(processorsService.getProcessorById(id));
        } catch (ItemNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Some error when searching for a processor by id");
        }
    }
}
