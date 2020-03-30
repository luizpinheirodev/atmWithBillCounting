package com.luiz.atm.controller;

import com.luiz.atm.exception.WithdrawNotEnoughMoneyWrongNumberFormatException;
import com.luiz.atm.exception.WithdrawWrongNumberFormatException;
import com.luiz.atm.model.ErrorResponse;
import com.luiz.atm.model.dto.ResponseBillDto;
import com.luiz.atm.service.AtmService;
import com.luiz.atm.util.NumberUtil;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AtmController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AtmController.class);

    private final AtmService atmService;

    public AtmController(AtmService atmService) {
        this.atmService = atmService;
    }

    @ApiOperation(value = "Set the value to withdraw")
    @GetMapping("/withdraw")
    public ResponseBillDto withdraw(@RequestParam(value = "value") String value) {
        int valueToWithdraw = NumberUtil.toValidNumber(value);
        return atmService.withdraw(valueToWithdraw);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(WithdrawNotEnoughMoneyWrongNumberFormatException.class)
    public ResponseEntity<ErrorResponse> handleWithdrawNotEnoughMoneyException(WithdrawNotEnoughMoneyWrongNumberFormatException ex) {
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(WithdrawWrongNumberFormatException.class)
    public ResponseEntity<ErrorResponse> handleWithdrawWrongNumberFormatException(WithdrawWrongNumberFormatException ex) {
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

}
