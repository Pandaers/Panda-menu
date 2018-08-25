package com.panda.mapper;

import com.panda.model.Cat;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component(value = "catMapper")
public interface CatMapper {

    @Select("select * from pe_cat")
    List<Cat> selectAllCatData();
}
