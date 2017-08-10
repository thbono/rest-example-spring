package edu.unicesumar.rest.controller;

import edu.unicesumar.rest.domain.model.City;
import edu.unicesumar.rest.domain.model.Country;
import edu.unicesumar.rest.domain.repository.CityRepository;
import edu.unicesumar.rest.domain.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class CountryController {

    @Autowired
    private CountryRepository repository;

    @Autowired
    private CityRepository cityRepository;

    @RequestMapping(value = "/paises", method = RequestMethod.GET)
    public Iterable<Country> getAll(@RequestParam(value = "name", required = false) String name) {
        if (!StringUtils.isEmpty(name)) return repository.findByNameContainingIgnoreCase(name);
        return repository.findAll();
    }

    @RequestMapping(value = "/paises/{id}", method = RequestMethod.GET)
    public ResponseEntity<Country> getOne(@PathVariable("id") int id) {
        Optional<Country> obj = Optional.ofNullable(repository.findOne(id));
        return obj.isPresent()
                ? new ResponseEntity(obj.get(), HttpStatus.OK)
                : new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/paises/{id}/cidades", method = RequestMethod.GET)
    public ResponseEntity<Iterable<City>> getOneCities(@PathVariable("id") int id) {
        Optional<Country> obj = Optional.ofNullable(repository.findOne(id));
        return obj.isPresent()
                ? new ResponseEntity(cityRepository.findByCountry(obj.get()), HttpStatus.OK)
                : new ResponseEntity(HttpStatus.NOT_FOUND);
    }

}
