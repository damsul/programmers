package Level_1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 대충만든자판 {
    public int[] solution(String[] keymap, String[] targets) {

        int[] alphabet = new int[26];
        Arrays.fill(alphabet, Integer.MAX_VALUE);

        for (String key : keymap) {
            for (int i = 0; i < key.length(); i++) {
                int idx = key.charAt(i) - 'A';
                alphabet[idx] = Math.min(alphabet[idx], i + 1);
            }
        }

        int[] answer = new int[targets.length];
        for (int i = 0; i < targets.length; i++) {
            String target = targets[i];
            int cnt = 0;

            for (int j = 0; j < target.length(); j++) {
                int idx = target.charAt(j) - 'A';
                if (alphabet[idx] == Integer.MAX_VALUE) {
                    cnt = -1;
                    break;
                }
                cnt += alphabet[idx];
            }

            answer[i] = cnt;
        }

        return answer;
    }

    public int[] mySolution(String[] keymap, String[] targets) {
        Map<Character, Integer> map = new HashMap<>();

        for (String key : keymap) {
            for (int i = 0; i < key.length(); i++) {
                char c = key.charAt(i);
                map.put(c, Math.min(map.getOrDefault(c, Integer.MAX_VALUE), i + 1));
            }
        }

        List<Integer> list = new ArrayList<>();
        for (String target : targets) {
            int cnt = 0;
            boolean flag = true;

            for (int i = 0; i < target.length(); i++) {
                char c = target.charAt(i);
                if (!map.containsKey(c)) {
                    flag = false;
                    list.add(-1);
                    break;
                }
                cnt += map.get(c);
            }

            if (!flag) continue;
            list.add(cnt);
        }

        int[] answer = new int[list.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }

    public static void main(String[] args) {
        대충만든자판 main = new 대충만든자판();
        System.out.println(Arrays.toString(main.solution(new String[]{"ABACD", "BCEFD"}, new String[]{"ABCD","AABB"})));
        System.out.println(Arrays.toString(main.solution(new String[]{"AA"}, new String[]{"B"})));
        System.out.println(Arrays.toString(main.solution(new String[]{"AGZ", "BSSS"}, new String[]{"ASA","BGZ"})));
    }
}
