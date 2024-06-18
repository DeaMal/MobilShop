package ru.techtask.mobilshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.techtask.mobilshop.entity.Processors;
import ru.techtask.mobilshop.exception.ItemAlreadyExistException;
import ru.techtask.mobilshop.exception.ItemNotFoundException;
import ru.techtask.mobilshop.model.ProcessorsModel;
import ru.techtask.mobilshop.repository.ProcessorsRepo;

import java.util.Objects;

@Service
public class ProcessorsService {

    @Autowired
    private ProcessorsRepo processorsRepo;

    public Processors addNewProcessor(Processors newProcessor) throws ItemAlreadyExistException {
        if (Objects.nonNull(processorsRepo.findByDescription(newProcessor.getDescription()))) {
            throw new ItemAlreadyExistException("Processor already exist");
        }
        return processorsRepo.save(newProcessor);
    }

    public ProcessorsModel getProcessorById(Integer processorId) {
        return ProcessorsModel.toModel(processorsRepo.findById(processorId).orElseThrow(() -> new ItemNotFoundException("Processor not found")));
    }

    public Integer deleteProcessorById(Integer processorId) {
        processorsRepo.deleteById(processorId);
        return processorId;
    }
}
