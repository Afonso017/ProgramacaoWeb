package br.edu.ufersa.pizzaria.backend.domain.service;

import br.edu.ufersa.pizzaria.backend.api.dto.FlavorDTO.FlavorCreate;
import br.edu.ufersa.pizzaria.backend.api.dto.FlavorDTO.FlavorResponse;
import br.edu.ufersa.pizzaria.backend.api.dto.FlavorDTO.FlavorUpdate;
import br.edu.ufersa.pizzaria.backend.domain.entity.Flavor;
import br.edu.ufersa.pizzaria.backend.domain.repository.FlavorRepository;
import br.edu.ufersa.pizzaria.backend.utils.PizzaSizes;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlavorService {
  private final FlavorRepository repository;

  public FlavorService(FlavorRepository flavorRepository) {
    this.repository = flavorRepository;
  }

  @Transactional
  public List<FlavorResponse> listAll() {
    return repository.findAll().stream().map(FlavorResponse::new).collect(Collectors.toList());
  }

  public FlavorResponse save(FlavorCreate flavorCreate) throws IllegalArgumentException {
    if (repository.findByName(flavorCreate.name()) != null) {
      throw new IllegalArgumentException("Já existe um sabor com o nome informado");
    }

    Flavor newFlavor = repository.save(flavorCreate.toEntity());

    return new FlavorResponse(newFlavor);
  }

  @Transactional
  public FlavorResponse update(FlavorUpdate flavorUpdate) {
    Flavor existingFlavor = repository.findById(flavorUpdate.id())
        .orElseThrow(() -> new IllegalArgumentException("Sabor não encontrado"));

    existingFlavor.setFlavor(flavorUpdate);

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

  public BigDecimal getPriceEntry(Long id, PizzaSizes size) {
    Flavor flavor = repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Sabor não encontrado"));

    return flavor.getPriceEntry(size).getValue();
  }
}