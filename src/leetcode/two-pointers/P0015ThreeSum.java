import java.util.*;

class P0015ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        int n=nums.length;
        Arrays.sort(nums);
        List<List<Integer>> ans=new ArrayList<List<Integer>>();
        for(int i=0;i<n;i++){
            if(nums[i]>0){
                break;
            }
            if(i>0&&nums[i]==nums[i-1]){
                continue;
            }
            int left=i+1;
            int right=n-1;
            while(left<right){
                int sum=nums[i]+nums[left]+nums[right];
                if(sum<0){
                    left++;
                }
                if(sum>0){
                    right--;
                }
                if(sum==0){
                    List<Integer> list=new ArrayList<Integer>();
                    list.add(nums[i]);
                    list.add(nums[left]);
                    list.add(nums[right]);
                    ans.add(list);
                    while(left<right&&nums[left]==nums[left+1]){
                        left++;
                    }
                    while(left<right&&nums[right]==nums[right-1]){
                        right--;
                    }
                    left++;
                    right--;
                }
            }
        }
        return ans;
    }
}
/*
1. 先排序，让相同元素挨在一起，方便去重，同时双指针可以按大小移动。
2. 外层循环固定第一个数`nums[i]`，目标：在`i`右侧找两个数之和等于`-nums[i]`。
3. 左指针`left = i+1`，右指针`right = n-1`，双指针向中间夹逼：
   - \(sum = nums[i]+nums[left]+nums[right]\)
   - \(sum<0\)：总和太小，左指针右移，增大和
   - \(sum>0\)：总和太大，右指针左移，减小和
   - \(sum=0\)：找到一组解，存入结果，**左右指针同时移动并跳过重复元素**
4. 三层去重（本题最容易踩坑）
   - 固定数 i：`i>0 && nums[i]==nums[i-1]`，跳过，避免起点重复
   - 找到解后，左指针跳过所有相同值
   - 找到解后，右指针跳过所有相同值
5. 剪枝：`nums[i]>0`直接 break。数组升序，当前已经 > 0，后面所有数更大，三数之和不可能等于 0。
*/