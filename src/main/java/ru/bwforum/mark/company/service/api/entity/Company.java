package ru.bwforum.mark.company.service.api.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Company {

    @Id
    private String id;
    private String title;
    private String type;
    private Set<String> emails = new HashSet<>();
    private Set<String> phones = new HashSet<>();
    private Set<String> websites = new HashSet<>();
    private Set<String> customersId = new HashSet<>();
    private Requisites requisites;

}
