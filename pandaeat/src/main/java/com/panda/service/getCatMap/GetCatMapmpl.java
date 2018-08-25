package com.panda.service.getCatMap;

import com.panda.mapper.CatMapper;
import com.panda.model.Cat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GetCatMapmpl implements GetCatMap {
    @Autowired
    CatMapper catMapper;
    public  Map<Integer, String> getCatMap(){
    Map<Integer, String> catmap = catMapper.selectAllCatData().stream().collect(Collectors.toMap(Cat::getCatid, Cat::getCatname));
    return catmap;
    }
}
