package ru.bwforum.mark.company.service.api.dto;

import lombok.*;
import ru.bwforum.mark.company.service.api.entity.Requisites;

import java.util.HashSet;
import java.util.Set;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDTO {

    private String id;
    private String title;
    private String type;
    private Set<String> emails = new HashSet<>();
    private Set<String> phones = new HashSet<>();
    private Set<String> websites = new HashSet<>();
    private Set<String> uniqueCustomersId = new HashSet<>();
    private Requisites requisites;
    private boolean isDelete;
}
