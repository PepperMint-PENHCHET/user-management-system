package com.peppermint.usermanagementsystem.service;

import com.peppermint.usermanagementsystem.infrastructure.api.Pagination;
import com.peppermint.usermanagementsystem.payload.response.AppUserResponse;

import java.util.Collection;

public interface AppUserReadService {

    Collection<AppUserResponse> getAllUsers(Pagination pagination);

    AppUserResponse getUserById(Long userId);

}
