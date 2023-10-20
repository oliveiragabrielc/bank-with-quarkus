package oliveira.gabriel.resource;

import java.util.List;
import java.util.Optional;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import oliveira.gabriel.entities.User;

@Path("/users")
public class UserResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
    	List<User> users = User.findAll().list();
    	return Response.ok(users).build();
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id")Long id) {
    	User user = User.findById(id);
    	return Response.ok(user).build();
    }
    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByName(@PathParam("name") String name) {
    	Optional<User> user = User.find("SELECT n FROM User WHERE n.name == ?",name).singleResultOptional();
    	return Response.ok(user).build();
    }
}
