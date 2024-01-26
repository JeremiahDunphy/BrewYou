package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Beer;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcBeerDao implements BeerDao{
    private final JdbcTemplate jdbcTemplate;

    public JdbcBeerDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Beer> getAllBeers() {
        List <Beer> beerList = new ArrayList<>();
        String sql = "SELECT * FROM beer";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while(results.next()) {
                Beer beer = mapRowToBeer(results);
                beerList.add(beer);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return beerList;
    }
    @Override
    public Beer addBeer(Beer beer) {
        String sql = "INSERT INTO beer (name, description, image_path, abv, beer_type) VALUES (?, ?, ?, ?, ?)";
        try {
            jdbcTemplate.update(sql, beer.getName(), beer.getDescription(), beer.getImagePath(), beer.getAbv(), beer.getBeerType());

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation - unable to add beer", e);
        }
        return beer;
    }


    public Beer getBeerById(int beerId) {
        Beer beer = null;
        String sql = "SELECT * FROM beer WHERE beer_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, beerId);

            if (results.next()) {
                beer = mapRowToBeer(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return beer;
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
}
