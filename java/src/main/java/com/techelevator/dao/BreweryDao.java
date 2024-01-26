package com.techelevator.dao;

import com.techelevator.model.Brewery;

import java.util.List;

public interface BreweryDao {
    List<Brewery> getBreweries();
    Brewery getBreweryById(int breweryId);



    Brewery addBrewery(Brewery brewery);



    String deleteBrewery(int breweryId);
    Brewery updateBrewery(Brewery brewery);
}
