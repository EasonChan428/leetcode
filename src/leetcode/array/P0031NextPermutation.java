class P0031NextPermutation {
    /*例如 2, 6, 3, 5, 4, 1 这个排列， 我们想要找到下一个刚好比他大的排列，于是可以从后往前看
    我们先看后两位 4, 1 能否组成更大的排列，答案是不可以，同理 5, 4, 1也不可以
    直到3, 5, 4, 1这个排列，因为 3 < 5， 我们可以通过重新排列这一段数字，来得到下一个排列
    因为我们需要使得新的排列尽量小，所以我们从后往前找第一个比3更大的数字，发现是4
    然后，我们调换3和4的位置，得到4, 5, 3, 1这个数列
    因为我们需要使得新生成的数列尽量小，于是我们可以对5, 3, 1进行排序，可以发现在这个算法中，我们得到的末尾数字一定是倒序排列的，于是我们只需要把它反转即可
    最终，我们得到了4, 1, 3, 5这个数列
    完整的数列则是2, 6, 4, 1, 3, 5*/
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        if(n == 1) return;
        int flag = 0;
        
        // 找到第一个能组成更大的排列的数字
        for(int i = n - 2; i >= 0; i--) {
            if(nums[i] < nums[i + 1]) {
                flag = i;
                break;
            }
        }

        if(nums[flag] >= nums[flag + 1]) { 
            // 不存在下一个更大的排列（判断条件不能漏）
            reverse(nums, 0);
        } else {
            // 需要使得新的排列尽量小，所以从后往前找第一个比nums[flag]更大的数字
            int start = flag + 1;
            int t = n - 1;
            while(nums[t] <= nums[flag]) {
                t--;
            }
            swapNum(nums, flag, t);
            // 剩余的末尾数字要反转
            reverse(nums, start);
        }
    }

    public void swapNum(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    public void reverse(int[] nums, int start) {
        int left = start, right = nums.length - 1;
        while(left < right) {
            swapNum(nums, left, right);
            left++;
            right--;
        }
    }
}