package com.tmh.practice.web;

import com.tmh.practice.entity.User;
import com.tmh.practice.service.UserService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.annotations.jaxrs.QueryParam;

import java.util.List;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

  @Inject
  UserService userService;

  @GET
  @Path("/getAll")
  public List<User> getAll() {
    return userService.getAll();
  }

  @GET
  @Path("/getByPhone")
  public Response getByPhone(@QueryParam("phone") String phone) {
    return Response.ok(userService.getByPhone(phone)).build();
  }

  @POST
  @Path("/save")
  @Consumes(MediaType.APPLICATION_JSON)
  public User saveUser(User user) {
    return userService.saveUser(user);
  }
}