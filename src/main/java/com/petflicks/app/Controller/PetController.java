package com.petflicks.app.Controller;

import java.util.HashMap;
import java.util.Map;

import com.petflicks.app.Exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petflicks.app.Exception.PetAlreadyExists;
import com.petflicks.app.Exception.PetNotFoundException;
import com.petflicks.app.Exception.UsernameAlreadyExists;
import com.petflicks.app.Models.Pet;
import com.petflicks.app.Models.User;
import com.petflicks.app.Service.PetService;

@RestController
@RequestMapping(path="/pet/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class PetController {

	
	private PetService petServ;
	
	public PetController() {
		super();
	}
	
	
	@PostMapping
	public ResponseEntity<Map<String,Integer>> add(@RequestBody Pet newPet) {
		
		try
		{
			newPet = petServ.add(newPet);
			Map<String, Integer> newIdMap = new HashMap<>();
			newIdMap.put("generatedId", newPet.getPetId());
			return ResponseEntity.status(HttpStatus.CREATED).body(newIdMap);
			
		}catch (PetAlreadyExists e)	{
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	
	@GetMapping(path="/{petId}")//
	public ResponseEntity<Pet> getPetById(@PathVariable int petId) throws PetNotFoundException, UserNotFoundException {
		System.out.println("get by pet id");
		Pet pet = petServ.getPetById(petId);
		if (pet != null) {
		return  ResponseEntity.ok(pet);
	} else
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping(path="/{userId}")//
	public ResponseEntity<Pet> getPetByUserId(@PathVariable int userId) throws PetNotFoundException{
		Pet pet = petServ.findPetByUser(userId);
		if (pet != null) {
		return ResponseEntity.ok(pet);
		} else
			return ResponseEntity.notFound().build();
	}
	

	
	@PutMapping(path="/{petId}")//
	public ResponseEntity<Pet> updatePet(@PathVariable int petId,
			@RequestBody Pet petToEdit) throws PetNotFoundException {
			
		if (petToEdit != null && petToEdit.getPetId() == petId) {
			petToEdit = petServ.update(petToEdit);
			
			if (petToEdit != null)
				return ResponseEntity.ok(petToEdit);
			else
				return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	
	@DeleteMapping(path="/{petId}")//
	public ResponseEntity<Void> deletePet(@RequestBody Pet deletePet) throws PetNotFoundException {
		if (deletePet != null) {
			petServ.deletePet(deletePet);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
}
