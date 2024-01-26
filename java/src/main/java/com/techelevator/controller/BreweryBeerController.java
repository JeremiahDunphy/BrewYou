package com.techelevator.controller;

import com.techelevator.dao.BreweryBeerDao;
import com.techelevator.model.Beer;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class BreweryBeerController {
    private BreweryBeerDao breweryBeerDao;

    public BreweryBeerController(BreweryBeerDao breweryBeerDao) {
        this.breweryBeerDao = breweryBeerDao;
    }
    public static class BeerIdWrapper {
        private int beerId;

        public int getBeerId() {
            return beerId;
        }

        public void setBeerId(int beerId) {
            this.beerId = beerId;
        }
    }
    @RequestMapping(path = "breweries/{breweryId}/beers", method = RequestMethod.POST)
    public void addBeerToBrewery(@PathVariable int breweryId, @RequestBody BeerIdWrapper beerIdWrapper) {
        int beerId = beerIdWrapper.getBeerId();
        breweryBeerDao.addBeerToBrewery(breweryId, beerId);
    }
    @RequestMapping(path="breweries/{breweryId}/beers", method = RequestMethod.GET)
    public List<Beer> getBeersByBrewery(@PathVariable int breweryId) {
        List<Beer> beerList = breweryBeerDao.getBeersByBrewery(breweryId);
        return beerList;
    }

}
