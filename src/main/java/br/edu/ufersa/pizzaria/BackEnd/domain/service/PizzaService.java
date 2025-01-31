package br.edu.ufersa.pizzaria.BackEnd.domain.service;

import java.util.List;
import java.util.stream.Collectors;

import br.edu.ufersa.pizzaria.BackEnd.domain.entity.Pizza;
import br.edu.ufersa.pizzaria.BackEnd.domain.repository.BorderRepository;
import br.edu.ufersa.pizzaria.BackEnd.domain.repository.FlavorRepository;
import org.springframework.stereotype.Service;
import br.edu.ufersa.pizzaria.BackEnd.api.dto.PizzaDTO.PizzaCreate;
import br.edu.ufersa.pizzaria.BackEnd.api.dto.PizzaDTO.PizzaResponse;
import br.edu.ufersa.pizzaria.BackEnd.domain.repository.PizzaRepository;

@Service
public class PizzaService {

  private final PizzaRepository repository;
  private final AdditionalService additionalService;
  private final FlavorRepository flavorRepository;
  private final BorderRepository borderRepository;

  public PizzaService(PizzaRepository repository, AdditionalService additionalService, FlavorRepository flavorRepository, BorderRepository borderRepository) {
    this.repository = repository;
    this.additionalService = additionalService;
    this.flavorRepository = flavorRepository;
    this.borderRepository = borderRepository;
  }

  public List<PizzaResponse> listAll() {
    List<PizzaResponse> pizzas = repository.findAll()
        .stream().map(pizza -> new PizzaResponse(pizza))
        .collect(Collectors.toList());

    return pizzas;
  }

  public PizzaResponse save(PizzaCreate pizzaCreate) {
    if (repository.findByName(pizzaCreate.name()) != null) {
      throw new IllegalArgumentException("Já existe uma pizza com o nome informado");
    }

    var pizza = repository.save(toEntity(pizzaCreate));

    return new PizzaResponse(pizza);
  }

  public PizzaResponse update(Long id, PizzaCreate pizzaCreate) {
    var pizza = repository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Pizza não encontrada"));

    pizza.setPizza(pizzaCreate);
    repository.save(pizza);

    return new PizzaResponse(pizza);
  }

  public void delete(Long id) {
    var pizza = repository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Pizza não encontrada"));

    repository.delete(pizza);
  }

  public PizzaResponse findById(Long id) {
    var pizza = repository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Pizza não encontrada"));

    return new PizzaResponse(pizza);
  }

  public Pizza toEntity(PizzaCreate pizzaCreate) {
    var flavorOneObj = flavorRepository.findById(pizzaCreate.flavorOne()).orElseThrow(() -> new IllegalArgumentException("Sabor não encontrado"));
    var flavorTwoObj = flavorRepository.findById(pizzaCreate.flavorTwo()).orElseThrow(() -> new IllegalArgumentException("Sabor não encontrado"));
    var borderObj = borderRepository.findById(pizzaCreate.border()).orElseThrow(() -> new IllegalArgumentException("Borda não encontrada"));
    var aditionalsList = additionalService.findAllById(pizzaCreate.aditionals());
    return new Pizza(pizzaCreate.name(), pizzaCreate.description(), pizzaCreate.price(), pizzaCreate.image(),
        flavorOneObj, flavorTwoObj, borderObj, aditionalsList, pizzaCreate.size());
  }
}