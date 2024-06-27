package ru.techtask.mobilshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.techtask.mobilshop.entity.Processors;
import ru.techtask.mobilshop.exception.ItemAlreadyExistException;
import ru.techtask.mobilshop.exception.ItemNotFoundException;
import ru.techtask.mobilshop.model.ProcessorsModel;
import ru.techtask.mobilshop.repository.ProcessorsRepo;

import java.util.Objects;
import java.util.Optional;

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

    public Processors updateProcessor(Processors processor, Integer id) throws ItemAlreadyExistException {
        processorsRepo.findById(id).orElseThrow(() -> new ItemNotFoundException("Processor not found"));
        Integer processorIdUniqueName = Optional.ofNullable(processorsRepo.findByDescription(processor.getDescription())).map(Processors::getId).orElse(id);
        if (!Objects.equals(processorIdUniqueName, id)) {
            throw new ItemAlreadyExistException("Processor with this name already exist");
        }
        processor.setId(id);
        return processorsRepo.save(processor);
    }
}
