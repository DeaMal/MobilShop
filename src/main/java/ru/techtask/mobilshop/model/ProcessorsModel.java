package ru.techtask.mobilshop.model;

import lombok.Builder;
import lombok.Data;
import ru.techtask.mobilshop.entity.Processors;

@Builder
@Data
public class ProcessorsModel {
    private Integer id;
    private String description;

    public static ProcessorsModel toModel(Processors processors) {
        return ProcessorsModel.builder()
                .id(processors.getId())
                .description(processors.getDescription())
                .build();
    }

    @Override
    public String toString() {
        return "{id:" + id + ", name:'" + description + "'}";
    }
}
