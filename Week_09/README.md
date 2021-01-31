#第八周 
##一、学习笔记

###20.1 字符串基础知识和引申题目
这一节的课程的话，会介绍字符串最基本的一些操作，然后最重要的就是在字符串里面如何运用算法，和其他数据结构来解决各种问题。
字符串本身的话在面试的时候出得比较频繁，所以这一章节的话也是比较重要的，同时的话会和前面的递归以及动态规划相结合，所以大家一定要反复对于这一章节的题目进行练习，都是很重要的题目。
#### 字符串基础知识
##### 字符串
字符串本身的话，我想只要写过编程的人在日常工作的时候都用过，那这里的话就把典型的三种语言，字符串怎么定义的，给大家放在这里“
- python
  x = 'abbc'
  x = "abbc"
- Java:
  String x = "abc";
- C++:
  string x("abbc");
  
string immutable:
https://lemire.me/blog/2017/07/07/are-your-strings-immutable/
In Java, C#, JavaScript, Python and Go, strings are immutable. 
In Ruby and PHP, strings are mutable.
The C language does not really have string objects per se. However, we commonly represent strings as a pointer char *. In general, C strings are mutable. The C++ language has its own string class. It is mutable.
In both C and C++, string constants (declared with the const qualifier) are immutable, but you can easily “cast away” the const qualifier, so the immutability is weakly enforced.
C最典型的C的话肯定是可变的，那个时候的话都没有字符串，它就是一个字符数组，最后的话你是用-0来结束的。C++的话就出来了String，但是它是mutable的，同时的话它也跟你说了。
如果你要让它变成不可变的，那就加const即可。
In Swift, strings are mutable.However, if you declare a string to be a constant (keyword let), then it is immutable.
我想给大家提醒一句，就是说python和java的话，这个String它是immutable的，immutable的话指的是不可变的，就是这个单词immutable的，也就是说你定义了这个String之后，它就是不可变的，当你把它加一个字母或者减一个字母的话，它其实是新生成了一个String，原来的String的话还是原来的内容。
C++的话它是可变的，把这一点大家一定要记清楚。
所以的话不管你用python还是用java的话，你每次改变String里面的内容的话，其实你都是创建了一个新的String。
immutable的话有好处，就是说它是线程安全的，那么，可变的话就会有可能再多线程的环境里面有一些问题。

关于可变和不可变的性质的话，有可能在面试的时候面试官会问你，所以大家一定要弄清楚。
##### 遍历字符串
- python: 
  for ch in "abc":
     print(ch)
- java:
  String x = "abc";
  for(int i=0;i<x.length();i++){
    char ch = x.charAt(i);
  }
  for(char ch : x.toCharArray()){
    System.out.println(ch);
  }
- c++:
  string x("abc");
  for(int i=0;i<x.length();i++){
      count << x[i];
  }
##### 字符串比较
java :
String x = new String("abc");
String y = new String("abc");
x == y --> false：主要是java这块，以及python和javascript里面也有相应的问题，就是说如果在java里面x==y的话，它是比较它们的指针，比较它们的reference的地址，而不是比较字符串里面的内容
x.equals(y) --> true
x.equalsIgnoreCase(y) --> true
#### 基础问题
##### 1. https://leetcode-cn.com/problems/to-lower-case/  转换成小写字母
最简单的题目而且是最基础的字符串的操作运算，自己练习
##### 2. https://leetcode-cn.com/problems/length-of-last-word/  最后一个单词的长度
最简单的题目而且是最基础的字符串的操作运算，自己练习
##### 3. https://leetcode-cn.com/problems/jewels-and-stones/  宝石与石头
最简单的题目而且是最基础的字符串的操作运算，自己练习
##### 4. https://leetcode-cn.com/problems/first-unique-character-in-a-string/ 字符串中的第一个唯一字符
1. brute-force:
  i 枚举所有字符
    j 枚举i后面的所有字符  //找重复
O(n^2)
2. map (hashmap：哈希表实现,O(1)找重复, treemap：二叉搜索树实现,O(logN)找重复)
O(N) or O(NlogN)
3, 用字母的对应的数组，用字母它对应的下标来统计，这个的话其实就是一个最简单的哈希表。也就是说字母你直接去算一下，它到底有多少次出现就这样
##### 5. https://leetcode-cn.com/problems/string-to-integer-atoi/ 字符串转换整数 (atoi)

#### 字符串操作问题
##### 1. https://leetcode-cn.com/problems/longest-common-prefix/description/ 字符串公共前缀
1. 纯暴力：从最小的单词开始枚举，每次枚举它自己和它的前缀，然后看在其他的字符里面能不能。O(n^2*m,m是单词的平均长度)
2. 更快一点的办法：写两层嵌套的循环，把字符串对齐排在一起，然后来检查第1列是否相同，相同的话看第2列，也相同的话去看第3列，最开始出现有一列字符是不相同的那就停止把前面的输出即可
3. Trie
4. 分治：时间复杂度并不好
##### 2. https://leetcode-cn.com/problems/reverse-string/   反转字符串
头指针和尾指针，一个不断地加加，一个不断地减减，然后交换两个数，一定要滚瓜烂熟
##### 3. https://leetcode-cn.com/problems/reverse-string-ii/   反转字符串 II
自己练一下
##### 4. https://leetcode-cn.com/problems/reverse-words-in-a-string/  翻转字符串里的单词
C语言尝试用O(1)的办法，
JAVA或者Python，因为它String的话都是不可变的，那就直接用系统函数就行了
这个题目我拿出来，就是因为在实际当中的话，大家应该用得比较多。
1. split,reverse,join:直接把s trim之后split，代码里面+的话指的是可以多个空格，它就按照空格把它分开成一个数组，每一个数组里面的元素就是一个单词，然后把这个数组反序，反序了之后再连接起来，用空格隔开
    如果想直接在内部进行交换的话，也可以尝试去写一下，
2. reverse整个string，然后再单独reverse每个单词(翻转两次，一开始翻转整个字符串，接下来翻转里面的每一个单词)：O(N)
##### 5. https://leetcode-cn.com/problems/reverse-words-in-a-string-iii/ 反转字符串中的单词 III
自己做
##### 6. https://leetcode-cn.com/problems/reverse-only-letters/  仅仅反转字母

#### anagram 异位词问题
##### 1. https://leetcode-cn.com/problems/valid-anagram/  有效的字母异位词
以前讲过，放在这里是为了完整性
##### 2. https://leetcode-cn.com/problems/group-anagrams/  字母异位词分组
以前讲过，放在这里是为了完整性
##### 3. https://leetcode-cn.com/problems/find-all-anagrams-in-a-string/ 找到字符串中所有字母异位词
类似滑动窗口，看长度为3的子串，拥有一个长度为3的窗口，这个窗口的话从左边慢慢向右边滑，每次滑一步，你就看这个窗口里面单词和p是不是异位词对吧
可优化的一个地方，它每次都是增加一个字符，再减少一个字符，所以的话如果你是用map来存的话，就可以很方便地把出窗口的单词减掉，把进窗口这个单词再加进来，把进窗口这个单词再加进来，然后看和它这个单词是不是异位词

#### palindrome 回文串问题
##### 1. https://leetcode-cn.com/problems/valid-palindrome/  验证回文字符串 
自己做和练习
##### 2. https://leetcode-cn.com/problems/valid-palindrome-ii/ 验证回文字符串 Ⅱ
自己做和练习
##### 3. https://leetcode-cn.com/problems/longest-palindromic-substring/  最长回文子串
在第2节高级字符串算法中讲

###20.2 高级字符串算法

##二、学习总结

