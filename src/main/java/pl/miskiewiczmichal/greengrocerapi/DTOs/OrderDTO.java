package pl.miskiewiczmichal.greengrocerapi.DTOs;

import pl.miskiewiczmichal.greengrocerapi.entities.PaymentType;
import pl.miskiewiczmichal.greengrocerapi.entities.User;

import java.util.Date;
import java.util.UUID;

public class OrderDTO {
    public UUID orderId;
    public Date creationDate;
    public User createdBy;
    public String description;
    public User driver;
    public String status;
    public String warnings;
    public PaymentType payment;
}
