
 package wolf;
import java.lang.* ;
import java.util.* ;
public class Wolf {

    
    static  int V ;
    
    
    boolean bfs(int rGraph[][], int s, int t, int parent[])
    {
        
        boolean visited[] = new boolean[V];
        for (int i = 0; i < V; ++i)
            visited[i] = false;
 
        
        LinkedList<Integer> queue
            = new LinkedList<Integer>();
        queue.add(s);
        visited[s] = true;
        parent[s] = -1;
 
       
        while (queue.size() != 0) {
            int u = queue.poll();
 
            for (int v = 0; v < V; v++) {
                if (visited[v] == false
                    && rGraph[u][v] > 0) {
                    
                    if (v == t) {
                        parent[v] = u;
                        return true;
                    }
                    queue.add(v);
                    parent[v] = u;
                    visited[v] = true;
                }
            }
        }
 
       
        return false;
    }
static int  parentl[] ;
static int parentp [ ]  ; 

static   int gt[][] ;
    
   static void  bfso( int gt[][],int s, int t)
    {
        int parent[] = new int[V] ;
        for(int ui = 0 ; ui < V -1 ; ui ++ ){
            parent[ui] = -1 ; 
        }
        boolean visited[] = new boolean[V];
        for (int i = 0; i < V; ++i)
            visited[i] = false;
 
        
        LinkedList<Integer> queue
            = new LinkedList<Integer>();
        queue.add(s);
        visited[s] = true;
        parent[s] = -1;
 
       
        while (queue.size() != 0) {
            int u = queue.pop();
 
            for (int v = 0; v < V; v++) {
                if (visited[v] == false && gt[u][v] > 0) {
                    
                   
                
                        
                        
                    
                    queue.add(v);
                    parent[v] = u;
                    parentl[v] = u ;
                 
 
                    visited[v] = true;
                }
//                 gt[u][v] = 0  ;
//                 gt[v][u] = 0 ;
            }
        }
 
        
        int uo = t ; 
       
            while(uo!=s-1){
                
                int oii = uo+1 ;
               System.out.print( oii );
               if(parent[uo]>=0){
               gt[uo][parent[uo]] = 0 ; 
               gt[parent[uo]][uo] =0 ;
               }
               uo  = parent[uo] ;
               
               int tf = uo+1;
               System.out.println("-->" + tf );
               
            }
            
            
        
        
       
        
    }
 
    
    
    
   int ford(int s , int t , int graph[][]){
       int max =0 ;
       int u ,v  ;
       int rgraph[][] = new int [V][V] ;
       
       for (u = 0; u < V; u++){
            for (v = 0; v < V; v++){
                rgraph[u][v] = graph[u][v];
                 rgraph[v][u] = graph[u][v];
                
            }}
      int  parent[] = new int[V];
      
       while (bfs(rgraph, s, t, parent)) {
            
            int path_flow = Integer.MAX_VALUE;
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                path_flow = Math.min(path_flow, rgraph[u][v]);
            }
 
            
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                rgraph[u][v] -= path_flow;
                rgraph[v][u] += path_flow;
            }
 
           
            max += path_flow;
        }
 
       
        return max;
    }
       
       
   
 
    public static void main(String[] args) {
        
        Scanner io = new Scanner(System.in) ;
         System.out.println("Enter the number of vertex : " );
        int number  = io.nextInt() ;
        System.out.println("Enter the number S in vetex : " );
        int s = io.nextInt() ;
        System.out.println("Enter the number of T in vertex: " );
        int t = io.nextInt() ; 
        System.out.println("Enter the number edgs : " );
        int m = io.nextInt() ; 
        System.out.println("Enter " + m + " edges between the vertex : " );
        int vo= 2*number +1 ;
        int gr[][] = new int[vo][vo] ;
        for(int op = 0 ; op < vo; op ++ ){
            for(int iop = 0 ; iop <vo ; iop ++ )
            {
                gr[op][iop] =  0 ;
            }
        }
        int counter = 0 ;
        int ff[] = new int [vo] ;
        for(int k = 0 ; k < m ; k ++ ){
            int a = io.nextInt() ; 
            int b = io.nextInt() ; 
           
            gr[a-1][b-1] = 1 ;
            gr[b-1][a-1] = 1 ;
           
            int counter2 = 0;
            for(int yo = 0  ; yo <counter ; yo ++ ){
            
                if(ff[yo] != a){
                  counter2++ ;
                }else{
                    break ;
                }
                
            }
            if(counter2 == counter){
                ff[counter] = a ;
                counter++ ;
            }
            
            
            
            int counter1 = 0;
            for(int yop = 0  ; yop <counter ; yop ++ ){
            
                if( ff[yop] != b ){
                 counter1++ ;
                }else{
                    break ;
                }
                
            }
            if(counter1 == counter){
                ff[counter] = b ;
                counter++ ;
            }
            
        }
        V = counter+2 ;
        int kk[][] = new int[V][V] ;
        gt = new int [V][V] ;
        for(int ui = 0 ; ui < V ; ui++){
            for(int hh = 0 ; hh < V ; hh++){
                kk[ui][hh] = gr[ui][hh] ;
                kk[hh][ui] = gr[ui][hh] ;
                
                gt[ui][hh] = gr[ui][hh] ;
                gt[hh][ui] = gr[ui][hh] ;
            }
        }
        Wolf mm = new Wolf() ;
        int max = mm.ford(s-1, t-1, kk);
        System.out.println(max);
       if(max >= 2){
            parentl = new int[gr.length] ;
           
           
             System.out.println("the path of Wolf from t to s : " );
            bfso( gt , s-1,  t-1) ;
//            int uo = t-1;
//                while(uo!=s-1){
//                int oii = uo + 1 ;
//              
//               uo  = parentl[uo] ;
//               int tf = uo+1 ;
//               kk[tf][oii] = 0 ; 
//               kk[oii][tf] = 0 ; 
//            }
                
              
             System.out.println("the path of Sheep from t to s : " );
              bfso( gt ,s-1,  t-1) ;

            
            
            
            
            
            
       }else{
           System.out.println("Impossible");
       }
       
         
     }}
