package com.artyom.digital.domain.person;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PersonDetails {
    private final String fullName;
    private final String age;
}
