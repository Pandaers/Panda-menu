package com.panda.service.getCatMap;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface GetCatMap {
    Map<Integer, String> getCatMap();
}
