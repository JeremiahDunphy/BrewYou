package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Beer;
import com.techelevator.model.Brewery;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcBreweryBeerDao implements BreweryBeerDao{
    private final JdbcTemplate jdbcTemplate;

    public JdbcBreweryBeerDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addBeerToBrewery(int breweryId, int beerId) {
        String sql = "INSERT INTO brewery_beer (brewery_id, beer_id) VALUES (?, ?)";
        try {
            jdbcTemplate.update(sql, breweryId, beerId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
    }
    @Override
    public List<Beer> getBeersByBrewery(int breweryId) {
        List <Beer> beerList = new ArrayList<>();
        String sql = "SELECT * FROM beer JOIN brewery_beer AS bb ON beer.beer_id = bb.beer_id WHERE bb.brewery_id = ? ORDER BY beer.name";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, breweryId);
            while(results.next()) {
                Beer beer = mapRowToBeer(results);
                beerList.add(beer);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return beerList;
    }
    public Beer mapRowToBeer(SqlRowSet rs) {
        Beer beer = new Beer();
        beer.setBeerId(rs.getInt("beer_id"));
        beer.setName(rs.getString("name"));
        beer.setDescription(rs.getString("description"));
        beer.setImagePath(rs.getString("image_path"));
        beer.setAbv(rs.getDouble("abv"));
        beer.setBeerType(rs.getString("beer_type"));
        return beer;
    }
    @Override
    public void deleteBeerFromBrewery(int breweryId, int beerId) {
        String sql = "DELETE FROM brewery_beer WHERE brewery_id = ? AND beer_id = ?";
        try {
            jdbcTemplate.update(sql, breweryId, beerId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation - unable to delete beer from brewery", e);
        }
    }

    @Override
    public List<Brewery> getBreweriesByBeer(int beerId) {
        List<Brewery> breweries = new ArrayList<>();
        String sql = "SELECT b.* FROM brewery b INNER JOIN brewery_beer bb ON b.brewery_id = bb.brewery_id WHERE bb.beer_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, beerId);
            while(results.next()) {
                Brewery brewery = mapRowToBrewery(results);
                breweries.add(brewery);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return breweries;
    }

    private Brewery mapRowToBrewery(SqlRowSet rs) {
        Brewery brewery = new Brewery();
        // Map the columns of the brewery table to the Brewery object

        return brewery;
    }

}
