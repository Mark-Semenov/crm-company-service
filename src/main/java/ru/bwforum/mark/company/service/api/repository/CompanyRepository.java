package ru.bwforum.mark.company.service.api.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import ru.bwforum.mark.company.service.api.entity.Company;

public interface CompanyRepository extends ReactiveMongoRepository<Company, String> {

    Flux<Company> getCompaniesByCustomersIdContains(String customerId);

}
