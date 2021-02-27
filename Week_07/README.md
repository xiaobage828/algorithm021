#第七周 
##一、学习笔记
###13.1 字典树（Trie树）的基本实现和特性
#### 本节内容
那么在这里的话，我们主要是分为三部分来给大家讲：
1. 字典树的数据结构：首先字典树这个数据结构到底是什么样子的，为什么它会存在。
2. 字典树的核心思想
3. 字典树的基本性质

#### 复习树（略）
那么我们首先来复习一下树
#####树Tree
1. 定义
   二叉树
2. 层序遍历
3. 深度优先搜索
#####二叉搜索树
1. 定义
   任何一个结点，它的左子树的所有的结点都要小于这个根结点，它的右子树的所有的结点都要大于根结点，且对于它的任何子树同样地以此类推，对于任何子树都满足这样的特性。这个就是所谓的二叉搜索树。
2. 特性：
   二叉搜索树如果是中序遍历（左根右）的话是一个升序的序列。
3. 二叉搜索树主要解决的一个问题就是查找的时候效率会更高。


#### Trie树
那么接下来我们看，还有一种情况，在现实中特别常见。但是在二叉搜索树来进行存储的话，并不是特别好解决这样的一个实际问题。
这个实际问题是什么？这样的一个实际问题，就是在搜索的时候，很多时候当你打了一个字母的前缀、或者你中文也类似，就比如说你打了一个周杰，一般人可能会觉得你是伦吗？对，就是周杰伦
同理英文的话就是you的话，就是有这么多可以感应出来的，
像这种所谓的词频的感应或者是由前缀来推后面的可能的词语这种形式的话。在现实中其实是用得非常多的，那么它应该用怎样的数据结构来表示？
接下来的话就来给大家讲所谓的字典树或者trie树了。它就是因为这样的一种情形来应运而生的。

那么我们首先看一下，各位可以先思考一下，如果你自己要来设计的话，应该会怎么设计，同时的话你自己来进行设计的话，你的数据结构应该怎么选？
这个比较难，你可以多想想，然后停一分钟左右，当然的话一般来说就是不求你得到最优解了，因为发明这些数据结构的人，都是世界上最顶尖的图灵奖获得者。

那么我们说完了，我们接下来看字典树它是怎么来进行设计的？
首先要实现这样的一种所谓的前缀感应出整个单词，这里的话我们就要改变，二叉树的结点里面的存储方式了。
在之前我们介绍不管是二叉树二叉搜索树的话，这个结点里面存的是全部的值，这每个结点存的就是整个一个数字也好、一个单词一个字符串也好，就是全部的字符串。
但是在这里的话，字典树一个最关键的点的话，就是每个结点不再是存这里的单词本身，就是没有一个结点存youtube、或者是youbank这样的东西，
而是把这个字符串拆成单个单个的字母，每个字母存在这个结点里面去了，
从这一点的话，大家就首先要打破之前对树的认识，就是说它的存储是相对来说比较巧妙的，那么有了这个概念之后，接下来我把它整个定义先给大家叙述一遍。
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
####1. https://leetcode-cn.com/problems/implement-trie-prefix-tree/solution/  实现 Trie (前缀树)
https://leetcode-cn.com/problems/design-add-and-search-words-data-structure/  添加与搜索单词 - 数据结构设计
####2. https://leetcode-cn.com/problems/word-search-ii/  单词搜索 II（讲解）
word search 有1和2都是非常经典的搜索题目.
word search的话在之前DFS的话都给大家说过，接下来我们看word search 2以及怎么能够优化这样的代码
![](word%20search2.jpg)
网格里面的单词必须是通过相邻的单元格里的字母构成，所谓的相邻就是上下或者是水平方向是相邻的。
那么这里的相邻表示什么，表示所谓的专业术语叫做四联通，比如说和a相邻的话就是上面的a、下面的k、左面的t、右边的e，所以是上下左右四联通。
如果把斜线也包含进来，比如说a和a、n和a也说可以相邻的，那么就叫做八联通图。
那么一般情况下，像这个题目的话，它给你说明了，反正就是四联通就行了。八联通和四联通的话，写法都差不多。后面的话代码会给大家讲。

1. words 遍历 --> board search:先写一层循环，然后每一个元素word看oath，o在这里面什么位置找到了之后，看它附近的ath是不是可以在这里找到。然后再看p这样一个东西,然后又去board里面先查一遍，p有没有，p在哪：没有，这种方式是可以写的。
   这种方式的复杂度是多少呢？O(N*m*m*4^k)
   首先的话它要循环整个words里面的对吧，我们就认为words里面是O(n)的话，那就放在这里n来了，
   board我们就直接是认为它是m*m的好了，同时的话你需要做的一件事情，就是对它的首字符去这里面进行深度优先的查找，看它有没有存在，那么它要做的一件事情，在这个地方的话，它会再去里面搜索一遍就变成m*m，每个的话都会在这个里面遍历一遍它相应的字符，最后看它有没有，这用这种办法写的话，就是所谓的O(n*m*m)的一种办法，
   而且它最坏的情况下的话，因为它每次首字母要这么查一遍，其他的字符的话会每次向不同的通路再分散出去，分散四次，所以这里的话其实还要再*4^k，这里的4表示它的通路为四个通路，k次方指的是单词，这个单词的平均的长度是多少
   所以它的总复杂度是这样子O(N*m*m*4^k)的
   这个方式大家要做的话，就做它的word search 1，把这个地方的-II的话就不要了，你去做word search 1，word search 1的话，那个题目的话数据没那么复杂，它的数据规模没那么大，你用任何傻搜的办法都可以解决，你直接做那个题目就行。
2. Trie：直接说Trie这种做法，以及我们来理解一下为什么它会更好
   首先Trie这个做法应该怎么弄？
   它的本质就是你在这里看，你与其通过这里开始来找和回溯，
   这里有个最关键的一个问题，就是说你所有不断地从任何一个字符开始查找的时候，都要判断它，比如说我从任何一个地方，从k开始好了，k的话，往上走到a就变成ka，
   关键是你要让搜索怎么快的话，就是你能够最高效地知道，而且最早地知道ka是不是在这些候选单词里面存在，且是任何一个单词的前缀，如果ka不是任何一个单词的前缀，那么ka找到这里就不用找了，那就直接找kn或者找ki或者kr或者是找kl对不对，
   同理从t这个单词假设向外开始搜索的话，t往上走到ta这时候可以很方便的一点看到，ta不是任何单词的前序，那就不用找了，对吧，
   就找te假设te是前序、也不是，那就不用找了，假设从e这里开始，en不是任何单词的前序，但是ea是这个单词的前序，那么我们就可以继续再往下找，同时找ea的话，其他的单词的前序就直接在这个单词里面，看它的剩下的前序是不是也照样去符合这么一种思路，
   所以用Trie的话就变成这样：
   a. 首先第一步所有的words全部放到一个Trie里面去，构建起这样一个字典树，当然这就可以让prefix全部可以高效地查询了，
   b. 接下来就是对于board对于二维的字母矩阵，所谓的进行一次DFS，它的起点的话就是遍历每一个字符，通过这个起点的位置开始做一个DFS，
      DFS产生了任何的字符串，去这里面查，看是不是它的子串，如果是它的子串且最后存在的话，那么就输出，不然的话就不输出 
      你后来的话我们可以证明它这个时间复杂度的话，在现实中会快很多，大部分情况下，它的子串，它的前序串的话不在这里面存在，所以就直接可以抹掉了，
   c. 它的时间复杂度怎么算：在这里的话我不把答案直接告诉大家，你用类似的这种方法，去找一次它的时间复杂度是多少，当然你可以先写，因为这个好处的话，它已经帮你提示了，就要Trie就写。你可以先写，写完了之后你看这个程序对吧。
      你看到你的代码去分析它的时间复杂度是什么，然后你最后放在你的作业和总结里面去。
      因为像这种分析时间复杂度的话，像类似于这种样子分析时间复杂度的话，算是面试题中已经是要求最高的一种情况了。要是你自己可以琢磨出来的话，那么所有在面试题中，在面试的时候让你分析时间复杂度，你都是没有问题的，所以你们自己去完成这个。
   d. 代码:
      你首先的话把Trie的之前那个题目，上一个题目实现Trie的代码先要复制过来。
      因为的话你至少要用Trie对吧，然后循环这个words，把里面的全部单词都加到Trie里面去，
      接下来的话就开始遍历二维的字符矩阵，以它里面的每一个字符作为起点，开始做DFS，做DFS的话就是上下左右开始进行扩散，扩散出来的每一个中间的单词串的话，都去Trie里面查找，看它是否存在，是否有前序，然后最后的话就得出一个结论。
   e. 优秀题解: https://leetcode-cn.com/problems/word-search-ii/solution/dan-ci-sou-suo-ii-by-leetcode/  官方题解
####3. Search suggestion - system design

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
    private int count = 0; //集合的个数
	private int[] parent; 	
	public UnionFind(int n) { 		
	   count = n; 		
	   parent = new int[n]; 		
	   for (int i = 0; i < n; i++) { 			
	      parent[i] = i;		
	   }	
	} 	
	public int find(int p) { 		
	    while (p != parent[p]) { 			//路径压缩
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
#### BFS

#### Two-ended BFS双向BFS
那么双向BFS是什么样子呢？也很容易，与其从A开始一步一步扩散，我们直接这样做吧，既从A（起点）向右边一层一层扩散，也从L（终点）向左边一层一层一层地扩散。
所以这边第1层、第2层、第3层，同理对于L向左进行扩散，也是第1层、第2层、第3层、第4层，上面这个数字的话，就只针对A，对L的话就把它反过来：第1层、第2层、第3层，当它扩散的两层的结点有重合的时候，第1次重合的地方就是A和L两者之间的最短路径，把这边扩散的步数再加上右边扩散的步数，最后的话和就是最后的总的步数。
这就类似于我们要火车高铁要经过高山的时候，会打一个山洞，那么你要穿这个山的话，你从A点开始打洞，一直打到B点，那么更好的方式就是A和B同时往中间打就行了，最后在中间相遇的时候，那么更快，就是这么一个思路。
作业：总结双向BFS模板


#### 实战题目
##### 1. https://leetcode-cn.com/problems/word-ladder/ 单词接龙

##### 2. https://leetcode-cn.com/problems/minimum-genetic-mutation/ 最小基因变化

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
###1.效果、感受
 本周进度有所延迟，视频中高级搜索没有来得及看、其他两课虽然看了但是笔记没有做全，感觉第7周开始每周的时长比上半学期要多出一课的时长。
 ###2.学习过程
 先看录播，边看录播边做笔记，把笔记和每周的学习总结放到一个markDown文档里面，然后着手习题，用习题来检验学习效果。
 ###3.收获
 这七周对数组、链表、队列、栈、堆、图、递归、分治、回溯、dfs、bfs、贪心算法、二分查找、动态规划、Trie树、并查集、高级搜索、AVL树、红黑树均有一定掌握，但是由于底子较薄，只是建立初步印象，需要花更多时间去掌握。
 ###4.刷题笔记
 略