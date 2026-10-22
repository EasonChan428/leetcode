class P0004MedianOfTwoSortedArrays { // 较难理解 要背
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 为了简化代码，不分情况讨论，我们使用一个小trick，我们分别找第 (m+n+1) / 2 个，和 (m+n+2) / 2 个，然后求其平均值即可，这对奇偶数均适用。假如 m+n 为奇数的话，那么其实 (m+n+1) / 2 和 (m+n+2) / 2 的值相等，相当于两个相同的数字相加再除以2，还是其本身
        int m = nums1.length;
        int n = nums2.length;
        int l = (m + n + 1) / 2;
        int r  =(m + n + 2) / 2;
        return (findKth(nums1, nums2, 0, 0, l) + findKth(nums1, nums2, 0, 0, r)) / 2.0;
    }
    //i: nums1的起始位置 j: nums2的起始位置
    private int findKth(int[] nums1, int[]nums2, int i, int j, int k) {
        // 实现找到第K个元素
        if(i >= nums1.length) return nums2[j + k - 1]; //nums1为空数组
        if(j >= nums2.length) return nums1[i + k - 1]; //nums1为空数组
        if(k == 1) {
            return Math.min(nums1[i], nums2[j]);
        }
        // 对K二分，意思是我们需要分别在nums1和nums2中查找第K/2个元素，注意这里由于两个数组的长度不定，所以有可能某个数组没有第K/2个数字，所以我们需要先检查一下，数组中到底存不存在第K/2个数字，如果存在就取出来，否则就赋值上一个整型最大值
        int mid1 = (i + k / 2 - 1 < nums1.length) ? nums1[i + k / 2 - 1] : Integer.MAX_VALUE;
        int mid2 = (j + k / 2 - 1 < nums2.length) ? nums2[j + k / 2 - 1] : Integer.MAX_VALUE;
        // 二分法的核心:比较这两个数组的第K/2小的数字mid1和mid2的大小，如果第一个数组的第K/2个数字小的话，那么说明我们要找的数字肯定不在nums1中的前K/2个数字，所以我们可以将其淘汰，将nums1的起始位置向后移动K/2个，并且此时的K也自减去K/2，调用递归。反之，我们淘汰nums2中的前K/2个数字，并将nums2的起始位置向后移动K/2个，并且此时的K也自减去K/2，调用递归即可。
        if(mid1 < mid2) {
            return findKth(nums1, nums2, i + k / 2, j, k - k / 2);
        } else {
            return findKth(nums1, nums2, i, j + k / 2, k - k / 2);
        }
    }
}