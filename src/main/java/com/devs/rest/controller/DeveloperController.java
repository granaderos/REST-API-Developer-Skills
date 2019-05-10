package com.devs.rest.controller;

import java.util.HashMap;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.StatusType;

import com.devs.rest.domain.Developer;
import com.devs.rest.exceptions.NoListFoundException;
import com.devs.rest.service.DeveloperService;

@Path("/developers")
public class DeveloperController {
	private DeveloperService devService;
	
	public DeveloperController() {
		devService = new DeveloperService();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDevelopers() {
		List<HashMap<String, Object>> listDevs;
		try {
			listDevs = devService.getDevelopers();
			if(listDevs.isEmpty()) {
				throw new NoListFoundException("No developers found.");
			}
		} catch(NoListFoundException e) {
			e.printStackTrace();
			return Response.status(204).entity("Empty list of developers").build();
		}
		return Response.status(200).entity(listDevs).build();
	}
	
	@GET
	@Path("/search")
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchDevelopers(
			@QueryParam("skill") String skill, 
			@QueryParam("skillLevel") String skillLevel,
			@QueryParam("firstName") String firstName, 
			@QueryParam("lastName") String lastName,
			@QueryParam("monthsOfExperience") String monthsOfExperience 
		) {
		
		List<HashMap<String, Object>> listDevs;
		try {
			listDevs = devService.searchDevelopers(skill, skillLevel, firstName, lastName, monthsOfExperience);
			if(listDevs.isEmpty()) {
				throw new NoListFoundException("No developers found.");
			}
		} catch(NoListFoundException e) {
			e.printStackTrace();
			return Response.status(204).entity("No developers found.").build();
		}
		return Response.status(200).entity(listDevs).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/add")
	public Response addDeveloper(Developer dev) {
		HashMap<String, Object> response;
		try {
			response = devService.addDeveloper(dev);	
			
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}
		System.out.println("Status = " + response.get("code"));
		return Response.status((int) response.get("code")).entity(response.get("message")).build();
	}
}
