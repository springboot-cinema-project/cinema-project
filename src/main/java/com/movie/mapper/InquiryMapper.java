package com.movie.mapper;

import com.movie.domain.Inquiries;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InquiryMapper {
    void insertInquiry(Inquiries inquiries);
}
