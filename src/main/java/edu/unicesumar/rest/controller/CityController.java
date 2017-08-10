package edu.unicesumar.rest.controller;

import edu.unicesumar.rest.domain.model.City;
import edu.unicesumar.rest.domain.model.Country;
import edu.unicesumar.rest.domain.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class CityController {

    @Autowired
    private CityRepository repository;

    @RequestMapping(value = "/cidades", method = RequestMethod.GET)
    public Iterable<City> getAll(@RequestParam(value = "name", required = false) String name) {
        if (!StringUtils.isEmpty(name)) return repository.findByNameContainingIgnoreCase(name);
        return repository.findAll();
    }

    @RequestMapping(value = "/cidades/{id}", method = RequestMethod.GET)
    public ResponseEntity<City> getOne(@PathVariable("id") int id) {
        Optional<City> obj = Optional.ofNullable(repository.findOne(id));
        return obj.isPresent()
                ? new ResponseEntity(obj.get(), HttpStatus.OK)
                : new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/cidades/{id}/pais", method = RequestMethod.GET)
    public ResponseEntity<Country> getOneCountry(@PathVariable("id") int id) {
        Optional<City> obj = Optional.ofNullable(repository.findOne(id));
        return obj.isPresent()
                ? new ResponseEntity(obj.get().getCountry(), HttpStatus.OK)
                : new ResponseEntity(HttpStatus.NOT_FOUND);
    }

}
