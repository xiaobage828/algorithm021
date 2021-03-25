#第二周 
##一、学习笔记
##5.1哈希表、映射、集合的实现与特性
###1. Hash table
哈希表（Hash table），也叫散列表，是根据关键码值（Key value）而直接进行访问的数据结构。<br>
它通过把关键码值映射到表中一个位置来访问记录，以加快查找的速度。<br>
这个映射函数叫做散列函数（Hash Function），存放记录的数组叫做哈希表（或散列表）。
   
####工程实践：
- 电话号码簿、
- 用户信息表、
- 缓存（LRU Cache）、
- 键值对存储（Redis）

####Hash Function/哈希函数
![](Hash%20Collisions.png)
举例，lies：每个字符串的ASCii码mod一个数得到9，放到index为9的位置

####Hash Collisions/哈希碰撞
![](Hash%20Function.png)
那么像这种不同的字符串，最后算出来，比如说这里是lies，另外还有一个叫foes，它们最后算出来的话，发现都是9，像这种情况我们叫做哈希碰撞。
所谓的哈希表以及在比如说现在比较火的区块链、比特币以及大数据的技术里面经常要用到所谓的哈希。
那么这里就所谓的哈希碰撞，也就是说，不同的要存储的数据，它经过哈希函数之后会得到一个相同的值，对应的下标相同，则发生hash碰撞。
那怎么办？这种情况的话也很常见，那么它的下标都是9，这里的话那么就说明该下标处要存多个元素。
我们要做的事情也比较简单，其实有几种办法：
一个的话依次往下面存占别人的位置，
当然最好的一种方式，或者是相对在工程上常用的方式，就是再增加一个维度，这个位置不止存一个数了，而是存多个数，也就是从这里拉出来一个链表，这样的方法叫做拉链式解决冲突法。

这时候你会发现一个数据进来通过哈希得到它的下标的值，这整个的操作是O(1)的，也就是O(1)可以查询到它的位置，但是如果很多的元素都积累在相同的位置，这时候哈希表的它的查询时间就要遍历链表，
所以的话这时候如果链表很长的话，它就会效率进行退化，退化到所谓的O(n)的级别。
但是如果你设计得比较好的话，哈希函数它的碰撞的概率很小。
所以在平均时刻的话，我们可以认为整个哈希函数的查询它是O(1)的。

![](拉链法哈希函数.png)
那么在这正常情况下，这里大家注意，和之前所讲的优先队列一样，以及后面可能要讲的红黑树和AVL一样，像哈希表这样的结构，以及常见的哈希函数的话，都是在工程里面常用的非常常用。
所以高级的语言以及标准的库都已经实现好了，大家直接用就行了，不需要手写。

####JAVA CODE
Map和Set：在真正的工程代码里面，我们经常在用的话就不再是哈希表了，而是在哈希表基础上抽象出来的。经常抽象出来使用的比较多的是Map和Set
Map:key-value对，key不重复
new HashMap()/new TreeMap()
map.set(key,value)
map.get(key)
map.has(key)
map.size()
map.clear()
Set:不重复元素的集合
new HashSet()/new TreeSet()
set.add(value)
set.delete(value)
set.has(value)
区别：map的话是键值对，而key是不重复的，value可以重复，它是一个键值对的关系；set的话是不重复的元素的集合，这个的话就没有键值对，就是一个单个的元素，这里面单个元素就是不重复的，这边的话是Key不重复
Hash实现其实就是在背后建了一个hashMap，add时把元素放到key，随便放一个Object作为占位的value，remove也是调用map.remove，背后实现其实就是嫁接在hashmap上。这种实现有很多冗余元素在里面
treeMap和treeSet的实现都是用红黑树实现的，所有操作的时间复杂度都是log(n)

####复杂度分析
![](常用数据结构操作复杂度分析)

#####工程实现
Map : http://fuseyism.com/classpath/doc/java/util/Map-source.html
HashMap : http://fuseyism.com/classpath/doc/java/util/HashMap-source.html
HashTable : http://fuseyism.com/classpath/doc/java/util/HashTable-source.html
ConcurrentHashMap : http://fuseyism.com/classpath/doc/java/util/concurrent/ConcurrentHashMap-source.html

Set : http://fuseyism.com/classpath/doc/java/util/Set-source.html
HashSet : http://fuseyism.com/classpath/doc/java/util/HashSet-source.html
TreeSet : http://fuseyism.com/classpath/doc/java/util/TreeSet-source.html

####Homework
写一个关于 HashMap 的小总结。（重点看put和get）node分为hashNode和treeNode
##5.2实战题目解析：有效的字母异位词等问题
排序：所有面试的时候，排序不要自己写，直接调Arrays.sort()即可
有效的字母异位词：1.暴力：sort,sorted_arr是否相等  O(NlogN) 2.hash，map-->统计每个map的频次，技术处理：第1个单词碰到相应计数+1，第2个单词碰到相应计数-1，最后判map是否为空；用长度为256的数组来计数，因为ASCII码的范围为0-255，长度为256的数组来计数，其实它也是一种简化了的hash表，只不过它的hash函数是它的ASCII码的值
字母异位词分组：排序，用排序后的结果作为key，存到一个map里面，最后把map的值归类之后输出
两数之和：hash表，a,b--> a+b == target --> for each a:check(target - a) exists in nums
学习方法：养成收藏精选代码的习惯（把题目中多种解法及要点罗列起来），每次面试之前会看一下这些代码。这样真正面试下笔如有神，写出来是多种解法，且皆是比较优化的代码
         养成这种总结代码的习惯，同时形成自己的这种至少是算法和数据结构的一个精选代码库的概念，同时还可以把它放到github上面
##6.1树、二叉树、二叉搜索树
###树：
![](单链表Linked%20List.png)
现在树踏到二维结构，回顾链表等一维结构，链表有头、尾，还有next指针不断往后指，这叫单链表。 如果是双链表，还有一个前继指针往前指。
链表最大的问题就是查询的时候太慢，要O(n)的时间复杂度。正是这个原因，后来就出现了跳表结构，加上了更快的索引，比如从头指针跨到第二个节点。
如果要加速的话，最关键在于升维。
要升维的话，接下来就可以看我们的二维的数据结构，常见的是什么，那么其实就是树和图。讲树之前想给大家引出的一点是，在单链表这里的话，如果它的next指针，很多时候你再想想，它不再是一个next,而是多个next，有next1、next2、next3指向多个结点的话，它就变成一课树了
树的基本定义以及相应特点：
![](树tree.png)
那么首先有一个根结点对吧，然后叫左子树右子树，当然左儿子和右儿子，这位叫做它的儿子结点在这里，那么整个的话就叫子树。
相对于儿子结点来说的话，它就有父亲结点，当然的话D这个结点是H和I的父亲结点，同时的话它也是B结点的儿子结点。
F和G的话就是兄弟结点。
同时还有不同的层，从第零层一直到第一层第二层第三层，你层的话可以从零开始，也可以从一开始没有太大问题。
同时根结点这里的话它不一定要标为A，它也可以标为D或者标为B也行，
标为B的话其实相当于把B拎到外面去了，然后A的话就相当于下来了，这样也是可以的，当然你要把这个结点本身的指针的话，也要做相应的调整。


树节点的定义：
public class TreeNode{
  public int val;
  public TreeNode left,right;
  public TreeNode(int val){
    this.val = val;
    this.left = null;
    this.right = null;
  }

}
为什么会出现树：
人类本身生活在一个三维的世界里面、思维的世界里面当然也包括二维，所以人类本身的工程实践，其实是在二维基础上解决的，而树本身的话就是人经常会碰到的一种情况
AlphaGo、各种棋类游戏（星际争霸也类似），选手开始下了以后，这个棋盘状态就是向外扩散成不同的状态，每个不同的状态再往下面走，棋盘的状态本身的话它也是一个树形结构。
三子棋，空棋盘是开始状态，每个人可以走不同步，走了以后棋盘就往下继续扩散出不同的状态，形成第二层、第三层、第四层的树的结点，
到了最后所谓的叶子结点，就是这个棋盘的某一终极形态，这个终极形态肯定就是不能再下了，其中有一方赢了或者输了或者是和棋的状态。

而本身的话不同的棋它的树状的空间，我们叫做它的状态树的空间。同时还有一个叫做博弈的空间，也可以叫做你的decision tree叫决策树的空间的复杂度不一样，最后决定了这个棋或者这个游戏，它的复杂度。
比如说三子棋肯定是相对比较简单，跳棋就相对更难一点，国际象棋已经很难了，再到后面中国象棋更难，还有日本象棋、韩国象棋，再到最后就是所谓的围棋，以至于围棋使用alphaGo在这么大一个空间里面去找它的最优解情况

树的遍历：如果这是一棵非常基本的树，没有任何特征的话，就它的里面的结点全都是无序的话，你要查找一个元素的话就必须要遍历，你遍历的话就是什么，把所有的结点都走一遍，怎么走，其他的数据结构循环一遍，一直循环到没有对吧。
因为它树状的话会有左子树右子树这么一种情况，那就类似的递归去把它反复的求证。那么递归的代码的话也非常简单，就左中右遍历。为什么叫这种结构？就是因为你要查自己的根结点的值，同时你还要访问你的左子树和右子树，总共需要三句语言。
这三句语言的顺序，最后就变成了不同的遍历方式。我们把它简单归类为


二叉树的遍历Pre-order/In-order/Post-order：根在左右结点的相对位置有前、中、右，则有三种遍历
1.前序Pre-order：根-左-右
2.中序In-order：左-根-右
2.后序Post-order：根-左-右

可以看到
我们整个遍历的话，这里基本上就基于递归的。为什么基于递归？
给大家讲一下，就是树的定义本身的话，它没法有效地进行所谓的循环，后面会讲，可以强行进行循环，比如说用广度优先遍历。
但是很多情况下的话，你会发现它这种结构的话，写循环相对比较麻烦，而写递归调用相对比较简单。
可以看到不同的序的遍历，其实严格说来就四行语句，非常的漂亮，也非常的简洁
所以树的各种操作的话，大家一定不要怕递归，而且要拥抱递归，或者是爱上这么一个递归的形式
###二叉树：儿子结点只有2个
![](二叉树Binary%20Tree.png)

树和图最关键的差别：看有没有环，如果它这一个结点本身的话，只连到新的儿子结点，永远都不会走回去，为树；如果有指针指回去，那就相当于会形成一个环，形成环的情况下，按照定义不称为树，而是叫做图
但在特殊化情况下，可以简化的理解为：Linked List是特殊化的Tree（因为链表有两个next指针），Tree是特殊化的Graph（没有环的图就是树）
![](图Graph.png)
###二叉搜索树Binary Search Tree：
![](二叉搜索树Binary%20Search%20Tree.png)
二叉搜索树，也称二叉排序树、有序二叉树（Orderd Binary Tree）、排序二叉树（Sorted Binary Tree），是指一棵空树或者具有下列性质的树：
1.左子树上所有结点的值均小于它的根结点的值
2.右子树上所有结点的值均小于它的根结点的值
3.以此类推：左、右子树也分别为二叉查找树。（这就是重复性！）
中序遍历：升序排列


二叉搜索树常见操作(都是O(logN))
1.查询（O(logN)）
2.插入新结点(O(logN))：查找的到：count+1；查找不到：放在最后找到的位置left（小于当前就放左）或right（大于当前就放右）
  （创建）：创建一个空树，把所有结点依次调用插入即可
3.删除(O(logN))：叶子结点：直接删掉即可；根节点或者某子树的根节点：删除目标结点，拿最近的结点补充，一般来说取第一个大于当前结点的结点替换上去
Demo : https://visualgo.net/zh/bst

特殊情形：树退化成一根棍子，变成单链表
思考题：access 、search、insertion、 deletion都是O（n），加速的话，把它配平，变成平衡二叉树
树的面试题解法一般都是递归，为什么？
##6.2实战题目解析：二叉树的中序遍历
- 树的面试题解法一般都是递归，为什么？
第1，代码本身，树的定义没有所谓的，它的后继这么一个结构。或者是说一个便于循环的结构，而是更多的是左结点右结点。
这样的话，你要去访问它的子树的话，经常更好的一种方式，是直接对它的左结点再调相同的遍历函数
- 每个结点的定义代码:
python:
class TreeNode:
  def _init_(self,val):
     self.val = val
     self.left,self.right=None,None
     
c++
  struct TreeNode{
     int val;
     TreeNode *left;
     TreeNode *right;
     TreeNode(int x):val(x),left(Null),right(Null){}
  }
  
java
public class TreeNode{
  public int val;
  public TreeNode left,right;
  public TreeNode(int val){
    this.val = val;
    this.left = null;
    this.right = null;
  }
}
- 相应的前、中、后序遍历的代码(机械化记忆):
前:father-left-right,中:left-father-right,后:left-right-father
def preorder(self,root):
  if root:
     self.traverse_path.append(root.val)
     self.preorder(root.left)
     self.preorder(root.right)

def inorder(self,root):
    if root:
       self.inorder(root.left)
       self.traverse_path.append(root.val)
       self.inorder(root.right)
       
def postorder(self,root):
    if root:
       self.postorder(root.left)    
       self.postorder(root.right)
       self.traverse_path.append(root.val)
- 树的遍历demo: https://visualgo.net/zh/bst

1.binary-tree-inorder-traversal
2.binary-tree-preorder-traversal
3.n-ary-tree-postorder-traversal
4.n-ary-tree-preorder-traversal
5.n-ary-tree-level-order-traversal  其实就是广度优先遍历
##6.3堆和二叉堆的实现和特性
###堆Heap
代码实现：https://shimo.im/docs/Lw86vJzOGOMpWZz2/
Heap：可以迅速找到一堆树中的最大或者最小值的数据结构。
将根节点最大的堆叫做大顶堆或大根堆，根节点最小的堆叫做小顶堆或小根堆。
常见的堆有二叉堆、斐波那契堆（当然在工业级比较牛逼的应用的话，都是斐波那契以及非常严格的斐波那契，斐波那契时空复杂度更好，但是并不是二叉树是多叉的，而且的话它的性质更多一点）等。
假设是大顶堆，则常见操作（API):
find-max:O(1)
delete-max: O(logN)
insert(create):O(logN) or O(1)(斐波那契堆)
不同实现的比较：https://en.wikipedia.org/wiki/Heap_(data_structure)
比如说经常一个数一个数的过来，同时还有从另外一边的话经常去删除一些数据，问你这些数里面它最大值是多少？
这里的最大值可能就代表优先级最高的结点，或者是那个数要你先处理的，
比如说你的任务流里面随时你要拿出优先级最高的任务优先处理，那么这种数据结构就更加的好用
###二叉堆性质
通过完全二叉树来实现（注意：完全二叉树并不是之前上一节讲的二叉搜索树，和二叉搜索树一点关系没有，同时堆和二叉搜索树也没有任何关系）（实现相对容易，但是的话它的时间复杂度就是在刚刚及格里面）
二叉堆（大顶）它满足下列性质：
![](二叉大顶堆.png)
（性质一）是一棵完全树
（性质二）树中任意节点的值总是>=其子节点的值


那么可以保证根节点肯定是最大的结点
每次访问最大值，返回根节点即可
什么是完全二叉树？
就是它的根和每一级结点都是满的，除了最下面一层叶子可能不满之外，其他的上面的话全部都是满的
二叉搜索树可以实现堆吗？
二叉搜索树也能实现堆，只不过慢了，慢在什么地方：用二叉搜索树的话，树里面整体有序，可以找最小值，也可以找最大值，同时增加删除也是O(logN)，找最小值就不是O(1)的，找最小值变成O(logN)。
因为你要遍历这个树，找到它的最左边的叶子结点。用二叉搜索树实现这个堆，在find-min这个地方时间复杂度不合格
存储：
因为它是一棵完全二叉树，所以就不需要用之前的链表的结构（就是用一个树的结点，然后它有左儿子右儿子这种指针的形式了），我们直接就可以用数组来实现，直接用一维数组来实现


二叉堆实现细节：
![](二叉堆实现细节.png)
1.二叉堆一般都通过“数组”来实现
2.假设“第一个元素”在数组中的索引为0的话，则
  将结点从上到下层序放入数组相应位置，父节点和子节点的位置关系如下：
  （00）根节点（堆顶元素）是：a[0]
  (01)索引为i的左孩子的索引为(2*i+1)
  (02)索引为i的左孩子的索引为(2*i+2)
  (03)索引为i的左孩子的索引为floor((i-1)/2)
  因为每一个地方多了两倍的结点
  
  
  insert插入操作 — O(logN)
  1. 新元素一律先插入到堆的尾部
  2. 1之后破坏了堆的性质，需要依次从尾部向上调整整个堆的结构（一直到根即可）  HeapifyUp:O(logN)，树的深度是最大的时间复杂度:log2N
  ![](二叉堆插入1：将85添加到末尾.png)
  ![](二叉堆插入2：将85和40交换.png)
  ![](二叉堆插入3：将85和80交换.png)


  Delete Max删除堆顶操作 — O(logN)
  1. 将堆尾元素替换到顶部（即堆顶被替代删除掉）:保证是一个完全二叉树，所以不可能删除30，也不可能删除80，只能把最后一个元素替代它，其实就相当于把堆顶元素删除，堆尾元素放到堆顶，整个堆的大小减1，就是size一维数组的size就缩了一个
  2. 1之后破坏了堆的性质，对堆顶元素类似的从上往下做相应调整整个堆的结构（一直到堆尾即可）  HeapifyUp:O(logN)，树的深度是最大的时间复杂度:log2N
  

  但是这里我想在这里给大家强调一句，就是如果只是说堆是什么，那么它是一个抽象的数据结构，表示可以非常迅速地拿到一堆数里面的最大值或者最小值。它并不是二叉堆，二叉堆和其他的各种堆只是堆的一种实现形式。
  且二叉堆为什么出现得多，
  注意：二叉堆是堆（优先队列priority_queue)的一种常见且简单的实现；但是并不是最优的实现，所以二叉堆很多时候并不是完全的那么实用。
  https://en.wikipedia/wiki/Heap_(data_structure)
  工程里面用直接调优先队列priority_queue就行了
  
  讲解BinaryHeap
  二叉堆这个数据结构本身是比较精妙的，实现起来的话也比较方便，这就是为什么二叉堆经常会拿来做教科书里面的示例，以及面试的时候也会问，
  就让大家学会这种归去来兮，同时的话有相似性操作的这么一种性质
  ##6.4实战题目解析：
  1.zui-xiao-de-kge-shu-lcof/最小的k个数: sort:NlogN， heap: NlogK(参考视频代码实现，并且注意简化代码中冗余和复杂的地方)， quick-sort
  2.sliding-window-maximum/滑动窗口最大值：双端队列Deque，堆PriorityQueue(比前者多logK的时间复杂度)
  3.top-k-frequent-elements/前k个高频元素:
  业务代码中，做一些任务调度以及的话，各个请求不断地过来，然后你肯定要优先处理最高频的请求，类似于这样的事情
  要求：算法时间复杂度必须要优于O(nlogn)，一般来说看到logn的话，就是用堆或者是用二叉搜索树，或者是二分查找或者是排序，类似于这样的手段
  开始统计元素的次数，然后把次数作为key，值作为value放到大顶堆里面，最后循环把前k个元素放到结果里面
  作业：
  1. HeapSort：自学https://www.geeksforgeeks.org/heap-sort/：逻辑很简单，就是把要排序的数组放这个堆里面去，不断地取最大值，那么就按照从大到小可以排序出来
  2. chou-shu-lcof
  3. top-k-frequent-elements
  ##6.5图的实现和特性：
  面试和工程，图的相关的算法和运用都越来越少，特别是现在算法面试，很少考图相关的东西，这里相对略讲
  ###图的属性和分类
  图的定义：有点有边就行
  Graph(V,E)
  V-vertex：点
  1.度（一个点有多少个边）-入度和出度，边是无向的：入度=出度；根据边是有向还是无向，把它分为入度和出度。当这个边是有向的，比如有单行道，那么一个点一个交通枢纽，就有多少条路可以进来和多少 条路可以出去，那么这个路的个数就指的是它的入度和出度
  2.点与点之间：连通与否；连通表示是直接有边相连，还是必须绕很远最后连通，当然也有可能两点之间没有任何路连通，也就是说这个图的话有一个个孤岛存在，也是合理的
  E-edge：边
  1.有向和无向（单行线），单行道和双行道的区别，有些时候两个点之间的路，你只能从A到B，不能从B到A，有时候你随便走都可以
  2.权重（边长，也可以理解为边它的损耗，也就是在边上走，它的费用是多少）
  图的表示方法和分类：
  表示方法两种：
    邻接矩阵：见视频，第1个维度，每一行上面的标识代表点的下标；第2个维度，每一列上面的标识代表点的下标；对应的元素指的是行代表的点和列代表的点，它们之间的边是多少。如果这个边本身是有权重的，元素就写边的权重，如果无权重，那么就是0和1，0表示无直接的边相连，1表示无直接的边相连
             无向无权图：对称矩阵表示无向图，权只是0和1的边，就是无权图；无向有权图
             非对称矩阵表示有向图：有向无权图、有向有权图
   邻接表：见视频，第1个维度，每一行上面的标识代表点的下标；后面带的元素直接写的是边，索引后面放与他相连的点，用链表存储
    都是二维数据结构
  ###基于图相关的算法
  DFS:递归写法，代码模板
  
  
  BFS:递归写法，代码模板
  
  
  
  连通图个数： https://leetcode-cn.com/problems/number-of-islands/
  拓扑排序（Topological Sorting）： https://zhuanlan.zhihu.com/p/34871092
  最短路径（Shortest Path）：Dijkstra https://www.bilibili.com/video/av25829980?from=search&seid=13391343514095937158
  最小生成树（Minimum Spanning Tree）： https://www.bilibili.com/video/av84820276?from=search&seid=17476598104352152051
  面试具体公司之前进行综合性复习的时候，大家看一下；工程实现，github上搜相应代码，下载下来修改
  
##二、学习总结：
###1.效果、感受
本周进步很明显，把两周的例题和习题除数方面的都做了一遍，不少困难题都做出来了；两周的视频也仔细看了，而且边看还边做了一些笔记。感觉进步很明显，对算法题不再恐惧了，相信自己能够在超哥的帮助下很好的掌握算法。
###2.学习过程
先看录播，边看录播边做笔记，把笔记和每周的学习总结放到一个markDown文档里面，然后着手习题，用习题来检验学习效果。
###3.收获
这两周对数组、链表、队列、栈、堆、图均有一定掌握，但树因为没做例题，而疏于掌握，这块需要马上补上。
###4.刷题笔记
对jdk的一些api不太熟练，这里对刷题中翻看api手册得到的记录如下:
Object[]/数组: 长度length为arr.length(不需要加()，长度为数组的属性)；两数组是否相等，应该用Arrays.equals(a,b)比较，而不能用a.equals(b)比较，前面true的情况下后面为false

System: public static void arraycopy(Object src, int srcPos, Object dest, int destPos, int length)，src - the source array、srcPos - starting position in the source array、 dest - the destination array、destPos - starting position in the destination data、length - the number of array elements to be copied.

String: 长度length为s.length()(需要加(),String使用char[]实现的，获取方法得到的是长度为char[] chs.length);index对应的字符为char c = char.charAt(index);String转换为字符数组char[] chs = s.toCharArray();


StringBuilder: sb.setCharAt(j,c)[替换StringBuilder中的字符(Replace character in StringBuilder)]


Arrays: 两数组是否相等，应该用Arrays.equals(a,b)比较，而不能用a.equals(b)比较，前面true的情况下后面为false;数组排序用Arrays.sort(arr);int[]


ArrayList: new ArrayList<>(Collection<? extends E> c),T为可转换为list的Collection


Queue: offer(E e) //tail添加元素
       poll()  //head取并移除元素
       peek()  //head获取元素


Deque: double ended queue
       offer(E e)  //tail添加元素
       poll()   //head取并移除元素
       peek()   //head获取元素
       offerFirst(E e) //head添加元素
       pollLast(E e) //tail取并移除元素
       peekLast(E e) //tail获取元素
       isEmpty() 没有deque.isFull() 
       pop()  //head取并移除元素，实现了栈
       push(E e)  //head添加元素，实现了栈



ArrayDeque:实现了Deque，可以初始化大小

PriorityQueue:class。添加offer、取出堆顶poll、获取堆顶peek，自定义的话，需要实现Comparator接口;pq.addAll(Collection<? extends E> c)
Queue<int[]> q  = new PriorityQueue<int[]>(new Comparator<int[]>(){  //优先级队列
public int compare(int[] i1,int[] i2){
return Math.max(M - i1[0], N - i1[1])+i1[2] - i2[2]-Math.max(M - i2[0], N - i2[1]);  //先遍历步数+距离较小的点
}
});;
PriorityQueue(int initialCapacity, Comparator<? super E> comparator)  创建PriorityQueue具有指定初始容量的，并根据指定的比较器对元素进行排序。
    offer(E e)  //tail添加元素
    poll()   //head取并移除元素
    peek()   //head获取元素


Comparator:采用策略比较

Comparable:自比较


LinkedList:可以实现Queue，Deque




HashMap:合理初始化大小可以减少用时


Collections:
    sort();
    public <T> T[] toArray(T[] a)无法用来转换list<Integer>
    reverse(List list)：翻转list
    public static <T> Comparator<T> reverseOrder()：reverseOrder() 方法用于获取一个比较有否就实现Comparable接口的对象的集合的自然顺序相反。





四连通: dx={0,1,-1,0},dy={1,0,0,-1}
八连通：dx={0,1,-1,0,1,1,-1,-1},dy={1,0,0,-1,-1,1,1,-1}