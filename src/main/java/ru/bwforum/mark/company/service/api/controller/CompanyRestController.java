package ru.bwforum.mark.company.service.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.bwforum.mark.company.service.api.dto.CompanyDTO;
import ru.bwforum.mark.company.service.api.dto.CustomerDTO;
import ru.bwforum.mark.company.service.api.service.CompanyService;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class CompanyRestController {

    private final CompanyService companyService;

    @GetMapping("/company/{id}")
    public Mono<ResponseEntity<CompanyDTO>> getCompanyById(@PathVariable(name = "id") String id) {
        return companyService
                .getCompanyById(id)
                .map(ResponseEntity::ofNullable);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/company/customer/{id}")
    public Flux<CompanyDTO> getCompaniesByCompanyId(@PathVariable(name = "id") String customerId) {
        return companyService.getCompaniesByCustomersId(customerId);
    }


    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/companies")
    public Flux<CompanyDTO> getAllCompanies() {
        return companyService.getAllCompanies();
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/company/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<CompanyDTO> addNewCompany(@RequestBody Mono<CompanyDTO> dto) {
        return dto.flatMap(companyService::addNewCompany);
    }


    @ResponseStatus(value = HttpStatus.OK, reason = "DELETED")
    @DeleteMapping(value = "/company/delete/{companyId}")
    public Mono<Void> removeCompany(@PathVariable(name = "companyId") String companyId) {
        return companyService.removeCompanyById(companyId);
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "DELETED")
    @DeleteMapping(value = "/company/delete/all")
    public Flux<Void> removeAllCompanies() {
        return companyService.removeAllCompanies();
    }


    @ResponseStatus(value = HttpStatus.OK, reason = "UPDATED")
    @PatchMapping(value = "/company/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<CompanyDTO> updateCustomer(@RequestBody Mono<CompanyDTO> dto) {
        return dto.flatMap(companyService::updateCompany);
    }


    @PatchMapping(value = "/company/bind", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<CompanyDTO>> bindCompanyWithCustomer(@RequestBody Mono<CustomerDTO> dto) {
        return dto.flatMap(companyService::bindCustomerWithCompanyById)
                .map(ResponseEntity::ofNullable);
    }

    @GetMapping("/company/unbind/{id}")
    public Mono<ResponseEntity<Void>> unbindCustomerFromCompanyById(@PathVariable(name = "id") String id) {
        return companyService
                .unbindCustomer(id)
                .map(ResponseEntity::ok);
    }


}
