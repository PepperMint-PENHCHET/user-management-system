package com.peppermint.usermanagementsystem.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.peppermint.usermanagementsystem.domain.enumeration.Gender;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@ToString
public class CreateAppUserRequest {

    @JsonProperty("email")
    @Size(max = 100, message = "Email is exceed 100 characters")
    private String email;

    @NotNull
    @JsonProperty("username")
    @Size(max = 100, message = "Email is exceed 100 characters")
    private String username;

    @JsonProperty("firstName")
    @Size(max = 100, message = "FirstName is exceed 100 characters")
    private String firstName;

    @JsonProperty("lastName")
    @Size(max = 100, message = "LastName is exceed 100 characters")
    private String lastName;

    @NotNull
    @JsonProperty("gender")
    private Gender gender;

    @NotNull
    @JsonProperty("dateOfBirth")
    private LocalDate dateOfBirth;

    @NotNull
    @JsonProperty("countryCode")
    @Size(min = 2, max = 2, message = "CountryCode is Alpha2Code")
    private String countryCode;

    @NotEmpty
    @JsonProperty("password")
    private String password;

    @NotEmpty
    @JsonProperty("confirmPassword")
    private String confirmPassword;

    @NotNull
    @JsonProperty("enabled")
    private boolean enabled;
}
