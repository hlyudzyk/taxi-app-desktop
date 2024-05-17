package com.taxiapp.desktop.net.requests;

import com.taxiapp.desktop.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FilterUsersRequest {
    private Role role;
    private String userId;
    private String userEmail;
}
