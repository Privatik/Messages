package com.io.messages.controler;

public interface IFoundTwoElement<K,T> {
    T foundTwoElement(Long id);
    K foundElement(Long id);
}
