package com.iken.Labo.service;

import com.iken.Labo.model.Publication;
import com.iken.Labo.repository.PublicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublicationService {

    private final PublicationRepository publicationRepository;

    @Autowired
    public PublicationService(PublicationRepository publicationRepository) {
        this.publicationRepository = publicationRepository;
    }

    public List<Publication> getAllPublications() {
        return publicationRepository.findAll();
    }

    public void savePublication(Publication publication) {
        publicationRepository.save(publication);
    }

    public List<Publication> getRecentPublications() {
        return publicationRepository.findTop10ByOrderByPublicationDateDesc();

    }
}

