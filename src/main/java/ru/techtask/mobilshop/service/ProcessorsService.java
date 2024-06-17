package ru.techtask.mobilshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.techtask.mobilshop.exception.ItemNotFoundException;
import ru.techtask.mobilshop.model.ProcessorsModel;
import ru.techtask.mobilshop.repository.ProcessorsRepo;

@Service
public class ProcessorsService {

    @Autowired
    private ProcessorsRepo processorsRepo;

    public ProcessorsModel getProcessorById(Integer processorId) {
        return ProcessorsModel.toModel(processorsRepo.findById(processorId).orElseThrow(() -> new ItemNotFoundException("Processor not found")));
    }
}
