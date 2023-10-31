package com.iken.Labo.service;

import com.iken.Labo.model.Ressource;
import com.iken.Labo.repository.RessourceRepository;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RessourceService {

    private final RessourceRepository ressourceRepository;

    @Autowired
    public RessourceService(RessourceRepository ressourceRepository) {
        this.ressourceRepository = ressourceRepository;
    }

    public List<Ressource> getAllRessources() {
        return ressourceRepository.findAll();
    }

    public Ressource getRessourceById(Long ressourceId) {
        return ressourceRepository.findById(ressourceId).orElse(null);
    }

    public Ressource createRessource(Ressource ressource) {
        return ressourceRepository.save(ressource);
    }

    public Ressource updateRessource(Ressource ressource) {
        return ressourceRepository.save(ressource);
    }

    public void deleteRessource(Long ressourceId) {
        ressourceRepository.deleteById(ressourceId);
    }


}
