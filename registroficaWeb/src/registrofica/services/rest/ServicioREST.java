package registrofica.services.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import registrofica.model.dto.aca_sala_dto;
import registrofica.model.entities.aca_sala;
import registrofica.model.manager.ManagerSala;

@RequestScoped
@Path("/estados")
@Produces("application/json")
@Consumes("application/json")
public class ServicioREST {
	
	@EJB
	private ManagerSala msala;
	
	@GET
	public List<aca_sala_dto> Sala(){
		return msala.findAllSalasDTO();
	}

	@GET
	@Path("/{id}")
	public aca_sala_dto SalaByID(@PathParam("id") int id) {
		return msala.findSalabyIdDTO(id);
	}
	
	@POST
	public void crearSala(aca_sala a) {
		try {
			msala.insertarSala(a);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@DELETE
	@Path("/{id}")
	public void eliminarSala(@PathParam("id") int id) {
		try {
			msala.eliminarSala(id);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
	
}
