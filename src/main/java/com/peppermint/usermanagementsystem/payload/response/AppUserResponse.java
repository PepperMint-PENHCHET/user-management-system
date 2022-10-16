package com.peppermint.usermanagementsystem.payload.response;

public interface AppUserResponse {

    Long getId();

    String getEmail();

    String getUsername();

    String getFirstname();

    String getLastname();

    boolean getAccountNonExpired();

    boolean getAccountNonLocked();

    boolean getCredentialsNonExpired();

    boolean getEnabled();

    boolean getFirstTimeLoginRemaining();

    boolean getDeleted();

    CountryData getCountry();

    interface CountryData {

        String getName();

        String getAlpha2Code();

        String getAlpha3Code();
    }

}
