package com.peppermint.usermanagementsystem.logging.model;

import com.peppermint.usermanagementsystem.infrastructure.domain.AbstractAuditableCustom;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Data
@SuperBuilder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_api_io_loggers")
@Inheritance(
        strategy = InheritanceType.JOINED
)
public class ApiIOLogger extends AbstractAuditableCustom {
    @Column(name = "request_id")
    private String requestId;

    @Column(name = "api_url")
    private String apiUrl;

    @Column(name = "api_type")
    private String apiType;

    @Column(name = "request_method")
    private String requestMethod;

    @Column(name = "remote_ip")
    private String remoteIp;

    @Type(type = "text")
    @Column(name = "request_body")
    private String requestBody;

    @Type(type = "text")
    @Column(name = "response_body")
    private String responseBody;

    @Column(name = "responseCode")
    private Integer responseCode;
}
