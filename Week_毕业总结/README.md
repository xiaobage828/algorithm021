# **算法训练营学习笔记**

```
5~15分钟思考，不会直接看题解（精选题解），学习题解方法 至少刷五遍
算法和数据结构存复杂度是客观存在
五毒神掌：先背、再默、隔日做、隔周做、面试前做
四步结题：审题（题目细节）、所有解法都思考一遍（得出最优解法）、写代码、测试样例（正常测试、边界条件、错误测试、变态条件）
```

## 1、时间复杂度

- Big O notation 运行次数

```java
     O(1)常数 < O(log n)对数 < O(n)线性 < O(n^2)平方 < O(n^3)立方 < O(2^n)指数 < O(n!)阶乘
```

``常见时间复杂度：``

-  二叉树的前序、中序、后续遍历的时间复杂度：O(n) 每个节点都只会遍历一次，n为节点个数
-  图的遍历时间复杂度：O(n) n为图中的节点总数
-  搜索算法DFS（深度优先）、BFS（广度优先）的时间复杂度：O(n) n为搜索空间中的节点总数
-  二分查找的时间复杂度：O(logn)

``递归算法计算时间复杂度``

- 主定理 https://zh.wikipedia.org/wiki/%E4%B8%BB%E5%AE%9A%E7%90%86

## 2、空间复杂度

- 占用空间

- 程序中若使用了数组，基本上数组的长度就是空间复杂度
  使用到了递归算法，那么空间复杂度就是递归的最大深度
  如果数组和递归都有使用，空间复杂度就取两者最大值

  ![image-20201208145427771](https://gitee.com/kingoooo/typora/raw/master/img/image-20201208145427771.png)

## 3、数组、链表、跳表

### 数组 Array

- prepend、append、lookup = O(1)，insert、delete = O(n)

- Memory Controller在内存开辟一块连续内存地址

- 通过Memory Controller随机访问任何一个元素，插入和删除需要挪动元素

- 有序数组用二分查找


### 链表  Linked List

- prepend、append、insert、delete = O(1)，lookup = O(n)
- 只有next指针是单向链表
- next、prev指针是双向链表
- 最后一个节点next指向头结点是循环链表

### 跳表  Skip List

- 元素有序
- 解决有序链表查询效率（升纬、空间换时间）
- 添加、删除、查询 = O(logn)，空间复杂度 O(n)
- 跳表索引的高度 logn
- 原理简单、容易实现，但维护成本高，添加、删除需要更新索引



## 4、栈、队列

### 栈 Stack

- 先进后出 FILO

- 添加、删除 = O(1)，查询 = O(n)

- 原理：

  ​	- extend Vector（线程安全）

  ​	- 底层实现：数组

- 用于解决最近相关性等问题

### 队列 Queue

- 先进先出 FIFO

- 原理：

  ​	- extend Collection

  ​	- 底层实现：数组、链表

- 用于解决先来后到等问题

- 双端队列 Deque

  ​	- 添加、删除 = O(1)，查询 = O(n)

  ​	- 队列前后都可以插入数据删除数据

  ​	- extend Queue

- 优先队列 PriorityQueue

  ​	- 添加、删除 = O(1)，取出= O(logN)，按照元素优先级取出

  ​	- 有序列表，存储到队列中的元素会按照自然顺序排序

  ​	- 实现java.util.Comparator接口排序类来指定元素排序的顺序



## 5、哈希表、映射、集合

- 哈希表（Hashtable），也叫散列表，是根据关键码值而直接进行访问的数据结构

- 原理：

  ​	- 关键码通过Hash函数映射到哈希表中一个位置

  ​	- 底层数据结构：数组 + 链表 + 红黑树

- Map：key-value，key不重复

  ​	- HashMap（JDK1.8）：

    - HashMap是基于哈希表（数组+链表+红黑树）的Map接口的实现，以key-value的形式存储

    - HashMap底层实现是table数组，数组的每一项由Node构成的一条链表

    - HashMap扩容：当HashMap中的元素的数量 >= size * 负载因子，且key对应table数组 != null 时，HashMap进行扩容处理(大小为原数组容量2倍)

    - 查找元素的时间复杂度：O(1)

    - 新增元素的时间复杂度：

      ​	- O(1)：直接插入数组中

      ​	- O(logn)：查找红黑树中的值比较

      ​	- O(n)：查找链表中的值比较

- Set：不重复元素的集合



## 6、树、二叉树、二叉搜索树

### 树 Tree

- 底层数据结构：链表
- 树的一般解法：递归（节点遍历重复性的操作）

### 二叉树 Binary Tree

- 儿子节点只有两个

- 遍历：

  ​	- 前序（Pre-order）：根-左-右

  ​	- 中序（In-order）：左-根-右

  ​	- 后序（Post-order）：左-右-根

### 二叉搜索树 Binary Search Tree

- 时间复杂度：O(logn)

- 空间复杂度：O(n)

- 特点：

  ​	- 左子树上所有节点的值均小于它的根节点的值

  ​	- 右子树上所有节点的值均大于它的根节点的值

  ​	- 以此类推，左右子树也分别为二叉搜索树



## 堆、二叉堆

### 堆 Heap

- 底层数据结构：数组
- 可以迅速找到一堆中的最大或最小值的数据结构
- 根节点最大的堆叫做大顶堆或大根堆
- 根节点最小的堆叫做小顶堆或小根堆

### 二叉堆

- 通过完全二叉树来实现

- 性质：

  ​	- 是一棵完全数

  ​	- 树中任意节点的值总是大于等于或小于等于其子节点的值

- 父节点和子节点的位置关系：

  ​	- 索引为i的做孩子的索引是：2*i+1

  ​	- 索引为i的有孩子的索引是：2*i+2

  ​	- 索引为i的父节点的索引是：floor((i-1)/2)

- 查询最大或最小：O(1)

- 插入操作：O(logn)

  ​	- 新元素一律插入到堆的尾部

  ​	- 一次向上调整整个堆的结构，直到根即可

- 删除堆顶操作：O(logn)

  ​	- 将堆尾元素替换到堆顶

  ​	- 依次从根部向下调整整个堆的结构，直到堆尾即可

- 优先队列 PriorityQueue

  ​	- 二叉堆小顶堆的实现

##7、递归 Recursion

- 通过函数体来进行循环
- Java代码模板

```java
public void recur(int level, int param) {
    // 递归终结条件
    if (level > MAX_LEVEL) {
        // process result
        return;
    }
    // 处理当前层逻辑
    process(level, param);
  	// 下探到下一层
    recur( level: level + 1, newParam);
  	// 清理当前层
}
```

- 思路：

  ​	- 将其拆解成可重复解决的子问题

  ​	- 数学归纳法思维

## 8、分治、回溯

- 是一种特殊的递归
- 找重复性以及分解问题和最后组合每个子问题的结果
- 最近重复性 -> 分治、回溯
- 最优重复性 -> 动态规划

### 分治 Divide & Conquer

- 终结条件
- 分解问题
- 下探到下一层， 合并子问题结果
- 清理当前层
- 代码模板

```java
private static int divide_conquer(Problem problem, ) {
    
         //第一步 recursion terminator：就是problem解决了，它其实的话本质上就是递归的层级，到了最下面这个层级，也就到了它的叶子结点，而叶子结点的话对于分治来说的话，一般来说它的叶子结点到达的标志就是在于这个子问题没有了，就没有问题需要再解决了
      
  if (problem == NULL) {
    int res = process_last_result();
    return res;     
  }
         
          
           //  prepare data：第二步就是处理当前逻辑，处理当前逻辑的话，其实就是把这个大问题看如何分成子问题。
           //举个例子来说，求n的阶乘，这里的话就是写成n再乘以fac(n-1)，就是把n单独乘出来，然后去开始去调n-1的阶乘。
           //如果是Fibonacci的话，就是在这里的话变成两个n-1的递归结果再加上n-2的递归结果，存在一个变量里面去，或者是组装右括号存起来，当然你要判断左右括号是否用完
           //如果是之前的我们来组合左右括号的话，那么在这里的话，就是分别组装左括号
  subProblems = split_problem(problem)
  
           //  conquer subprobleams：第三步就是dirll down，就是调用这个函数下探一层。这里虽然名字就divide conqer，然后subproblem，其实就是调这个函数下探到下一层，解决更细节的子问题
           //  把这个比喻成公司的组织结构里面，每一个不同层级的人，你就解决自己层级的问题。像CEO就高瞻远瞩，看公司的战略和方向。到了高层的话就把这些任务分成相应的部门。比如说财务、比如说技术、比如说产品
           //  再到下面的话，技术产品那些，比如说VP或者总监，他也不会自己做事情，很多时候，他就把任务进行进一步地拆分，分给相应的技术总监去处理，这是比较大的公司，
           //  小的公司就直接是主要的开发人员，开发组组长程序员组成之类的，然后分到相应的小组的组长，再把任务分给相应的一线干活的这些人，然后他们就完成这个活，最后再把这个结果组装起来
           //  这其实和公司或者国家的运作也差不多
           //  其实你可以看到一个复杂的问题，其本身的话它的解决方法是异曲同工的，不管是在现实生活中还是在程序里面。
  res0 = divide_conquer(subProblems[0])
  res1 = divide_conquer(subProblems[1])
  
          // meger(比泛型递归多的一步): 最后 的话就组装这个结果，然后返回来就行了
  result = process_result(res0, res1);
  
          //revert the current level states
          
  return result;
  
}
```

### 回溯 Backtracking

- 分步解决问题
- 分步解决答案不是有效的正确解答，取消上一步或上几步的计算
- 通过其他分步解决再次尝试寻找问题的答案
````回溯的公式
 result = []
 def backtrack(路径, 选择列表):
     if 满足结束条件:
         result.add(路径)
         return
     
     for 选择 in 选择列表:
         做选择
         backtrack(路径, 选择列表)
         撤销选择
````

## 9、深度优先搜索和广度优先搜索

- 搜索：每个节点仅仅访问一次
- 时间复杂度和空间复杂度：O(n)

### 深度优先搜索 Depth First Search

- 递归、栈实现搜索
- 递归实现代码模板

```java
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
```

### 广度优先搜索 Breadth First Search

- 队列实现搜索
- 队列实现代码模板

```java
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
```

## 10、贪心算法 Greedy

- 是一种在每一步选择中都采用在当前状态下最好或最优的选择，从而希望导致结果是全局最好或最优的算法
- 解决最优问题
- 贪心算法高效以及其所求得的答案比较接近最优结果，贪心算法可用作辅助算法或者直接解决一些要求结果不是很精确的问题
- 使用场景：最优子结构
- 贪心算法与动态规划的不同在于它对每个子问题的解决方案都做出选择，不能回退。动态规划则会保存以前的运行结果，并根据以前的结果对当前进行选择，有回退功能

## 11、二分查找 BinarySearch

- 二分查找的前提

  ​	- 目标函数单调性

  ​	- 存在上下界

  ​	- 能够通过索引访问

- Java代码模板

```java
public int binarySearch(int[] array, int target) {
    int left = 0, right = array.length - 1, mid;
    while (left <= right) {
        mid = (right - left) / 2 + left;
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
```

- 寻找一个半有序数组 [4, 5, 6, 7, 0, 1, 2] 中间无序的地方

```java
/**
 * 寻找半有序数组中间无序的地方 -> 寻找数组中最小值的index
 * 二分查找
 * 时间复杂度：O(logn)
 * 空间复杂度：O(1)
 */
public int findMinIndex(int[] nums) {
    int i = 0;
    int j = nums.length - 1;
    // 初始化最小值和最小值index
    int min = nums[0];
    int minIndex = 0;
    while (i <= j) {
        int mid = i + (j - i) >>>2;
        // 前半部分升序
        if (nums[i] <= nums[mid]) {
            // 前半部分第一个数与最小数取值min
            if (min > nums[i]) {
                min = nums[i];
                minIndex = i;
            }
            // 查找后半部分区间最小数和index
            i = mid + 1;
        } else {
            // 后半部分升序 后半部分第一个数与最小数取值min
            if (min > nums[mid]) {
                min = nums[mid];
                minIndex = mid;
            }
            // 查找前半部分区间最小数和index
            j = mid - 1;
        }
    }
    return minIndex;
}
```

## 12、动态规划 Dynamic Programming

- 将复杂的问题分解成简单的子问题，用一种递归的方式

- 分治 + 最优子结构

- 区别：

  ​	- 动态规划、递归、分治没有本质上的区别，关键看有无最优的子结构

  ​	- 共性：找到重复子问题

  ​	- 差异：最优子结构、中途可以淘汰次优解

- 动态规划关键点：

  ​	- 最优子结构 opt[n] = beat_of(opt[n-1], opt[n-2], ...)

  ​	- 存储中间状态：opt[i]

  ​	- 递推公式：

  ​		Fib：opt[n] = opt[n-1] + opt[n-2]

  ​		二维路径：opt[i,j] = opt[i+1,j] + opt[i,j+1]

## 13、字典树和并查集

### 字典树 Trie

- 字典树，即Tire树，又称单词查找树或键树，是一种树形结构。

- 优点是：最大限度地减少无畏的字符串比较，查询效率比哈希表高

- 性质：

  ​	- 结点本身不存完整单词

  ​	- 从根结点到某一点，路径上经过的字符连接起来，为该节点对应的字符串

  ​	- 每个节点的所有子节点路劲代表的字符串都不相同

- 核心思想：

  ​	- 空间换时间

  ​	- 利用字符串的公共前缀来降低查询时间的开销已达到提高效率的目的

- Java代码模板

```java
class Trie {
    private TrieNode root;
    public Trie() {
        root = new TrieNode();
    }
  
    public void insert(String word) {
        TrieNode ws = root;
        for(int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            if(ws.children[c - 'a'] == null){
                ws.children[c - 'a'] = new TrieNode();
            }
            ws = ws.children[c - 'a'];
        }
        ws.isWord = true;
    }
    
    public boolean search(String word) {
        TrieNode ws = root; 
        for(int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            if(ws.children[c - 'a'] == null) {
              	return false;
            }
            ws = ws.children[c - 'a'];
        }
        return ws.isWord;
    }
    
    public boolean startsWith(String prefix) {
        TrieNode ws = root; 
        for(int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if(ws.children[c - 'a'] == null) return false;
            ws = ws.children[c - 'a'];
        }
        return true;
    }
}
class TrieNode {
    public boolean isWord; 
    public TrieNode[] children = new TrieNode[26];
    public TrieNode() {}
}
```

### 并查集 Disjoint Set

- 适用场景：组团、配对问题

- 基本操作：

  ​	- makeSet(s)：建立一个新的并查集，其中包含s个单元数集合

  ​	- unionSet(x,y)：把元素x和元素y所在的集合合并，要求x和y所在的集合不相交，如果相交则不合并

  ​	- find(x)：找到元素x所在的集合的代表，可以用于判断两个元素是否位于同一个集合，各自代表是否相等

- Java代码模板

```java
class UnionFind {
    private int count = 0;
    private int[] parent;
    public UnionFind(int n) {
      	count = n;
      	parent = new int[n];
      	for (int i = 0; i < n; i++) {
        		parent[i] = i;
      	}
    }
    public int find(int p) {
      	while (p != parent[p]) {
        		parent[p] = parent[parent[p]];
        		p = parent[p];
      	}
      	return p;
    }
    public void union(int p, int q) {
      	int rootP = find(p);
      	int rootQ = find(q);
      	if (rootP == rootQ) {
        		return; 
        }
        parent[rootP] = rootQ;
        count--;
    }
}
```

## 14、高级搜索

### 剪枝

- 去重复
- 搜索过程中移除决策树中分辨能力较弱的部分而减少树大小的方法，降低复杂度

### 双向 Breadth First Search (BFS)

- 双向搜索算法是一种图的遍历算法，用于在有向图中搜索从一个顶点到另一个顶点的最短路径问题
- 算法同时运行两个搜索：一个从初始状态正向搜索，另一个从目标状态反向搜索，当两者在中间汇合时搜索停止

### 启发式搜索（A*）

- 代码模板

```python
def AstarSearch(graph, start, end):
  # 优先级 —> 估价函数
	pq = collections.priority_queue() 
	pq.append([start])
	visited.add(start)
	while pq:
    # can we add more intelligence here ?
		node = pq.pop() 
		visited.add(node)
		process(node)
		nodes = generate_related_nodes(node)
		unvisited = [node for node in nodes if node not in visited]
		pq.push(unvisited)
```

- 启发函数：h(n)，用来评价哪些节点最有希望的是一个我们要找的节点，h(n)会返回一个非负实数，也可以认为是从结点n的目标结点路径的估 计成本
- 启发式函数是一种告知搜索方向的方法

## 15、AVL树和红黑树

### AVL树

- AVL树是一个平衡二叉搜索树，每个节点左右两子树高度差不超多1

- Balance Factor（平衡因子）：节点的左子树的高度减去右子树的高度（有时相反），balance factor = {-1, 0, 1}

- 查找、插入、删除在平均和最坏情况下的时间复杂度：O(logn)，n是树中元素的数目

- 插入和删除操作可能需要一次或多次旋转实现树的重新平衡

- 四种旋转操作：

  ​	- 右右子树 —> 左旋

  ​	- 左左子树 —> 右旋

  ​	- 左右子树 —> 左右旋

  ​	- 右左子树 —> 右左旋

- 不足：节点需要存储额外信息、且调整次数频繁

### 红黑树

- 红黑树是一种近似平衡二叉搜索树，它能确保任何一个节点的左右子树高度差小于二倍

- 红黑树满足一下条件：

  ​	- 每个节点要么是红色，要么是黑色

  ​	- 根节点是黑色

  ​	- 每个叶子节点（NIL节点）是黑色

  ​	- 不能有两个相邻的红色节点

  ​	- 从任一个节点到其每个叶子节点所有路径都包含相同数目的黑色节点

- 关键性质：从根到叶子的最长的可能路径不多于最短的可能路径的两倍长

- 查找、插入、删除的时间复杂度：O(logn)，n是树中元素的数目

### AVL树和红黑树对比

- AVL树提供更快的查找比红黑树，因为AVL提供更严格平衡
- 红黑树提供了更快的插入和删除操作比AVL树，由于相对宽松的平衡，旋转次数较少
- AVL树每个节点需要存储平衡因子和高度，需要更多存储空间，而红黑树每个节点只需要用一个bit位来存储0和1表示红或者黑
- 大多数语言库都是使用的红黑树（Java1.8  Map），AVL树则用于需要更快检索的数据库

## 16、位运算

- 为什么需要位运算：机器里数字表示方式和存储格式就是二进制
- 常用位运算符：

| 含义 |            运算符            |
| :--: | :--------------------------: |
|  >>  |         左移（除2）          |
|  <<  |         右移（乘2）          |
|  \|  |             位或             |
|  &   |             位与             |
|  ~   |           按位取反           |
|  ^   | 按位异或（相同为零不同为一） |

- 异或操作：

  ​	- x ^ 0 = x

  ​	- x ^ 1s = ~x 	// 注意 1s = ~0

  ​	- x ^ (~x) = 1s

  ​	- x ^ x = 0

  ​	- c = a ^ b => a ^ c = b, b ^ c = a 		// 交换两个数

  ​	- a ^ b ^ c = a ^ (b ^ c) = (a ^ b) ^ c 	// associative

- 指定位置的位运算

  ​	- 将 x 最右边的 n 位清零：x & (~0 << n)

  ​	- 获取 x 的第 n 位值（0 或者 1）： (x >> n) & 1

  ​	- 获取 x 的第 n 位的幂值：x & (1 << n)

  ​	- 仅将第 n 位置为 1：x | (1 << n)

  ​	- 仅将第 n 位置为 0：x & (~ (1 << n))

  ​	- 将 x 最高位至第 n 位（含）清零：x & ((1 << n) - 1)

- 实战运算要点：

  ​	- 判断奇偶：x % 2 == 1 —> (x & 1) == 1    x % 2 == 0 —> (x & 1) == 0

  ​	- x >> 1 —> x / 2

  ​	- X = X & (X-1) 清零最低位的1

  ​	- X & -X => 得到最低位的1

  ​	- X & ~X => 0

## 17、布隆过滤器和LRU缓存

### Bloom Filter

- 核心实现是一个很长的位数组和几个哈希函数

- 用于检索一个元素是否在一个集合中，空间效率和查询效率高，有一定误判率和删除困难

- 示意图：

  ![img](https://gitee.com/kingoooo/typora/raw/master/img/1030776-20170106143141784-1475031003.png)

- 添加元素：将添加的元素经过k个哈希函数计算，对应位数组上的k个位置设为1

- 查询元素：将查询的元素经过k个哈希函数计算，对应位数组上的k个位置有一个0，则肯定不在集合中；如果k个位置全部为1，则可能在集合中

- 实例代码：

  ​	- https://github.com/lovasoa/bloomfilter/blob/master/src/main/java/BloomFilter.java

  ​	- https://github.com/Baqend/Orestes-Bloomfilter

### LRU Cache

- 查询、修改时间复杂度：O(1)
- 实现方式：Hash Table + Double LinkedList
- LRU - least recently used
- LFU - least frequently used
- 替换算法总览： https://en.wikipedia.org/wiki/Cache_replacement_policies
- Java代码模板：

```java
public class LRUCache {
    private Map<Integer, Integer> map;
    public LRUCache(int capacity) {
        map = new LinkedCappedHashMap<>(capacity);
    }
    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        return map.get(key);
    }
    public void put(int key, int value) {
        map.put(key, value);
    }
    private static class LinkedCappedHashMap<K, V> extends LinkedHashMap<K, V> {
        int maximumCapacity;
        LinkedCappedHashMap(int maximumCapacity) {
            super(16, 0.75f, true);
            this.maximumCapacity = maximumCapacity;
        }
        @Override
        protected boolean removeEldestEntry(Map.Entry eldest) {
            return size() > maximumCapacity;
        }
    }
}
```

## 18、排序算法

- **比较类排序：**通过比较来决定元素间的相对次序，由于其时间复杂度不能突破O(nlogn)，因此也称为非线性时间比较类排序

- **非比较类排序**：不通过比较来决定元素间的相对次序，它可以突破基于比较排序的时间下界，以线性时间运行，因此也称为线性时间非比较类排序

- ![img](https://gitee.com/kingoooo/typora/raw/master/img/image-20210124192957241.png)

- ![img](https://gitee.com/kingoooo/typora/raw/master/img/image-20210124193512979.png)

- 初级排序 - O(n^2)

  ​	- 选择排序（Selection Sort）：每次找最小值，然后放到待排序数组的起始位置

  ​	- 插入排序（Insertion Sort）：从前到后逐步构建有序序列，对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入

  ​	- 冒泡排序（Bubble Sort）：嵌套循环，每次查看相邻的元素如果逆序，则交换

- 高级排序 - O(N*LogN)

 - 快速排序（Quick Sort）：数组取标杆pivot，将小元素放pivot左边，大元素放右侧，然后依次对右边和右边的子数组继续快排

```java
      public static void quickSort(int[] array, int begin, int end) {
          if (end < begin) {
              return;
          }
          int pivot = partition(array, begin, end);
          quickSort(array, begin, pivot - 1);
          quickSort(array, pivot + 1, end);
      }
      static int partition(int[] a, int begin, int end) {
          int pivot = end, counter = begin;
          for (int i = begin; i < end; i++) {
              if (a[i] < a[pivot]) {
                  int temp = a[counter];
                  a[counter] = a[i];
                  a[i] = temp;
                  counter++;
              }
          }
          int temp = a[pivot];
          a[pivot] = a[counter];
          a[counter] = temp;
          return counter;
      }
```

- 归并排序（Merge Sort）—分治

      ​	- 把长度为n的输入序列分成两个长度为n/2的子序列

      ​	- 对这两个子序列分别采用归并排序

      ​	- 将两个排序好的子序列合并成一个最终的排序序列

      ```java
      public static void mergeSort(int[] array, int left, int right) {
          if (right < left) {
              return;
          }
          int mid = (left + right) >> 1;
          mergeSort(array, left, mid);
          mergeSort(array, mid + 1, right);
          merge(array, left, mid, right);
      }
      public static void merge(int[] arr, int left, int mid, int right) {
          int[] temp = new int[right - left + 1];
          int i = left, j = mid + 1, k = 0;
          while (i <= mid && j <= right) {
              temp[k++] = arr[i] <= arr[j] ? arr[i++] : arr[j++];
          }
          while (i <= mid) {
              temp[k++] = arr[i++];
          }
          while (j <= right) {
              temp[k++] = arr[j++];
          }
          for (int p = 0; p < temp.length; p++) {
              arr[left + p] = temp[p];
          }
          // 也可以用 System.arraycopy(a, start1, b, start2, length)
      }
      ```
- 堆排序（Heap Sort）：堆插入O(logN)，取最大/小值O(1)

      ​	- 数组元素依次建立小顶堆

      ​	- 依次取堆顶元素，并删除

 ```java
      public void heapSort(int[] array) {
          int length = array.length;
          if (length == 0) {
              return;
          }
          for (int i = length / 2 - 1; i >= 0; i--) {
              heapify(array, length, i);
          }
          for (int i = length - 1; i >= 0; i--) {
              int temp = array[0];
              array[0] = array[i];
              array[i] = temp;
              heapify(array, i, 0);
          }
      }
      public static void heapify(int[] array, int length, int i) {
          int left = 2 * i + 1, right = 2 * i + 2;
          int largest = i;
          if (left < length && array[left] > array[largest]) {
              largest = left;
          }
          if (right < length && array[right] > array[largest]) {
              largest = right;
          }
          if (largest != i) {
              int temp = array[i];
              array[i] = array[largest];
              array[largest] = temp;
              heapify(array, length, largest);
          }
      }
      ```

    - 特殊排序-O(n)

      ​	- 计数排序（Counting Sort）：计数排序要求输入的数据必须是有确定范围的整数。将输入的数据值转化为键存储在额外开辟的数组空间中；然后依次把计数大于1 的填充回原数组

      ​	- 桶排序（Bucket Sort）：桶排序(Bucket sort)的工作的原理：假设输入数据服从均匀分布，将数据分到有限数量的桶里，每个桶再分别排序（有可能再使用别的排序算法或是以递归方式继续使用桶排序进行排）

      ​	- 基数排序（Radix Sort）：基数排序是按照低位先排序，然后收集；再按照高位排序，然后再收集；依次类推，直到最高位。有时候有些属性是有优先级顺序的，先按低优先级排序，再按高优先级排序

##  19、高级动态规划

- 状态转换成多维度

## 20、字符串

- 常见问题：异位词、回文串、最长子序列、最长子串、编辑距离

- 常见解决办法：递归或动态规划

- 字符串匹配算法

  ​	- 暴力算法：挨个比较所有字符

  ​	- Rabin Karp算法：通过哈希函数算出子串的哈希值，然后将它和目标字符串中的子串的哈希值进行比较，尝试一次性判断

  ​	- Rabin Karp算法思想：

  ​		- 假设子串的长度为M(pat)，目标字符串的长度为N(txt)

  ​		- 计算子串的hash值hash_pat

  ​		- 计算目标字符串txt中每个长度为M的子串的hash值（共需要计算N-M+1次）

  ​		- 比较hash值：如果hash值不同，字符串必然不匹配；如果hash值相同，还需要使用朴素算法再次判断

  ​	- Rabin Karp代码示例：

```java
public final static int D = 256;
public final static int Q = 9997;
static int RabinKarpSerach(String txt, String pat) {
    int M = pat.length();
    int N = txt.length();
    int i, j;
    int patHash = 0, txtHash = 0;
    for (i = 0; i < M; i++) {
        patHash = (D * patHash + pat.charAt(i)) % Q;
        txtHash = (D * txtHash + txt.charAt(i)) % Q;
    }
    int highestPow = 1; // pow(256, M-1)
    for (i = 0; i < M - 1; i++) {
        highestPow = (highestPow * D) % Q;
    }
    for (i = 0; i <= N - M; i++) { // 枚举起点
        if (patHash == txtHash) {
            for (j = 0; j < M; j++) {
                if (txt.charAt(i + j) != pat.charAt(j)) {
                    break;
                }
            }
            if (j == M) {
                return i;
            }
        }
        if (i < N - M) {
        		txtHash = (D * (txtHash - txt.charAt(i) * highestPow) + txt.charAt(i + M)) % Q;
            if (txtHash < 0) {
                txtHash += Q;
            }
        }
    }
    return -1;
}
```

​		- KMP算法：Knuth-Morris-Pratt，当子串与目标字符串不匹配时，其实已经知道前面已经匹配成功的那一部分字符。KMP算法的思想就是利用这个已知信息，不要把搜索位置移回已经比较过的位置，继续把它向后移，这样就提高效率

​			- KMP算法思想讲解：https://www.bilibili.com/video/av11866460?from=search&seid=17425875345653862171

​			- KMP算法代码讲解：https://www.bilibili.com/video/BV1hW411a7ys/?spm_id_from=333.788.videocard.0
