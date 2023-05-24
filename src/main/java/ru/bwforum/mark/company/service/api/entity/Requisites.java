package ru.bwforum.mark.company.service.api.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Requisites {

    private String INN;
    private String KPP;
    private String OGRN;
    private Set<String> address;
    private String ceoTitle;
    private String ceoName;


}
