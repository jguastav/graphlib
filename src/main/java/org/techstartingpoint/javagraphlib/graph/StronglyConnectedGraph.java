package org.techstartingpoint.javagraphlib.graph;

import java.util.*;

// based on http://www.sanfoundry.com/java-program-check-whether-graph-strongly-connected-not/
public class StronglyConnectedGraph
{
    private int                 V;
    private int                 preCount;
    private int[]               low;
    private boolean[]           visited;
    private List<Integer>[]     graph;
    private List<List<Integer>> sccComp;
    private Stack<Integer>      stack;

    /** function to get all strongly connected components **/
    public List<List<Integer>> getSCComponents(List<Integer>[] graph)
    {
        V = graph.length;
        this.graph = graph;
        low = new int[V];
        visited = new boolean[V];
        stack = new Stack<Integer>();
        sccComp = new ArrayList<>();
        for (int v = 0; v < V; v++)
            if (!visited[v])
                dfs(v);
        return sccComp;
    }

    /** function dfs **/
    public void dfs(int v)
    {
        low[v] = preCount++;
        visited[v] = true;
        stack.push(v);
        int min = low[v];
        for (int w : graph[v])
        {
            if (!visited[w])
                dfs(w);
            if (low[w] < min)
                min = low[w];
        }
        if (min < low[v])
        {
            low[v] = min;
            return;
        }
        List<Integer> component = new ArrayList<Integer>();
        int w;
        do
        {
            w = stack.pop();
            component.add(w);
            low[w] = V;
        }
        while (w != v);
        sccComp.add(component);
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter number of Vertices");
        /** number of vertices **/
        int V = scan.nextInt();
        /** make graph **/
        List<Integer>[] g = new List[V];
        for (int i = 0; i < V; i++)
            g[i] = new ArrayList<Integer>();
        /** accept all edges **/
        System.out.println("Enter number of edges");
        int E = scan.nextInt();
        /** all edges **/
        System.out.println("Enter the edges in the graph : <from> <to>");
        for (int i = 0; i < E; i++)
        {
            int x = scan.nextInt();
            int y = scan.nextInt();
            g[x].add(y);
        }
        StronglyConnectedGraph t = new StronglyConnectedGraph();
        System.out.print("The graph is strongly connected? : ");
        /** print all strongly connected components **/
        List<List<Integer>> scComponents = t.getSCComponents(g);
        Iterator<List<Integer>> iterator = scComponents.iterator();
        boolean stronglyConnected = true;
        while (iterator.hasNext())
        {
            if (iterator.next().size() <= 1)
            {
                stronglyConnected = false;
            }
        }
        System.out.println(stronglyConnected);
        scan.close();
    }
}
