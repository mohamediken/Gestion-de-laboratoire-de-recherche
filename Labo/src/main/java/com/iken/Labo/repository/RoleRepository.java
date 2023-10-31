package com.iken.Labo.repository;

import com.iken.Labo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    Role findByRolename(String rolename);

}
