#第三周 
##一、学习笔记
###7.1泛型递归、树的递归
之前讲了关于树的，发现很多时候，树的数据结构很多都是递归型的定义，所以这一课我们重点来讲递归，也就是所谓的泛型递归以及关于树的各种递归。
1. 前序知识回顾：
树的面试题解法一般都是递归，为什么？1.节点的定义：它的结点和树本身，它数据结构的定义，就是用递归的方式来进行的。2.重复性（自相似性）不仅是树本身、二叉树以及搜索二叉树，它在定义它数据结构和它的算法特性的时候，也是用所谓的重复性，也就是自相似性。
比如说二叉搜索树的话，就会是左子树都要小于根结点，右子树都要大于根结点，且左右子树具有相似的特征；或者是说以此类推左右子树也满足以上特点。

那么递归本身的话具体有哪些要注意的点，和它本身的特点，以及相应的递归的面试题会是什么，这就是我们这一节课的重点所在
2. 递归Recursion
递归-循环
通过函数体来进行的循环（递归其实本质上它就类似于循环，只不过是通过循环体调用自己，来进行所谓的循环）
为什么会有这样的一种形式？
就是因为在计算机语言在创造的时候，它本质上就是汇编嘛，而汇编的话，它有个特点，就是说它没有所谓的循环嵌套这么一说，很多时候它更多的用的就是，你之前有一段函数写在什么地方，或者你一段指令写在什么地方，你就直接不断地反复跳到之前的那段指令，不断地去执行，其实这就是所谓的递归。
而循环本身的话，你可以把它编译出来，你看它的汇编代码的话，其实和递归本身的话有异曲同工之处。所以递归和循环他们没有明显的边界
递归从现实意义上讲的话，就是我们小时候讲的最多的一个故事
1.从前一个山
2.山里有个庙
3.庙里有个和尚讲故事
4.返回1
可以发现，在我们现实生活中，很多时候也有所谓的重复性，而这种重复性的话，其实用计算机解决的话，就能够给大家省很多的事情
找到这种归去来兮的感觉，类比：盗梦空间
递归的特点：
- 向下进入到不同梦境中；向上又回到原来一层（一般来说它不能直接跳跃，而是必须一层一层地下，然后一层一层的回来，所谓的就是有一种对称性，或者叫做归去来兮的感觉）
- 通过声音同步回到上一层（所谓这种同步的关系就是用参数来进行，函数不同层之家的传递变量）
- 每一层的环境和周围的人都是一份拷贝、主角等几个人穿越不同层级的梦境，发生和携带变化
 （也就是他进到每一层的话，这每一层的房子建筑和那些电脑，我们所谓的无关的人物的话，其实就是你就类似，于是硬生生地整个创造了一份新的世界，你把这个世界全部都打坏了之后，你去到下一层，或者是回到上一层，上一层的世界里面的楼，还是不受影响的，一般来说是这样的。
 然而主角等几个人的话就是主角那个团队，他可以穿越不同的梦境，同时把自身和自己所要携带的东西，都可以带到不同的梦境里面去发生变化，且把变化可以携带回来。
 而这样主角团队，这个就类似于函数里面的参数，同时还会有一些全局变量）
通过现实中盗梦空间的这样一个跳跃的含义，给大家说一下递归的话它整个发生和现实中怎么联系在一起的
那么这三点的话，我希望大家自己在脑子里面多思考一下，思考好了之后，平时在做类似于题目的时候、写程序的话都想到盗梦空间，来想一下自己怎么弄
3. 最简单的递归：计算n!  （递归栈见视频）
写一个函数即可，每次就括它自己掉n-1。那么递归它最后的运行方式的话就成了这么一个所谓的递归栈，就一层一层展开，而这种形式的话就是更像一种剥洋葱的形式，
所谓剥洋葱类似于一个栈的形式一层一层一层进去，然后把它剥开，而栈本身的话就是递归调用的时候，它系统就给我们做了一个这样的调用栈
4. 参考链接：递归的代码模板https://shimo.im/docs/EICAr9lRPUIPHxsH/read
递归终结条件->处理当前层逻辑->下探到下一层->清理当前层
-- Python
def recursion(level, param1, param2, ...): 
    <!-- recursion terminator -->
    if level > MAX_LEVEL: 
	   process_result 
	   return 
    <!-- process logic in current level --> 
    process(level, data...) 
    <!-- drill down -->
    self.recursion(level + 1, p1, ...) 
    <!-- reverse the current level status if needed -->

// Java  本身的话代码不长，结构的话更加重要，记住这四个结构模块，总共的话就是四块，整个递归代码
public void recur(int level, int param) { 
  // 第一部分的话就叫recursion terminator：递归终结条件（也就是写递归函数开始的话，一定要记得先把函数就是递归终止条件给写上。这一点的话如果不注意的话，最后造成的结果就是无限递归或者叫做死循环。类似于死递归之类的，就这个函数出不来了，你就只能强行把这个程序给杀掉）
  if (level > MAX_LEVEL) { 
    // process result 
    return; 
  }
  // 第二部分：process logic in current level ：处理当前层逻辑（就是你到了这一层，这个递归的层次的话，你要处理这一层要进行的逻辑就在这里的话，完成这一层要进行的业务代码逻辑代码）
  process(level, param); 
  // 第三部分：drill down ：下探到下一层（下到下一层的话，这里的话，它的参数来标记当前是哪一层，就level这里level就必须加一，同时把相应的参数p1p2p3放下去就行）
  recur( level: level + 1, newParam); 
  //第四部分：reverse/restore/clean/store the current level status if needed ：清理当前层
  //（要是递归完了，最后一部分你这一层有些东西可能要清理，就把它清理。
  //有些时候我们不需要清理这一层，因为的话它这一层的很多时候，它这一层本身的那些环境的话，它是拷贝一份出来的，对吧。
  //但很多时候的话会有一些全局变量，还有其他的一些事情你要进行清理的，那你就在最后这一部分来进行清理即可）
}

// C/C++
void recursion(int level, int param) { 
  // recursion terminator
  if (level > MAX_LEVEL) { 
    // process result 
    return ; 
  }

  // process current logic 
  process(level, param);

  // drill down 
  recursion(level + 1, param);

  // reverse the current level status if needed
}

// JavaScript
const recursion = (level, params) =>{
   // recursion terminator
   if(level > MAX_LEVEL){
     process_result
     return 
   }
   // process current level
   process(level, params)
   //drill down
   recursion(level+1, params)
   //clean current level status if needed
   
}
5. 思维要点
递归难以领悟或者难以理解的话，对症下药（提的三点，大家要注意的）
1.不要人肉进行递归（最大误区）：这一点主要是针对如果大家后来递归开始慢慢比较熟练了之后，一定要抛弃这样的习惯，就是不要再进行人肉递归了。如果你刚开始学的话，你可以在纸上把递归所进行的状态，也就所谓的递归的状态树画出来可以。但是在后面的话，我希望大家主动抛弃人肉递归，以及要画递归的状态树这么一种特点。你一定要记得直接看函数本身开始写即可，不然的话你永远没法有效地掌握，或者是熟练地使用递归
2.找到最近最简方法，将其拆解成可重复解决的问题（重复子问题）：为什么要找最近重复子问题？就是因为我们写的程序的指令，只包括if else然后for和while loop，然后就是第三部分的话，就是递归调用。正是因为我们所有的代码，只有这三部分，所以最后你的程序肯定，如果是比较复杂的程序，你个人觉得比较复杂，不就是它逻辑相对来说比较多。逻辑比较多，但却可以用，比如说五行语句或者十行语句解决。为什么？就是因为这个很复杂，你觉得很多的逻辑本身的话，有所谓的可重复性
3.数学归纳法思维：其实就和上面是一样的。数学归纳法的要点：就是最开始最简单的条件是成立的，比如说n=1、n=2的时候是成立的，且第二点你能够证明当n成立的时候，可以推导出n+1也成立的。那么就类似于放鞭炮一样，第一个我保证可以我把它点燃，同时我还可以保证前一个炮竹炸的时候，后一个炮竹也会炸。那么这一串炮竹一百响，或者是一千向的炮竹，只要我点燃第1颗，后面肯定都可以证明能够爆炸的。
这就是所谓的递归的理论部分

6. 注意
1. 4链接中的代码模板认真去看，然后记下来，一定要养成机械化的记忆。也就是说一开始写递归的话，这个模板啪啪啪四部分马上写下来。这四部分脑子里面也很清楚
2. 5三个思维的要点：大家一定注意 一、抵制人肉递归的诱惑，二、找最近重复性，三、数学归纳法的思维
###7.2爬楼梯、括号生成等问题
####思维要点
1. 不要人肉进行递归（最大误区）
2. 找到最近最简方法，将其拆解成可重复解决的问题（重复子问题）
3. 数学归纳法思维
所有递归的题目要干嘛？这三点找最近重复性中间的这样
第二点，如果它本身很复杂，就是说没有任何重复性的话。有没有可能没有任何重复性？
假设没有任何重复性的话，就说明它的复杂度是客观存在的。有多少复杂的逻辑的话，就写多少复杂的代码，代码就是几千行几万行都有可能，这种情况的话的确只能这样
先倒推思考n、n-1、n-2...（其实是找递归树的展开结点），然后正推思考1、2、3...
不过话说回来，所有的面试题的话，基本上就是在五行十行最多二十行以下可以完成的，说明它肯定有所谓的重复性
####climbing-stairs
思考本身的话也很简单，主要是从这个问题能够抽象出它的重复性，就是在于你解题的能力了。怎么抽出来，就是高中解数学常用的办法——数学归纳法
首先如果直接思考n的话比较难，那我们就思考n=1的情况怎么办，n=2的情况怎么办，以及比如说n=3的情况怎么办
对吧，在这里1和2的话都很好理解，1的话就走一步就行，2的话就一下子干吗，走一步走一步或者走一步走两步，
关键是3的时候怎么思考，3的时候要思考的时候就不是要穷举了，
这里穷举的话其实是一种人肉递归，你要是人肉递归的话，那你4的时候就更麻烦了，你可能根本就猜不出来，
接下来怎么办，就是找所谓的重复性（其实是找递归树上走过的结点），
所以3这里的话你就要这么想，你要上到3的话应该怎么上，其实只有两种走法，只有两种可能性，一个的话就是跨一步台阶，从2这个楼梯走上来，走到3对吧，或者是你跨两步，从1这个台阶走到3，
也就是说你走到3的话，它的总的走法，只可能是1的楼梯的总的走法，然后跨两步跨上3，或者是2这个楼梯的总的走法，跨一步走上3，
这样一个思维特点和思维习惯的话，我想要大家养成这样的一个办法
所以在这里上第三极台阶的话，它就等于上第一级台阶总步数，加上上第二级台阶的总步数。
在这里你可能还要简单思考一下，有没有所谓漏算的情况，没有，
另外这两个中间有没有重复计算的步数，也是没有的，因为你最后一步都不一样，1到3最后一步要跨两级，2到3最后一步只能跨一级，所以的话最后一步它铁定不一样，所以这两个的话也没有重复的
那这样的话，我们就可以证明它们是没有所谓的重复计算，且是能够包含所有情况的，
这个英文叫mutual exclusive和complete exhaustive，指的是所有这些分项，它们都是互斥的且它们加在一起的话，把所有的可能性都已经包含了，那么这两个问题这一步的话，其实就是这个题目的解题的关键
解完了你要是想到这样的话，这个题目就很简单了
那么就把它泛化成n的形式，n的话怎么办，n的话就是走到第n级台阶的总的步数，就等于n-1级台阶总步数再加上n-2级台阶总的步数：f(n)=f(n-1)+f(n-2)，这个所谓递推公式就类似所谓Fibnacca数列了
这个题目的话转换为求Fibnacca数列即可
Fibnacca数列有很多办法，第一个就是傻递归，时间复杂度太高了（用缓存优化即可）
第二个就是动态递归，用一个数组就行循环
第三个就是所谓的二维矩阵来求解（感兴趣）

####generate-parentheses 例题，初学者必写
这个是典型的一个递归的题目，人肉递归很麻烦，n=4的时候可能就有点晕了，对吧，要在纸上慢慢一步一步写
先不考虑括号的合法性
分析及写代码过程见视频
这里要判断合法性的话，关键有两点，就是一个括号在中间生成的时候怎样是合法的
left括号 随时可以加，只要别超标
right括号 左个数>右个数（必须之前有左括号，且左个数>右个数）
但是这里你会发现和上一个程序比起来的话，它少做了不少的无用功，这里的无用功为什么不需要做，就是因为我们提前就判断它不合法了之后，
我们在上传中间结果的时候，就剪掉了一些没用的分支，这就是所谓的剪枝，或者是提前去掉了一些肯定不合法的形式，就得到了这样的代码

最关键的就是我刚才整个怎么开始思路写递归，怎么决定它的参数，以及在后面的话怎么一步一步把递归本身，来给大家化简
为什么可以七行解决这种人脑思考起来，相对特别复杂的一个问题，也很简单，就是因为人脑平时思考的时候，老是喜欢暴力递归，而不去找它的所谓的额重复性，你要是认真去思考它的重复性之后，你会发现它重复性的话，
其实化简下来的话，逻辑的代码就这两个，如何用左如何用右，以及全部用完了之后输出即可。正是这样的话，我们刚好可以用支持重复的计算机的语言来表达
那么你可以看计算机编程在这个地方的对于解决生活中有反复重复的问题，就可以有非常强大的一些运用了

优秀题解：光头哥，StefanPochmann，经常会玩一些代码艺术
各位的话也有兴趣的可以看一下，对你阅读代码的能力有理解。我想各位想要找到一种感觉，我强调过很多次了
要找到一种感觉，就是爱上看别人的代码，或者是你看别人的代码，这个地方还可以这么写好厉害。原来这个还搞的这个样子
而且你看到别人特别工整、特别简单，特别是那种重剑无锋大巧不工的代码的时候，从他那里感觉，马上就想把他这个代码给抄下来，同时自己练会学会写出来，代码也保持这个样子的话，那么我觉得你已经上道了，这个课程最重要的道已经学到了
所以大家把这个代码养成阅读的习惯，和养成模拟和自己不断反复地写，把自己的代码提高养成一个好的习惯
那么接下来就是所谓的自己要多训练和自己做homework的作业了


 实战题
#### invert-binary-tree/description
#### validate-binary-search-tree   
难：递归，BST-->中序遍历是递增的
#### maximum-depth-of-binary-tree（高频题）
难：找出重复性：最大深度：左子树的深度+root，右子树的深度+root。。。
#### minimum-depth-of-binary-tree 
####serialize-and-deserialize-binary-tree

####homework
1. lowest-common-ancestor-of-a-binary-tree：二叉树最近公共祖先（高频老题）
2. contruct-binary-tree-from-preorder-and-inorder-traversal
3. combinations  排列/组合，必做
4. permutations
5. permutations-li
###8.1分治、回溯的实现和特性
分治和回溯的话，其实本质上就是递归。只不过它是递归的其中一个细分类。你可以认为分治和回溯，最后就是一种特殊的递归，或者是较为复杂的递归即可。
我想要各位最后能达到的效果就是说，碰到一个题目你就找它的重复性，重复性有最近的重复性，或者是有所谓的最优重复性。
那么最优重复性就是动态规划。
最近的重复性的话，根据重复性怎么构造、以及怎么分解有什么分治或者是最后要回溯或者是最后要回溯或者是在其它的各种办法，但本质上其实就是一种递归。
而本质上的话其实就是找它的重复性。
####分治Divide & Conquer

- 递归状态树
见视频图
- 分治它的思想是什么？
它其实就是一种递归，只不过在它的递归的状态树的时候，对一个问题，它要化解成好几个子问题。
其实基本上也就是废话。
举个例子，什么地方不需要化成一个子问题的，基本上是没有。
如果我非要给你举个例子，你可以认为之前求阶乘的题目，每次你要求n的阶乘，你就转换成n再乘以n-1，它的子问题其实就只有一个，你要求n的阶乘对吧，就是它再乘以n-1，所以它的子问题可能没那么多，但的确它有子问题。
而其他的比如说Fibonacci数列，比如说爬楼梯问题，比如说抛硬币问题这种，都是分解成多个子问题。所以在之前的话，我们那些递归其实多多少少都需要分治。
- 为什么很多的问题都需要分治？
其实也很简单，因为它一个大的问题之所以为大问题，肯定是有很多细的子问题组成，不然的话它就不算一个大问题，或者是不算一个复杂的问题了。
还有人可能会问了，那是不是没有这么乘，也有可能，假设没有这么乘的话，其实也不需要递归了。
所以既然这个问题看似比较复杂，或者是相对来说能进入比较高级的面试，要用高级的算法的话，那它来说是相对复杂的，要相对复杂的话，
那要怎么用程序解决，其实就是找重复性，找到重复性之后你会发现一个大问题，它肯定是重复的话，那就能够分成若干的子问题，最后就变成分治这样一种算法了。
这样理解和这样的一个逻辑的话，希望让大家可以透过这些现象看到问题的本质，就是重复性，好吗？
这样说来的话整个分治就比较好理解了，不需要大家去打开百度百科之类的东西，把分治法或者是像其他的算法书上，分治是干嘛干嘛的，或者是什么去背毫无意义
##### 所以不管是递归不管是分治或者是回溯，或者是其他的叉叉叉的办法的话，其实本质上就是找重复性以及分解问题和最后组合每个子问题的结果，都是这一个思路
- 分治的话应该怎么求？
就假设这么一个问题，这个问题的话就是为了讲分治而讲分治构造出来的，所以相对来说比较简单
字符串abcdefghij，把它转为大写字符串该怎么做？
分治是什么，
先拆分成子问题，每个子问题就是相应每个字符，那么你要做的就是对每一个字符都转换成一个大写的，然后最后再拼回去，然后就变成一个大写的。
####分治代码模板
其实分治的代码模板的话和递归的代码模板是一模一样的。
默写泛型递归的代码模板：1.recursion terminator 2.process logic in currrent level 3.drill down 4.clear the current level status
这里分治的话其实和它你从概念上或者结构上可以说是一模一样的就行了，它最后的话也需要reverse state，
但是在这里多加了一个，就是把这些结果组装成一个大的结果，最后返回
你如果非要说它和泛型递归有一点不同的话，就是它最后的话，这些子结果它要再进行一次组合就这样
那么泛型递归的话，有些时候你直接在这个地方（第三步）就得到结果了，
比如说之前写的左括号右括号，不断地排列组合的，就是拼出各种排列组合的结果的话，就可以直接在这里print。但有些时候的话，你必须把每一个子问题它的中间结果全部都组合起来，然后得到最终的结果再返回回去，可能这一点就是一个小区别
当然在最后的话也需要revert，所以在这里的话，你可以认为它是比递归的话多了一步，多的这一步在drill down和revert state中间再加一步，就是把这些drill down得到的这些子结果要合并在一起，返回回去
这样理解的话，我想递归分治回溯，其他的都可以把它串在一起，其实它们思想都是一致的
1. Java
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
             
     //这里最关键一点是给你一个现实的现实题目或者面试题目。怎样把它拆分成子问题比较重要，主要看经验，而这个的话其实就是CEO，或者是所谓的架构师经常完成必做的事情，
     //还有一点就是说怎样来merge这些subresult，得到这些子结果把它合并起来可能也有个讲究
     //还有中间的话可能还需要做一些事情，假设比较复杂的这种分治的话，或者是比较复杂的公司组织结构的话，经常要做一件事情，就是中间的结果，比如说子结果一子结果二子结果三，如何做质量控制和质量保证的，也就是下面的人给你一个返回结果的话，你怎么知道这个他做的好还是不好，以及在公司的时候根据这些结果的好坏，给下面的完成这些子任务的人以相应的激励，这就成为了一个比较丰满的公司的组织文化
     //所以你来看它其实就是分治的结果
     //这里还有一点就是，和自订向下的编程思想一致，当前层你就只要考虑当前层的问题，
     //一般来说不要下探，或者至少不要下探太多，一方面的话人脑不太擅长用人肉递归，当你做人肉递归的话，你会觉得经常累，而且容易经常搞错，这是第一个
     //第二个的话，如果你一竿子要插下去，干扰别人做事的话，其实在公司组织结构，这是一种微管理或者是事无巨细的话，很讨厌的，个人是这么觉得，程序也如此
2. Python
def divide_conquer(problem, param1, param2, ...): 
  // recursion terminator 
  if problem is None: 
	print_result 
	return 

  // process current problem
  data = prepare_data(problem) 
  subproblems = split_problem(problem, data) 

  subresult1 = self.divide_conquer(subproblems[0], p1, ...) 
  subresult2 = self.divide_conquer(subproblems[1], p1, ...) 
  subresult3 = self.divide_conquer(subproblems[2], p1, ...) 
  …

  // meger
  result = process_result(subresult1, subresult2, subresult3, …)
	
  //revert the current level states
  
3. C/C++
int divide_conquer(Problem *problem, int params) {
        
       // recursion terminator
       
  if (problem == nullptr) {
    process_result
    return return_result;
  } 

       // process current problem
  subproblems = split_problem(problem, data)
  subresult1 = divide_conquer(subproblem[0], p1)
  subresult2 = divide_conquer(subproblem[1], p1)
  subresult3 = divide_conquer(subproblem[2], p1)
  ...

       // merge
  result = process_result(subresult1, subresult2, subresult3)
           
       // revert the current level status
 
  return 0;
}

4. Javascript
const divide_conquer = (problem, params) => {

     // recursion terminator

  if (problem == null) {

   process_result

   return

  } 

    // process current problem

  subproblems = split_problem(problem, data)

  subresult1 = divide_conquer(subproblem[0], p1)

  subresult2 = divide_conquer(subproblem[1], p1)

  subresult3 = divide_conquer(subproblem[2], p1)
 
  ...
  
    // merge
  
   result = process_result(subresult1, subresult2, subresult3)
  
    // revert the current level status
  
 }
 
 ####回溯Backtracking
 另外一种特殊的递归就叫做回溯。之所以叫回溯的话，我（超哥）个人觉得，你就把它理解成一种递归的情况
 百度百科的解释：
 回溯法/试探法采用试错的思想，它尝试分步的去解决一个问题。在分步解决问题的过程中，当她通过尝试发现现有的分步答案不能得到有效的正确的解答的时候，
 它将取消上一步甚至是上几步的计算，再通过其它的可能的分步解答再次尝试寻找问题的答案。
 回溯法通常用最简单的递归方法来实现，在反复重复上述的步骤后可能出现两种情况：
 找到一个可能存在的正确的答案；
 在尝试了所有可能的分步方法后宣告该问题没有答案。
 在最坏的情况下，回溯法会导致一次复杂度为指数时间的计算
 最新的百度百科链接： https://baike.baidu.com/item/%E5%9B%9E%E6%BA%AF%E7%AE%97%E6%B3%95/9258495?fr=aladdin
 这里的话想表达的一个意思是：就是不断地在每一层去试就行了，你每一层有不同的办法，就是类似于这样，那么一个一个一个去试，看这个方法行不行。
 它最典型的应用是在八皇后问题以及数独上面
 回到括号生成这个题目：
 假设我们没有采用最开始最简洁的形式，而是相当于2n个格子，每个格子里面都放左括号和右括号，都可以放的情况下
 其实就类似于一个回溯，在中间这里的话，就是把所有可能的解，或者所有备选的括号都往里面放，放到最后之后我们再来看这里放左括号，到底是行还是不行，只不过你在回溯的时候，
 你要是尽早地可以判定，你当前的结果是不行的，你其实可以约节省你的执行次数，以及执行时间对不对。
 那么我当时最暴力的一种回溯的话，就是把六层全部都建好了，且不管在中间的时候可能左括号就超标了，或者右括号和左括号就不匹配了，就右括号就是多于左括号了。
 像这种最暴力的回溯的话，你只有在最后去判断，那么发现不行，然后就把这个结果给卡掉，然后再继续回到前面几层再继续摆，
 类似于这样，这也是一种相应可以叫回溯的办法，
 那么它的代码模板的话，其实就类似于用这样的一个泛型递归的代码模板即可。
 在这里回溯本身的话，其实它有一种所谓的归去来兮的感觉。
 这种感觉的话不管在这个题目的话，大家有所感觉归去来兮，整个递归的话其实也是所谓的归去来兮的感觉
 
 另外的话后面讲八皇后，以及在最后的话，我们讲位运算的时候，再来说八皇后的终极解法的时候，我们会来反复讲整个回溯，其实本质上就是递归的思想
 
 
 ##二、学习总结：
 ###1.效果、感受
 本周进度比课程安排进度稍慢，视频中分治、回溯的实战和习题部分还未观看。感觉通过这三周的努力学习，整个学习状态和效果都比较好了。
 ###2.学习过程
 先看录播，边看录播边做笔记，把笔记和每周的学习总结放到一个markDown文档里面，然后着手习题，用习题来检验学习效果。
 ###3.收获
 这三周对数组、链表、队列、栈、堆、图、递归、分治、回溯均有一定掌握，但分治、回溯因为没做习题，而疏于掌握，这块需要马上补上。
 ###4.刷题笔记
 见第二周的学习总结第4点，有把练题过程中对一些JDK的api做了补充，但对于好的代码暂时没有记录。