package ua.lb4.restserver.bo;

import java.util.List;

public class Deliveries {
    public List<DeliveryType> deliveriesList;

    Deliveries(List<DeliveryType> deliveriesList) {
        this.deliveriesList = deliveriesList;
    }

    public static Deliveries of(List<DeliveryType> deliveriesList) {
        return new Deliveries(deliveriesList);
    }
}
