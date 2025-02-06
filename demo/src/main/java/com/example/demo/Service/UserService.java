package com.example.demo.Service;

import com.example.demo.DTO.ProfileDisplay;
import com.example.demo.DTO.SearchDisplay;
import com.example.demo.DTO.UserUpdateFields;
import com.example.demo.Entity.Contacts;
import com.example.demo.Entity.User;
import com.example.demo.Repository.ContactRepo;
import com.example.demo.Repository.SpamReportRepo;
import com.example.demo.Repository.UserRepo;
import com.example.demo.Util.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepository;
    @Autowired
    private ContactRepo contactRepo;
    @Autowired
    private SpamReportRepo spamReportRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;

    public String saveUser(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        Contacts contacts = contactRepo.findByPhoneNumber(user.getPhoneNumber());
        if(contacts==null) {
            Contacts contact = new Contacts();
            contact.setPhoneNumber(user.getPhoneNumber());
            contact.setName(user.getName());
            contactRepo.save(contact);
        }
        List<Contacts> contactsList = user.getContacts();
        List<Contacts> newContactList = new ArrayList<>();
        if(contactsList!=null)
        {
            contactsList.forEach(var -> {

                Contacts existingContact = contactRepo.findByPhoneNumber(var.getPhoneNumber());
                if (existingContact == null) {
                    var.setUser(user);
                    newContactList.add(var);
                } else {
                    existingContact.setUser(user);
                    newContactList.add(existingContact);
                }

            });
            contactRepo.saveAll(newContactList);
        }
        return "User Saved Successfully";
    }

    public List<SearchDisplay> searchByName(String name) {
        List<User> results = userRepository.findByNameContainingIgnoreCaseOrderByNameAsc(name);
        List<SearchDisplay> result= new ArrayList<>();
        results.forEach(user -> {
           Long spamcount= spamReportRepo.countByPhoneNumber(user.getPhoneNumber());
           result.add(new SearchDisplay(user.getName(), user.getPhoneNumber(),spamcount));
        });
        return result;
    }

    public List<SearchDisplay> searchContactByPhoneNumber(String phoneNumber) {

        Contacts contact = contactRepo.findByPhoneNumber(phoneNumber);
        List<SearchDisplay> result= new ArrayList<>();
        if (contact != null) {

            long spamCount = spamReportRepo.countByPhoneNumber(phoneNumber);
            result.add(new SearchDisplay(contact.getName(), contact.getPhoneNumber(), spamCount));
        } else {

            List<Contacts> contactsList = contactRepo.findByPhoneNumberContaining(phoneNumber);
            contactsList.forEach(contacts -> {
                Long spamCount= spamReportRepo.countByPhoneNumber(contacts.getPhoneNumber());
                result.add(new SearchDisplay(contacts.getName(), contacts.getPhoneNumber(), spamCount));
            });
        }
        return result;
    }
    public ProfileDisplay DisplayPersonInfo(String currentUserToken,String displayPhoneNumber) {

        String currentUserPhoneNumber = jwtUtil.extractPhoneNumber(currentUserToken.replace("Bearer ", ""));
        ProfileDisplay result = new ProfileDisplay();
        Optional<User> searchedUser = userRepository.findByPhoneNumber(displayPhoneNumber);
        if (searchedUser.isPresent()) {
            User user = searchedUser.get();
            result.setName(user.getName());
            result.setPhoneNumber(user.getPhoneNumber());
            result.setSpamCount(spamReportRepo.countByPhoneNumber(user.getPhoneNumber()));

            boolean isContact = user.getContacts().stream()
                    .anyMatch(contact -> contact.getPhoneNumber().equals(currentUserPhoneNumber));
            if (isContact) {
                result.setEmail(user.getEmail());
            } else {
                result.setEmail("");
            }
        } else {
            return new ProfileDisplay("","",-1,"");
        }
        return result;
    }

    public String updateUser(@Valid UserUpdateFields user, String userToken) {
        String phoneNumber = jwtUtil.extractPhoneNumber(userToken.replace("Bearer ", ""));
        Optional<User> tempUser = userRepository.findByPhoneNumber(phoneNumber);
        User currentUser = tempUser.get();
        Contacts contact = contactRepo.findByPhoneNumber(phoneNumber);
        currentUser.setName(user.getName());
        currentUser.setEmail(user.getEmail());
        contact.setName(user.getName());
        userRepository.save(currentUser);
        contactRepo.save(contact);
        return "Changes updated Succesfully!";
    }
}
