import java.util.*;

class Solution {

    List<List<String>> ans = new ArrayList<>();
    Map<String, List<String>> parent = new HashMap<>();
    String begin;

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {

        begin = beginWord;

        Set<String> words = new HashSet<>(wordList);
        if (!words.contains(endWord)) return ans;

        Map<String, Integer> level = new HashMap<>();
        Queue<String> q = new LinkedList<>();

        q.offer(beginWord);
        level.put(beginWord, 0);

        while (!q.isEmpty()) {

            String word = q.poll();
            int currLevel = level.get(word);

            char[] arr = word.toCharArray();

            for (int i = 0; i < arr.length; i++) {

                char original = arr[i];

                for (char ch = 'a'; ch <= 'z'; ch++) {

                    if (ch == original) continue;

                    arr[i] = ch;
                    String next = new String(arr);

                    if (!words.contains(next)) continue;

                    if (!level.containsKey(next)) {

                        level.put(next, currLevel + 1);
                        q.offer(next);

                        parent.putIfAbsent(next, new ArrayList<>());
                        parent.get(next).add(word);
                    }
                    else if (level.get(next) == currLevel + 1) {

                        parent.putIfAbsent(next, new ArrayList<>());
                        parent.get(next).add(word);
                    }
                }

                arr[i] = original;
            }
        }

        if (!level.containsKey(endWord)) return ans;

        List<String> path = new ArrayList<>();
        path.add(endWord);

        dfs(endWord, path);

        return ans;
    }

    private void dfs(String word, List<String> path) {

        if (word.equals(begin)) {

            List<String> temp = new ArrayList<>(path);
            Collections.reverse(temp);
            ans.add(temp);
            return;
        }

        if (!parent.containsKey(word)) return;

        for (String p : parent.get(word)) {

            path.add(p);
            dfs(p, path);
            path.remove(path.size() - 1);
        }
    }
}