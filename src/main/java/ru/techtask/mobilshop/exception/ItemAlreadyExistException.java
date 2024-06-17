package ru.techtask.mobilshop.exception;

public class ItemAlreadyExistException extends Exception {
    public ItemAlreadyExistException(String message) {
        super(message);
    }
}
