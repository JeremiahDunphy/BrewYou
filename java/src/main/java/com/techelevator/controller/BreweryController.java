package com.techelevator.controller;

import com.techelevator.dao.BreweryDao;
import com.techelevator.model.Brewery;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
@CrossOrigin
public class BreweryController {
    private BreweryDao breweryDao;

    public BreweryController (BreweryDao breweryDao) {
        this.breweryDao = breweryDao;
    }

    @RequestMapping(path = "/breweries", method = RequestMethod.GET)
    public List<Brewery> getBreweries() {
        List<Brewery> breweryList = breweryDao.getBreweries();
        return breweryList;

    }

    @RequestMapping(path = "/breweries/{breweryId}", method = RequestMethod.GET)
    public Brewery getBreweryById(@PathVariable int breweryId) {
        Brewery brewery = breweryDao.getBreweryById(breweryId);
        return brewery;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(path = "/breweries", method = RequestMethod.POST)
    public Brewery addBrewery(@Valid @RequestBody Brewery brewery) {
        Brewery newBrewery = breweryDao.addBrewery(brewery);
                return newBrewery;

    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(path = "/breweries/{breweryId}", method = RequestMethod.PUT)
    public Brewery updateBrewery(@PathVariable int breweryId, @Valid @RequestBody Brewery brewery) {
        brewery.setBreweryId(breweryId);
        Brewery updatedBrewery = breweryDao.updateBrewery(brewery);
        return updatedBrewery;

    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(path = "/breweries/{breweryId}", method = RequestMethod.DELETE)
    public String deleteBrewery(@PathVariable int breweryId) {
        return breweryDao.deleteBrewery(breweryId);
    }
}



