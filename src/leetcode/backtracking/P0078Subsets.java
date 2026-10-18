import java.util.*;

class P0078Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>();
        backtrack(0, nums, res, tmp);
        return res;
    }

    private void backtrack(int cur, int[] nums, List<List<Integer>> res, List<Integer> tmp) {
        res.add(new ArrayList<>(tmp)); // 把当前tmp保存到结果集，new ArrayList<>(tmp)是拷贝

        for(int i = cur; i < nums.length; i++) { // 循环：从cur开始，依次选取后面每一个元素
            tmp.add(nums[i]);
            backtrack(i + 1, nums, res, tmp); // 递归，下一轮只能选j后面的元素（避免重复子集）
            tmp.remove(tmp.size() - 1); // 撤销当前选择，回溯
        }
        // for循环结束void函数自动返回
    }
}