package com.peppermint.usermanagementsystem.service.impl;

import com.peppermint.usermanagementsystem.exception.AppUserNotFoundException;
import com.peppermint.usermanagementsystem.infrastructure.api.Pagination;
import com.peppermint.usermanagementsystem.payload.response.AppUserResponse;
import com.peppermint.usermanagementsystem.repository.AppUserRepository;
import com.peppermint.usermanagementsystem.service.AppUserReadService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AppUserReadServiceImpl implements AppUserReadService {

    private final AppUserRepository appUserRepository;

    public AppUserReadServiceImpl(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public Collection<AppUserResponse> getAllUsers(Pagination pagination) {
        Page<AppUserResponse> appUserDataPage = appUserRepository.findAllAppUsersProjected(PageRequest.of(pagination.getPage() - 1, pagination.getSize()), AppUserResponse.class);
        pagination.setTotalCounts(appUserDataPage.getTotalElements());
        return appUserDataPage.getContent();
    }

    @Override
    public AppUserResponse getUserById(Long userId) {
        return appUserRepository.findAppUserById(userId, AppUserResponse.class).orElseThrow(() -> new AppUserNotFoundException(String.format("AppUser Id = %d is not found", userId)));
    }

}
