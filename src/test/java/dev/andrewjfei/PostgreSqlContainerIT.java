package dev.andrewjfei;

import dev.andrewjfei.entity.Country;
import dev.andrewjfei.repository.CountryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

/**
 * An integration test class to check if the PostgreSQL test container is up and running.
 */
public class PostgreSqlContainerIT extends BaseIT {

    @Autowired
    CountryRepository countryRepository;

    @Test
    public void addCountryToDatabase_shouldSucceed() {
        // Given
        Country country = new Country();
        country.setName("New Zealand");

        // When
        Country addedCountry = countryRepository.save(country);

        // Then
        assertEquals(country.getName(), addedCountry.getName());
    }
}
