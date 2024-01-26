package com.techelevator.controller;

import com.techelevator.dao.BeerDao;
import com.techelevator.model.Beer;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class BeerController {
    private BeerDao beerDao;

    public BeerController (BeerDao beerDao) {
        this.beerDao = beerDao;
    }
    @RequestMapping(path = "/beers", method = RequestMethod.GET)
    public List<Beer> getAllBeers() {
        List<Beer> beerList = beerDao.getAllBeers();
        return beerList;
    }

    @RequestMapping(path = "/beers/{beerId}", method = RequestMethod.GET)
    public Beer getBeerById(@PathVariable int beerId) {
        Beer beer = beerDao.getBeerById(beerId);
        return beer;
    }

    @RequestMapping(path = "/beers", method = RequestMethod.POST)
    public Beer addBeer(@RequestBody Beer beer) {
        return beerDao.addBeer(beer);
    }



}
