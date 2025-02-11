package br.edu.ufersa.pizzaria.backend.api.dto;

import br.edu.ufersa.pizzaria.backend.domain.entity.Client;
import br.edu.ufersa.pizzaria.backend.domain.entity.ClientDelivery;
import br.edu.ufersa.pizzaria.backend.domain.entity.ClientLocal;
import br.edu.ufersa.pizzaria.backend.domain.entity.ClientRetirement;
import jakarta.validation.constraints.*;

public class ClientDTO {
  public record ClientResponse(
      String email,
      String phone
  ) {
    public ClientResponse(Client client) {
      this(
          client.getEmail(),
          client.getPhone()
      );
    }
  }

  public record ClientDeliveryCreate(
      Long id,
      String phone,
      @Email String email,
      @Size(min = 8) String password,
      @NotBlank String address
  ) {
    public ClientDelivery toEntity() {
      return new ClientDelivery(phone, email, password, address);
    }
  }

  public record ClientDeliveryResponse(
      Long id,
      String phone,
      String email,
      String address
  ) {
    public ClientDeliveryResponse(ClientDelivery client) {
      this(
          client.getId(),
          client.getPhone(),
          client.getEmail(),
          client.getAddress()
      );
    }
  }

  public record ClientLocalCreate(
      String phone,
      @Email String email,
      @Size(min = 8) String password,
      @NotNull @PositiveOrZero Integer clientTable
  ) {
    public ClientLocal toEntity() {
      return new ClientLocal(phone, email, password, clientTable);
    }
  }

  public record ClientLocalResponse(
      Long id,
      String phone,
      String email,
      Integer clientTable
  ) {
    public ClientLocalResponse(ClientLocal client) {
      this(
          client.getId(),
          client.getPhone(),
          client.getEmail(),
          client.getClientTable()
      );
    }
  }

  public record ClientRetirementCreate(
      String phone,
      @Size(min = 8) String email,
      @Email String password,
      @NotNull @PositiveOrZero Integer code
  ) {
    public ClientRetirement toEntity() {
      return new ClientRetirement(phone, email, password, code);
    }
  }

  public record ClientRetirementResponse(
      Long id,
      String phone,
      String email,
      Integer code) {

    public ClientRetirementResponse(ClientRetirement client) {
      this(
          client.getId(),
          client.getPhone(),
          client.getEmail(),
          client.getCode()
      );
    }
  }
}