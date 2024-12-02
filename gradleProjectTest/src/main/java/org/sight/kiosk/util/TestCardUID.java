package org.sight.kiosk.util;

import javax.smartcardio.CardChannel;
import javax.smartcardio.CardException;
import javax.smartcardio.CommandAPDU;
import javax.smartcardio.ResponseAPDU;

public class TestCardUID {
    public static String getCardUID(CardChannel channel) throws CardException {
        // APDU Command: GET DATA (FF CA 00 00 00)
        CommandAPDU getUIDCommand = new CommandAPDU(new byte[]{(byte) 0xFF, (byte) 0xCA, 0x00, 0x00, 0x00});
        ResponseAPDU response = channel.transmit(getUIDCommand);

        if (response.getSW() == 0x9000) { // Success
            byte[] uidBytes = response.getData();
            String uid = bytesToHex(uidBytes);
            System.out.println("Card UID: " + uid);

            return uid;
        } else {
            System.out.println("Failed to get UID. SW: " + Integer.toHexString(response.getSW()));
        }
        return null;
    }

    // Helper method to convert byte array to hex string
    public static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xFF & b);
            if (hex.length() == 1) {
                hexString.append('0'); // Ensure two characters for each byte
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
