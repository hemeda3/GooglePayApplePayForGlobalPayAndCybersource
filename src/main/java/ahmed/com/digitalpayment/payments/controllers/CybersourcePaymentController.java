package ahmed.com.digitalpayment.payments.controllers;

import ahmed.com.digitalpayment.payments.dtos.*;
import ahmed.com.digitalpayment.payments.models.CybersourceCaptureRequest;
import ahmed.com.digitalpayment.payments.models.CybersourceReverseRequest;
import ahmed.com.digitalpayment.payments.services.CybersourcePayService;
import com.global.api.entities.exceptions.ApiException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ahmed.com.digitalpayment.payments.models.CybersourceAuthRequest;
import ahmed.com.digitalpayment.payments.models.CybersourceRefundRequest;

@RestController
@RequestMapping("/digitalwallet/cybersource")
class CybersourcePaymentController {


    @Autowired
    CybersourcePayService cybersourcePayService;

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
    ResponseEntity<V1PaymentResponseDTO> authorize(@RequestBody V1CyberPaymentRequestDTO requestDTO) throws ApiException {
        CybersourceAuthRequest request = modelMapper.map(requestDTO, CybersourceAuthRequest.class);

        String response = cybersourcePayService.authorize(request);
        V1PaymentResponseDTO v1PaymentResponseDTO = V1PaymentResponseDTO.builder()
                .authCode(response)
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
    ResponseEntity<V1PaymentResponseDTO> reverseAuthorization(@RequestBody V1CybersourceReverseRequestDTO requestDTO) throws ApiException {
        CybersourceReverseRequest request = modelMapper.map(requestDTO, CybersourceReverseRequest.class);

        String response = cybersourcePayService.voidCancelReverseAuthorization(request);
        V1PaymentResponseDTO v1PaymentResponseDTO = V1PaymentResponseDTO.builder()
                .authCode(response)
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
    ResponseEntity<V1PaymentResponseDTO> capture(@RequestBody V1CybersourceCaptureRequestDTO requestDTO) throws ApiException {
        CybersourceCaptureRequest request = modelMapper.map(requestDTO, CybersourceCaptureRequest.class);

        String response = cybersourcePayService.capture(request);
        V1PaymentResponseDTO v1PaymentResponseDTO = V1PaymentResponseDTO.builder()
                .captureID(response)
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
    ResponseEntity<V1PaymentResponseDTO> refund(@RequestBody V1CybersourceRefundRequestDTO requestDTO) throws ApiException {
        CybersourceRefundRequest request = modelMapper.map(requestDTO, CybersourceRefundRequest.class);

        String response = cybersourcePayService.refund(request);
        V1PaymentResponseDTO v1PaymentResponseDTO = V1PaymentResponseDTO.builder()
                .refundID(response)
                .build();

        return ResponseEntity.ok(v1PaymentResponseDTO);
    }
}
