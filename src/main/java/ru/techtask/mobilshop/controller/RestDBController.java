package ru.techtask.mobilshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.techtask.mobilshop.entity.Phone;
import ru.techtask.mobilshop.entity.Processors;
import ru.techtask.mobilshop.entity.Transaction;
import ru.techtask.mobilshop.exception.ItemAlreadyExistException;
import ru.techtask.mobilshop.exception.ItemNotFoundException;
import ru.techtask.mobilshop.service.PhoneService;
import ru.techtask.mobilshop.service.ProcessorsService;
import ru.techtask.mobilshop.service.TransactionService;

@RestController
@RequestMapping("/mobilshop")
public class RestDBController {

    @Autowired
    private PhoneService phoneService;

    @Autowired
    private ProcessorsService processorsService;

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/phone/add")
    public ResponseEntity addNewPhone(@RequestBody Phone phone) {
        try {
            return ResponseEntity.ok("Phone '" + phoneService.addNewPhone(phone).getName() + "' added successfully");
        } catch (ItemAlreadyExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error add a new phone: " + e.getMessage());
        }
    }

    @GetMapping("/phone")
    public ResponseEntity getPhoneById(@RequestParam Integer id) {
        try {
            return ResponseEntity.ok(phoneService.getPhoneById(id));
        } catch (ItemNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error search a phone by id: " + e.getMessage());
        }
    }

    @DeleteMapping("/phone/{id}")
    public ResponseEntity deletePhoneById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok("Phone with id " + phoneService.deletePhoneById(id) + " successfully deleted");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error delete phone: " + e.getMessage());
        }
    }

//    @PatchMapping

    @PostMapping("/processor/add")
    public ResponseEntity addNewProcessor(@RequestBody Processors processor) {
        try {
            return ResponseEntity.ok("Processor '" + processorsService.addNewProcessor(processor).getDescription() + "' added successfully");
        } catch (ItemAlreadyExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error add a new processor: " + e.getMessage());
        }
    }

    @GetMapping("/processor")
    public ResponseEntity getProcessorById(@RequestParam Integer id) {
        try {
            return ResponseEntity.ok(processorsService.getProcessorById(id));
        } catch (ItemNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error search a processor by id: " + e.getMessage());
        }
    }

    @DeleteMapping("/processor/{id}")
    public ResponseEntity deleteProcessorById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok("Processor with id " + processorsService.deleteProcessorById(id) + " successfully deleted");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error delete processor: " + e.getMessage());
        }
    }

    @PostMapping("/transaction/add")
    public ResponseEntity addNewTransaction(@RequestBody Transaction transaction) {
        try {
            return ResponseEntity.ok(transactionService.addNewTransaction(transaction) + " added successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error add a new transaction: " + e.getMessage());
        }
    }

    @GetMapping("/transaction")
    public ResponseEntity getTransactionById(@RequestParam Integer id) {
        try {
            return ResponseEntity.ok(transactionService.getTransactionById(id));
        } catch (ItemNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error search a transaction by id: " + e.getMessage());
        }
    }

    @DeleteMapping("/transaction/{id}")
    public ResponseEntity deleteTransactionById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok("Transaction with id " + transactionService.deleteTransactionById(id) + " successfully deleted");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error delete transaction: " + e.getMessage());
        }
    }
}
