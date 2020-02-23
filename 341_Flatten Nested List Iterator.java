// Solution 1: DFS to flatten the nestedList to arrayList
public class NestedIterator implements Iterator<Integer> {
    
    private List<Integer> flattenList;
    private Iterator<Integer> iterator;
        
    public NestedIterator(List<NestedInteger> nestedList) {
        flattenList = new ArrayList<>();
        dfs(nestedList, flattenList);
        iterator = flattenList.iterator();
    }
    
    private void dfs(List<NestedInteger> nestedList, List<Integer> flattenList) {
        for (NestedInteger n : nestedList) {
            if (n.isInteger()) flattenList.add(n.getInteger());
            else dfs(n.getList(), flattenList);
        }
    }
    
    @Override
    public Integer next() {
        return iterator.next();
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

}
