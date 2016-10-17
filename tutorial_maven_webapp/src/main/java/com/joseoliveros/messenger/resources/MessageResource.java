package com.joseoliveros.messenger.resources;

import com.joseoliveros.messenger.dao.DaoException;
import com.joseoliveros.messenger.model.Message;
import com.joseoliveros.messenger.services.MessageService;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.List;

@Path("/messages")
@Consumes("application/json")
@Produces("application/json")
public class MessageResource {

    private MessageService messageService = new MessageService();

    @Context
    Request request;

    @Context
    HttpHeaders httpHeaders;
    

    @GET
    public List<Message> getMessages() throws DaoException {
        return messageService.getAllMessages();
    }


    @GET
    @Path("/{messageId}")
    public Message getMessage(@PathParam("messageId") long messageId) throws DaoException {
        return messageService.getMessage(messageId);
    }

    @POST
    public Response addMessage(Message message, @Context UriInfo uriInfo) throws DaoException {
        Message newMessage = messageService.addMessage(message);
        String newId = String.valueOf(newMessage.getId());
        URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
        NewCookie cookie1 = new NewCookie("cookie1", request.getMethod());
        NewCookie cookie2 = new NewCookie("cookie2", httpHeaders.getRequestHeaders().toString());
        return Response.created(uri).cookie(cookie1).cookie(cookie2).entity(newMessage).build();
//                .entity(newMessage).build();
    }

    @PUT
    @Path("/{messageId}")
    public Response updateMessage(@PathParam("messageId") long messageId, Message message, @Context UriInfo uriInfo) throws DaoException {
        message.setId(messageId);
        Message newMessage = messageService.updateMessage(message);
        URI uri = uriInfo.getAbsolutePathBuilder().build();
        return Response.status(Response.Status.OK).contentLocation(uri).build();
    }

    @DELETE
    @Path("/{messageId}")
    public Response deleteMessage(@PathParam("messageId") long messageId) throws DaoException {
        boolean delete = messageService.deleteMessage(messageId);
        if (delete) {
            return Response.ok().status(204).build();
        } else {
            return Response.status(404).build();
        } 
    }
}
