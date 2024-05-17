package com.taxiapp.desktop.net.responses;

import lombok.Data;

@Data
public class MessageDataResponse {
    private String subject;
    private String content;
    private String senderEmail;
}