package com.peppermint.usermanagementsystem.logging.repository;

import com.peppermint.usermanagementsystem.logging.model.ApiIOLogger;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiIOLoggerRepository extends CrudRepository<ApiIOLogger, Long> {
}
