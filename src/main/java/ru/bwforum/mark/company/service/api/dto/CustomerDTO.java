package ru.bwforum.mark.company.service.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {

    private String id;
    private Set<String> uniqueCompaniesId = new HashSet<>();

}
