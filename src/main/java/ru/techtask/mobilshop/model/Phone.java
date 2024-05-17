package ru.techtask.mobilshop.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Phone {
    String name;
    Integer processorId;
    Integer memorySize;
    String display;
    String camera;
    String size;
    Integer price;
}
