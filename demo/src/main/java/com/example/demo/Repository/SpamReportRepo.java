package com.example.demo.Repository;

import com.example.demo.Entity.SpamReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpamReportRepo extends JpaRepository<SpamReport, Long> {
    long countByPhoneNumber(String phoneNumber);
}
