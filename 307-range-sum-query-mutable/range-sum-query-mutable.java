class NumArray {

    int[] bit;   // Fenwick Tree
    int[] nums;  // original array
    int n;

    public NumArray(int[] nums) {
        this.n = nums.length;
        this.nums = new int[n];
        this.bit = new int[n + 1];

        // build BIT
        for(int i = 0; i < n; i++) {
            update(i, nums[i]);
        }
    }

    // Update index to new value
    public void update(int index, int val) {
        int diff = val - nums[index];
        nums[index] = val;

        index++; // BIT is 1-based
        while(index <= n) {
            bit[index] += diff;
            index += index & (-index);
        }
    }

    // Prefix sum [0..index]
    private int getSum(int index) {
        int sum = 0;
        index++; // 1-based
        while(index > 0) {
            sum += bit[index];
            index -= index & (-index);
        }
        return sum;
    }

    // Range sum [left..right]
    public int sumRange(int left, int right) {
        return getSum(right) - getSum(left - 1);
    }
}