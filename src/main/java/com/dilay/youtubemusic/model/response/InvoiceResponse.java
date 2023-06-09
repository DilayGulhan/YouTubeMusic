package com.dilay.youtubemusic.model.response;

import com.dilay.youtubemusic.entity.Invoice;
import lombok.*;

import java.time.ZonedDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvoiceResponse {
    private String id ;
    private double fee ;
    private String contract_record_id ;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
    private boolean isPaid;

    public static InvoiceResponse fromEntity(Invoice invoice){
        return InvoiceResponse.builder().
                id(invoice.getId()).
                fee(invoice.getFee()).
                contract_record_id(invoice.getContractRecordId()).
                createdAt(invoice.getCreated()).
                updatedAt(invoice.getUpdated()).
                isPaid(invoice.isPaid()).
                build();

    }
}
