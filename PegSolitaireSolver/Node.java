import java.util.ArrayList;
import java.util.List;

public class Node {

   public String name;
   public List<String> path;
   public int[][] state;
   public List<Node> children;

   // a node contains a name, state, path, and children
   public Node() {
      name = "Starting Node";
      state = new int[][] {
            { 2, 2, 2, 2, 0 },
            { 2, 2, 2, 1, 1 },
            { 2, 2, 1, 1, 1 },
            { 2, 1, 1, 1, 1 },
            { 1, 1, 1, 1, 1 } };

      children = new ArrayList<Node>();
      path = new ArrayList<String>();
   }

   // get the values of this state for the children to manipulate
   public int[][] getState() {
      int[][] returnState = new int[this.state.length][this.state[0].length];

      for (int i = 0; i < this.state.length; i++) {
         for (int j = 0; j < this.state[i].length; j++) {
            returnState[i][j] = this.state[i][j];
         }
      }
      return returnState;
   }

   // returns the path of the current node
   public ArrayList<String> getPath(String newNodeName) {
      ArrayList<String> newPath = new ArrayList<String>(path);
      newPath.add(newNodeName);
      return newPath;
   }

}
