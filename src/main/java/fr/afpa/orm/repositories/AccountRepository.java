package fr.afpa.orm.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.afpa.orm.entities.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

    /**
     * @return tous les comptes enregistrés
     */
    List<Account> findAll();


}
