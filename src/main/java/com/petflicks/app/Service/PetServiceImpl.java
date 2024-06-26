package com.petflicks.app.Service;

import com.petflicks.app.Exception.UsernameAlreadyExists;
import com.petflicks.app.Models.User;
import com.petflicks.app.Repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.petflicks.app.Exception.PetAlreadyExists;
import com.petflicks.app.Exception.PetNotFoundException;
import com.petflicks.app.Exception.UserNotFoundException;
import com.petflicks.app.Models.Pet;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PetServiceImpl implements PetService {

    private PetRepository petRepo;

    @Autowired
    public PetServiceImpl(PetRepository petRepo) {
        this.petRepo = petRepo;
    }

    @Override
    public Pet add(Pet newPet) throws PetAlreadyExists {
        try {
            newPet = ((CrudRepository<Pet, Integer>) petRepo).save(newPet);
            return newPet;
        } catch (Exception e) {
            throw new PetAlreadyExists();
        }
    }

    @Override
    @Transactional
    public Pet getPetById(int userId) throws UserNotFoundException, PetNotFoundException {

          //  return petRepo.findById(userId).get();

        return null;
    }

    @Override
    public Pet getPetsByUsername(String username) throws UserNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Pet update(Pet pet) throws PetNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Pet deletePet(Pet pet) throws PetNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Pet findPetByUser(int userId) {
        // TODO Auto-generated method stub
        return null;
    }

}
