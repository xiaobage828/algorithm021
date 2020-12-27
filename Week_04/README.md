#第四周 
##一、学习笔记
###9.1深度优先搜索和广度优先搜索
####搜索-遍历
比较简单朴素的搜索，很多时候所有结点遍历一次，找到你所要的结果
我们要实现这样一个遍历或者搜索的话，毫无疑问我们要保证的事情就是
- 每个节点都要访问一次
- 每个节点仅仅要访问一次（就是代表我们在搜索中，我们不想做过多无用的访问，不然的话，我们的访问的效率会非常的慢，会非常的低）
- 对于节点的访问顺序不限
  深度优先： depth first search
  广度优先:  breadth first search
  优先级优先:  priority-first search  (其实更加适用于现实中的很多业务场景，而这样的算法我们一般把它称为启发式搜索。而这个估价函数以及搜索的整个效率的问题的话，就已经超出了我们这门课的范畴，而更多的是深度学习，
                                      而这种比如说优先级优先的话，在很多时候现在已经应用在各种推荐算法和高级的搜索算法，让你搜出你自己最感兴趣的内容，以及你每天打开抖音，打开快手的话，就给你推荐你最感兴趣的内容，其实就是这个原因)
这个的话深度优先和广度优先的话，其实就是简单的像for loop、recursion一样，就是各种约定俗成和常用的一些写法，大家把它记住就行

#####深度优先遍历 Depth-First-Search
queue加循环实现
DFS代码模板
- 递归写法：
一开始是递归的终止条件，然后再处理当前层，然后再下钻，那么处理当前层就相当于访问了结点node，然后把这个node加到已访问的结点里面去，那么终止条件的话就是，如果这个结点之前已经访问过了，那就不管了。
下钻的话，就做到它的子结点去，它的子结点一般来说我们是二叉树的话，就是左孩子和右孩子，如果是图的话，就是它的连通的相邻结点，可以这么认为，如果是多叉树的话，这里就是一个children，然后把所有的children遍历一次
这就是所谓的深度优先的算法
如果是DFS的话，这棵树，先走下来走到底，再回来再从这里走到底，再回来，再走这个分支走到底，然后再回来再从这边走到底
//Java
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> allResults = new ArrayList<>();
        if(root==null){
            return allResults;
        }
        travel(root,0,allResults);
        return allResults;
    }
    private void travel(TreeNode root,int level,List<List<Integer>> results){
        //递归终止条件:到达最底层的叶子结点
        if(results.size()==level){
            results.add(new ArrayList<>());
        }
        //处理当前层
        results.get(level).add(root.val);
        //下钻
        if(root.left!=null){
            travel(root.left,level+1,results);
        }
        if(root.right!=null){
            travel(root.right,level+1,results);
        }
    }

//Python 二叉树
visited = set() 

def dfs(node, visited):
    if node in visited: # terminator  保证新加入的结点是没有访问过的
    	# already visited 
    	return 
	visited.add(node) 
	# process current node here. 
	# ...# logic here
	dfs(node.left,visited)
	dfs(node.right,visited)

//Python 多叉树，遍历顺序见视频动态图
visited = set() 

def dfs(node, visited):
    if node in visited: # terminator
    	# already visited 
    	return 
	visited.add(node) 
	# process current node here. 
	...
	for next_node in node.children(): 
		if next_node not in visited: 
			dfs(next_node, visited)

//C/C++
//递归写法：
map<int, int> visited;

void dfs(Node* root) {
  // terminator
  if (!root) return ;

  if (visited.count(root->val)) {
    // already visited
    return ;
  }

  visited[root->val] = 1;

  // process current node here. 
  // ...
  for (int i = 0; i < root->children.size(); ++i) {
    dfs(root->children[i]);
  }
  return ;
}

//JavaScript
const visited = new Set()
const dfs = node => {
  if (visited.has(node)) return
  visited.add(node)
  dfs(node.left)
  dfs(node.right)
}

- 非递归写法：就是手动维护一个栈，把它加进去就行
其实就是模拟一个递归，用栈来进行表示
//Python
def DFS(self, tree): 
	if tree.root is None: 
		return [] 
	visited, stack = [], [tree.root]
	while stack: 
		node = stack.pop() 
		visited.add(node)
		process (node) 
		nodes = generate_related_nodes(node) 
		stack.push(nodes) 
	//other processing work 
	...

//C/C++
//非递归写法：
void dfs(Node* root) {
  map<int, int> visited;
  if(!root) return ;

  stack<Node*> stackNode;
  stackNode.push(root);

  while (!stackNode.empty()) {
    Node* node = stackNode.top();
    stackNode.pop();
    if (visited.count(node->val)) continue;
    visited[node->val] = 1;
    for (int i = node->children.size() - 1; i >= 0; --i) {
        stackNode.push(node->children[i]);
    }
  }

  return ;
}

#####广度优先遍历 Breath-First-Search
广东优先遍历不再是用递归，也不再是用栈了，而是用所谓的队列
所谓的广度的话，其实是一层一层一层地向下扩散。所谓的广度的话，把它想象成一个水滴，滴到1这个位置，然后它的水波纹一层一层一层扩散出去就行了，而不管是地震也好，冲击波也好，其他的水波纹都是按照广度优先遍历向外扩散，这在现实中也经常见
这种扩散的方式是更加符合，比如说人脑的思考方式，的确也这样，
而且在一些最短路求的过程中，比如说你从1走到这个结点，这个假设是4结点，从1走到4结点，最短可以走多少步。
用广度优先的，第一次到达4这个结点的话，就是最优的步数。如果是深度优先，还不好说，还需要多遍历几次
BFS代码模板
java里面用链表或者deque双端队列，python用 数组或者connection里面的deque
一开始队列为空，然后把开始结点加入到队列里面去，然后同时的话，也要维护这么一个，visited这个一个结点，
接下来的是只要队列不为空的情况下的话，就把这个结点往里面加就行了，然后process这个结点，
同时的话从这个结点扩散出它的周围结点，依次加到队列里面去，这样的话产生了一个效果，
就是对于这个队列的点，我们一个一个访问，同时因为队列本身是先入先出的，
所以它就会按照成层的这个结点的顺序，一个一个一个从队列里面取就行了，也就是说按照排队来看的话，这里产生了一个队列，排队的队列是queue
然后把最开始的结点放到队列里面去，然后每次从队列里面取了之后，把它的儿子结点再放到队列里面去，它就会按照这个层次一下走一下走，
就类似于最开始这个程序，我把公司的老总放到qeque里面，然后每次只要queue不为空的话，把老总取出来，对吧，然后拿到老总的相关结点，老总的相关结点就是VP和总监，
然后再把所有的VP和总监放这个队列里面去，然后再从队列里面拿，拿的话都是先入先出的，就接下来按照这个层级，把所有的人就一次捋了一遍，这么一个过程，
这个方法是最可取或者是最简洁的
//Java
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) {
        val = x;
    }
}

public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> allResults = new ArrayList<>();
    if (root == null) {
        return allResults;
    }
    Queue<TreeNode> nodes = new LinkedList<>();
    nodes.add(root);
    while (!nodes.isEmpty()) {
        int size = nodes.size();
        List<Integer> results = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            TreeNode node = nodes.poll();
            results.add(node.val);
            if (node.left != null) {
                nodes.add(node.left);
            }
            if (node.right != null) {
                nodes.add(node.right);
            }
        }
        allResults.add(results);
    }
    return allResults;
}


//Python
def BFS(graph, start, end):
    visited = set()
	queue = [] 
	queue.append([start]) 
	while queue: 
		node = queue.pop() 
		visited.add(node)
		process(node) 
		nodes = generate_related_nodes(node) 
		queue.push(nodes)
	# other processing work 
	...
	
// C/C++
void bfs(Node* root) {
  map<int, int> visited;
  if(!root) return ;

  queue<Node*> queueNode;
  queueNode.push(root);

  while (!queueNode.empty()) {
    Node* node = queueNode.top();
    queueNode.pop();
    if (visited.count(node->val)) continue;
    visited[node->val] = 1;

   for (int i = 0; i < node->children.size(); ++i) {
        queueNode.push(node->children[i]);
    }
  }

  return ;
}

//JavaScript
const bfs = (root) => {
  let result = [], queue = [root]
  while (queue.length > 0) {
    let level = [], n = queue.length
    for (let i = 0; i < n; i++) {
      let node = queue.pop()
      level.push(node.val) 
      if (node.left) queue.unshift(node.left)
      if (node.right) queue.unshift(node.right)
    }
    result.push(level)
  }
  return result
};


###9.2深度优先搜索和广度优先搜索实战题目解析：二叉树的层次遍历等问题






###10.贪心算法Greedy
####贪心算法
是一种在每一步选择中都采取在当前状态下最好或最优（即最有利）的选择，从而希望导致结果是全局最好或最优的算法。
注意：
最后它结局是希望导致全局最好或者最优，但是它为了达到这样的效果，它采用的办法就是在当下选择最好或者最优的，
那么在这样一种决策的过程中，经常就会有同学会思考，每次我当下选择最好的，会不会全局就是最优的呢，以及我们中文中有很多的成语，就来解释这样的情况，比方说只顾眼前，或者不考虑未来，或者说可能极端一点的成语，叫鼠目寸光之类，
那么这也是造就了这种方法的话，其实有一定的局限性。
我想大部分人其实也就知道用贪心算法的话，尤其是最基础的贪心，每次当下情况下找最优不一定能够达到全局最优的情况，
但是在某些时候可以，在这些时候的话，我们就可以用所谓的贪心算法。
以及在很多情况下的话，贪心算法在某一步可以用贪心，然后在全局的话再加一个搜索递归，或者是动态规划类似于这种

- 贪心算法与动态规划的不同在于它对每个子问题的解决方案都做出选择，不能回退。
动态规划则会保存以前的运算结果，并根据以前的结果对当前进行选择，有回退功能。

#### 贪心、回溯、动态规划的区别
贪心：当下做局部最优判断
回溯：能够回退
动态规划：最优判断+回退
贪心对子问题解决办法的话，就直接在当下做出最粗暴的选择，也就局部最优的选择，同时是不能回退的。如果能回退的话，其实就是回溯，以及带最优判断的回溯的话，我们就叫做动态规划

贪心法可以解决一些最优化问题，如：求图中的最小生成树、求哈夫曼编码等。
然而对于工程和生活中的问题，贪心法一般不能得到我们所要求的答案。
一旦一个问题可以通过贪心法来解决，那么贪心法一般是解决这个问题的最好办法。由于贪心法的高效性以及其所求得的答案比较接近最优结果，贪心法也可以用作辅助算法或者直接解决一些要求结果不特别精确的问题。
比如最小生成树，比如说Dijkstra里面都会用一些所谓的辅助的办法用贪心来进行解决

#### 实战题目
Coin Change特别版本：
https://leetcode-cn.com/problems/coin-change/
当硬币可选集合固定： Coins = [20,10,5,1]
求最少可以几个硬币拼出总数。比如total=36
为什么可以用贪心法？就是因为这个硬币的话，20 10 5 1前面的硬币，依次是后面这些硬币的倍数，所以如果你需要用两个10或者四个5的话，你肯定还不如用一个20，因为后面这些的话都是整除前面最大的硬币的，
在这种情况下的话，那么可以从数学上来证明贪心法的话，就每次用最大的即可。因为既然你能用20的话，你要是选后面的话，肯定后面这些会不优于直接选20的情况。那么很多时候，你会发现在这种特殊的情况下，贪心法是成立的
这里用贪心法的一个特点，就是有它所谓的特殊性，这里的特殊性指的是，这个硬币有整除的关系，所以用贪心法

但是在大部分情况下的话，其实是不能用贪心法的
贪心法的反例
非整除关系的硬币，可选集合：Coins=[10,9,1]
求拼出总数为18最少需要几个硬币？

所以由此可以看出你要用贪心算法的话，第一问题比较特殊，第二的话你需要能够证明，这个地方用最简单粗暴的贪心算法，是能够得到最优解的

####适用贪心算法的场景
何种情况下用到贪心算法？首先我们看适用贪心算法的情况：
简单地说，问题能够分解成子问题来解决，子问题的最优解能递推到最终问题的最优解。这种子问题最优解称为最优子结构。

- 贪心算法与动态规划的不同在于它对每个子问题的解决方案都做出选择，不能回退。
动态规划则会保存以前的运算结果，并根据以前的结果对当前进行选择，有回退功能。

#### homework
基本上它的算法的思路都是异曲同工的，贪心法的难就难在你怎么证明，它是可以用贪心法的，以及贪心的角度可能不一样，
有些时候你可以正常去用贪心，有些时候你必须把问题稍微转化一下，再进行所谓的贪心求解，
或者有些时候你必须从前往后贪，这是比较常规的套路，但有些时候你可能是从后往前进行贪心，
而所谓的贪心的套路以及证明傻贪心是可以得到最优解的过程，反而更加的重要
1. assign-cookies:讲解，排序后依次把最小的胃口先用最小的饼干去满足，然后依次满足下来即可
这里可以看到一般如果贪心可以用来解决问题的话，那么贪心的话它解决的思路的话是比较自然的，而且程序的话一般来说是相对比较好写的，算法复杂度肯定也是最优的，
因为你每一个局部都用最优解的话，那么你可以抛掉所有的它的次优解，你也不用存储它所有的中间结果，你也更不需要什么回溯分治，然后把它的状态树全部遍历一次，
所以一般来说贪心算法的话，即使你的递归功底很差的话，也一般可以写正确贪心的题目，
但是关键就是在于真正在面试或者现实生活中很少有贪心的
举个现实例子，你每天只满足自己当下的需求，比如你觉得游戏好玩就去打游戏，或者饿了就饱餐一顿，最后发现长期达不到最优解，比如时间就浪费了...
2. best-time-to-buy-and-sell-stock-ii/最佳买卖股票时机:讲解，回溯、动归（最典型），贪心：只要后一天比前一天大，那么你就在前一天买，在后一天抛就行
3. lemonade-change
4. walking-robot-simulation
5. jump-game:讲解，穷举（暴力搜索）、类似Fibonacci数列O(n^2)，贪心查找:从后往前贪心O(n)
   为什么是贪心？就是因为只要记能够跳到最后的那个位置的第一个值，就是只要记这么一个最前者的值，那就节省了一个数组来记录中央的结果，当然也可以节省一层循环，所以它最后是O(N)的时间复杂度，这个时间复杂度是最优的
   关键巧妙的地方是，它是从后往前进行所谓的循环或者叫贪心查找
    jump-game-ii
    
    
###11.二分查找
这里最关键是化繁为简，首先我们讲二分查找最关键的三个前提条件
####二分查找的前提
1. 目标函数单调性(单调递增或者递减)：二分查找指的是在有序的里面进行查找，如果它是无序的话就没法进行二分查找。无序的话，只能从头到尾遍历，正因为是有序的，所以能够通过判断它的某些特征排除掉，比如说前半部分或者排除掉后半部分
2. 存在上下界(bound)：如果没有上下界的话，那么它的空间可能是无穷大的，那么它就没法往中间扩。当然有一种特殊形态，后面我们继续讲，但是出现的情况少之又少的
3. 能够通过索引访问(index accessible)：很多时候如果是单链表的话，相对来说即使是有序的，单链表进行二分查找都不是那么容易的。当然如果把单链表进行改造，比如说用它所谓跳表的方式，那就另当别说了
####代码模板（一定要写熟）
面试人的时候，最大的问题是写二分的话，经常容易一下子懵了，不知道如何起笔，因为它们概念都知道，但是你要起笔的话就很难，或者是他的代码里面多多少少有一些bug
所以在这个时候一定要把这个代码模板，写得非常熟练，
就一开始就把代码模板全部都打完了，打完了之后再把这些值全部都填好，
根据你最主要的数组是什么，以及它的左右界到底是小于等于的，还是在这里要加一减一，还是不用，再进行微调，最后反复地验证是没有问题的
一定要把代码模板写得非常熟练，
// Java
public int binarySearch(int[] array, int target) {
    int left = 0, right = array.length - 1, mid;
    while (left <= right) {
        mid = (right - left) / 2 + left;//或者 mid = (left+right) >>> 2
        if (array[mid] == target) {
            return mid;
        } else if (array[mid] > target) {
            right = mid - 1;
        } else {
            left = mid + 1;
        }
    }
    return -1;
}
首先左右界分别是0和数组长度减1，也就是左右的下标值，这个毋庸置疑，
while的话里面是left小于等于right，所以这个条件的话有些时候，会变成没有等于号，但是大部分情况下你可以认为是有小于等于的，
然后得到它的中间值，就是mid这个值，判断mid是否等于等于target，然后来break或者是return这个result，可以先把等于等于放在这里，只要它等于的话就马上return即可
在这里的话，我们假设这个数组是上升的就是升序排列的，
如果target大于array[mid]的话说明什么，说明它在右侧，那么要继续向右查找，所以left就把左界向右进行移动，变成mid+1了
否则的话说明在这左侧，那么右界的话就要向左移动，变成mid-1像这么一个形式
那么根据这里的话是一种所谓的左下界和右下界为整型的情况下，就是为Integer的情况下，在有些时候可能为实数的情况下，那么就没有所谓的加一和减一，在这个地方就直接等于mid即可
另外在有些特殊的情况下，这里直接是小于的这种情况下，
后面在例题的时候会给大家一个更深刻的了解
//jdk8 实现
public static int binarySearch(long[] a, long key) {
        return binarySearch0(a, 0, a.length, key);
}

 public static int binarySearch(long[] a, int fromIndex, int toIndex,
                                   long key) {
        rangeCheck(a.length, fromIndex, toIndex);
        return binarySearch0(a, fromIndex, toIndex, key);
 }

private static int binarySearch0(long[] a, int fromIndex, int toIndex,
                                     long key) {
        int low = fromIndex;
        int high = toIndex - 1;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            long midVal = a[mid];
            if (midVal < key)
                low = mid + 1;
            else if (midVal > key)
                high = mid - 1;
            else
                return mid; // key found
        }
        return -(low + 1);  // key not found.
 }

/* JavaScript */
let left = 0, right = len(array) - 1
while (left <= right) {
  let mid = (left + right) >>> 1  //这里是无符号右移
  if (array[mid] === target) { /*find the target*/; return }
  else if (array[mid] < target) left = mid + 1
  else right = mid - 1
}

C/C++

int binarySearch(const vector<int>& nums, int target) {
	int left = 0, right = (int)nums.size()-1;
	while (left <= right) {
		int mid = left + (right - left)/ 2;
		if (nums[mid] == target) return mid;
		else if (nums[mid] < target) left = mid + 1;
		else right = mid - 1;
	}
	return -1;
}

// Python
left, right = 0, len(array) - 1 
while left <= right: 
	  mid = (left + right) / 2 
	  if array[mid] == target: 
		    # find the target!! 
		    break or return result 
	  elif array[mid] < target: 
		    left = mid + 1 
	  else: 
		    right = mid - 1
####二分查找的形式
见视频
它是由左右两个边界不断地向中间进行夹逼的过程，这种夹逼的过程又由于这个数组本身它是单调递增的，所以我们每次可以排除一半，
你可以认为有点像二叉搜索树一样的特性，但是这里的话它是用所谓的数组来进行实现的，而二分查找本身这种有序性的话，很多时候在数学里面的话用得也特别多

####实战题目
1. sqrtx：必会
方法1： 二分查找
y = x^2, (x>0)：抛物线，在y轴右侧单调递归：上下界
long防止越界 ，left==right返回结果
方法二：牛顿迭代法  在现实中用得更多的是牛顿迭代法，而不是所谓的二分查找
https://www.beyond3d.com/content/articles/8/   Fast InvSqrt() 扩展阅读   雷神之锤3的引擎里面  John Carmack
这个平方根分之一为什么他要这么写，就是因为平方根分之1的话，在3D引擎里面特别是涉及到矩阵变幻、坐标变幻、平方根分之一用得特别多
因为的话你求两点之间或者三点之间的距离的话，经常是用x平方再用y平方开根号嘛，所以它的根号然后分之1的话用得很多，在这里就把这个加速
magic number
//python 
class Solution(object):
  def mySqrt(self,x):
    r = x
    while r*r > x:
       r = (r+x/r) /2
    return r
也就是不断地迭代当前的值r，迭代的方式就是r再加x除以r，然后除以2，最后要判断r乘以r的话是小于等于x的，说明什么，第一次到达了它的平方根的整数部分它的整数部分肯定是小于等于x就这样
因为r初始值是x，所以x再乘以x肯定是大于x的，只要它大于x的话，就不断地循环，直到r再乘以r的话，是小于等于x的，就得到它的解，当然等于最好，等于就直接把这个平方根拿到了，如果是小于的话，说明平方根不是一个整数，我们就得到整数部分
    
2. valid-perfect-square

####Homework
1. search-in-rotated-sorted-array搜索旋转排序数组（Facebook、字节跳动、亚马逊在半年内面试常考）：半有序  判断左半部分还是右半部分单调递增
-暴力：还原O(logN) ->升序 ->二分 :O(logN) (写、总结)
-正解：二分查找  单调、边界、index
2. search-a-2d-matrix搜索二维矩阵（亚马逊、微软、Facebook 在半年内面试中考过）
-化成一维数组进行二分查找，
-当然也可以从右上角或者在左上角进行一个线性的查找也行
3. find-minimum-in-rotated-sorted-array寻找旋转排序数组中的最小值（亚马逊、微软、字节跳动在半年内面试中考过）
4. 使用二分查找，寻找一个半有序数组 [4, 5, 6, 7, 0, 1, 2] 中间无序的地方



 ##二、学习总结：
 ###1.效果、感受
 本周已跟上课程安排进度稍慢，视频均已看完并做了笔记，但是感觉第4周内容比前面的难。
 ###2.学习过程
 先看录播，边看录播边做笔记，把笔记和每周的学习总结放到一个markDown文档里面，然后着手习题，用习题来检验学习效果。
 ###3.收获
 这四周对数组、链表、队列、栈、堆、图、递归、分治、回溯、dfs、bfs、贪心算法、二分查找均有一定掌握，尤其第3周、第4周的代码模板有掌握，但是贪心算法、dfs、bfs、二分查找有待做更多题目来掌握。
 ###4.刷题笔记
 略