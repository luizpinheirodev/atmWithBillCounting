package com.luiz.atm.service;

import com.luiz.atm.model.dto.ResponseBillDto;
import org.springframework.stereotype.Service;

@Service
public interface AtmService {

    ResponseBillDto withdraw(int value);

}
