package com.researchecosystems.contentserviceapi.model.response;

import com.researchecosystems.contentserviceapi.entity.Subscription;
import com.researchecosystems.contentserviceapi.entity.Video;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.time.ZonedDateTime;

@Data
@ToString
@Builder
public class SubscriptionResponse {
    private String id ;

    private String name ;
    private double monthlyFee;
    private int duration ;
    private boolean isActive;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    public static SubscriptionResponse fromEntity(Subscription subscription) {
        return SubscriptionResponse.builder().
                id(subscription.getId()).
                name(subscription.getName()).
                duration(subscription.getDuration()).
                monthlyFee(subscription.getMonthlyFee()).
                createdAt(subscription.getCreated()).
                updatedAt(subscription.getUpdated()).build();
    }
}
