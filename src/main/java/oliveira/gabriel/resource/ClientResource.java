package oliveira.gabriel.resource;

import java.util.List;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import oliveira.gabriel.entities.Client;
import oliveira.gabriel.services.ValidationService;
import oliveira.gabriel.services.PaymentService;

@Path("/clients")
public class ClientResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll() {
		List<Client> clients = Client.findAll().list();
		return Response.ok(clients).build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getById(@PathParam("id") Long id) {
		Client client = Client.findById(id);
		return Response.ok(client).build();
	}

	@GET
	@Path("/forname")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getByName(@QueryParam("name") String name) {
		return Client.find("SELECT m FROM Client m WHERE m.name = ?1", name)
				.singleResultOptional()
				.map(client -> Response.ok(client).build())
				.orElse(Response.status(Response.Status.NOT_FOUND).build());
	}

	@POST
	@Transactional
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createUser(Client client) {
		if (!ValidationService.isExist(client)) {
			Client.persist(client);
			if (client.isPersistent()) {
				return Response.ok(client).build();
			}
		}
		return Response.status(Response.Status.BAD_REQUEST).build();
	}

	@PUT
	@Path("/payment")
	@Transactional
	@Produces(MediaType.APPLICATION_JSON)
	public Response payment(@QueryParam("idP") Long idPayer,
			@QueryParam("idR") Long idReciver,
			@QueryParam("value") Double value) {
				Client clientPayer = Client.findById(idPayer);
				Client clientReciver = Client.findById(idReciver);
				PaymentService.paymet(clientPayer, clientReciver, value);
				return Response.ok().build();

	}
}
