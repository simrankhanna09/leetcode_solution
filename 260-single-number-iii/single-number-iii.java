class Solution {
    public int[] singleNumber(int[] nums) {
         int xor = 0;

        // Step 1: XOR of all elements
        for(int num : nums) {
            xor ^= num;
        }

        // Step 2: find rightmost set bit
        int diffBit = xor & (-xor);

        int a = 0, b = 0;

        // Step 3: divide into two groups
        for(int num : nums) {
            if((num & diffBit) == 0) {
                a ^= num;
            } else {
                b ^= num;
            }
        }

        return new int[]{a, b};
    }
}