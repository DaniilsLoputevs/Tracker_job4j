package ru.job4j.utils;

public interface Observe<T> {
    
    void receive(T model);
    
}