package com.luiz.atm.controller;

import com.luiz.atm.exception.WithdrawNotEnoughMoneyException;
import com.luiz.atm.model.ErrorResponse;
import com.luiz.atm.model.dto.ResponseBillDto;
import com.luiz.atm.service.AtmService;
import com.luiz.atm.util.NumberUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;

@RestController
public class AtmController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AtmController.class);

    private final AtmService atmService;

    public AtmController(AtmService atmService) {
        this.atmService = atmService;
    }

    @GetMapping("/withdraw")
    public ResponseBillDto withdraw(@RequestParam(value = "value") String value) {
        int valueToWithdraw = NumberUtil.toNumber(value);

        if (valueToWithdraw == 0) {
            LOGGER.info("Invalid value. Bills allowed are 10, 20, 50 and 100");
            ResponseBillDto responseBillDto = new ResponseBillDto();
            responseBillDto.setBills(new ArrayList<String>(Arrays.asList("Invalid value. Bills allowed are 10, 20, 50 and 100")));
            return responseBillDto;
        } else {
            return atmService.withdraw(valueToWithdraw);
        }
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(WithdrawNotEnoughMoneyException.class)
    public ResponseEntity<ErrorResponse> handleWithdrawNotEnoughMoneyException(WithdrawNotEnoughMoneyException ex) {
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
