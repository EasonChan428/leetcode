class P0136SingleNumber { // 不需要额外空间的方法，就往位运算上想
    public int singleNumber(int[] nums) {
        // 对于这道题，可使用异或运算 ⊕。异或运算有以下三个性质。
        // 任何数和 0 做异或运算，结果仍然是原来的数，即 a⊕0=a。
        // 任何数和其自身做异或运算，结果是 0，即 a⊕a=0。
        // 异或运算满足交换律和结合律，即 a⊕b⊕a=b⊕a⊕a=b⊕(a⊕a)=b⊕0=b。
        int single = 0;
        for(int num : nums) {
            single = single ^ num;
        }
        return single;
    }
}