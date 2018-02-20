package ru.job4j.springmvc.controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import ru.job4j.springmvc.models.Order;

import java.util.List;

public class ListToJson {

    public static JsonArray listToJsonArray(List<Order> orders) {
        JsonArray array = new JsonArray();
        for (Order order: orders) {
            JsonObject object = new JsonObject();
            object.addProperty("orderId", order.getId());
            object.addProperty("mileage", order.getMileage());
            object.addProperty("sold", order.isSold());
            object.addProperty("price", order.getPrice());
            object.addProperty("carName", order.getCar().getName());
            object.addProperty("carId", order.getCar().getId());
            object.addProperty("date", String.format("%1$TY", order.getReleaseDate()));
            object.addProperty("userId", order.getUser().getId());
            array.add(object);
        }
        return array;
    }
}
