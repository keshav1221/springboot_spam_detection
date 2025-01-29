package com.example.demo.Repository;

import com.example.demo.Entity.Contacts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepo extends JpaRepository<Contacts,Long> {
    Contacts findByPhoneNumber(String phoneNumber);

    List<Contacts> findByPhoneNumberContaining(String phoneNumber);
}
