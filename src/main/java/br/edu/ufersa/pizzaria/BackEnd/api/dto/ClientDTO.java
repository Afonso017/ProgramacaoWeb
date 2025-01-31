package br.edu.ufersa.pizzaria.BackEnd.api.dto;

import br.edu.ufersa.pizzaria.BackEnd.domain.entity.Client;
import br.edu.ufersa.pizzaria.BackEnd.domain.entity.ClientDelivery;
import br.edu.ufersa.pizzaria.BackEnd.domain.entity.ClientLocal;
import br.edu.ufersa.pizzaria.BackEnd.domain.entity.ClientRetirement;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public class ClientDTO {
  public record ClientResponse(
      String email,
      String phone) {

    public ClientResponse(Client client) {
      this(
          client.getEmail(),
          client.getPhone());
    }
  }

  public record ClientDeliveryCreate(
      String email,
      String password,
      String phone,
      @NotBlank String address) {

    public Client toEntity() {
      return new ClientDelivery(email, password, phone, address);
    }
  }

  public record ClientDeliveryResponse(
      Long id,
      String email,
      String phone,
      String address) {

    public ClientDeliveryResponse(ClientDelivery client) {
      this(
          client.getId(),
          client.getEmail(),
          client.getPhone(),
          client.getAddress());
    }
  }

  public record ClientLocalCreate(
      Long id,
      String email,
      String password,
      String phone,
      @PositiveOrZero int clientTable) {

    public Client toEntity() {
      return new ClientLocal(id, email, password, phone, clientTable);
    }
  }

  public record ClientLocalResponse(
      String email,
      String phone,
      int clientTable) {

    public ClientLocalResponse(ClientLocal client) {
      this(
          client.getEmail(),
          client.getPhone(),
          client.getClientTable());
    }
  }

  public record ClientRetirementCreate(
      String email,
      String password,
      String phone,
      @PositiveOrZero int code) {

    public Client toEntity() {
      return new ClientRetirement(email, password, phone, code);
    }
  }

  public record ClientRetirementResponse(
      String email,
      String phone,
      int code) {

    public ClientRetirementResponse(ClientRetirement client) {
      this(
          client.getEmail(),
          client.getPhone(),
          client.getCode());
    }
  }
}