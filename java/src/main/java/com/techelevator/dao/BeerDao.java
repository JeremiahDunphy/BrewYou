package com.techelevator.dao;

import com.techelevator.model.Beer;

import java.util.List;

public interface BeerDao {
 List<Beer> getAllBeers();
 Beer addBeer(Beer beer);
 Beer getBeerById(int beerId);
}
