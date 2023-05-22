package com.example.crossfundwallet.dto.response;

import lombok.Data;

@Data
public class FindUserResponse {
    private String username;
    private String fullName;
    private String dateRegistered;
}
