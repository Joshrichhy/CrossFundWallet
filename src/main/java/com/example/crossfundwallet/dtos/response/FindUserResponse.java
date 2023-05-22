package com.example.crossfundwallet.dtos.response;

import lombok.Data;

@Data
public class FindUserResponse {
    private String username;
    private String fullName;
    private String dateRegistered;
}
