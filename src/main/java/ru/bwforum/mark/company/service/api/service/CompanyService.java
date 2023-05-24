package ru.bwforum.mark.company.service.api.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.bwforum.mark.company.service.api.dto.CompanyDTO;
import ru.bwforum.mark.company.service.api.dto.CustomerDTO;

public interface CompanyService {

    Mono<CompanyDTO> getCompanyById(String companyId);

    Flux<CompanyDTO> getAllCompanies();

    Mono<CompanyDTO> addNewCompany(CompanyDTO company);

    Mono<Void> removeCompanyById(String companyId);

    Mono<CompanyDTO> updateCompany(CompanyDTO company);

    Flux<Void> removeAllCompanies();

    Mono<CompanyDTO> bindCustomerWithCompanyById(CustomerDTO dto);

    Mono<Void> unbindCustomer(String customerId);

    Flux<CompanyDTO> getCompaniesByCustomersId(String customerId);
}
