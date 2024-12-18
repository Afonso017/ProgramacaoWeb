package br.edu.ufersa.pizzaria.Michelangelo.domain.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import br.edu.ufersa.pizzaria.Michelangelo.api.dto.PizzaDTO.PizzaCreate;
import br.edu.ufersa.pizzaria.Michelangelo.api.dto.PizzaDTO.PizzaResponse;
import br.edu.ufersa.pizzaria.Michelangelo.domain.repository.PizzaRepository;

@Service
public class PizzaService {

  private final PizzaRepository repository;

  public PizzaService(PizzaRepository repository) {
    this.repository = repository;
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

    var pizza = repository.save(pizzaCreate.toEntity());

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
}