/*
    @author Qiaojian Hu
*/
import java.util.*;
import java.lang.*;
import java.io.*;

public class GraphAdjMatrix implements Graph{

	private int graph[][];
	private int V;
	private int count = 0;

	public GraphAdjMatrix(int vertices){
		this.graph = new int[vertices][vertices];
		this.V = vertices;
	}

	public void addEdge(int v1, int v2){
		graph[v1][v2] = 1;
		count++;
	}

	public void addEdge(int v1, int v2, int w){
		graph[v1][v2] = w;
		graph[v2][v1] = w;
		count++;
	}

	public int getEdge (int v1, int v2){
		return graph[v1][v2];		
	}

    public int createSpanningTree(){
    	
    	return primMST(graph);
    }


    public int[] neighbors(int vertex){
		int i,j=0,y=0;

		for (i = 0; i < V; i++ ) {
			if (graph[vertex][i] == 1) {
				j++;
			}
		}
		int[] tmp = new int[j];
		for (i = 0; i < V; i++ ) {
			if (graph[vertex][i] == 1) {
				tmp[y] = i;
				y++;
			}
		}
		return tmp;
	}

	private void topologicalSort(int x, boolean visited[],Stack stack){
        visited[x] = true;
        Integer i;
        int tmp[] = neighbors(x);

        for (int j = 0; j < tmp.length ; j++ ) {
        	i = tmp[j];
        	if (!visited[i]){
                topologicalSort(i, visited, stack);
            }
        }

        stack.push(new Integer(x));

    }
 
    public void topologicalSort(){
        Stack stack = new Stack();
 
        boolean visited[] = new boolean[V];
        for (int i = 0; i < V; i++){
            visited[i] = false;
        }
 
        for (int i = 0; i < V; i++){
            if (visited[i] == false){
                topologicalSort(i, visited, stack);
 			}
 		}

        while (stack.empty()==false){
            System.out.print(stack.pop() + " ");
        }
    }

    private int minKey(int key[], Boolean mstSet[])
    {

        int min = Integer.MAX_VALUE, min_index=-1;
 
        for (int v = 0; v < V; v++)
            if (mstSet[v] == false && key[v] < min)
            {
                min = key[v];
                min_index = v;
            }
 
        return min_index;
    }
 
    private int primMST(int graph[][])
    {
        int parent[] = new int[V];
 
        int key[] = new int [V];
 
        Boolean mstSet[] = new Boolean[V];
 
        for (int i = 0; i < V; i++)
        {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }
 
        key[0] = 0;
        parent[0] = -1; 
 

        for (int j = 0; j < V-1; j++)
        {

            int u = minKey(key, mstSet);
            mstSet[u] = true;
 
            for (int v = 0; v < V; v++)

                if (graph[u][v]!=0 && mstSet[v] == false &&
                    graph[u][v] <  key[v])
                {
                    parent[v]  = u;
                    key[v] = graph[u][v];
                }
        }
 
 		int result = 0;

 		for (int i = 1; i < V; i++)
        	result += graph[i][parent[i]];
        return result;
    }
 

}