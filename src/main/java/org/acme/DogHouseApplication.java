package org.acme;

import io.quarkus.runtime.Quarkus;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

/**
 *
 * @author ctoska
 */
@Path("/dogs")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DogHouseApplication {
    private final DogService dogService;    
    
    public static void main(String[] args) {
        Quarkus.run(args);
    }
    
    
    public DogHouseApplication(DogService dogService) {
        this.dogService = dogService;
    }

    @GET
    public List<Dog> getAllDogs() {
        return dogService.getAllDogs();
    }

    @GET
    @Path("/{id}")
    public Dog getDog(@PathParam("id") Long id) {
        return dogService.getDogById(id);
    }

    @POST
    public Dog createDog(Dog dog) {
        return dogService.createDog(dog);
    }

    @PUT
    @Path("/{id}")
    public Dog updateDog(@PathParam("id") Long id, Dog updatedDog) {
        return dogService.updateDog(id, updatedDog);
    }

    @DELETE
    @Path("/{id}")
    public void deleteDog(@PathParam("id") Long id) {
        dogService.deleteDog(id);
    }

    @GET
    @Path("/findByName")
    public List<Dog> findDogsByName(@QueryParam("name") String name) {
        return dogService.findDogsByNameSortedByBirthdate(name);
    }
    
}