package br.edu.ufersa.pizzaria.Michelangelo.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "client_id")
public class ClientLocal extends Client {
  private int clientTable; // Específico para clientes presenciais (local)

  public ClientLocal() {
  }

  public ClientLocal(int clientTable) {
    this.clientTable = clientTable;
  }

  public ClientLocal(String email, String password, String phone, int clientTable) {
    super(email, password, phone);
    this.clientTable = clientTable;
  }

  /**
   * @return int return the clientTable
   */
  public int getClientTable() {
    return clientTable;
  }

  /**
   * @param clientTable the clientTable to set
   */
  public void setClientTable(int clientTable) {
    this.clientTable = clientTable;
  }

}