#第二周 
##一、学习笔记
##5.1哈希表、映射、集合的实现与特性
1. Hash table
哈希表（Hash table），也叫散列表，是根据关键码值（Key value）而直接进行访问的数据结构。<br>
它通过把关键码值映射到表中一个位置来访问记录，以加快查找的速度。<br>
这个映射函数叫做散列函数（Hash Function），存放记录的数组叫做哈希表（或散列表）。
工程实践：电话号码簿、用户信息表、缓存（LRU Cache）、键值对存储（Redis）
Hash Function：举例，lies：每个字符串的ASCii码mod一个数得到9，放到index为9的位置
Hash Collisions：不同的要存储的数据，它经过哈希函数之后会得到一个相同的值，对应的下标相同，则发生hash碰撞。那么说明，该下标处要存多个元素，一个的话依次往下面存占别人的位置，
当然最好的一种方式，或者是相对在工程上常用的方式，就是再增加一个维度，这个位置存多个数，也就是从这里拉出来一个链表，这样的方法叫做拉链式解决冲突法
2.Map和Set：在真正的工程代码里面，我们经常在用的话就不再试哈希表了，而是在哈希表基础上抽象出来的。经常抽象出来使用的比较多的是Map和Set
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
课后作业
写一个关于 HashMap 的小总结。（重点看put和get）node分为hashNode和treeNode
##5.2
排序：所有面试的时候，排序不要自己写，直接调Arrays.sort()即可
有效的字母异位词：1.暴力：sort,sorted_arr是否相等  O(NlogN) 2.hash，map-->统计每个map的频次，技术处理：第1个单词碰到相应计数+1，第2个单词碰到相应计数-1，最后判map是否为空；用长度为256的数组来计数，因为ASCII码的范围为0-255，长度为256的数组来计数，其实它也是一种简化了的hash表，只不过它的hash函数是它的ASCII码的值
字母异位词分组：排序，用排序后的结果作为key，存到一个map里面，最后把map的值归类之后输出
两数之和：hash表，a,b--> a+b == target --> for each a:check(target - a) exists in nums
学习方法：养成收藏精选代码的习惯（把题目中多种解法及要点罗列起来），每次面试之前会看一下这些代码。这样真正面试下笔如有神，写出来是多种解法，且皆是比较优化的代码
         养成这种总结代码的习惯，同时形成自己的这种至少是算法和数据结构的一个精选代码库的概念，同时还可以把它放到github上面