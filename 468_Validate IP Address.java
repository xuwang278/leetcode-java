class Solution {
    private boolean isIPv4(String IP) {
        // 检查是否有三个 '.'
        int cnt = 0;
        for (char ch : IP.toCharArray()) {
            if (ch == '.') {
                cnt++;
            }
        }

        if (cnt != 3) {
            return false;
        }

        // 检查是否有四个部分
        String[] fields = IP.split("\\.");
        if (fields.length != 4) {
            return false;
        }

        // 每个部分是否合法
        for (String field : fields) {
            if (field.isEmpty() || field.length() > 3) {
                return false;
            }
            // 是否是数字
            int sz = field.length();
            for (int i = 0; i < sz; ++i) {
                if (!Character.isDigit(field.charAt(i))) {
                    return false;
                }
            }
            // 数值应在[0, 255]间 
            // 如果没有leading 0, num should equal to field
            int num = Integer.valueOf(field); // 抹去leading 0
            if (!String.valueOf(num).equals(field) || num < 0 || num > 255) {
                return false;
            }
        }

        return true;
    }

    private boolean isIPv6(String IP) {
        int cnt = 0;
        for (char ch : IP.toCharArray()) {
            if (ch == ':') {
                cnt++;
            }
        }

        if (cnt != 7) {
            return false;
        }

        String[] fields = IP.split(":");
        if (fields.length != 8) {
            return false;
        }

        for (String field : fields) {
            if (field.isEmpty() || field.length() > 4) {
                return false;
            }

            int sz = field.length();
            for (int i = 0; i < sz; ++i) {
                if (!Character.isDigit(field.charAt(i)) && (field.charAt(i) < 'A' || field.charAt(i) > 'F')) {
                    return false;
                }
            }
        }

        return true;
    }
    
    public String validIPAddress(String IP) {
        if (isIPv4(IP)) {
            return "IPv4";
        }

        if (isIPv6(IP.toUpperCase())) {
            return "IPv6";
        }

        return "Neither";
    }
}