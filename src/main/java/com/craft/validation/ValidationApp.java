package com.craft.validation;

import io.vavr.collection.Seq;
import io.vavr.control.Validation;

public class ValidationApp {
    public static void main(String[] args) {
        PersonValidator personValidator = new PersonValidator();

        // Valid(Person(John Doe, 30))
        Validation<Seq<String>, Person> valid = personValidator.validatePerson("John Doe", 30);
        System.out.println(valid);

        // Invalid(List(Name contains invalid characters: '!4?', Age must be greater than 0))
        Validation<Seq<String>, Person> invalid = personValidator.validatePerson("John? Doe!4", -1);
        System.out.println(invalid);
    }
}
