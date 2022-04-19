package pl.miskiewiczmichal.greengrocerapi.entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "user_tb")
public class User {
    @Id
    @GeneratedValue(generator = "uuid4")
    @GenericGenerator(name = "UUID", strategy = "uuid4")
    @Type(type = "org.hibernate.type.UUIDCharType")
    @Column(columnDefinition = "CHAR(36)")
    private UUID id;

    private String username;

    private String name;

    private String surname;

    private String emailAddress;

    private String telNumber;

    @OneToOne
    private Address address;

    @ManyToOne
    private UserType userType;
}
