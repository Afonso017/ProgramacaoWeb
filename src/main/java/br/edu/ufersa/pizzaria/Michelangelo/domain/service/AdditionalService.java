package br.edu.ufersa.pizzaria.Michelangelo.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import br.edu.ufersa.pizzaria.Michelangelo.domain.repository.AdditionalRepository;

@Service
public class AdditionalService {
  private final AdditionalRepository repository;

  public AdditionalService(AdditionalRepository additionalRepository) {
    this.repository = additionalRepository;
  }

  
}