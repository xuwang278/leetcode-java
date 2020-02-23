class Solution {
    public String defangIPaddr(String address) {
        StringBuilder sb = new StringBuilder();
        for (char c : address.toCharArray()) {
            if (c >= '0' && c <= '9') {
                sb.append(c);
            } else if (c == '.') {
                sb.append("[.]");
            }
        }
        return sb.toString();
    }
}