package com.movie.service;

import com.movie.domain.Inquiries;
import com.movie.mapper.InquiryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InquiryService {
    @Autowired
    private InquiryMapper inquiryMapper;

    public void insertInquiry(Inquiries inquiry) {
        inquiryMapper.insertInquiry(inquiry);
    }
}
