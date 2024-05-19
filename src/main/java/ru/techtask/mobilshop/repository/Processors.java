package ru.techtask.mobilshop.repository;

import java.util.List;

public interface Processors {
    List<String> listProcessors();

    Integer addProcessor(String processorName);

    Integer getProcessorId(String processorName);

    Integer updateProcessor(Integer processorId, String processorName);

    Integer deleteProcessor(String processorName, Boolean cascade);
}
