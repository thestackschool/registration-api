package com.app.amrit.service;

import com.app.amrit.entity.UserEntity;
import com.app.amrit.props.AppProperties;
import com.app.amrit.repository.CityRepository;
import com.app.amrit.repository.CountryRepository;
import com.app.amrit.repository.StateRepository;
import com.app.amrit.repository.UserRepository;
import com.app.amrit.util.EmailUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class RegistrationServiceImplTest {

    @MockBean
    private UserRepository userRepo;

    @MockBean
    private CountryRepository countryRepo;

    @MockBean
    private StateRepository stateRepo;

    @MockBean
    private CityRepository cityRepo;

    @MockBean
    private EmailUtils emailUtils;

    @MockBean
    private AppProperties appProps;

    @InjectMocks
    private RegistrationServiceImpl service;

    @Test
    public void uniqueEmailTest1() {
        when(userRepo.findByUserEmail("amr@gmail.com")).thenReturn(new UserEntity());
        boolean uniqueEmail = service.uniqueEmail("amr@gmail.com");
        assertFalse(uniqueEmail);
    }

    @Test
    public void uniqueEmailTest2() {
        when(userRepo.findByUserEmail("amr@gmail.com")).thenReturn(null);
        boolean uniqueEmail = service.uniqueEmail("amr@gmail.com");
        assertTrue(uniqueEmail);
    }
}