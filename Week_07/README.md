#第七周 
##一、学习笔记
###13.1 Trie树的基本实现和特性
有待补全

####基本结构
字典树，即Trie树（多叉树），又称单词查找树或键树，是一种树形结构。典型应用是用于统计和排序大量的字符串（但不仅限于字符串），所以经常被搜索引擎系统用于文本词频统计。
它的优点是：最大限度地减少无谓的字符串比较，查询效率比哈希表高。
####基本性质
1. 结点本身不存完整单词：只存你下一个字符是什么，可以去到相应的路径；
2. 从根结点到某一结点，路径上经过的字符连接起来，为该结点对应的字符串；
3. 每个结点的所有子结点路径代表的字符都不相同：如果你两个是相同的话，你再下一步开始查找的话，程序就错乱了。

####结点的内部实现
![](Trie树结点的内部实现)
我们再来看一下结点里面的内部的它显示的结构，每个结点的话如果你是英文的话，那么毫无疑问它就会存到了下一个结点去的话，指向下一个结点的不同的指针，
这里的话它存储不再是用left和right来表示左右结点了，它就直接用什么，用相应的字符来指向下一个结点，比如说a的话表示如果你第一个字符是a的话，那么应该走到这个结点去，这里就是a这是英文，同时的话它除了abcdefg小写的，还有大写的所谓ABCDEFG，同时的话如果还有存在一些特殊符号的话，也可以放在这里，
所以的话如果是简单单词的话，同时不分大小写，你可以认为这里是26个分叉，就从a一直分到z对吧，abcdef一直到z，26个分叉出去，当然的话如果你要包含大小写，或者包括其他的话，可能是更多，同时如果是整个字符串的话，它的ASCII域的话是255，所以是255分叉。
一般来说的话，这里的话你可以认为是26分叉的一棵多叉树，到最后的话，如果它是叶子结点的话，相应这个字符就指向空，表示它这里的话就没有儿子结点了，对吧。
从这里你可以看出这棵树的话至少是一个26叉树，对吧。
当然的话你在这里的话可以动态来分配，一开始都是0叉的，出来一个单词的话就分叉出去一个，那么在最坏情况下的话，在最大可能的情况下的话会变成一个26叉树，所以的话它的空间是相对来说比较大的消耗。一层出去就是26，
那么我们再看它单词的长度，就是它的深度，在这种情况下，所以一般来说它查询是很快的，因为它要查多少次，各位，它要查的次数就是这个单词到底有多少个字符，它就查多少次，对吧。如果它单词只有两个字符，比如说to的话，它就只要从t走到这里，再走到这里，它只要走两次，最后就查到了to这个单词它所代表的，比如说它的频次到底是什么，那么单词len一般来说都不长，最多是10这么单词这么长，所以在整个字典里面，你们一般来说最多就查十多次，碰到就是长度为十几的这样一个单词，就一直查下来，这样的话就比，
比如说to你去整个字典里面去查，就算你排好序了，用二分查找的话，它的查找的时间也会比用排序，再用二分搜索来得快以及快不少，

####结点存储额外信息
数字：表示相应到这个结点所代表的单词，它的统计的技术就放在这个地方，后续的话可以给用户做相应的推荐
布尔（isEnd）：指的是到这个点为止，是不是有相应的单词存在。
当然了它的结点上可以存其他的额外的信息

####结点的核心思想
Trie树的核心思想是空间换时间。
利用字符串的公共前缀来降低查询时间的开销以达到提高效率的目的。

另外的话想这里提一点，正是因为它用了公共前缀，来表示这样一个路径的话，就可以非常天然地来解决，你只输了前缀的前几个字母的话，它就会把所有的以这个为前缀的候选词，都给你很方便地找出来。
从这里的话，其实就是比如说you就在这里，表示you这个东西，那么它下面所有的子树就代表了它所有单词，可以候选的单词，每个单词它的频次，你只要取出比如说前十大的频次对吧，然后就可以放在这个地方。那么这个业务功能，其实就相对来说可以比较好地解决。

####Tire 树代码模板
//leetcode
class Trie {
    class TrieNode {
        private TrieNode[] links;
        private boolean isEnd;
        public TrieNode(){
           links = new TrieNode[26];
           isEnd = false;
        }
        public boolean containsKey(char c){
               return links[c-'a']!=null;
        }
        public TrieNode get(char c){
               return links[c-'a'];
        }
        public void put(char c, TrieNode node){
               links[c-'a']=node;
        }
        public void setEnd(){
               isEnd = true;
        }
        public boolean getEnd(){
               return isEnd;
        }
    }
    private TrieNode root;  
    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode();
    }
    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode node=root;
        for(char c : word.toCharArray()){
            if(!node.containsKey(c)){
                node.put(c,new TrieNode());
            }
            node = node.get(c);
        }
        node.setEnd();
    }
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode node=searchPrefix(word);
        return node !=null && node.getEnd();
    }    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
         TrieNode node = searchPrefix(prefix);
        return node !=null;
    }
    public TrieNode searchPrefix(String prefix){
        TrieNode node=root;
        for(char c : prefix.toCharArray()){
            if(node.containsKey(c)){
                node = node.get(c);
            }else{
                return null;
            }
        }
        return node ;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */


####应用
1. 自动补全：谷歌的搜索建议
2. 拼写检查：文字处理软件中的拼写检查
3. IP 路由 (最长前缀匹配)：使用Trie树的最长前缀匹配算法，Internet 协议（IP）路由中利用转发表选择路径。
4. T9 (九宫格) 打字预测： T9（九宫格输入），在 20 世纪 90 年代常用于手机输入
5. 单词游戏：Trie 树可通过剪枝搜索空间来高效解决 Boggle 单词游戏

###13.2 Trie树实战题目解析
https://leetcode-cn.com/problems/implement-trie-prefix-tree/solution/  实现 Trie (前缀树)
https://leetcode-cn.com/problems/design-add-and-search-words-data-structure/  添加与搜索单词 - 数据结构设计
https://leetcode-cn.com/problems/word-search-ii/  单词搜索 II

###13.3 并查集(Disjoint Set)的基本实现、特性和实战题目解析
有待补充
####适用场景
- 组团、配对问题
- Group or not?
- 连通性的问题，即将一个图中连通的部分划出来。
####基本操作
并查集的思想就是，同一个连通区域内的所有点的根节点是同一个。将每个点映射成一个数字。先假设每个点的根节点就是他们自己，然后我们以此输入连通的点对，然后将其中一个点的根节点赋成另一个节点的根节点，这样这两个点所在连通区域又相互连通了。
- makeSet(s):建立一个新的并查集，其中包含s个单元素集合。
- unionSet(x,y):把元素x和元素y所在的集合合并，要求x和y所在的集合不相交，如果相交则不合并。
- find(x):找到元素x所在的集合的代表，该操作也可以用于判断两个元素是否位于同一个集合，只要它们各自的代表比较一下就可以了。
####并查集代码模板
// Java
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
	      parent[p] = parent[parent[p]];//将p的祖父作为p的父结点
	      p = parent[p];//将指针指向p的新父结点
	    }		
	    return p; 	
	}	
	public void union(int p, int q) { 		
	     int rootP = find(p); 		
	     int rootQ = find(q); 		
	     if (rootP == rootQ) return; 		
	     parent[rootP] = rootQ; 		
	     count--;	
	}
}




1. https://leetcode-cn.com/problems/number-of-provinces/  朋友圈（已下线）
   a.DFS，BFS （类似 岛屿问题）
   b.并查集
2. https://leetcode-cn.com/problems/number-of-provinces/ 省份数量

3. https://leetcode-cn.com/problems/number-of-islands/ 岛屿数量

4. https://leetcode-cn.com/problems/surrounded-regions/ 被围绕的区域

###14.1 剪枝的实现和特性
####初级搜索
1. 朴素搜索
优化搜索
2. 优化方式：不重复（fibonacci）、剪枝（生成括号问题）
3. 搜索方向：
   DFS: depth first search 深度优先搜索：傻搜
   BFS：breath first search 广度优先搜索：广度优先一般扩散到的都是距离 最近的
优化的高级搜索的另外两种
双向搜索：它从起点和终点分别做一个广度优先，然后在中间相遇，这样的话它的时间更快
启发式搜索：不再是用栈或者用队列了，这种先入先出先入后出的行驶，而是用一个优先队列放在这里面。而优先队列的话，它是按照这个结点，所谓它的优先级，也就是有些结点更可能会达到我们需要的结果的话，就先把它从队列里面拿出来进行搜索，我们这个就叫做启发式搜索，也叫作A*算法或者叫做优先级搜索。
Coin change(零钱置换) 的状态树
这张图我想每个人都要记在脑子里面
####剪枝

####回溯法


###14.2 剪枝实战题目解析：数独
- https://leetcode-cn.com/problems/sudoku-solver/#/description  解数独
优秀题解：python https://leetcode-cn.com/problems/sudoku-solver/solution/pythonsethui-su-chao-guo-95-by-mai-mai-mai-mai-zi/

###14.3 双向BFS的实现、特性和题解


###14.4 启发式搜索(Heuristic Search(A*))的实现、特性和题解



###15. 红黑树和AVL树

####树Tree

####二叉树Binary Tree

#####二叉树遍历Pre-order/In-order/Post-order

####二叉搜索树Binary Search Tree
之前给大家已经提示过，或者是已经打过一个伏笔的，就是树和链表没有本质上的区别，
当一个链表的话，当它分出两个next的话，我们就把它称为树，你可以这么认为。
所以它的数据结构的一个本质就是从一维空间扩散到二维空间了。

#####如何查找

#####极端情况
这种极端情况的话就是在你维护二叉搜索树的时候，没有特殊处理的话，在极端情况下，它会变成一根棍子，这根棍子的话就是你在插入的时候始终插在左边，始终插在左边，当这根棍子出现的时候，二叉搜索树的话就退化成一个链表，就类似于链表的查询的时间复杂度了

#####保证性能的关键
1. 保证二维维度！ -> 左右子树结点平衡（recursively）:左右子树高度尽量是平衡的，且左右子树以此类推下去都尽量是平衡的
2. Balanced：所以这里引入了一个平衡二叉树的概念
3. https://en.wikipedia.org/wiki/Self-balancing_binary_search_tree 维基百科：平衡树
2-3 tree掌握
AA tree
AVL tree掌握
B-tree掌握
Red-black tree掌握
Scapegoat tree
Splay tree了解
Treap了解
Weight-balanced tree

所有的平衡二叉树有很多种，在面试的时候一般喜欢给大家出AVL和红黑树以及比如说treap以及splay叫伸展树，以及后面的话还会有B+树（主要在数据库的索引结构里面用得很多）和二三树，
那么一般来说面试的时候喜欢出这些，但是它整个的话平衡二叉树有很多
#####思考：如何保证一棵树的平衡

####AVL树
1. 发明者 G.M.Adelson-Velsky 和 Evgenii Landis
2. Balance Factor（平衡因子）：
   是它的左子树的高度减去它的右子树的高度（有时相反）。
   balance Factor = {-1,0,1}
注意：记住这里是高度并不是左子树的结点树或者是右子树的结点树，只是它的高度。
      因为查询二叉搜索树的效率只与高度有关，和它这个结点的个数是没有关系的。
      那么就得到了它的平衡因子取值的范围只有-1、0和1这三种。
3. 通过旋转操作来进行平衡（四种）
4. https://en.wikipedia.org/wiki/Self-balancing_binary_search_tree 
#####记录左右子树高度  


#####旋转操作
1. 左旋
子树形态：右右子树->左旋
2. 右旋
子树形态：左左子树->右旋
3. 左右旋
子树形态：左右子树->左右旋
4. 右左旋
子树形态：右左子树->右左旋
######带有子树的旋转
参考动画： https://zhuanlan.zhihu.com/p/63272157

######代码：
不要求掌握

#####AVL总结
1. 平衡二叉搜索树
2. 每个结点存balance factor ={-1,0,1}
3. 四种旋转操作
不足：结点需要存储额外信息、且调整次数频繁

正是这个原因的话，我们就会引入了其它的一些树，这些树的话我们就叫做近似平衡二叉树。
所谓叫近似平衡二叉树就是不需要每次非常严格地来平衡
近似平衡二叉树：
####红黑树
#####Red-Black Tree
红黑树是一种近似平衡的二叉搜索树（Binary Search Tree），它能够确保任何一个结点的左右子树的高度差小于两倍。
具体来说，红黑树是满足如下条件的二叉搜索树：
- 每个结点要么是红色，要么是黑色
- 根结点是黑色
- 每个叶结点（NIL结点，空结点/叶结点即指树尾端NIL指针或NULL结点）是黑色的
- 不能有相邻接的两个红色结点/如果一个结点是红的，那么它的俩个儿子都是黑的
- 从任一结点到其每个叶子的所有路径都包含相同数目的黑色结点/对于任一结点而言，其到叶结点树尾端NIL指针的每一条路径都包含相同数目的黑结点

#####对比
有待翻译（见视频）
- AVL trees provide FASTER LOOKUPS than Red Black Trees because they are MORE STRICTLY BALANCED
- Red Black Trees provide FASTER INSERTION AND REMOVAL operations than AVL trees as fewer rotations are done due to relatively relaxed balancing 
- AVL trees store balance FACTORS OR HEIGHTS with each node, thus requires storage for an integer per node whereas Red Black Tree requires only 1 bit of information per node
- Red Black Trees are used in most of the LANGUAGE LIBRARIES LIKE MAP, MULTIMAP. MULTISETIN C++ whereas AVL trees are used in DATABASES where faster retrievals are required


##二、学习总结：