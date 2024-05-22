package ru.techtask.mobilshop.repository;

import org.junit.jupiter.api.*;
import org.mockito.MockedStatic;
import ru.techtask.mobilshop.controller.DataBaseController;
import ru.techtask.mobilshop.model.Phone;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

public class PhonesImplTest {
    private static final Integer ONE = 1;
    private static final String TEST_STRING = "test";

    private final DataBaseController data = mock(DataBaseController.class);

    @Test
    @DisplayName("Success add phone in db")
    public void testAddPhone() {
        var newPhone = phoneBuilder();
        try (MockedStatic<DataBaseController> dataMockStatic = mockStatic(DataBaseController.class)) {
            dataMockStatic.when(DataBaseController::getInstance).thenReturn(data);
            when(data.makeQuery(anyString())).thenReturn(ONE);
            PhonesImpl phones = new PhonesImpl();

            var result = phones.addPhone(newPhone);
            assertEquals(ONE, result);
        }
    }

    @Test
    @DisplayName("Error add phone in db")
    public void successAddPhone() {
        var newPhone = phoneBuilder();
        try (MockedStatic<DataBaseController> dataMockStatic = mockStatic(DataBaseController.class)) {
            dataMockStatic.when(DataBaseController::getInstance).thenReturn(data);
            when(data.makeQuery(anyString())).thenReturn(null);
            PhonesImpl phones = new PhonesImpl();

            var result = phones.addPhone(newPhone);
            assertNull(result);
        }
    }

    private Phone phoneBuilder() {
        return Phone.builder()
                .name(TEST_STRING)
                .processorName(TEST_STRING)
                .memorySize(ONE)
                .display(TEST_STRING)
                .camera(TEST_STRING)
                .size(TEST_STRING)
                .price(ONE)
                .build();
    }
}
