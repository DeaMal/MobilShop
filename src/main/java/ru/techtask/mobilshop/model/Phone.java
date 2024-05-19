package ru.techtask.mobilshop.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Phone {
    Integer id;
    String name;
    Integer processorId;
    Integer memorySize;
    String display;
    String camera;
    String size;
    Integer price;
    String processorName;

    @Override
    public String toString() {
        return "{id:" + id + ", name:'" + name + "', " + "processorId:" + processorId + ", memorySize:" + memorySize
                + ", display:" + display + ", camera:" + camera + ", size:" + size + ", price:" + price + "}";
    }
}
