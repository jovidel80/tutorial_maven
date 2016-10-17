package com.joseoliveros;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/test")
public class SimpleResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String test() {
        return "It works!";
    }
}
