package br.edu.ufersa.pizzaria.backend.domain.service;

import br.edu.ufersa.pizzaria.backend.api.dto.PizzaDTO.PizzaCreate;
import br.edu.ufersa.pizzaria.backend.api.dto.PizzaDTO.PizzaResponse;
import br.edu.ufersa.pizzaria.backend.api.dto.PizzaDTO.PizzaUpdate;
import br.edu.ufersa.pizzaria.backend.domain.entity.Additional;
import br.edu.ufersa.pizzaria.backend.domain.entity.Flavor;
import br.edu.ufersa.pizzaria.backend.domain.entity.Pizza;
import br.edu.ufersa.pizzaria.backend.domain.repository.AdditionalRepository;
import br.edu.ufersa.pizzaria.backend.domain.repository.BorderRepository;
import br.edu.ufersa.pizzaria.backend.domain.repository.FlavorRepository;
import br.edu.ufersa.pizzaria.backend.domain.repository.PizzaRepository;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PizzaService {

  private final PizzaRepository repository;
  private final FlavorRepository flavorRepository;
  private final BorderRepository borderRepository;
  private final AdditionalRepository additionalRepository;

  public PizzaService(PizzaRepository repository , FlavorRepository flavorRepository, BorderRepository borderService, AdditionalRepository additionalService) {
    this.repository = repository;
    this.flavorRepository = flavorRepository;
    this.borderRepository = borderService;
    this.additionalRepository = additionalService;
  }

  public List<PizzaResponse> listAll() {
    return repository.findAll().stream().map(PizzaResponse::new).collect(Collectors.toList());
  }

  public PizzaResponse save(PizzaCreate pizzaCreate) {
    Pizza pizza = pizzaCreate.toEntity();
    setElements(pizza);

    pizza = repository.save(pizza);

    return new PizzaResponse(pizza);
  }

  public Pizza save(Pizza pizzaCreate) {
    setElements(pizzaCreate);

    return repository.save(pizzaCreate);
  }

  public PizzaResponse update(Long id, PizzaUpdate pizzaUpdate) {
    var pizza = repository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Pizza não encontrada"));

    pizza.setPizza(pizzaUpdate);
    setElements(pizza);
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

  public BigDecimal findPricePizza(Long id) {
    var pizza = repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Pizza não encontrada"));
    return pizza.getPrice();
  }

  public void setElements(Pizza pizza) {
    setFlavors(pizza);
    setBorder(pizza);
    setAditionals(pizza);
    setDefaultNameIfEmpty(pizza);
    setDefaultDescriptionIfEmpty(pizza);
    pizza.setPrice(pizza.getPrice());
  }

  private void setFlavors(Pizza pizza) {
    pizza.setFlavorOne(findFlavorById(pizza.getFlavorOne().getId(), "Sabor 1 não encontrado"));
    if (pizza.getFlavorTwo() != null) {
      pizza.setFlavorTwo(findFlavorById(pizza.getFlavorTwo().getId(), "Sabor 2 não encontrado"));
    }
  }

  private Flavor findFlavorById(Long id, String errorMessage) {
    return flavorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(errorMessage));
  }

  private void setBorder(Pizza pizza) {
    if (pizza.getBorder() != null) {
      pizza.setBorder(borderRepository.findById(pizza.getBorder().getId())
              .orElseThrow(() -> new IllegalArgumentException("Borda não encontrada")));
    }
  }

  private void setAditionals(Pizza pizza) {
    if (pizza.getAditionals() != null && !pizza.getAditionals().isEmpty()) {
      pizza.setAditionals(pizza.getAditionals().stream()
              .map(additional -> additionalRepository.findById(additional.getId())
                      .orElseThrow(() -> new IllegalArgumentException("Adicional não encontrado")))
              .collect(Collectors.toList()));
    }
  }

  private void setDefaultNameIfEmpty(Pizza pizza) {
    if (pizza.getName() == null || pizza.getName().isEmpty()) {
      String flavorNames = pizza.getFlavorOne().getName();
      if (pizza.getFlavorTwo() != null) {
        flavorNames += " e " + pizza.getFlavorTwo().getName();
      }
      pizza.setName(flavorNames);
    }
  }

  private void setDefaultDescriptionIfEmpty(Pizza pizza) {
    if (pizza.getDescription() == null || pizza.getDescription().isEmpty()) {
      StringBuilder description = new StringBuilder();
      description.append(pizza.getFlavorOne().getDescription());

      if (pizza.getFlavorTwo() != null) {
        description.append(" com ").append(pizza.getFlavorTwo().getDescription());
      }

      if (pizza.getBorder() != null) {
        description.append(", com borda de ").append(pizza.getBorder().getName());
      }

      if (pizza.getAditionals() != null && !pizza.getAditionals().isEmpty()) {
        description.append(" e adicionais de ");
        description.append(pizza.getAditionals().stream()
                .map(Additional::getName)
                .collect(Collectors.joining(", ")));
      }

      pizza.setDescription(description.toString());
    }
  }
}