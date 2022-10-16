package com.peppermint.usermanagementsystem.service.impl;

import com.peppermint.usermanagementsystem.domain.entity.AppUser;
import com.peppermint.usermanagementsystem.payload.request.CreateAppUserRequest;
import com.peppermint.usermanagementsystem.repository.AppUserRepository;
import com.peppermint.usermanagementsystem.repository.CountryRepository;
import com.peppermint.usermanagementsystem.service.AppUserWriteService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@Service
public class AppUserWriteServiceImpl implements AppUserWriteService {

    private final AppUserRepository appUserRepository;
    private final CountryRepository countryRepository;
    private final PasswordEncoder passwordEncoder;

    public AppUserWriteServiceImpl(AppUserRepository appUserRepository, CountryRepository countryRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.countryRepository = countryRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AppUser createUser(CreateAppUserRequest request) {
        // Validate the Age > 18 Only Adult
        if (calculateAge(request.getDateOfBirth(), LocalDate.now()) < 18) {
            throw new RuntimeException("Your age is less than 18 years old");
        }

        // Validate the Living Country Code only "FR"
        if (!"FR".equals(request.getCountryCode())) {
            throw new RuntimeException("You are not living in France");
        }

        // Validate the Password & Confirm Password
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new RuntimeException("Password and Confirm Password do not match");
        }

        AppUser appUser = AppUser.builder()
                .email(request.getEmail())
                .username(request.getUsername())
                .firstname(request.getFirstName())
                .lastname(request.getLastName())
                .gender(request.getGender())
                .country(countryRepository.findByAlpha2Code(request.getCountryCode()).orElseThrow(() -> new RuntimeException(String.format("Country Code = %s does not exist", request.getCountryCode()))))
                .password(passwordEncoder.encode(request.getPassword()))
                .enabled(true)
                .passwordNeverExpires(true)
                .accountNonLocked(true)
                .accountNonExpired(true)
                .isSelfServiceUser(false)
                .credentialsNonExpired(true)
                .firstTimeLoginRemaining(true)
                .cannotChangePassword(false)
                .enabled(request.isEnabled())
                .build();
        return appUserRepository.save(appUser);
    }

    public int calculateAge(LocalDate birthDate, LocalDate currentDate) {
        return Period.between(birthDate, currentDate).getYears();
    }

}
