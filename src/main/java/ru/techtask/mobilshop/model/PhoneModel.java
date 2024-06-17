package ru.techtask.mobilshop.model;

import lombok.Builder;
import lombok.Data;
import ru.techtask.mobilshop.entity.Phone;

@Builder
@Data
public class PhoneModel {
    Integer id;
    String name;
    Integer processorId;
    Integer memorySize;
    String display;
    String camera;
    String size;
    Integer price;
    String processorName;

    public static PhoneModel toModel(Phone phone) {
        return PhoneModel.builder()
                .id(phone.getId())
                .name(phone.getName())
                .processorId(phone.getProcessorid())
                .memorySize(phone.getMemorysize())
                .display(phone.getDisplay())
                .camera(phone.getCamera())
                .size(phone.getSize())
                .price(phone.getPrice())
                .build();
    }

    @Override
    public String toString() {
        return "{id:" + id + ", name:'" + name + "', " + "processorId:" + processorId + ", memorySize:" + memorySize
                + ", display:" + display + ", camera:" + camera + ", size:" + size + ", price:" + price + "}";
    }
}
