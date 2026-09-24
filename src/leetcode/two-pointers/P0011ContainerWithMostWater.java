class P0011ContainerWithMostWater {
    public int maxArea(int[] height) {
        if(height==null){
            return 0;
        }
        int i=0,j=height.length-1;
        int max=0,current=0;
        while(i<j){
            if(height[i]<=height[j]){
                current=height[i]*(j-i);
                i++;
            }
            else{
                current=height[j]*(j-i);
                j--;
            }
            max=Math.max(max,current);
        }
        return max;
    }
}