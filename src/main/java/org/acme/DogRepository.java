/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.acme;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.InternalServerErrorException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.acme.Dog.Gender;

/**
 *
 * @author ctoska
 */
@ApplicationScoped
public class DogRepository implements PanacheRepository<Dog> {
    @Inject
    private DataSourceProvider dataSourceProvider;
    
    public List<Dog> findByNameSortedByBirthdate(String name) {
        List<Dog> dogs = new ArrayList<>();
        try (Connection connection = dataSourceProvider.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Dog WHERE name = ? ORDER BY birthdate");
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Dog dog = new Dog();
                dog.id = resultSet.getLong("id");
                dog.name = resultSet.getString("name");
                dog.birthdate = resultSet.getDate("birthdate").toLocalDate();
                String genderString = resultSet.getString("gender");
                dog.gender = Gender.valueOf(genderString);                
                dogs.add(dog);
            }
        } catch (SQLException e) {
            throw new InternalServerErrorException(e.getSQLState());// Handle exceptions
        }
        return dogs;
    }
}