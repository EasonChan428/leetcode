class P0208ImplementTriePrefixTree {
    // 指向子节点的指针数组 children 对于本题而言，数组长度为 26，即小写英文字母的数量
    private P0208ImplementTriePrefixTree[] children;
    // 布尔字段 isEnd，表示该节点是否为字符串的结尾
    private boolean isEnd;

    public P0208ImplementTriePrefixTree() {
        children = new P0208ImplementTriePrefixTree[26];
        isEnd = false;
    }
    
    public void insert(String word) {
        P0208ImplementTriePrefixTree node = this; // this指向调用函数的对象,从根节点开始遍历
        for(char c : word.toCharArray()) {
            int i = c - 'a'; //得到该字符在数组中的坐标
            //如果正在遍历的该字母在上一个节点的数组坐标中没有记录，就新建一个字母节点在字典树中
            if(node.children[i] == null) {
                node.children[i] = new P0208ImplementTriePrefixTree();
            }
            //每一次生成字母都移动指针到下一个字母节点
            node = node.children[i];
        }
        node.isEnd = true; // isEnd=true只标记完整单词结束，不代表这个节点不能再有子节点
    }
    
    public boolean search(String word) {
        P0208ImplementTriePrefixTree node = searchPrefix(word);
        return node != null && node.isEnd;
    }
    
    public boolean startsWith(String prefix) {
        return searchPrefix(prefix) != null;
    }

    private P0208ImplementTriePrefixTree searchPrefix(String prefix) {
        P0208ImplementTriePrefixTree node = this; // 从根出发
        for(int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);
            int indax = ch - 'a';
            if(node.children[indax] == null) { // 子节点为空，说明前缀不存在，直接返回null
                return null;
            }
            node = node.children[indax];
        }
        return node; // 前缀全部匹配，返回当前节点
    }
}

/**
 * Your P0208ImplementTriePrefixTree object will be instantiated and called as such:
 * P0208ImplementTriePrefixTree obj = new P0208ImplementTriePrefixTree();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */