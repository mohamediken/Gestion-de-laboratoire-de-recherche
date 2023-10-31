package com.iken.Labo.repository;

import com.iken.Labo.model.Ressource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RessourceRepository extends JpaRepository<Ressource, Long> {
    // Vous pouvez ajouter des méthodes personnalisées de requête ici si nécessaire
}
