package com.peppermint.usermanagementsystem.restcontroller;

import com.peppermint.usermanagementsystem.domain.entity.AppUser;
import com.peppermint.usermanagementsystem.infrastructure.api.ApiResponse;
import com.peppermint.usermanagementsystem.infrastructure.api.Pagination;
import com.peppermint.usermanagementsystem.payload.request.CreateAppUserRequest;
import com.peppermint.usermanagementsystem.payload.response.AppUserResponse;
import com.peppermint.usermanagementsystem.service.AppUserReadService;
import com.peppermint.usermanagementsystem.service.AppUserWriteService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(value = AppUserApiController.API_URL)
public class AppUserApiController {

    public static final String API_URL = "/api/v1/appusers";


    private final AppUserReadService appUserReadService;
    private final AppUserWriteService appUserWriteService;

    public AppUserApiController(AppUserReadService appUserReadService, AppUserWriteService appUserWriteService) {
        this.appUserReadService = appUserReadService;
        this.appUserWriteService = appUserWriteService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Collection<AppUserResponse>>> listAllUsers(Pagination pagination) {
        return ResponseEntity.ok(new ApiResponse<>(appUserReadService.getAllUsers(pagination), pagination));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<AppUser>> createNewAppUser(@Validated @RequestBody CreateAppUserRequest request) {
        return ResponseEntity.ok(new ApiResponse<>(appUserWriteService.createUser(request)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AppUserResponse>> getAppUserById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(new ApiResponse<>(appUserReadService.getUserById(id)));
    }

}
