class P0215KthLargestElementInAnArray {
    public int findKthLargest(int[] nums, int k) {
        // 建立一个大根堆，做 k−1 次删除操作后堆顶元素就是要找的答案
        int heapSize = nums.length;
        buildMaxHeap(nums, heapSize); // 第一步：把整个数组建为大顶堆
        // 重复 k-1 次：把当前最大值换到末尾，缩小堆并调整
        for(int i = nums.length - 1; i >= nums.length - k + 1; i--) {
            swap(nums, 0, i); // 堆顶(最大值) 和 当前堆末尾元素交换
            heapSize--; // 堆大小减1，末尾已排序区域不再参与堆调整
            maxHeapify(nums, 0, heapSize); // 从堆顶开始重新调整成大顶堆
        }
        return nums[0]; // 第k-1个最大值被移走后，堆顶就是第k大
    }

    private void buildMaxHeap(int[] nums, int heapSize) {
        // 数组模拟完全二叉树：
        // 下标i的左孩子：2*i+1，右孩子：2*i+2；
        // 最后一个节点下标heapSize-1，它的父节点就是(heapSize-1)/2，最后一个非叶子节点 = heapSize/2 -1；
        // 从最后一个非叶子节点倒序向前做堆调整，保证每个子树都是大顶堆，最终整棵树成为大顶堆。
        // 倒序调整的原因：先保证下层子树合法，再调整上层，避免上层调整完下层又被破坏。
        for(int i = heapSize / 2 - 1; i >= 0; i--) {
            // 从最后一个非叶子节点开始，倒序向前逐个调整
            maxHeapify(nums, i, heapSize);
        }
    }

    private void maxHeapify(int[] nums, int i, int heapSize) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int max = i;
        // 找i、左孩子、右孩子三者中的最大值下标
        if(left < heapSize && nums[left] > nums[max]) {
            max = left;
        }
        if(right < heapSize && nums[right] > nums[max]) {
            max = right;
        }
        // 如果最大值不是i本身，交换后递归向下调整被破坏的子树
        if(max != i) {
            swap(nums, i, max);
            maxHeapify(nums, max, heapSize);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
/*
// 桶排序
class Solution {
    public int findKthLargest(int[] nums, int k) {
        // 数值范围[-10000,10000]，偏移10000后映射到[0,20000]，开20001个桶
        int[] bucket = new int[20001];
        final int OFFSET = 10000;

        // 1. 把每个数字放进对应桶，桶下标 = 数字 + 偏移量，桶值 = 出现次数
        for (int num : nums) {
            bucket[num + OFFSET]++;
        }

        // 2. 从最大数值的桶往最小数值遍历，累计次数，数到第K个就是答案
        for (int i = 20000; i >= 0; i--) {
            k -= bucket[i]; // 减去当前数值出现次数
            if (k <= 0) {   // 累计够K次，当前数值就是第K大
                return i - OFFSET;
            }
        }
        return -1; // 题目保证有解，不会走到这里
    }
}
*/

/*
// 快速排序
class Solution {
    int quickselect(int[] nums, int l, int r, int k) {
        if (l == r) return nums[k];
        int x = nums[l], i = l - 1, j = r + 1;
        while (i < j) {
            do i++; while (nums[i] < x);
            do j--; while (nums[j] > x);
            if (i < j){
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;
            }
        }
        if (k <= j) return quickselect(nums, l, j, k);
        else return quickselect(nums, j + 1, r, k);
    }
    public int findKthLargest(int[] _nums, int k) {
        int n = _nums.length;
        return quickselect(_nums, 0, n - 1, n - k);
    }
}
*/
