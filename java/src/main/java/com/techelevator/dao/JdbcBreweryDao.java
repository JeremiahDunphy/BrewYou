package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Brewery;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Component
public class JdbcBreweryDao implements BreweryDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcBreweryDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public List<Brewery> getBreweries() {
        List<Brewery> breweries = new ArrayList<>();
        String sql = "SELECT * FROM brewery ORDER BY name";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while(results.next()) {
                Brewery brewery = mapRowToBrewery(results);
                breweries.add(brewery);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return breweries;
    }

//    @Override
//    public Brewery getBreweryByBrewerId(int brewerId) { //beerId?
//        return null;
//    }
    @Override
    public Brewery getBreweryById(int breweryId){
        Brewery brewery = null;
        String sql = "SELECT * FROM brewery WHERE brewery_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, breweryId);
            if(results.next()) {
                brewery = mapRowToBrewery(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return brewery;
    }

    @Override
    public Brewery updateBrewery(Brewery brewery) {

        String sql = "UPDATE brewery SET name = ?, founded_date = ?, contact_phone = ?, street_address = ?, city = ?, state = ?, days_of_operation = ?, hours_of_operation = ? " +
                "WHERE brewery_id = ?";
        try {
            int rowsAffected = jdbcTemplate.update(sql,
                    brewery.getName(),
                    brewery.getFoundedDate(),
                    brewery.getContactPhone(),
                    brewery.getStreetAddress(),
                    brewery.getCity(),
                    brewery.getState(),
                    brewery.getDaysOfOperation(),
                    brewery.getHoursOfOperation(),
                    brewery.getBreweryId());

            if (rowsAffected == 0) {
                throw new NoSuchElementException("No brewery found with the provided Brewery " + brewery.getName());
            }
        } catch(CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or DataBase");
        }


        return brewery;
    }

    @Override
    public Brewery addBrewery(Brewery brewery) {
        String sql = "INSERT INTO brewery (name, founded_date, contact_phone, email, street_address, city, state, zip_code, days_of_operation, hours_of_operation, image_path) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING brewery_id;";
try {

    int breweryId = jdbcTemplate.queryForObject(sql, Integer.class,
            brewery.getName(),
            brewery.getFoundedDate(),
            brewery.getContactPhone(),
            brewery.getEmail(),
            brewery.getStreetAddress(),
            brewery.getCity(),
            brewery.getState(),
            brewery.getZipCode(),
            brewery.getDaysOfOperation(),
            brewery.getHoursOfOperation(),
            brewery.getImagePath()
    );

    brewery.setBreweryId(breweryId);
} catch (CannotGetJdbcConnectionException e) {
    throw new DaoException("Unable to connect to server or database", e);
} catch (DataIntegrityViolationException e) {
    throw new DaoException("Data integrity violation", e);
}

return brewery;

}
    @Override
    public String deleteBrewery(int breweryId) {

        Brewery breweryToDelete = getBreweryById(breweryId);
        if (breweryToDelete == null) {
            throw new NoSuchElementException("No brewery found with the provided brewery ID " + breweryId);
        }

        String sql = "DELETE FROM brewery WHERE brewery_id = ?";
        try {
            int rowsAffected = jdbcTemplate.update(sql, breweryId);
            if (rowsAffected == 0) {

                throw new IllegalStateException("Unexpected error occurred while deleting the brewery.");
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation - perhaps related to foreign key constraints", e);
        }

        return "The Brewery '" + breweryToDelete.getName() + "' was successfully deleted.";
    }

    private Brewery mapRowToBrewery(SqlRowSet rs) {
        Brewery brewery = new Brewery();
        brewery.setBreweryId(rs.getInt("brewery_id"));
        brewery.setName(rs.getString("name"));
        brewery.setFoundedDate(rs.getDate("founded_date"));
        brewery.setContactPhone(rs.getString("contact_phone"));
        brewery.setEmail(rs.getString("email"));
        brewery.setStreetAddress(rs.getString("street_address"));
        brewery.setCity(rs.getString("city"));
        brewery.setState(rs.getString("state"));
        brewery.setZipCode(rs.getInt("zip_code"));
        brewery.setDaysOfOperation(rs.getString("days_of_operation"));
        brewery.setHoursOfOperation(rs.getString("hours_of_operation"));
        brewery.setImagePath(rs.getString("image_path"));
        return brewery;
    }
}
