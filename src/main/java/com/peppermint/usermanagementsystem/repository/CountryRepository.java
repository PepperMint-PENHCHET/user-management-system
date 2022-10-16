package com.peppermint.usermanagementsystem.repository;

import com.peppermint.usermanagementsystem.domain.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country, String> {

    Optional<Country> findByAlpha2Code(String alpha2Code);
}
