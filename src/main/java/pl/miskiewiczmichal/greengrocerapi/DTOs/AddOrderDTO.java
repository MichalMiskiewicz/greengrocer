package pl.miskiewiczmichal.greengrocerapi.DTOs;

import pl.miskiewiczmichal.greengrocerapi.entities.OrderWithProducts;
import pl.miskiewiczmichal.greengrocerapi.entities.PaymentType;
import pl.miskiewiczmichal.greengrocerapi.entities.User;

import java.util.Date;
import java.util.List;

public class AddOrderDTO {
    public Date creationDate;
    public User createdBy;
    public User driver;
    public String status;
    public String warnings;
    public PaymentType payment;
    public List<OrderWithProducts> products;
}
