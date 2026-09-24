import java.util.*;

class P0128LongestConsecutiveSequence {
    public int longestConsecutive(int[] nums) {
        Set<Integer> num_set=new HashSet<Integer>();
        for(int num : nums){
            num_set.add(num);
        }
        int longest=0;
        for(int num : num_set){
            if(!num_set.contains(num-1)){
                int currentNum=num;
                int current=1;
            
                while(num_set.contains(currentNum+1)){
                    currentNum+=1;
                    current+=1;
                }
            longest=Math.max(longest,current);
            }
        }
        return longest;
    }
}