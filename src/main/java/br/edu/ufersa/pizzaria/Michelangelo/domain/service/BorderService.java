package br.edu.ufersa.pizzaria.Michelangelo.domain.service;

import org.springframework.stereotype.Service;

import br.edu.ufersa.pizzaria.Michelangelo.domain.repository.BorderRepository;

@Service
public class BorderService {
  private final BorderRepository repository;

  public BorderService(BorderRepository borderRepository) {
    this.repository = borderRepository;
  }

}
