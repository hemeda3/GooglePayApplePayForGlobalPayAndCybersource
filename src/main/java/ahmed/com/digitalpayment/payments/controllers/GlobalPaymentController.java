package ahmed.com.digitalpayment.payments.controllers;

import ahmed.com.digitalpayment.payments.models.GlobalpaymentsRequest;
import com.global.api.entities.Transaction;
import com.global.api.entities.exceptions.ApiException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ahmed.com.digitalpayment.payments.dtos.V1GlobalPaymentRequestDTO;
import ahmed.com.digitalpayment.payments.dtos.V1PaymentResponseDTO;
import ahmed.com.digitalpayment.payments.services.GlobalPaymentsService;

@RestController
@RequestMapping("/digitalwallet/globalpay")
class GlobalPaymentController {


    @Autowired
    GlobalPaymentsService globalPaymentsService;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping(value = "/authorize", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "authorize")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = V1PaymentResponseDTO.class),
            @ApiResponse(code = 401, message = ""),
            @ApiResponse(code = 403, message = ""),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    ResponseEntity<V1PaymentResponseDTO> authorize(@RequestBody V1GlobalPaymentRequestDTO v1GlobalPaymentRequestDTO) throws ApiException {
        GlobalpaymentsRequest globalpaymentsRequest = modelMapper.map(v1GlobalPaymentRequestDTO, GlobalpaymentsRequest.class);
        Transaction resp = globalPaymentsService.authorize(globalpaymentsRequest);
        V1PaymentResponseDTO v1PaymentResponseDTO = V1PaymentResponseDTO.builder()
                .transactionId(resp.getTransactionId())
                .orderId(resp.getOrderId())
                .authCode(resp.getAuthorizationCode())
                .build();

        return ResponseEntity.ok(v1PaymentResponseDTO);
    }

    @PostMapping(value = "/reverseAuthorization", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "reverseAuthorization")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = V1PaymentResponseDTO.class),
            @ApiResponse(code = 401, message = ""),
            @ApiResponse(code = 403, message = ""),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    ResponseEntity<V1PaymentResponseDTO> reverseAuthorization(@RequestBody V1GlobalPaymentRequestDTO v1GlobalPaymentRequestDTO) throws ApiException {
        GlobalpaymentsRequest globalpaymentsRequest = modelMapper.map(v1GlobalPaymentRequestDTO, GlobalpaymentsRequest.class);
        Transaction resp = globalPaymentsService.voidCancelReverseAuthorization(globalpaymentsRequest);
        V1PaymentResponseDTO v1PaymentResponseDTO = V1PaymentResponseDTO.builder()
                .transactionId(resp.getTransactionId())
                .orderId(resp.getOrderId())
                .authCode(resp.getAuthorizationCode())
                .build();

        return ResponseEntity.ok(v1PaymentResponseDTO);
    }


    @PostMapping(value = "/capture", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "capture")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = V1PaymentResponseDTO.class),
            @ApiResponse(code = 401, message = ""),
            @ApiResponse(code = 403, message = ""),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    ResponseEntity<V1PaymentResponseDTO> capture(@RequestBody V1GlobalPaymentRequestDTO v1GlobalPaymentRequestDTO) throws ApiException {
        GlobalpaymentsRequest globalpaymentsRequest = modelMapper.map(v1GlobalPaymentRequestDTO, GlobalpaymentsRequest.class);
        Transaction resp = globalPaymentsService.capture(globalpaymentsRequest);
        V1PaymentResponseDTO v1PaymentResponseDTO = V1PaymentResponseDTO.builder()
                .transactionId(resp.getTransactionId())
                .orderId(resp.getOrderId())
                .authCode(resp.getAuthorizationCode())
                .build();

        return ResponseEntity.ok(v1PaymentResponseDTO);
    }
    @PostMapping(value = "/refund", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "refund")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = V1PaymentResponseDTO.class),
            @ApiResponse(code = 401, message = ""),
            @ApiResponse(code = 403, message = ""),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    ResponseEntity<V1PaymentResponseDTO> refund(@RequestBody V1GlobalPaymentRequestDTO v1GlobalPaymentRequestDTO) throws ApiException {
        GlobalpaymentsRequest globalpaymentsRequest = modelMapper.map(v1GlobalPaymentRequestDTO, GlobalpaymentsRequest.class);
        Transaction resp = globalPaymentsService.refund(globalpaymentsRequest);
        V1PaymentResponseDTO v1PaymentResponseDTO = V1PaymentResponseDTO.builder()
                .transactionId(resp.getTransactionId())
                .orderId(resp.getOrderId())
                .authCode(resp.getAuthorizationCode())
                .build();

        return ResponseEntity.ok(v1PaymentResponseDTO);
    }


}
