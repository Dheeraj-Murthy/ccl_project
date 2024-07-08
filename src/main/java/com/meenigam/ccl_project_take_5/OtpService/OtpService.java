package com.meenigam.ccl_project_take_5.OtpService;

import java.util.Random;
import com.meenigam.ccl_project_take_5.twillo.sendSMS;

public class OtpService {
    private static String getOtp() {
        // It will generate 6 digit random Number.
        // from 0 to 999999
        Random rnd = new Random();
        int number = rnd.nextInt(999999);

        // this will convert any number sequence into 6 character.
        return String.format("%06d", number);
    }
    private static String sendOtp(String phoneNumber) {
        String otp = getOtp();
        System.out.println("OTP: " + otp);
//        sendSMS sms = new sendSMS();
        sendSMS.message(phoneNumber, otp);
        return otp;
    }
}
