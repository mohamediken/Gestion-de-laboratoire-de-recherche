package com.iken.Labo.repository;

import com.iken.Labo.model.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicationRepository extends JpaRepository<Publication, Long> {
    List<Publication> findTop10ByOrderByPublicationDateDesc();

    // Vous pouvez ajouter des méthodes spécifiques à la recherche ou à la manipulation des publications ici si nécessaire.
}
