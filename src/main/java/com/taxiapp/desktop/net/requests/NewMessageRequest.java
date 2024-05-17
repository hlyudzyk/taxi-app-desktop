package com.taxiapp.desktop.net.requests;

import com.taxiapp.desktop.entities.Role;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewMessageRequest {
    private String subject;
    private String content;
    private List<String> recipientsIds;
    private List<Role> targetRoles;
}
