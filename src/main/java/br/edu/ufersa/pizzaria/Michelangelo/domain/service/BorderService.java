package br.edu.ufersa.pizzaria.Michelangelo.domain.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import br.edu.ufersa.pizzaria.Michelangelo.api.dto.BorderDTO.BorderCreate;
import br.edu.ufersa.pizzaria.Michelangelo.api.dto.BorderDTO.BorderResponse;
import br.edu.ufersa.pizzaria.Michelangelo.domain.entity.Border;
import br.edu.ufersa.pizzaria.Michelangelo.domain.repository.BorderRepository;

@Service
public class BorderService {
  private final BorderRepository repository;

  public BorderService(BorderRepository borderRepository) {
    this.repository = borderRepository;
  }

  public List<BorderResponse> findAll() {
    List<BorderResponse> borders = repository.findAll()
        .stream().map(border -> new BorderResponse(border))
        .collect(Collectors.toList());

    return borders;
  }

  public BorderResponse create(BorderCreate borderCreate) {
    if (repository.findByName(borderCreate.name()) != null) {
      throw new IllegalArgumentException("Já existe uma borda com o nome informado");
    }

    Border border = repository.save(borderCreate.toEntity());

    return new BorderResponse(border);
  }

  public BorderResponse update(Long id, BorderCreate borderCreate) {
    Border border = repository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Borda não encontrada"));

    border.setName(borderCreate.name());
    border.setPrice(borderCreate.price());

    repository.save(border);

    return new BorderResponse(border);
  }

  public void delete(Long id) {
    Border border = repository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Borda não encontrada"));

    repository.delete(border);
  }

  public BorderResponse findById(Long id) {
    Border border = repository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Borda não encontrada"));

    return new BorderResponse(border);
  }
}