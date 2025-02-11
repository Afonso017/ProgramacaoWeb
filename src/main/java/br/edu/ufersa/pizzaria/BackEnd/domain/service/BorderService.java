package br.edu.ufersa.pizzaria.backend.domain.service;

import br.edu.ufersa.pizzaria.backend.api.dto.BorderDTO.BorderCreate;
import br.edu.ufersa.pizzaria.backend.api.dto.BorderDTO.BorderResponse;
import br.edu.ufersa.pizzaria.backend.domain.entity.Border;
import br.edu.ufersa.pizzaria.backend.domain.repository.BorderRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BorderService {
  private final BorderRepository repository;

  public BorderService(BorderRepository borderRepository) {
    this.repository = borderRepository;
  }

  public List<BorderResponse> listAll() {
    return repository.findAll().stream().map(BorderResponse::new).collect(Collectors.toList());
  }

  public BorderResponse save(BorderCreate borderCreate) {
    if (repository.findByName(borderCreate.name()) != null) {
      throw new IllegalArgumentException("Já existe uma borda com o nome informado");
    }

    Border border = repository.save(borderCreate.toEntity());

    return new BorderResponse(border);
  }

  public BorderResponse update(Long id, BorderCreate borderCreate) {
    Border border = repository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Borda não encontrada"));

    border.setBorder(borderCreate);

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

  public BigDecimal getPriceEntry(Long id) {
    Border border = repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Borda não encontrada"));

    return border.getPrice();
  }
}