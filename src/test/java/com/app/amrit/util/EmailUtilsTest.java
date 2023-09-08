package com.app.amrit.util;

import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class EmailUtilsTest {

    @MockBean
    private JavaMailSender mailSender;

    @InjectMocks
    private EmailUtils emailUtils;

    public void sendEmailTest(){
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        doNothing().when(mailSender).send(mimeMessage);
        emailUtils.sendEmail("test-subject", "test-body", "test@gmail.com");
    }
}