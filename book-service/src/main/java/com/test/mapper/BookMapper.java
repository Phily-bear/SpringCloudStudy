package com.test.mapper;

import com.test.entity.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface BookMapper {

    @Select("select * from DB_BOOK where bid = #{bid}")
    Book getBookById(@Param("bid") int bid);
}
