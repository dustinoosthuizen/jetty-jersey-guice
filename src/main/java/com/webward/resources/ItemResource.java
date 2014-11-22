package com.webward.resources;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import com.webward.dao.ItemDao;
import com.webward.dto.ItemDto;
import com.webward.entities.Item;
import com.webward.transformer.ItemTransformer;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * A minimal Jersey Resource.
 */
@Path("/item")
public class ItemResource {

    @Inject
    private ItemDao itemDao;
    @Inject
    private ItemTransformer itemTransformer;

    @GET
    @Produces("text/plain")
    @Path("/who/{who}")
    public String who(@PathParam("who") String name) {
        return "Greetings, " + name + "!";
    }

    @GET
    @Produces("application/json")
    @Path("/getById/{id}")
    public ItemDto getItemById(@PathParam("id") String id) {
        Item anItem = itemDao.findById(new Integer(id));
        return itemTransformer.toDto(anItem);
    }

    @GET
    @Produces("application/json")
    @Path("/getByName/{name}")
    public ItemDto getByName(@PathParam("name") String name) {
        return itemTransformer.toDto(itemDao.findByName(name));
    }

    @POST
    @Path("/create")
    @Consumes("application/json")
    @Produces("application/json")
    @Transactional
    public ItemDto createItem(ItemDto item)
    {
        itemDao.save(itemTransformer.fromDto(item));
        return item;
    }


    @DELETE
    @Path("/deleteById/{id}")
    @Transactional
    public Response deleteById(@PathParam("id") String id)
    {
        itemDao.deleteById(new Integer(id));
        return Response.status(204).build();
    }

//    @GET
//    @Produces("text/plain")
//    @Path("/whoAsync/{who}")
//    public String whoAsync(@Suspended final AsyncResponse asyncResponse) {
//
//        return "Greetings";
//    }
}
