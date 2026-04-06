class Solution {
    public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
        int n = req_skills.length;

        // Step 1: Map each skill to a bit
        Map<String, Integer> skillMap = new HashMap<>();
        for(int i = 0; i < n; i++) {
            skillMap.put(req_skills[i], i);
        }

        // Step 2: Convert each person to bitmask
        int[] peopleMask = new int[people.size()];
        for(int i = 0; i < people.size(); i++) {
            int mask = 0;
            for(String skill : people.get(i)) {
                if(skillMap.containsKey(skill)) {
                    mask |= (1 << skillMap.get(skill));
                }
            }
            peopleMask[i] = mask;
        }

        // Step 3: DP → mask → team
        Map<Integer, List<Integer>> dp = new HashMap<>();
        dp.put(0, new ArrayList<>()); // no skills → empty team

        for(int i = 0; i < peopleMask.length; i++) {
            int personSkill = peopleMask[i];

            if(personSkill == 0) continue;

            Map<Integer, List<Integer>> newDp = new HashMap<>(dp);

            for(int mask : dp.keySet()) {
                int newMask = mask | personSkill;

                List<Integer> team = new ArrayList<>(dp.get(mask));
                team.add(i);

                if(!newDp.containsKey(newMask) || 
                   newDp.get(newMask).size() > team.size()) {
                    newDp.put(newMask, team);
                }
            }

            dp = newDp;
        }

        int fullMask = (1 << n) - 1;
        List<Integer> result = dp.get(fullMask);

        // convert to array
        int[] ans = new int[result.size()];
        for(int i = 0; i < result.size(); i++) {
            ans[i] = result.get(i);
        }

        return ans;
    }
}