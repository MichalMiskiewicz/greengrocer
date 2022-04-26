package pl.miskiewiczmichal.greengrocerapi.DTOs;

import pl.miskiewiczmichal.greengrocerapi.entities.MeasureType;

import java.util.UUID;

public class ProductDTO {
    public UUID productId;
    public String name;
    public String category;
    public String description;
    public Double price;
    public Integer amount;
    public MeasureType measureType;
}
