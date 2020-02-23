class Solution {
    public boolean validUtf8(int[] data) {
		if (data == null || data.length == 0) return false;
		
		boolean isValid = true;
		for (int i = 0; i < data.length; i++) {
			if (data[i] > 255) return false; // 255 = 0b1111 1111
			int numberOfBytes = 0;
			if ((data[i] & 128) == 0) { // 128 = 0b1000 0000
				numberOfBytes = 1; 
			} else if ((data[i] & 224) == 192) { // 11xx xxxx, 2 bytes, 224(1110 0000), 192(1100 0000)
				numberOfBytes = 2;
			} else if ((data[i] & 240) == 224) { // 111x xxxx, 3 bytes, 240(1111 0000), 224(1110 0000)
				numberOfBytes = 3;
			} else if ((data[i] & 248) == 240) { // 1111 xxxx, 4 bytes, 248(1111 1000), 240(1111 0000)
				numberOfBytes = 4;
			} else {
				return false;
			}

			for (int j = 1; j < numberOfBytes; j++) { // check that the next n bytes start with 10xxxxxx
				if (i + j >= data.length)return false;
				if ((data[i + j] & 192) != 128)
					return false; // 192(11000000), 128(10000000)
			}

			i = i + numberOfBytes - 1;
		}

		return isValid;
	}

	public boolean validUtf8(int[] data) {
		int cnt = 0;
		for (int d : data) {
			if (cnt == 0) {
				if ((d >> 5) == 0b110) cnt = 1;
				else if ((d >> 4) == 0b1110) cnt = 2;
				else if ((d >> 3) == 0b11110) cnt = 3;
				else if ((d >> 7) != 0) return false;
			} else {
				if ((d >> 6) != 0b10) return false;
				cnt--;
			}
		}
		return cnt == 0;
	}
}