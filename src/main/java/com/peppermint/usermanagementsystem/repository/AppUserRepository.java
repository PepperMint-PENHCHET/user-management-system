package com.peppermint.usermanagementsystem.repository;

import com.peppermint.usermanagementsystem.domain.entity.AppUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long>, JpaSpecificationExecutor<AppUser> {

    @Query("SELECT appUser FROM AppUser appUser WHERE appUser.username = :username")
    AppUser findAppUserByUsername(@Param("username") String username);

    @Query("SELECT appUser FROM AppUser appUser WHERE appUser.id = :id")
    <T> Optional<T> findAppUserById(@Param("id") Long id, Class<T> clazz);

    @Query("SELECT appUser FROM AppUser appUser")
    <T> Page<T> findAllAppUsersProjected(Pageable pageable, Class<T> clazz);

}
