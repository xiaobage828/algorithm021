#第六周 
##一、学习笔记
###动态规划（二）
####实战题目解析
1. https://leetcode-cn.com/problems/climbing-stairs/description/，爬楼梯
   DP方程: F(n)=F(n-1) + F(n-2)
   思考：a. 假设每次可以上1，2，3级 (easy)
         b. 还是1、2、3，如果相邻两步的步伐不能相同  (medium)
2. https://leetcode-cn.com/problems/triangle/description/ 三角形最小路径和
   a. brute-force，递归，n层：left or right: 2^n
   b. DP
      a) 重复性（分治） problem(i,j) = min(sub(i+1,j),sub(i+1,j+1)) + a[i,j]
      b) 定义状态数组: f[i,j]
      c) DP方程: f[i,j] = min(f[i+1,j],f[i+1,j+1]) + a[i,j]
      d）代码:
      //python:
      class Solution:
         def minimumTotal(self,triangle):
            """"""
              :type triangle: list[list[int]]
              :rtype: int
            """"""
            dp = triangle
            for i in range(len(triangle)-2, -1, -1):
               for j in range(len(triangle[i])):
                   dp[i][j] += min(dp[i+1][j],dp[i+1][j+1])
            print(triangle[0][0])
            return dp[0][0]
      //c++:
      //坏处是什么？在工业级代码里面这样写一般来说是不行的，因为你给它传一个数组进来的话，你直接改到这个参数里面，最后的值人家给你一个triangle，你直接把它triangle里面值全部改得物是人非。这样的话其实在工业级代码里面是在埋坑的。
      //除非你自己copy一份出来，一般是这样的；或者你就在这个函数里面写明白了，你穿进来的数组我会改变这么一个副作用。
      class Solution:
      public:
      int minimumTotal(vector<vector<int>> triangle):
          for(int i=triangle.size()-2;i>=0;--i){
              for(int j=0;j<triangle[i].size();++j){
                  triangle[i][j]+=min(triangle[i+1][j],triangle[i+1][j+1]);
               }
          }
      return triangle[0][0];
      //java:
      class Solution{
           public int minimumTotal(List<List<Integer>> triangle){
                int[] A = new int[triangle.size()+1];
                for(int i=triangle.size()-1;i>=0;--i){
                    for(int j=0;j<triangle.get(i).size();++j){
                        A[j]=Math.min(A[j],A[j+1])+triangle.get(i).get(j);
                    }
                 }
                 return A[0];
           }
      }
      e) 三角形最小路径和高票回答：   https://leetcode.com/problems/triangle/discuss/38735/Python-easy-to-understand-solutions-(top-down-bottom-up)
3. https://leetcode-cn.com/problems/maximum-subarray/  最大子序和  (非常高频)
   a. 暴力: n^2
   b. DP:
      a)分治（子问题） max_sum(i) = Max(max_sum(i-1),0) + a[i]
      b)状态数组定义: f[i]
      c)DP方程: f[i] = Max(f[i-1],0) + a[i]
      
   c. 分治法：时间复杂度:O(nlongn)
   d. 代码
   class Solution(object):
      def maxSubArray(self, nums):
         ``````
              1. dp问题，公式为: dp[i] = max(nums[i],nums[i]+dp[i-1])
              2. 最大子序和 = 当前元素自身最大， 或者包含之前后最大
         ``````
         dp = nums
         for i in range(1,len(nums)):
            //nums[i-1]代表dp[i-1]
            dp[i] = nums[i] + max(0,dp[i-1])
         return max(dp)
      //nums移步变换也可以，或者用sum
   https://leetcode-cn.com/problems/maximum-product-subarray/description/ 乘积最大子数组
      其实和前面异曲同工之处，但是稍微复杂的一点就是在于和的话，如果是小于0的话就丢掉就行了嘛，积这里的话如果是小于0的话就不能丢掉了，最大乘积的话如果是小于0的话就不能丢掉，因为可能负负得正，所以你要把最负的数保留下来，也要把最正的乘积保留下来。
      因为最负的乘积的话在后面要是负负得正的话，它的绝对值反而有可能是大的，因为这个所以大家去看一下。
      代码：
      class Solution:
            def maxProduct(self, nums: List[int]) -> int:
               mi = ma = res = nums[0]
               for i in range(1,len(nums)):
                   if nums[i]<0 : mi ,ma = ma, mi
                   ma = max(ma * nums[i], nums[i])
                   mi = min(mi * nums[i], nums[i])
                   res = max(res,ma)
               return res
       只要把最正的记下来，同时要把最负的记下来即可。

4. https://leetcode-cn.com/problems/coin-change/description/  零钱兑换  （滚瓜烂熟）
   为什么反复讲？就是它这个题目本身的话可以有很多种变换，同时它是理解的基础，你把这个题目搞的滚瓜烂熟一样，就类似于它的基石，最后可以组成一个很大的思维逻辑的框架，这样的话你就把这个算法的话练得越来越薄，或者是说练得越来越简单。
   假设因为有最少这个样子，所以这里的额话有最佳的子结构。
   把这个问题稍微再泛化一点，就是不是最少的硬币个数，问你有多少不同的组合方式。假设是不同的组合方式，这个题目就变成了什么题目。之前就给大家讲了无数次了，这个题目其实就等于上楼梯问题，上楼梯问题就是你一次可以走一步，或者一次可以走两步，问有多少不同的走法，走到第n级台阶去。
   就类似于在这里，我每次可以走一步，或者每次可以走两步，或者每次可以走五步，我要上到第11级台阶去，我有多少种不同的走法。所以这里的coin change问题和之前的爬楼梯问题其实是异曲同工之处。当然稍微有点不一样，假设我121的话是不同的走法，但是coin change的话，你121和112的话都是相同的硬币组合，这里顺序稍微有点变化，但其实你与DP的话就差不多了。这么一个转换的思路大家一定要记得
   a. 暴力法： 递归： 指数级的时间复杂度   sum越来越少，把所有不同的路径都给它求出来了
   coin change的状态树见视频
   它的叶子结点是什么？叶子结点就是到0的话表示什么？表示刚好凑对了，凑对的话就说明我们得到一组解，得到一组解的话，我们就把这个结果往上传，最后的话就得到什么，我们现在要的是最优的，就是硬币最少的一种情况。我们就把最少的情况最后比较出来。还有一种情况他的叶子结点是负了，是负表示什么情况就是凑不到，凑不到就是非法的，我们就在这里停掉即可，就第一次变负的结点我们就停掉，说明不需要再走了。
   这时候你会发现这棵树的深度，表示用的硬币的个数。每一个边代表用了一个硬币，那树的层次的话就表示已经用了几个硬币。
   那么这个题目的话其实就变换成为一个什么？在这棵状态树里面找到结点为0的结点，且是层次最低的，就是它的深度要最小
   b. BFS: 把它转换成一个递归的树之后，就可以直接用广度优先遍历来求解，第一次碰到这个结点，就是它深度最浅的，这个深度的话就是用的硬币数
   c. DP: 
      a) subproblems
      b) DP arrays: f(n) = min{f(n-k), for k in [1,2,5]} + 1
      c) DP方程
      代码:
      public class Solution{
          public int coinChange(int[] coins, int amount){
             int max = amount + 1;
             int[] dp = new int[amount+1];
             Arrays.fill(dp,max);
             dp[0] = 0;
             for(int i=1;i<=amount;i++){
                for(int j=0;j<coins.length;j++){
                   if(coins[j]<=i){
                      dp[i] = Math.min(dp[i],dp[i-coins[j]]+1);
                   }
                }
             }
             return dp[amount] > amount ? -1 : dp[amount];
          }
      }
      
      
      
   d. 官方题解仔细看
   

5. https://leetcode-cn.com/problems/house-robber/  打家劫舍  (非常经典高频)
   那么在这里的话给大家带着大家一步一步思考，DP它的状态应该怎么来创立？
   首先按照之前的经验，那么就直接设成a[i]就行了，当然你也可以叫dp[i]也一样的，如果你看他们的评论或者题解的话，很多人还用其他的名字都行，那么下标就是i表示的话，表示偷第几个房子的状态。
   按照之前给大家总结的，如果大家在定义状态的时候，可以它已经有数组的话，那么就根据这个数组的不同的位置，就定义它这个维度它的状态就行了
        a[i]  : 0...i 能偷到 max value 
   表示什么呢？从0到i这么多房子里面，能够偷到的最大的值是多少？能够偷到最大的值就放在这个地方。
   关键的问题来了，它这个DP方程是什么？
   //DP方程

   也就是说我的dp[i]应该等于dp[i-1]，再怎么推导（a[i]=a[i-1]），再加个什么东西（a[i]=a[i-1] + ...），或者是这中间如果要有个函数（a[i]=f(a[i-1])），这个函数会是什么样子？
   首先我要讲在这里的话，肯定要把第i个房子用上来，因为这里的是a[i]对吧。我们如果只是简单地把第i个房子里面的钱累加进来（a[i]=a[i-1] + nums[i]），你们看觉得这样行不行，这个时候你肯定会想应该是不行的，因为如果第i-1个房子没有被偷的话，那么的确可以打劫第i个房子，那么你把里面的钱可以抢下来，加到a[i]里面去对吧。但是这里的问题是你的i-1可能这个房子也被偷了，在这个时候的话，你的nums[i]就不能被偷了，在这种情况下。所以在这里能否被加的情况下，要取决于前面的i-1这个房子时候被偷。
   那么我们怎么知道这个房子有没有被偷呢？这个问题首先我再给大家直接说一个我觉得比较干脆的一个解决方式，这里有两种解决方式
   我们先讲解法一：
   解法一，我觉得是相对比较正统的一种思维方式，它是怎么思考的，在这个状态（a[i]）这里，因为我们只记录了它的最大值，而不知道当前这个房子到底是不是被偷，那么按照之前讲的，经常我们做的一个方法就是再在里面拓展一个维度，这一维的信息用来存你需要的额外的信息， 比如说我这里就需要第i个房子到底是否是被偷对吧，那么我们再加一维，这一维的话它的取值就是0或者1只有两个值。
          0表示什么？0表示不偷这个房子，那么1就表示当前这个房子我来偷了。
               a[i][0,1]  : 0...i 能偷到 max value 
               0：不偷， 1：偷
          那么根据这个样子的话，我们的状态方程就直接变成
              a[i][0] = max(a[i-1][0],a[i-1][1])
              a[i][1] = a[i-1][0] + nums[i]
          这里的话i这里的话就有可能一个是0，一个是1。对吧，这样的0和1。那么如果我们看如果第i个房子是不偷的话，那就不用加这个i-1对吧，它的值应该由什么推导出来，这时候你会发现它的侯选值就变成有两个了，一个是a[i-1][0]，还有一个是a[i-1][1]，表示第i-1个房子的最大值，同时第i-1个房子不被偷的话是什么样子，同时第i-1个房子也可以被偷也行，对吧。但是你只能选其一，同时我们要选最大的值。所以它的动态规划的转移方程就是这个形式的（a[i][0] = max(a[i-1][0],a[i-1][1])）。
          那么如果第i个房子我要偷的话，是怎么弄，如果我要偷的话，这时候这个地方就不能选1了，因为你i-1个房子就不能偷了，因为你相邻的房子是不允许偷的，所以这里只能从0过来，同时因为我偷了第i个房子，所以我可以把第i个房子的钱也就是nums[i]加上，所以它的DP方程就变成了这样一种形式（a[i][1] = a[i-1][0] + nums[i]）
          再过一遍，因为我多加了一维，所以在DP方程里的话，你既要推导出0的状态，也要推导出1的状态，接下来我们就来看[i][0]从哪里来呢，从[i-1][0]或者[i-1][1]来，但是这里注意和nums[i]就没有关系了。因为我不偷第i个，正是因为不偷第i个的话，所以i-1的话我可以不偷也可以偷，从这里面我可以选择一个最大值赋过来（a[i][0] = max(a[i-1][0],a[i-1][1])）。
                   如果我要偷第i个，那么i-1的话我就只能不偷，只能不偷之后，好处就是我能把第i个房子的值给拿过来放这里，这就是动态转移方程（a[i][1] = a[i-1][0] + nums[i]）。已经给大家复述了一遍，希望大家能够这块理解比较透彻了。
          代码:
          class Solution{
                public int rob(int[] nums){
                   if(nums==null||nums.length==0) return 0;
                   int n = nums.length;
                   int[][] a = new int[n][2];
                   a[0][0] = 0;
                   a[0][1] = nums[0];
                   for(int i=1;i<n;i++){
                      a[i][0]=Math.max(a[i-1][0],a[i-1][1]);
                       a[i][1]=a[i-1][0] + nums[i];
                    }
                     return Math.max(a[n-1][0],a[n-1][1]);
                }
          }
          它整个是O(n)也就是线性的时间复杂度，空间复杂度，因为申请了一个数组，所以的话，它的空间复杂度也是O(n)的
   解法二，那么接下来和大家想动态规划的状态，可不可以再简化一点，以及DP方程能不能再简化一点呢？答案肯定是可以的，那么我们来看怎么来简化，那么之前的话我多加了一维的状态，就是要来解决第i个房子到底偷还是不偷，不然的话你没法确定的，
          但其实我们也可以不用确定，不用确定的话怎么来，那么我们还是把它第二维给删掉了之后，a[i]表示从0到i偷到的最大值，同时这里我们可以说nums[i]，就是第i个房子可偷也可不偷。这里显示写在这里，可能有些同学觉得这是一句废话，但是我只想强调，如果我这么一定的话，表示第i个房子我可以是偷的，也可以是不偷的，你不用管，
          但是我表示的是从0到第i个房子，所有情况下我能够达到偷到的最大值，好吧，我是这么定的，从0到第i个房子够偷到它的最大值，第i个房子可偷也可不偷，你没法知道我偷不偷。我们接下来看DP方程怎么搞？
                a[i] = Math.max(a[i-1],nums[i] + a[i-2])
          DP方程我就直接放过来给大家看了。那么DP方程的话，我们稍微再多思考一下，我们就会发现它来自于a[i-1]，表示什么，表示因为这里没有加任何东西，你就可以认为它是加0(a[i] = Math.max(a[i-1] + 0,nums[i] + a[i-2]))，加0表示什么，我第i个房子不偷，那么我可以从第i-个的最大值直接挪过来，同时我如果我如果第i个房子我要偷的话，假设第i个房子我明确要偷，这个时候我就不能从i-1过来了，我就直接从i-2过来。
          基于这个状态转移方程的话，我们也可以把上面这个程序给写出来，那么在这里的话，我把这个程序直接拿过来了
          class Solution{
             public int rob(int[] nums){
                if(nums==null||nums.length==0) return 0;
                int n = nums.length;
                if(n==1) return nums[0];
                int[] dp = new int[n];
                dp[0] = 0;
                dp[1] = Math.max(nums[0],nums[1]);
                for(int i=2;i<n;i++){
                    dp[i]=Math.max(dp[i-1],dp[i-2]+nums[i]);
                }
                return dp[n-1];
              }
           }
   解法三，同理和Fibonacci问题一样，这时候你会发现我要递推dp[i]的话，我只需要dp[i-1]和dp[i-2]，它的时间复杂度O(n)和空间复杂度O(n),但是因为按照之前Fibonacci数列的问题的话，递推只需要i-1和i-2，说明我们不需要这么长的一个数组，我们只需要两个值就行了，接下来可以来到最终的化简版本
          见自己写的



   https://leetcode-cn.com/problems/house-robber-ii/description/  打家劫舍 II 
        见Krahets的讲解:https://leetcode-cn.com/problems/house-robber-ii/solution/213-da-jia-jie-she-iidong-tai-gui-hua-jie-gou-hua-/




   https://leetcode-cn.com/problems/house-robber-iii/   打家劫舍 III  高级动态规划才讲
##二、学习总结：