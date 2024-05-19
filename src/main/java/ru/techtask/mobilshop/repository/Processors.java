package ru.techtask.mobilshop.repository;

import java.util.List;

public interface Processors {
    List<String> listProcessors();
    Integer addProcessor(String processor);
    Integer getProcessorId(String processor);
    Integer updateProcessor(Integer processorId, String processor);
}
