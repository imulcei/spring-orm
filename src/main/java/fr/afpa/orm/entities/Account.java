package fr.afpa.orm.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

// import com.fasterxml.jackson.annotation.JsonBackReference;
// import com.fasterxml.jackson.annotation.JsonIgnore;
// import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
// import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
// import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "account")
public class Account {
    /**
     * Identifiant unique du compte
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Prénom du compte
     */
    @Column(name = "first_name")
    private String firstName;
    /**
     * Nom du compte
     */
    @Column(name = "last_name")
    private String lastName;
    /**
     * Adresse email (unique) du compte
     */
    @Column(name = "email")
    private String email;
    /**
     * Date d'anniversaire du compte
     */
    @Column(name = "birthday")
    private LocalDate birthday;
    /**
     * Date de création du compte
     */
    @Column(name = "creationTime")
    private LocalDateTime creationTime;
    /**
     * Balance du compte
     */
    @Column(name = "balance")
    private BigDecimal balance;

    /**
     * Association de type "OneToMany" : une personne peut avoir plusieurs comptes
     */
    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client owner;

    /**
     * Constructeur vide
     */
    public Account() {

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
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

    public Client getOwner() {
        return owner;
    }

    public void setOwner(Client owner) {
        this.owner = owner;
    };
}
