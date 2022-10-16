package com.peppermint.usermanagementsystem.service;

import com.peppermint.usermanagementsystem.domain.entity.AppUser;
import com.peppermint.usermanagementsystem.payload.request.CreateAppUserRequest;

public interface AppUserWriteService {

    AppUser createUser(CreateAppUserRequest request);

}
