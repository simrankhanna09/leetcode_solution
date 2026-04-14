class Solution {
    public long minimumTotalDistance(List<Integer> robot, int[][] factory) {
      // Step 1: Sort robots and factories
        Collections.sort(robot);
        Arrays.sort(factory, (a, b) -> a[0] - b[0]);
        
        // Step 2: Expand factories into slots
        List<Integer> slots = new ArrayList<>();
        for (int[] f : factory) {
            int pos = f[0], limit = f[1];
            for (int i = 0; i < limit; i++) {
                slots.add(pos);
            }
        }
        
        int n = robot.size();
        int m = slots.size();
        
        // Step 3: DP array
        long[][] dp = new long[n + 1][m + 1];
        
        // Initialize with large values
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], Long.MAX_VALUE / 2);
        }
        
        // Base case
        for (int j = 0; j <= m; j++) {
            dp[0][j] = 0; // 0 robots → 0 distance
        }
        
        // Fill DP
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                
                // Option 1: skip this slot
                dp[i][j] = dp[i][j - 1];
                
                // Option 2: assign robot to slot
                long cost = Math.abs(robot.get(i - 1) - slots.get(j - 1));
                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1] + cost);
            }
        }
        
        return dp[n][m];  
    }
}