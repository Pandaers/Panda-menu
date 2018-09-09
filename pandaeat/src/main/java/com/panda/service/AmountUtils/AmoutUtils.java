package com.panda.service.AmountUtils;

import org.springframework.stereotype.Service;

@Service
public interface AmoutUtils {
    String changeF2Y(Integer amount)throws Exception;
    Integer changeY2F(String yuan)throws Exception;
}
