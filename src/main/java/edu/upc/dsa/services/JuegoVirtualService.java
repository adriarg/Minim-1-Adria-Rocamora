package edu.upc.dsa.services;

import edu.upc.dsa.JuegoVirtualManager;
import edu.upc.dsa.JuegoVirtualManagerImpl;
import edu.upc.dsa.models.ElementType;
import edu.upc.dsa.models.InterestPoint;
import edu.upc.dsa.models.User;
import edu.upc.dsa.exceptions.UserNotFoundException;
import edu.upc.dsa.exceptions.InterestPointNotFoundException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/juego", description = "Servicio REST para el juego virtual")
@Path("/juego")
public class JuegoVirtualService {

    private JuegoVirtualManager manager = JuegoVirtualManagerImpl.getInstance();

    @ApiOperation(value = "Añadir un usuario", notes = "Añade un nuevo usuario al sistema")
    @POST
    @Path("/user")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(User user) {
        manager.addUser(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getBirthDate());
        return Response.status(Response.Status.CREATED).build();
    }

    @ApiOperation(value = "Listar usuarios", notes = "Devuelve una lista de todos los usuarios ordenados alfabéticamente")
    @GET
    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> listUsers() {
        return manager.listUsers();
    }

    @ApiOperation(value = "Obtener un usuario", notes = "Devuelve la información de un usuario por ID")
    @GET
    @Path("/user/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("id") String id) {
        try {
            User user = manager.getUser(id);
            return Response.ok(user).build();
        } catch (UserNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @ApiOperation(value = "Añadir punto de interés", notes = "Añade un punto de interés en el mapa")
    @POST
    @Path("/interestPoint")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addInterestPoint(InterestPoint point) {
        // Convertimos el tipo de interés a ElementType
        ElementType type = ElementType.valueOf(point.getType());
        manager.addInterestPoint(point.getX(), point.getY(), type);
        return Response.status(Response.Status.CREATED).build();
    }

    @ApiOperation(value = "Registrar paso por punto de interés", notes = "Registra que un usuario ha pasado por un punto de interés")
    @POST
    @Path("/register/{userId}/{x}/{y}")
    public Response registerUserAtInterestPoint(@PathParam("userId") String userId, @PathParam("x") int x, @PathParam("y") int y) {
        try {
            manager.registerUserAtInterestPoint(userId, x, y);
            return Response.ok().build();
        } catch (UserNotFoundException | InterestPointNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @ApiOperation(value = "Obtener puntos de interés de un usuario", notes = "Devuelve los puntos de interés visitados por un usuario")
    @GET
    @Path("/user/{userId}/interestPoints")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserInterestPoints(@PathParam("userId") String userId) {
        try {
            List<InterestPoint> points = manager.getUserInterestPoints(userId);
            return Response.ok(points).build();
        } catch (UserNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @ApiOperation(value = "Obtener usuarios por punto de interés", notes = "Lista los usuarios que han pasado por un punto de interés")
    @GET
    @Path("/interestPoint/{x}/{y}/users")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsersByInterestPoint(@PathParam("x") int x, @PathParam("y") int y) {
        try {
            List<User> users = manager.getUsersByInterestPoint(x, y);
            return Response.ok(users).build();
        } catch (InterestPointNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @ApiOperation(value = "Obtener puntos de interés por tipo", notes = "Consulta los puntos de interés de un tipo determinado")
    @GET
    @Path("/interestPoints/{type}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<InterestPoint> getInterestPointsByType(@PathParam("type") ElementType type) {
        return manager.getInterestPointsByType(type);
    }
}
