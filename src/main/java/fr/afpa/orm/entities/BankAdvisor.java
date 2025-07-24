package fr.afpa.orm.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@DiscriminatorValue("B")
@Table(name = "bankadvisors")
public class BankAdvisor extends Person {
    /**
     * Date d'embauche
     */
    @Column(name = "hiring_date")
    private LocalDateTime hiringDate;
    /**
     * Type d'assurance : assurance, placement, prêt immobilier,
     * crédit à la consommation
     */
    @Column(name = "speciality")
    private Speciality speciality;

    public BankAdvisor() {
    }

    public LocalDateTime getHiringDate() {
        return hiringDate;
    }

    public void setHiringDate(LocalDateTime hiringDate) {
        this.hiringDate = hiringDate;
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

}
