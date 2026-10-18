import java.util.*;

// 给新手的回溯法总结：
// 1）当组合中允许元素重复：则迭代的起始值就是i，每次递增
// 2）当组合中不允许重复：迭代的起始值是i + 1，每次递增
// 3）当这是排列不是组合：迭代的起始值是传进去的start（初始位），每次都从头开始遍历所有元素

class P0039CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>();
        dfs(candidates, target, res, tmp, 0, 0);
        return res;
    }

    private void dfs(int[] candidates, int target, List<List<Integer>> res, List<Integer> tmp, int cur, int sum) {
        if(sum >= target) {
            if(sum == target) {
                res.add(new ArrayList<>(tmp)); // 依旧拷贝，不能直接add tmp
            }
            return;
        }
        for(int i = cur; i < candidates.length; i++) {
            tmp.add(candidates[i]);
            // 允许重复，则i不用加1
            dfs(candidates, target, res, tmp, i, sum + candidates[i]); // 注意这里的sum的更新要写在函数调用里，不然就要定义一个新的int newSum = sum + candidates[i] 或者手动加减原来的sum
            tmp.remove(tmp.size() - 1);
        }
    }
}