package ru.bwforum.mark.company.service.api.util;


import ru.bwforum.mark.company.service.api.dto.CompanyDTO;
import ru.bwforum.mark.company.service.api.entity.Company;

public class CompanyMapper {

    public static CompanyDTO mapToCompanyDto(Company company) {

        return CompanyDTO.builder()
                .id(company.getId())
                .title(company.getTitle())
                .type(company.getType())
                .emails(company.getEmails())
                .phones(company.getPhones())
                .websites(company.getWebsites())
                .requisites(company.getRequisites())
                .uniqueCustomersId(company.getCustomersId())
                .build();
    }

    public static Company mapToCompanyFromDto(CompanyDTO dto) {

        return CompanyWrapper.builder()
                .setId(dto.getId())
                .setTitle(dto.getTitle())
                .setType(dto.getType())
                .setEmail(dto.getEmails())
                .setPhone(dto.getPhones())
                .setWebsite(dto.getWebsites())
                .setRequisites(dto.getRequisites())
                .setCustomerId(dto.getUniqueCustomersId())
                .build();
    }

}
