package net.gaelixinfo.Journal.App.service;

import net.gaelixinfo.Journal.App.entity.User;
import net.gaelixinfo.Journal.App.repository.UserRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserService userService;


//    @ValueSource(strings = {
//            "towfiq",
//            "hridoy",
//            "rabby"
//    })
    @ParameterizedTest
    @ArgumentsSource(UserArgumentProvider.class)
    public void testSaveNewUser(User user){

        assertTrue(userService.saveNewUser(user));
        //assertNotNull(userRepo.findByUsername(name), "Username not found "+name);
    }
}
