package com.petflicks.app.Petflicks.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.petflicks.app.PetflicksApplication;
import com.petflicks.app.Exception.UsernameAlreadyExists;
import com.petflicks.app.Models.User;
import com.petflicks.app.Repository.PetRepository;
import com.petflicks.app.Repository.UserRepository;
import com.petflicks.app.Service.UserService;

@SpringBootTest(classes = PetflicksApplication.class)
public class UserServiceTest {

	@MockBean
	private UserRepository userRepo;
	@MockBean
	private PetRepository petRepo;

	@Autowired
	private static UserService userServ;

	private static ArrayList<User> mockUsers;
	private static User testUser = new User("cwid", "pass1234", "Chirs", "Wider", "cw@mmail.ccc", "Admin", null,
			"sdfsdfswdef", 1);
	static String name;

	@BeforeAll
	public static void mockUsersSetup() {
		mockUsers = new ArrayList<>();

		for (int i = 1; i <= 5; i++) {
			User user = new User();
			user.setUserId(i);
			name = String.valueOf(i);
			if (i < 3)
				user.setFirstName("Test" + name);
			mockUsers.add(user);
		}
	}

	//@Test
	public void registerUser() throws UsernameAlreadyExists {

		//User newUser = userServ.register(testUser);

		User user = new User();
		user.setUserId(10);

		when(userRepo.save(user)).thenReturn(user);

		User actualUser = userServ.register(user);
		assertEquals(10, actualUser.getUserId());
		
		assertEquals(userRepo.findById(user.getUserId()), 10);

	}

}
