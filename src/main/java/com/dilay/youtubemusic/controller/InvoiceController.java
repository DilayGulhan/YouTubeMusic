package com.dilay.youtubemusic.controller;

import com.dilay.youtubemusic.model.request.Payment.CreatePaymentRequest;
import com.dilay.youtubemusic.model.response.InvoiceResponse;
import com.dilay.youtubemusic.service.AuthenticationService;
import com.dilay.youtubemusic.service.InvoiceService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/invoice")
public class InvoiceController {

    private final AuthenticationService authenticationService;
    private final InvoiceService invoiceService;

    @GetMapping
    @ApiPageable
    public Page<InvoiceResponse> listInvoice(@ApiIgnore Pageable pageable ) {
        return invoiceService.listInvoice(pageable  , authenticationService.getAuthenticatedUserId());

    }

    @PostMapping("/{contractRecordId}")
    public InvoiceResponse payInvoice(@Valid @RequestBody CreatePaymentRequest
                                                  paymentRequest,
                                      @PathVariable String contractRecordId){

return invoiceService.payInvoice(paymentRequest, authenticationService.getAuthenticatedUserId(), contractRecordId);
        
    }




    @GetMapping("/{invoiceId}")
    public InvoiceResponse getInvoicebyAdmin(@PathVariable String invoiceId){
    return invoiceService.getInvoiceByAdmin(invoiceId , authenticationService.getAuthenticatedUserId());
    }
}
