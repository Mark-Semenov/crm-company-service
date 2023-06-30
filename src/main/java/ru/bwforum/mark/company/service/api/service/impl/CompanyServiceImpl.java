package ru.bwforum.mark.company.service.api.service.impl;

import com.thoughtworks.xstream.converters.reflection.MissingFieldException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.bwforum.mark.company.service.api.dto.CompanyDTO;
import ru.bwforum.mark.company.service.api.dto.CustomerDTO;
import ru.bwforum.mark.company.service.api.entity.Company;
import ru.bwforum.mark.company.service.api.repository.CompanyRepository;
import ru.bwforum.mark.company.service.api.service.CompanyService;
import ru.bwforum.mark.company.service.api.util.CompanyMapper;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    @Override
    public Mono<CompanyDTO> getCompanyById(String companyId) {
        return companyRepository
                .findById(companyId)
                .map(CompanyMapper::mapToCompanyDto)
                .onErrorComplete(NoSuchElementException.class)
                .log();
    }

    @Override
    public Flux<CompanyDTO> getAllCompanies() {
        return companyRepository
                .findAll()
                .map(CompanyMapper::mapToCompanyDto)
                .log();
    }

    @Override
    public Mono<CompanyDTO> addNewCompany(CompanyDTO dto) {
        Company company = CompanyMapper.mapToCompanyFromDto(dto);
        return companyRepository
                .save(company)
                .map(CompanyMapper::mapToCompanyDto)
                .log();
    }

    @Override
    public Mono<Void> removeCompanyById(String companyId) {
        return companyRepository
                .findById(companyId)
                .map(CompanyMapper::mapToCompanyDto)
                .doOnNext(item -> item.setDelete(true))
                .map(CompanyDTO::getId)
                .flatMap(companyRepository::deleteById)
                .log();
    }

    @Override
    public Flux<Void> removeAllCompanies() {
        return companyRepository.deleteAll().flux().log();
    }

    @Override
    public Mono<CompanyDTO> bindCustomerWithCompanyById(CustomerDTO dto) {
        return companyRepository
                .findAllById(dto.getUniqueCompaniesId())
                .doOnNext(item -> item.getCustomersId().add(dto.getId()))
                .flatMap(companyRepository::save)
                .map(CompanyMapper::mapToCompanyDto)
                .reduce((one, two) -> two)
                .log();
    }


    @Override
    public Mono<Void> unbindCustomer(String customerId) {
        return companyRepository
                .findAll()
                .filter(item -> item.getCustomersId().contains(customerId))
                .doOnNext(item -> item.getCustomersId().remove(customerId))
                .flatMap(companyRepository::save)
                .then()
                .log();
    }

    @Override
    public Flux<CompanyDTO> getCompaniesByCustomersId(String customerId) {
        return companyRepository.getCompaniesByCustomersIdContains(customerId)
                .map(CompanyMapper::mapToCompanyDto);
    }


    @Override
    public Mono<CompanyDTO> updateCompany(CompanyDTO dto) {

        if (dto.getId() == null) {
            throw new MissingFieldException("String", "ID");
        }
        Company updated = CompanyMapper.mapToCompanyFromDto(dto);
        return companyRepository
                .findById(dto.getId())
                .onErrorComplete(NoSuchElementException.class)
                .map(item -> updated)
                .flatMap(companyRepository::save)
                .map(CompanyMapper::mapToCompanyDto)
                .log();
    }


}
