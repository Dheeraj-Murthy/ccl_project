package com.meenigam.ccl_project_take_5.twillo;

import com.meenigam.ccl_project_take_5.utils.AccountCreds;
import com.twilio.Twilio;
import com.twilio.converter.Promoter;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.net.URI;
import java.math.BigDecimal;

public class sendSMS {
    // Find your Account Sid and Token at twilio.com/console
    public static final String ACCOUNT_SID = AccountCreds.getAccountSSid();
    public static final String AUTH_TOKEN = AccountCreds.getAccounttoken();

    public static void message(String phoneNumber, String message) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message sms = Message.creator(
                        new com.twilio.type.PhoneNumber(phoneNumber),
                        new com.twilio.type.PhoneNumber("+13082706829"),
                        message)
                .create();

        System.out.println(sms.getSid());
    }

    public static void main(String[] args) {
        message("+919431786037", "this is a message sent by the twillo api");
    }
}