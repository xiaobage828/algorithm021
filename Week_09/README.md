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


##二、学习总结