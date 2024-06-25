package com.petflicks.app.Repository;

import com.petflicks.app.Models.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import javax.persistence.Table;

@Repository
@Table(name="pet")
public interface PetRepository extends JpaRepository<Pet, Integer> {

}
