package com.example.demo.Service;

import com.example.demo.Entity.SpamReport;
import com.example.demo.Repository.SpamReportRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpamService {
    @Autowired
    private SpamReportRepo spamReportRepository;

    public void reportSpam(String phoneNumber) {
        SpamReport report = new SpamReport();
        report.setPhoneNumber(phoneNumber);
        spamReportRepository.save(report);
    }

}
