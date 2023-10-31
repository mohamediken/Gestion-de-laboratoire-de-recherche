package com.iken.Labo.model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    @Column(unique = true)
    private String id_role;
    //@Column(name = "role_name",unique = true) // Add this annotation to specify the column name
    private String rolename;


}