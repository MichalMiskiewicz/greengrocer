package pl.miskiewiczmichal.greengrocerapi.DTOs;

import pl.miskiewiczmichal.greengrocerapi.entities.MeasureType;

public class AddProductDTO {
    public String name;
    public String category;
    public String description;
    public Double price;
    public Integer amount;
    public String measureType;
}
