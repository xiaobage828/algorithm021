class Solution {
    public int trap(int[] height) {
        Deque<Integer> s = new LinkedList<>();
        int i = 0 ,sum = 0;
        while(i < height.length){
            while(!s.isEmpty() && height[i] > height[s.peek()]){
                int top = s.pop();
                if(s.isEmpty()) break;
                int broad = i - s.peek() -1;
                int broad_height = Math.min(height[s.peek()],height[i]) - height[top];
                sum += broad*broad_height;
            }
            s.push(i);
            i++;
        }
        return sum;
    }
}