package com.petflicks.app.Service;

import org.springframework.stereotype.Service;

import com.petflicks.app.Exception.InvalidLoginException;
import com.petflicks.app.Exception.PetAlreadyExists;
import com.petflicks.app.Exception.PetNotFoundException;
import com.petflicks.app.Exception.UserNotFoundException;
import com.petflicks.app.Exception.UsernameAlreadyExists;
import com.petflicks.app.Models.Pet;
import com.petflicks.app.Models.User;

@Service
public interface PetService {

	public Pet add(Pet newPet) throws PetAlreadyExists;
	public Pet getPetById(int petId) throws UserNotFoundException, PetNotFoundException;
	public Pet getPetsByUsername(String username) throws UserNotFoundException;
	public Pet update(Pet pet) throws PetNotFoundException;
	public Pet deletePet(Pet pet) throws PetNotFoundException;
	public Pet findPetByUser(int userId);
	
	
	
}
