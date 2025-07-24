package fr.afpa.orm.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@DiscriminatorValue("C")
@Table(name = "client")
public class Client extends Person {

    /**
     * Identifiant unique de l'utilisateur
     * Article présentant l'utilisation d'UUID ->
     * https://www.baeldung.com/java-hibernate-uuid-primary-key
     */
    // @Id
    // @GeneratedValue(strategy = GenerationType.UUID)
    // private UUID id;

    /**
     * Association de type "OneToMany" : une personne peut avoir plusieurs comptes
     */
    @JsonIgnore
    @OneToMany(targetEntity = Account.class, mappedBy = "owner")
    private List<Account> accounts;

    /**
     * Association de type "ManyToMany" : Un utilisateur peut souscrire à une ou
     * plusieurs assurances. Chaque assurance peut être associée à plusieurs
     * clients.
     */
    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "client_insurance", joinColumns = @JoinColumn(name = "id_client"), inverseJoinColumns = @JoinColumn(name = "id_insurance"))
    private List<Insurance> insurances = new ArrayList<>();

    public Client() {
        // Constructeur vide.
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
