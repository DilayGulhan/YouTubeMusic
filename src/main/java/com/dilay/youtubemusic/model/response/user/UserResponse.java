package com.dilay.youtubemusic.model.response.user;

import com.dilay.youtubemusic.entity.Category;
import com.dilay.youtubemusic.entity.User;
import com.dilay.youtubemusic.entity.UserRole;
import com.dilay.youtubemusic.entity.Video;
import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
public class UserResponse {

    private String id;
    private ZonedDateTime created;
    private ZonedDateTime updated;
    private String name;
    private String surname;
    private String email;
    private UserRole userRole;
    private List<String> favoriteVideos ;
    private List<String> followedCategories ;

    public static UserResponse fromEntity(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .created(user.getCreated())
                .updated(user.getUpdated())
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .userRole(user.getUserRole())
               .favoriteVideos(user.getFavoriteVideos().stream().map(Video::getTitle).collect(Collectors.toList()))
                .followedCategories(user.getFollowedCategory().stream().map(Category::getName).collect(Collectors.toList()))
                .build();
    }

}
