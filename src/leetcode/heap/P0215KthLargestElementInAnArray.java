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

    // 交换数组两个下标的元素（原代码片段里没贴这个方法，补上，否则编译不过）
    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}