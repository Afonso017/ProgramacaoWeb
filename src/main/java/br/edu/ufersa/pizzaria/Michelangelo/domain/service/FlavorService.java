package br.edu.ufersa.pizzaria.Michelangelo.domain.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import br.edu.ufersa.pizzaria.Michelangelo.api.dto.FlavorDTO.FlavorCreate;
import br.edu.ufersa.pizzaria.Michelangelo.api.dto.FlavorDTO.FlavorResponse;
import br.edu.ufersa.pizzaria.Michelangelo.domain.entity.Flavor;
import br.edu.ufersa.pizzaria.Michelangelo.domain.repository.FlavorRepository;

@Service
public class FlavorService {
  private final FlavorRepository repository;

  public FlavorService(FlavorRepository flavorRepository) {
    this.repository = flavorRepository;
  }

  public List<FlavorResponse> listAll() {
    List<FlavorResponse> flavorList = repository.findAll()
        .stream().map(flavor -> new FlavorResponse(flavor))
        .collect(Collectors.toList());

    return flavorList;
  }

  public FlavorResponse create(FlavorCreate flavorCreate) {
    if (repository.findByName(flavorCreate.name()) != null) {
      throw new IllegalArgumentException("Já existe um sabor com o nome informado");
    }

    Flavor newFlavor = repository.save(flavorCreate.toEntity());

    return new FlavorResponse(newFlavor);
  }

  public FlavorResponse update(Long id, FlavorCreate flavorCreate) {
    Flavor flavor = repository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Sabor não encontrado"));

    flavor.setName(flavorCreate.name());
    flavor.setDescription(flavorCreate.description());
    flavor.setPrice(flavorCreate.price());

    repository.save(flavor);

    return new FlavorResponse(flavor);
  }

  public void delete(Long id) {
    Flavor flavor = repository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Sabor não encontrado"));

    repository.delete(flavor);
  }

  public FlavorResponse findById(Long id) {
    Flavor flavor = repository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Sabor não encontrado"));

    return new FlavorResponse(flavor);
  }
}