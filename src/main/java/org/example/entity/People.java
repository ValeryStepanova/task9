package org.example.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@EqualsAndHashCode
@Entity
@ToString
@Table(name = "people")
public class People implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private int age;

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinTable(name = "people_address",
            joinColumns = {@JoinColumn(name = "people_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "address_id", referencedColumnName = "id")})
    private Set<Address> addresses = new HashSet<>();
}
