package edu.unicesumar.rest.domain.repository;

import edu.unicesumar.rest.domain.model.City;
import edu.unicesumar.rest.domain.model.Country;
import edu.unicesumar.rest.domain.model.QCity;
import edu.unicesumar.rest.infrastructure.CustomQuerydslBinderCustomizer;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource
public interface CityRepository extends PagingAndSortingRepository<City, Integer>,
        QueryDslPredicateExecutor<City>, CustomQuerydslBinderCustomizer<QCity> {

    Iterable<City> findByNameContainingIgnoreCase(String name);

    Iterable<City> findByCountry(Country country);

}
