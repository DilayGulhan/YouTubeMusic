package com.researchecosystems.contentserviceapi.service;

import com.researchecosystems.contentserviceapi.entity.ContractRecord;
import com.researchecosystems.contentserviceapi.entity.Subscription;
import com.researchecosystems.contentserviceapi.entity.User;
import com.researchecosystems.contentserviceapi.exception.BusinessException;
import com.researchecosystems.contentserviceapi.exception.ErrorCode;
import com.researchecosystems.contentserviceapi.model.request.contractrecord.CreateandUpdateContractRecordRequest;
import com.researchecosystems.contentserviceapi.model.response.ContractRecordResponse;
import com.researchecosystems.contentserviceapi.repository.ContractRecordRepository;
import com.researchecosystems.contentserviceapi.repository.SubscriptionRepository;
import com.researchecosystems.contentserviceapi.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
@AllArgsConstructor

public class ContractRecordService {
private final SubscriptionRepository subscriptionRepository;

private final UserRepository userRepository;
private final ContractRecordRepository contractRecordRepository;
public ContractRecordResponse addContractRecord(String authenticatedUserId , String subscriptionName,
                                                CreateandUpdateContractRecordRequest contractRecord){
    User currentUser = userRepository.findById(authenticatedUserId)
            .orElseThrow(() -> new BusinessException(ErrorCode.resource_missing, "There is no user like that!"));
    Subscription subscription = subscriptionRepository.findByName(subscriptionName)
            .orElseThrow(() -> new BusinessException(ErrorCode.resource_missing, "There is no subscription like that!"));
    ContractRecord record = new ContractRecord();
    record.setActive(contractRecord.isActive() && subscription.isActive());
    record.setName(subscription.getName());
    record.setMonthlyFee(subscription.getMonthlyFee());
    record.setDuration(subscription.getDuration());
    record.setUserId(authenticatedUserId);
    contractRecordRepository.save(record);
    return ContractRecordResponse.fromEntity(currentUser , subscription , record);
}
public ContractRecordResponse updateContractRecord(String authenticatedUserId ,
                                                   CreateandUpdateContractRecordRequest contractRecordRequest
                                                   , String oldRecordName){

    ContractRecord contractRecord = contractRecordRepository.findByName(oldRecordName)
            .orElseThrow(() -> new BusinessException(ErrorCode.resource_missing, "There is no record like that!"));

    Subscription newRecordRequest = subscriptionRepository.findByName(contractRecordRequest.getName())
            .orElseThrow(() -> new BusinessException(ErrorCode.resource_missing, "There is no record like that!"));


    User currentUser = userRepository.findById(authenticatedUserId)
            .orElseThrow(() -> new BusinessException(ErrorCode.resource_missing, "There is no user like that!"));

    Subscription subscription = subscriptionRepository.findByName(oldRecordName)
            .orElseThrow(() -> new BusinessException(ErrorCode.resource_missing, "There is no subscription like that!"));

    if(subscription.getDuration() >= contractRecord.getDuration()){
        throw new BusinessException(ErrorCode.forbidden , "You can only upgrade your subscription ");
    }
    contractRecord.setName(newRecordRequest.getName());
    contractRecord.setDuration(newRecordRequest.getDuration());
    contractRecord.setMonthlyFee(newRecordRequest.getMonthlyFee());
    contractRecordRepository.save(contractRecord);
    return ContractRecordResponse.fromEntity(currentUser , newRecordRequest , contractRecord );


}


}
