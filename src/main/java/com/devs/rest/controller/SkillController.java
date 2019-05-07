package com.devs.rest.controller;

import java.util.HashMap;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import com.devs.rest.domain.Skill;
import com.devs.rest.service.SkillService;


@Path("/skills")
public class SkillController {
	
	private SkillService skillService;
	
	public SkillController() {
		skillService = new SkillService();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Skill> getSkills() {
		try {
			return skillService.getSkills();
		} catch (Exception e) {
			throw new WebApplicationException(e);
		} 
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/add")
	public String addDeveloper(Skill skill) {

		try {
			String message = skillService.addSkill(skill);
			return message;
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}

	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/levels")
	public List<String> getSkillLevels() {
		try {
			return skillService.getSkillLevels();
		} catch (Exception e) {
			throw new WebApplicationException(e);
		} 
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/capabilityReport")
	public List<HashMap<String, Object>> generateSkillCapabilityReport() {
		try {
			return skillService.generateSkillCapabilityReport();
		} catch (Exception e) {
			throw new WebApplicationException(e);
		} 
	}

}
