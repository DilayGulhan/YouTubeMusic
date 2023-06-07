package com.researchecosystems.contentserviceapi.model.response;

import com.researchecosystems.contentserviceapi.entity.Category;
import com.researchecosystems.contentserviceapi.entity.Video;
import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@Builder
public class CategoryResponse {
    private String id ;
    private String name;
    private boolean isSuperCategory;
    private List<Video> videos ;
    private Category parent;
private List<Category> childCategories;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    public static CategoryResponse fromEntity(Category category){
        return CategoryResponse.builder().id(category.getId())
                .name(category.getName())
                .isSuperCategory(category.isSuperCategory()).
             //   videos(category.getVideosList()).
                parent(category.getParent()).createdAt(category.getCreated()).
             //   childCategories(category.getCategories()).
                updatedAt(category.getUpdated()).build();





    }
}
