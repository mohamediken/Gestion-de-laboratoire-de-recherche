package com.iken.Labo.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Builder
@Entity
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private  String username;
    @Column(unique = true)
    private String email;
    private String password;
    private String Phone ;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "member_role",
            joinColumns = @JoinColumn(name = "id_member"),
            inverseJoinColumns = @JoinColumn(name = "id_role")
    )
    @MapKey(name = "id")
    private List<Role> roles;
}