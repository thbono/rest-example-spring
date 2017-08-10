package edu.unicesumar.rest.domain.repository;

import edu.unicesumar.rest.domain.model.Country;
import edu.unicesumar.rest.domain.model.QCountry;
import edu.unicesumar.rest.infrastructure.CustomQuerydslBinderCustomizer;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource
public interface CountryRepository extends PagingAndSortingRepository<Country, Integer>,
        QueryDslPredicateExecutor<Country>, CustomQuerydslBinderCustomizer<QCountry> {

    Iterable<Country> findByNameContainingIgnoreCase(String name);

}
