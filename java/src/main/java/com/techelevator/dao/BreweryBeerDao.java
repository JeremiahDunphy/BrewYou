package com.techelevator.dao;

import com.techelevator.model.Beer;
import com.techelevator.model.Brewery;

import java.util.List;

public interface BreweryBeerDao {
    void addBeerToBrewery(int breweryId, int beerId);
    //void deleteBeerFromBrewery()
    List<Beer> getBeersByBrewery(int breweryId);

    void deleteBeerFromBrewery(int breweryId, int beerId);

    List<Brewery> getBreweriesByBeer(int beerId);
}
