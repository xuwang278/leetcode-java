class Solution {
    遍历每对年龄，会超时；
    用map统计每个年龄的人数，然后按照每个年龄，按批次处理；
    比如17岁有2人，16岁有3人，每个17岁向每个16岁邀请：那么一共2*3 = 6次邀请
    注意，当ageA == ageB时，因为是同一撮儿人，ans += countA即可
    // T: O(120*120 + n) nested loop + iterate ages to make count
    // S: O(120)
    public int numFriendRequests(int[] ages) {
        int[] map = new int[121]; // idx: age, map[idx]: # of people at this age
        for (int age : ages) {
            map[age]++;
        }
        
        int cnt = 0;
        for (int ageA = 0; ageA <= 120; ageA++) {
            for (int ageB = 0; ageB <= 120; ageB++) {
                if (ageA * 0.5 + 7 >= ageB) continue;
                if (ageA < ageB) continue;
                if (ageA < 100 && 100 < ageB) continue;
                
                int cntA = map[ageA];
                int cntB = map[ageB];
                cnt += cntA * cntB;
                if (ageA == ageB) cnt -= cntA;
            }
        }
        return cnt;
    }

    
    public int numFriendRequests(int[] ages) {
        int[] count = new int[121];
        for (int age : ages) count[age]++;
        
        int ans = 0;
        for (int ageA = 0; ageA <= 120; ageA++) {
            int countA = count[ageA];
            if (countA == 0) continue;
            for (int ageB = 0; ageB <= 120; ageB++) {
                int countB = count[ageB];
                if (countB == 0) continue;
                
                if (ageA * 0.5 + 7 >= ageB) continue;
                if (ageA < ageB) continue;
                if (ageA < 100 && 100 < ageB) continue;
                
                // people of age A now is eligible to friend request people of age B 
                ans += countA * countB;
                
                // people of age A friend request each other 互粉 
                // 减去自己粉自己的情况
                // 3个同龄人ABC: A->(B,C), B->(A,C), C->(A,b),6次邀请
                if (ageA == ageB) ans -= countA; 
            }
        }
        return ans;
    }

    // TLE
    假如两个人年龄相同，那么满足了前两个条件后，其实是可以互粉的，所以要额外的加1
    public int numFriendRequests(int[] ages) {
        if (ages == null || ages.length == 0) return 0;
        
        Integer[] array = new Integer[ages.length];
        for (int i = 0; i < array.length; i++)
            array[i] = ages[i];
        
        // sort array by descending order
        Arrays.sort(array, new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return b - a;
            }
        });
        
        int cnt = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                int ageA = array[i];
                int ageB = array[j]; 
                if (ageB <= 0.5 * ageA + 7) continue; // rule out 1st
                if (ageB > 100 && ageA < 100) continue; // rule out 3rd
                if (ageA == ageB)cnt++;
                cnt++;
            }
            
        }
        return cnt;
    }
}