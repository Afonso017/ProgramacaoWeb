package br.edu.ufersa.pizzaria.Michelangelo.domain.service;

import org.springframework.stereotype.Service;

import br.edu.ufersa.pizzaria.Michelangelo.domain.repository.FlavorRepository;

@Service
public class FlavorService {
  private final FlavorRepository repository;

  public FlavorService(FlavorRepository flavorRepository) {
    this.repository = flavorRepository;
  }

}