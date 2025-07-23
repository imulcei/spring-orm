package fr.afpa.orm.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Un DTO (Data Transfer Object) est un objet léger utilisé pour transférer des
 * données entre les couches d’une application. Un DTO se compose généralement
 * de getters et setters pour chaque champ des données à transmettre.
 * - Sépare la couche Entity de la couche Controllers
 * - Masquer les attributs non-nécessaires pour une requête donnée => diminue la
 * taille des chargements
 */
public class AccountDto {
    private Long id;
    private LocalDateTime creationTime;
    private BigDecimal balance;
    private ClientDto client;

    public AccountDto() {

    }

    /**
     * Getters et setters
     */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public ClientDto getClient() {
        return client;
    }

    public void setClient(ClientDto client) {
        this.client = client;
    }

}
