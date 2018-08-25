package com.panda.mapper;

import com.panda.model.Store;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Mapper
@Component(value = "storeMapper")
public interface StoreMapper {

    @Select("select * from pe_store where storeid=${storeid}")
    Store selcetStoreDateByStoreId(@Param("storeid") String storeid);
}
