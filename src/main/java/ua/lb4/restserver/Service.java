package ua.lb4.restserver;

import ua.lb4.restserver.bo.CourierType;
import ua.lb4.restserver.bo.DeliveryType;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Path("/")
public class   Service {

    private static Logger log;
    private static Database database = new Database();

    static {log = Logger.getLogger(Service.class.getName());}

    @GET
    @Path("test")
    @Produces(MediaType.APPLICATION_JSON)
    public String test() {
        return "work";
    }

    @POST
    @Path("deliveries")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public DeliveryType createDelivery(DeliveryType delivery) {
        database.deliveries.add(delivery);
        return delivery;
    }

    @GET
    @Path("/clients/{client_id}/deliveries")
    @Produces(MediaType.APPLICATION_XML)
    public List<DeliveryType> getDeliveries(@PathParam("client_id") String id) {
       return database.deliveries.stream()
                .filter(delivery -> delivery.getClient().getClientId().equals(id))
                .collect(Collectors.toList());
    }

    @DELETE
    @Path("deliveries/cancel/{delivery_id}")
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_XML)
    public DeliveryType cancelDelivery(@PathParam("delivery_id") String deliveryId, DeliveryType dle) {
        DeliveryType deliveryType = database.deliveries.stream()
                .filter(delivery -> delivery.getDeliveryId().equals(deliveryId))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);

        database.deliveries.removeIf(delivery -> delivery.getDeliveryId().equals(deliveryId));
        return deliveryType;
    }
    @PUT
    @Path("deliveries")
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_XML)
    public DeliveryType updateDelivery(DeliveryType deliveryType) {
        database.deliveries.removeIf(delivery -> delivery.getDeliveryId().equals(deliveryType.getDeliveryId()));
        database.deliveries.add(deliveryType);
        return deliveryType;
    }

    @PUT
    @Path("deliveries/couriers/{delivery_id}")
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_XML)
    public DeliveryType assignCourier(@PathParam("delivery_id") String deliveryId, CourierType courierType) {
        return database.deliveries.stream()
                .filter(deliveryType -> deliveryType.getDeliveryId().equals(deliveryId))
                .peek(deliveryType -> deliveryType.setCourier(courierType))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }

    @GET
    @Path("couriers")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public List<CourierType> getCouriers(DeliveryType delivery) {
        return database.couriers;
    }
}
