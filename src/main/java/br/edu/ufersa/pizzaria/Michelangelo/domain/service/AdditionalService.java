package br.edu.ufersa.pizzaria.Michelangelo.domain.service;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import br.edu.ufersa.pizzaria.Michelangelo.api.dto.AdditionalDTO.AdditionalCreate;
import br.edu.ufersa.pizzaria.Michelangelo.api.dto.AdditionalDTO.AdditionalResponse;
import br.edu.ufersa.pizzaria.Michelangelo.domain.entity.Additional;
import br.edu.ufersa.pizzaria.Michelangelo.domain.repository.AdditionalRepository;

@Service
public class AdditionalService {
  private final AdditionalRepository repository;

  public AdditionalService(AdditionalRepository additionalRepository) {
    this.repository = additionalRepository;
  }

  public List<AdditionalResponse> listAll() {
    List<AdditionalResponse> additionalList = repository.findAll()
        .stream().map(additional -> new AdditionalResponse(additional))
        .collect(Collectors.toList());
    return additionalList;
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

    additional.setName(additionalCreate.name());
    additional.setDescription(additionalCreate.description());
    additional.setPrice(additionalCreate.price());
    additional.setImage(additionalCreate.image());

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
}