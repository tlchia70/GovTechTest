package com.ahq.addons;

import com.atlassian.onetime.core.TOTP;
import com.atlassian.onetime.core.TOTPGenerator;
import com.atlassian.onetime.model.TOTPSecret;


public class totp {
    
    public static String getOneTimeCode(String OTPSecret) {
        TOTPSecret secret = TOTPSecret.Companion.fromBase32EncodedString(OTPSecret);
        TOTPGenerator totpGenerator = new TOTPGenerator();
        TOTP totp = totpGenerator.generateCurrent(secret);
        return totp.getValue();
    }
}
