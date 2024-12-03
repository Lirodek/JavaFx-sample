package org.sight.kiosk.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public final class StringUtils {

    // 인스턴스화 방지
    private StringUtils() {
        throw new UnsupportedOperationException("유틸리티 클래스는 인스턴스화할 수 없습니다.");
    }

    // 1. Hex -> UTF-8 문자열 디코딩
    public static String decodeHexToUtf8(String hexString) {
        try {
            String[] hexArray = hexString.split(":");

            // Hex 데이터를 바이트 배열로 변환
            byte[] bytes = new byte[hexArray.length];
            for (int i = 0; i < hexArray.length; i++) {
                bytes[i] = (byte) Integer.parseInt(hexArray[i], 16);
            }

            // UTF-8로 디코딩
            return new String(bytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("Hex to UTF-8 디코딩 실패", e);
        }
    }

    // 2. UTF-8 -> Hex 문자열 인코딩
    public static String encodeUtf8ToHex(String input) {
        StringBuilder hexBuilder = new StringBuilder();
        for (byte b : input.getBytes(StandardCharsets.UTF_8)) {
            hexBuilder.append(String.format("%02x:", b));
        }
        return hexBuilder.substring(0, hexBuilder.length() - 1); // 마지막 ":" 제거
    }

    // 3. 문자열이 숫자로만 구성되었는지 확인
    public static boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        return str.chars().allMatch(Character::isDigit);
    }

    // 4. 문자열 뒤집기
    public static String reverse(String input) {
        return new StringBuilder(input).reverse().toString();
    }

    // 5. 특정 문자열의 빈도수 계산
    public static int countOccurrences(String input, char target) {
        if (input == null) return 0;
        return (int) input.chars().filter(ch -> ch == target).count();
    }

    // 6. 문자열 공백 제거
    public static String removeWhitespaces(String input) {
        if (input == null) return null;
        return input.replaceAll("\\s+", "");
    }

    // 7. 문자열 대소문자 변환 (타이틀 케이스)
    public static String toTitleCase(String input) {
        if (input == null || input.isEmpty()) return input;
        String[] words = input.split(" ");
        StringBuilder titleCase = new StringBuilder();
        for (String word : words) {
            if (word.length() > 1) {
                titleCase.append(Character.toUpperCase(word.charAt(0)))
                        .append(word.substring(1).toLowerCase())
                        .append(" ");
            } else {
                titleCase.append(word.toUpperCase()).append(" ");
            }
        }
        return titleCase.toString().trim();
    }

    // 8. Base64 인코딩
    public static String encodeBase64(String input) {
        return Base64.getEncoder().encodeToString(input.getBytes(StandardCharsets.UTF_8));
    }

    // 9. Base64 디코딩
    public static String decodeBase64(String input) {
        return new String(Base64.getDecoder().decode(input), StandardCharsets.UTF_8);
    }
}
