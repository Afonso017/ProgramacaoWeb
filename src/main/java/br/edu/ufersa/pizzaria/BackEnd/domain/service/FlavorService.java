package br.edu.ufersa.pizzaria.BackEnd.domain.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import br.edu.ufersa.pizzaria.BackEnd.api.dto.FlavorDTO.FlavorCreate;
import br.edu.ufersa.pizzaria.BackEnd.api.dto.FlavorDTO.FlavorResponse;
import br.edu.ufersa.pizzaria.BackEnd.api.dto.FlavorDTO.FlavorUpdate;
import br.edu.ufersa.pizzaria.BackEnd.domain.entity.Flavor;
import br.edu.ufersa.pizzaria.BackEnd.domain.repository.FlavorRepository;
import jakarta.transaction.Transactional;

@Service
public class FlavorService {
  private final FlavorRepository repository;

  public FlavorService(FlavorRepository flavorRepository) {
    this.repository = flavorRepository;
  }

  @Transactional
  public List<FlavorResponse> listAll() {
    List<FlavorResponse> flavorList = repository.findAll()
        .stream().map(flavor -> new FlavorResponse(flavor))
        .collect(Collectors.toList());

    return flavorList;
  }

  public FlavorResponse save(FlavorCreate flavorCreate) throws IllegalArgumentException {
    if (repository.findByName(flavorCreate.name()) != null) {
      throw new IllegalArgumentException("Já existe um sabor com o nome informado");
    }

    Flavor newFlavor = repository.save(flavorCreate.toEntity());

    return new FlavorResponse(newFlavor);
  }

  @Transactional
  public FlavorResponse update(Long id, FlavorUpdate flavorCreate) {
    Flavor existingFlavor = repository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Sabor não encontrado"));

    existingFlavor.setFlavor(flavorCreate);

    repository.save(existingFlavor);

    return new FlavorResponse(existingFlavor);
  }

  @Transactional
  public void delete(Long id) {
    Flavor flavor = repository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Sabor não encontrado"));

    repository.delete(flavor);
  }

  @Transactional
  public FlavorResponse findById(Long id) {
    Flavor flavor = repository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Sabor não encontrado"));

    return new FlavorResponse(flavor);
  }
}