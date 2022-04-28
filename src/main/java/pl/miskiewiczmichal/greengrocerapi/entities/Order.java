package pl.miskiewiczmichal.greengrocerapi.entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "order_tb")
public class Order {
    @Id
    @GeneratedValue(generator = "uuid4")
    @GenericGenerator(name = "UUID", strategy = "uuid4")
    @Type(type = "org.hibernate.type.UUIDCharType")
    @Column(columnDefinition = "CHAR(36)")
    private UUID id;

    private Date creationDate;

    private String status;

    private String warnings;

    @ManyToOne
    @NonNull
    private User createdBy;

    @ManyToOne
    private User driver;

    @ManyToOne
    @NonNull
    private PaymentType paymentType;

    @OneToMany
    @NonNull
    private List<OrderWithProducts> products;

}
