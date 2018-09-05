package com.panda.service.catNameReform;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.panda.service.getCatMap.GetCatMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CatNameReformmpl implements CatNameReform{
    @Autowired
    GetCatMap getCatMap;

    public String catNameReform(String intCat,Integer storeid){
        JSONObject jsonObject = JSONObject.parseObject(intCat);
        Map<String,Object> tempmap = (Map<String,Object>)jsonObject;

        //得到菜品名int转string对应map
        Map<Integer, String> catmap = getCatMap.getCatMap(storeid);
        Map<String,Object> resultMap= new HashMap();

        for(Map.Entry<String, Object> entry : tempmap.entrySet()){
            resultMap.put(catmap.get(Integer.parseInt(entry.getKey())),entry.getValue());

        }
        return JSON.toJSONString(resultMap);

    }

}
