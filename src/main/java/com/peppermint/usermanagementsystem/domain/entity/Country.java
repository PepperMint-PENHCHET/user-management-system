package com.peppermint.usermanagementsystem.domain.entity;

import com.peppermint.usermanagementsystem.domain.enumeration.Status;
import com.peppermint.usermanagementsystem.infrastructure.domain.AbstractAuditableCustom;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "tb_countries")
@Audited
public class Country extends AbstractAuditableCustom {

    @Column(name = "alpha2_code", length = 2)
    private String alpha2Code;

    @Column(name = "alpha3_code", length = 3)
    private String alpha3Code;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;
}
