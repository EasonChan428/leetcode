class P0169MajorityElement {
    public int majorityElement(int[] nums) {
        // Boyer-Moore 投票算法
        // 假设你在投票选人 如果你和候选人（利益）相同，你就会给他投一票（count+1），如果不同，你就会踩他一下（count-1）当候选人票数为0（count=0）时，就换一个候选人，但因为和你利益一样的人占比超过了一半 不论换多少次 ，最后留下来的都一定是个和你（利益）相同的人。
        int count = 0;
        int res = nums[0];

        for(int num : nums) {
            if(count == 0) {
                res = num;
            }
            if(res == num) {
                count++;
            } else {
                count--;
            }
        }
        return res;
    }
}