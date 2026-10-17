import java.util.*;

class P0207CourseSchedule {
    // 拓扑排序 | 课程表问题
    // 把一个 有向无环图 转成 线性的排序 就叫 拓扑排序
    // 有向图有入度和出度的概念：如果存在一条有向边A-->B，则这条边给A增加了1 出度，给B增加了 1 个入度
    // 每次只能选入度为0的课，因为它不依赖别的课，是当下能上的课

    public boolean canFinish(int numCourses, int[][] prerequisites) {  
        // 入度数组，用于记录每门课程的入度
        int [] inDegree = new int[numCourses]; 
        // 邻接表，存储每门课程的后续课程
        List<List<Integer>> adjList = new ArrayList<>(); 
        for(int i = 0; i < numCourses; i++) {
            adjList.add(new ArrayList<>());
        }

        // 计算每门课程的入度，并构建邻接表
        for(int[] prerequisite : prerequisites) {
            int course = prerequisite[0];
            int precourse = prerequisite[1];
            inDegree[course]++;
            adjList.get(precourse).add(course); // adjList.get(u)存放u的所有后继节点
        }

        // 存储入度为 0 的课程的队列
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < numCourses; i++) {
            if(inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        // 记录已完成课程的数量
        int count = 0;
        while(!queue.isEmpty()) {
            count++;
            int curCourse = queue.poll();
            // 获取当前课程的后续课程列表
            List<Integer> nextCourses = adjList.get(curCourse);
            for(int nextCourse : nextCourses) {
                // 后续课程的入度减 1
                inDegree[nextCourse]--;
                if(inDegree[nextCourse] == 0) {
                    queue.offer(nextCourse);
                }
            }
        }

        // 如果已完成课程的数量等于总课程数，则可以完成所有课程
        return count == numCourses;
    }
}