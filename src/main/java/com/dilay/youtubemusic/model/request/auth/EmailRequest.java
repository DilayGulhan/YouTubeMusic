package com.dilay.youtubemusic.model.request.auth;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class EmailRequest {

    @Email
    @NotEmpty
    private String email;

}
