import java.util.*;

class P0295FindMedianFromDataStream {
    // 大小堆 
    // 使用 PriorityQueue 配比较器可以实现自动堆化效果
    PriorityQueue<Integer> queMin;
    PriorityQueue<Integer> queMax;
    public P0295FindMedianFromDataStream() {
        queMin = new PriorityQueue<Integer>((a, b) -> (b - a)); // 大顶堆：Comparator 返回`b-a`，大元素排在堆顶；
        queMax = new PriorityQueue<Integer>((a, b) -> (a - b)); // 小顶堆：Comparator 返回`a-b`，小元素排在堆顶。
    }
    
    public void addNum(int num) {
        // 情况1：新数 <= 左半最大值，归入左半大顶堆
        if(queMin.isEmpty() || num <= queMin.peek()) {
            queMin.offer(num);
            // 左半比右半多2个，把左半最大的移到右半，恢复平衡
            if(queMax.size() + 1 < queMin.size()) {
                queMax.offer(queMin.poll());
            }
        } else { // 情况2：新数 > 左半最大值，归入右半小顶堆
            queMax.offer(num);
            if(queMax.size() > queMin.size()) {
                queMin.offer(queMax.poll());
            }
        }
    }
    
    public double findMedian() {
        // 总数奇数：左半多1个，中位数就是左半堆顶
        if(queMin.size() > queMax.size()) {
            return queMin.peek();
        } else { // 总数偶数：左右两半相等，中位数 = 两堆顶平均值
            return (queMin.peek() + queMax.peek()) / 2.0;
        }
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */