class P0042TrappingRainWater {
    public int trap(int[] height) {
        int sum=0;
        int maxleft=0;
        int maxright=0;
        int left=1;
        int right=height.length-2;
        //最两端的列不用考虑，因为一定不会有水。所以下标从 1 到 length - 2
        for(int i=1;i<height.length-1;i++){
            if(height[left-1]<height[right+1]){
                //右比左大时从左到右更
                maxleft=Math.max(maxleft,height[left-1]);
                int min=maxleft;
                if(min>height[left]){
                    sum=sum+(min-height[left]);
                }
                left++;
            }else{
                //反之从右到左更
                maxright=Math.max(maxright,height[right+1]);
                int min=maxright;
                if(min>height[right]){
                    sum=sum+(min-height[right]);
                }
                right--;
            }
        }
        return sum;
    }
}