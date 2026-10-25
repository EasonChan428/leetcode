import java.util.*;

class P0347TopKFrequentElements {
    // 桶排序
    public int[] topKFrequent(int[] nums, int k) {
        // 第一步：用HashMap数每个数字出现几次
        Map<Integer, Integer> countMap = new HashMap<>();
        for(int num : nums) {
            // 数字存在就取出原次数 + 1，不存在默认 0 再 + 1，完成频次累加
            countMap.put(num, countMap.getOrDefault(num, 0) + 1); 
        }
        // 第二步：建桶，桶的下标 = 出现次数，桶里装对应次数的数字
        // 数组最多n个元素，最多出现n次，桶长度开n+1足够
        List<Integer>[] buckets = new List[nums.length + 1]; // 同一次数可以有多个数字，所以用List数组存储
        for(int num : countMap.keySet()) {
            int count = countMap.get(num);
            if(buckets[count] == null) buckets[count] = new ArrayList<>();
            buckets[count].add(num);
        }
        // 第三步：从最高频次的桶往低频次倒着拿，拿满k个就停
        int[] res = new int[k];
        int index = 0;
        for(int c = nums.length; c >= 1 && index < k; c--) {
            // 桶的下标就是出现次数，下标越大代表频次越高，天然从高到低排好
            if(buckets[c] == null) continue;
            for(int num : buckets[c]) {
                res[index] = num;
                index++;
                if(index == k) break;
            }
        }

        return res;
    }
}

/*
// 堆排序，面试可能问，理解思路
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        // 1. 先统计每个数字出现多少次
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        // 2. 建最小堆：按出现次数从小到大排，堆顶永远是当前堆里次数最少的
        // 堆里存 int[]{数字, 出现次数}，lambda写比较器，比匿名内部类好读
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(
                (a, b) -> a[1] - b[1]
        );

        // 3. 把所有数字逐个放进堆，只保留次数最高的k个
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            int num = entry.getKey();
            int freq = entry.getValue();

            // 堆还没满k个，直接放进去
            if (minHeap.size() < k) {
                minHeap.offer(new int[]{num, freq});
            }
            // 堆满了：如果当前数字次数比堆顶（堆里最少的）还多，就淘汰堆顶，放当前数字
            else if (freq > minHeap.peek()[1]) {
                minHeap.poll(); // 踢掉次数最少的堆顶
                minHeap.offer(new int[]{num, freq});
            }
        }

        // 4. 把堆里剩下的k个数字拿出来当结果
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = minHeap.poll()[0];
        }
        return result;
    }
}
*/