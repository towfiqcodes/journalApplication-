package net.gaelixinfo.Journal.App.service;

import net.gaelixinfo.Journal.App.entity.User;
import net.gaelixinfo.Journal.App.repository.UserRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

import static org.mockito.Mockito.when;

public class UserDetailsServiceImplTests {

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    UserRepo userRepo;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void loadUserByUserNameTest() {
        when(userRepo.findByUsername("Towfiq")).thenReturn(User.builder().username("Towfiq").password("ksdjfjshfsj").roles(new ArrayList<>()) .build());
        UserDetails userDetails = userDetailsService.loadUserByUsername("Towfiq");
        Assertions.assertNotNull(userDetails);
    }
}
