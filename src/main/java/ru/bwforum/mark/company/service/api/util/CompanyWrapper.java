package ru.bwforum.mark.company.service.api.util;

import ru.bwforum.mark.company.service.api.entity.Company;
import ru.bwforum.mark.company.service.api.entity.Requisites;

import java.util.Set;

public class CompanyWrapper {


    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private final Company company;

        public Builder() {
            company = new Company();
        }

        public Builder setId(String companyId) {
            company.setId(companyId);
            return this;
        }

        public Builder setTitle(String title) {
            company.setTitle(title);
            return this;
        }

        public Builder setEmail(Set<String> emails) {
            company.setEmails(emails);
            return this;
        }

        public Builder setPhone(Set<String> phones) {
            company.setPhones(phones);
            return this;
        }

        public Builder setType(String type) {
            company.setType(type);
            return this;
        }

        public Builder setWebsite(Set<String> websites) {
            company.setWebsites(websites);
            return this;
        }

        public Builder setRequisites(Requisites requisites) {
            company.setRequisites(requisites);
            return this;
        }

        public Builder setCustomerId(Set<String> customersId) {
            company.setCustomersId(customersId);
            return this;
        }


        public Company build() {
            return company;
        }
    }
}
