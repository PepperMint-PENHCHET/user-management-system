package com.peppermint.usermanagementsystem.domain.entity;

import com.peppermint.usermanagementsystem.domain.enumeration.Gender;
import com.peppermint.usermanagementsystem.infrastructure.domain.AbstractPersistableCustom;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Proxy;
import org.hibernate.envers.Audited;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Builder
@ToString
@Slf4j
@Entity
@Table(name = "tb_appusers", uniqueConstraints = @UniqueConstraint(columnNames = {"username"}))
@Audited
@Proxy(lazy = false)
public class AppUser extends AbstractPersistableCustom implements UserDetails {

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "username", nullable = false, length = 100)
    private String username;

    @Column(name = "firstname", nullable = false, length = 100)
    private String firstname;

    @Column(name = "lastname", nullable = false, length = 100)
    private String lastname;

    @Column(name = "gender", nullable = false, length = 7)
    private Gender gender;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "nonexpired", nullable = false)
    private boolean accountNonExpired;

    @Column(name = "nonlocked", nullable = false)
    private boolean accountNonLocked;

    @Column(name = "nonexpired_credentials", nullable = false)
    private boolean credentialsNonExpired;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @Column(name = "firsttime_login_remaining", nullable = false)
    private boolean firstTimeLoginRemaining;

    @Column(name = "is_deleted", nullable = false)
    private boolean deleted;

    @Column(name = "last_time_password_updated")
    @Temporal(TemporalType.DATE)
    private Date lastTimePasswordUpdated;

    @Column(name = "password_never_expires", nullable = false)
    private boolean passwordNeverExpires;

    @Column(name = "is_self_service_user", nullable = false)
    private boolean isSelfServiceUser;

    @Column(name = "cannot_change_password")
    private Boolean cannotChangePassword;

    public AppUser() {
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.accountNonExpired = true;
        this.firstTimeLoginRemaining = true;
        this.enabled = true;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        final List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return grantedAuthorities;
    }

    @Override
    public String toString() {
        return "AppUser [username=" + this.username + ", getId()=" + this.getId() + "]";
    }

}
