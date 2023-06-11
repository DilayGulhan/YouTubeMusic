package com.researchecosystems.contentserviceapi.controller;

import com.researchecosystems.contentserviceapi.entity.Subscription;
import com.researchecosystems.contentserviceapi.model.request.Subscription.CreateSubscriptionRequest;
import com.researchecosystems.contentserviceapi.model.request.Subscription.UpdateSubscriptionRequest;
import com.researchecosystems.contentserviceapi.model.request.user.CreateUserRequest;
import com.researchecosystems.contentserviceapi.model.response.SubscriptionResponse;
import com.researchecosystems.contentserviceapi.model.response.user.UserResponse;
import com.researchecosystems.contentserviceapi.service.AuthenticationService;
import com.researchecosystems.contentserviceapi.service.SubscriptionService;
import com.researchecosystems.contentserviceapi.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/subscription")
public class SubscriptionController {
    private final SubscriptionService subscriptionService ;
    private final AuthenticationService authenticationService;

    @GetMapping
    @ApiPageable
    public Page<SubscriptionResponse> listSubscription(@ApiIgnore Pageable pageable ) {
        return subscriptionService.listSubscription(pageable );
    }

    @PostMapping
    public SubscriptionResponse createSubscription(@Valid @RequestBody CreateSubscriptionRequest createSubscriptionRequest) {
        return subscriptionService.addSubscription(createSubscriptionRequest , authenticationService.getAuthenticatedUserId());
    }

    @DeleteMapping("/{subscriptionId}")
    public SubscriptionResponse deleteSubscription(@PathVariable String subscriptionId) {
        return subscriptionService.deleteSubscription(subscriptionId, authenticationService.getAuthenticatedUserId());
    }

    @PutMapping("/{subscriptionId}")
    public SubscriptionResponse updateSubscription
            (@PathVariable String subscriptionId ,
             @Valid @RequestBody UpdateSubscriptionRequest updateSubscriptionRequest){
        return  subscriptionService.updateSubscription(updateSubscriptionRequest , subscriptionId ,
                authenticationService.getAuthenticatedUserId());

    }

}
