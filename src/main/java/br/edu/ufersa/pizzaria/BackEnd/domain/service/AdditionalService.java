package br.edu.ufersa.pizzaria.backend.domain.service;

import br.edu.ufersa.pizzaria.backend.api.dto.AdditionalDTO.AdditionalCreate;
import br.edu.ufersa.pizzaria.backend.api.dto.AdditionalDTO.AdditionalResponse;
import br.edu.ufersa.pizzaria.backend.domain.entity.Additional;
import br.edu.ufersa.pizzaria.backend.domain.repository.AdditionalRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdditionalService {
  private final AdditionalRepository repository;

  public AdditionalService(AdditionalRepository additionalRepository) {
    this.repository = additionalRepository;
  }

  public List<AdditionalResponse> listAll() {
    return repository.findAll().stream().map(AdditionalResponse::new).collect(Collectors.toList());
  }

  public AdditionalResponse save(AdditionalCreate additionalCreate) {
    if (repository.findByName(additionalCreate.name()) != null) {
      throw new IllegalArgumentException("Já existe um adicional com o nome informado");
    }

    Additional additional = repository.save(additionalCreate.toEntity());

    return new AdditionalResponse(additional);
  }

  public AdditionalResponse update(Long id, AdditionalCreate additionalCreate) {
    Additional additional = repository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Adicional não encontrado"));

    additional.setAdditional(additionalCreate);

    repository.save(additional);

    return new AdditionalResponse(additional);
  }

  public void delete(Long id) {
    Additional additional = repository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Adicional não encontrado"));

    repository.delete(additional);
  }

  public AdditionalResponse findById(Long id) {
    Additional additional = repository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Adicional não encontrado"));

    return new AdditionalResponse(additional);
  }

  public BigDecimal getPriceEntry(Long id) {
    Additional additional = repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Adicional não encontrado"));

    return additional.getPrice();
  }
}