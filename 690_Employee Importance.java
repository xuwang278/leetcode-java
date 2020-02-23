/*
// Employee info
class Employee {
    // It's the unique id of each node;
    // unique id of this employee
    public int id;
    // the importance value of this employee
    public int importance;
    // the id of direct subordinates
    public List<Integer> subordinates;
};
*/
class Solution {
    int sum = 0;
    public int getImportance(List<Employee> employees, int id) {
        Set<Employee> visited = new HashSet<>();
        for (Employee e : employees) 
            if (e.id == id) dfs(e, visited, employees);
        return sum;
    }
    
    private void dfs(Employee e, Set<Employee> visited, List<Employee> employees) {
        if (visited.contains(e)) return;
        visited.add(e);
        sum += e.importance;
        for (int id : e.subordinates)
            for (Employee ee : employees)
                if (ee.id == id) dfs(ee, visited, employees);
    }

    // Better DFS version
    public int getImportance(List<Employee> employees, int id) {
        Map<Integer, Employee> map = new HashMap<>(); // id -> employee
        for (Employee e : employees) map.put(e.id, e);
        return dfs(map, id);
    }

    private int dfs(Map<Integer, Employee> map, int id) {
        Employee e = map.get(id);
        int sum = e.importance;
        for (int next : e.subordinates)
            sum += dfs(map, next);
        return sum;
    }

    // BFS
    public int getImportance(List<Employee> employees, int id) {
        Map<Integer, Employee> map = new HashMap<>(); // id -> employee
        for (Employee e : employees) map.put(e.id, e);
        Queue<Integer> q = new LinkedList<>();
        int sum = 0;
        q.offer(id);
        while (!q.isEmpty()) {
            int top = q.poll();
            sum += map.get(top).importance;
            for (int next : map.get(top).subordinates) {
                q.offer(next);
            }
        }
        return sum;
    }









}