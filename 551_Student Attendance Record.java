public boolean checkRecord(String s) {
        char[] chs = s.toCharArray();
        int absent = 0;
        int late = 0;
        for (char c : chs) {
            if (c == 'A') {
                absent++;
                late = 0;
                if (absent == 2) return false;
            } else if (c == 'L') {
                late++;
                if (late == 3) return false;
            } else late = 0;
        }
        return true;
    }