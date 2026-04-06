class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict); // fast lookup
        boolean[] dp = new boolean[s.length() + 1];

        dp[0] = true; // empty string is valid

        for(int i = 1; i <= s.length(); i++) {
            for(int j = 0; j < i; j++) {

                // split: s[0..j-1] + s[j..i-1]
                if(dp[j] && set.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[s.length()];
    }
}