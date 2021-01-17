class Solution {
    char[] chars = new char[]{'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> set = new HashSet<>();
        set.add(beginWord);
        set.addAll(wordList);
        int steps = 0;
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i=0;i<size;i++){
                String poll = queue.poll();
                if(poll.equals(endWord)){
                    steps++;
                    return steps;
                }else{
                    int len = poll.length();
                    for(int j=0;j<len;j++){
                        char s = poll.charAt(j);
                        StringBuilder sb = new StringBuilder(poll);
                        for(char c:chars){
                            if(s==c) continue;
                            sb.setCharAt(j,c);
                            String newStr = sb.toString();
                            if(set.contains(newStr)){
                                set.remove(newStr);
                                queue.offer(newStr);
                            }
                        }
                    }
                }
            }
            steps++;
        }
        return 0;
    }

}