package com.youtube.rest.status;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
@Path("/v1/status")
public class v1_status {
   
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnstatus()
	{
		return "This is fucking awesome";
	}
}
