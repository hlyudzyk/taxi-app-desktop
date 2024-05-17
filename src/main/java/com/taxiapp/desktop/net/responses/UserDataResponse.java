package com.taxiapp.desktop.net.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDataResponse {

    private String userId;
    private String firstname;
    private String lastname;
    private String email;
}