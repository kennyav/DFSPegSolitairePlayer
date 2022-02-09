public class Solver {
   public Node winningNode = new Node();
   public Node node;
   public Stack solverStack;

   public Solver(Node node, Stack stack) {
      // initializing the winning state
      winningNode.name = "Winning Node";
      winningNode.state = new int[][] {
            { 2, 2, 2, 2, 1 },
            { 2, 2, 2, 0, 0 },
            { 2, 2, 0, 0, 0 },
            { 2, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0 } };

      this.node = node;
      solverStack = stack;

      // pushing the starting node to the stack
      solverStack.stack_push(node);

      // running the solver
      while (!search(solverStack));
   }

   // returns true if node states are equal, false otherwise (used by search)
   boolean isEqual(int[][] check) {
      int i, j;
      for (i = 0; i < check.length; i++)
         for (j = 0; j < check[i].length; j++)
            if (check[i][j] != winningNode.state[i][j]) {
               return false;
            }
      return true;
   }

   // searches the tree using DFS because space complexity is limited
   // if the state is a winning state return the nodes path
   // otherwise continue to search the tree
   public boolean search(Stack solverStack) {
      Node compare = solverStack.stack_pop();

      if (isEqual(compare.state)) {
         System.out.println("Found winning state");
         System.out.println(compare.path);
         return true;
      } else {
         generateTree(compare);
         return false;
      }
   }

   // runs through the states matrix looking for 0
   // if at 0 then check for potential moves
   void generateTree(Node startingNode) {
      for (int i = 0; i < startingNode.state.length; i++) {
         for (int j = 0; j < startingNode.state[i].length; j++) {
            if (startingNode.state[i][j] == 0) {
               // check surrondings to see available moves and then generate a new node
               generateTreeHelper(startingNode, i, j);
            }
         }
      }
   }

   // checks for 6 different potential moves
   // {Right, Left, UpperRight, LowerRight, UpperLeft, LowerLeft}
   void generateTreeHelper(Node node, int i, int j) {
      if (j < (node.state[0].length - 2)) {
         // case 1 to the right
         if ((node.state[i][j + 1] == 1) && (node.state[i][j + 2] == 1)) {
            Node rightChildNode = new Node();
            rightChildNode.state = node.getState();
            node.children.add(rightChildNode);
            rightChildNode.name = "(" + Integer.toString(i) + "," + Integer.toString(j + 2) + ")" + "->" + "("
                  + Integer.toString(i) + "," + Integer.toString(j) + ")";
            rightChildNode.path = node.getPath(rightChildNode.name);
            rightChildNode.state[i][j] = 1;
            rightChildNode.state[i][j + 1] = 0;
            rightChildNode.state[i][j + 2] = 0;
            solverStack.stack_push(rightChildNode);
         }
      }

      if (j > 1) {
         // case 2 to the left
         if ((node.state[i][j - 1] == 1) && (node.state[i][j - 2] == 1)) {
            Node leftChildNode = new Node();
            leftChildNode.state = node.getState();
            node.children.add(leftChildNode);
            leftChildNode.name = "(" + Integer.toString(i) + "," + Integer.toString(j - 2) + ")" + "->" + "("
                  + Integer.toString(i) + "," + Integer.toString(j) + ")";
            leftChildNode.path = node.getPath(leftChildNode.name);
            leftChildNode.state[i][j] = 1;
            leftChildNode.state[i][j - 1] = 0;
            leftChildNode.state[i][j - 2] = 0;
            solverStack.stack_push(leftChildNode);
         }
      }

      if ((i > 1) && (j < (node.state[0].length - 2))) {
         // case 3 upper right
         if ((node.state[i - 1][j + 1] == 1) && (node.state[i - 2][j + 2] == 1)) {
            Node upperRightChildNode = new Node();
            upperRightChildNode.state = node.getState();
            node.children.add(upperRightChildNode);
            upperRightChildNode.name = "(" + Integer.toString(i - 2) + "," + Integer.toString(j + 2) + ")" + "->" + "("
                  + Integer.toString(i) + "," + Integer.toString(j) + ")";
            upperRightChildNode.path = node.getPath(upperRightChildNode.name);
            upperRightChildNode.state[i][j] = 1;
            upperRightChildNode.state[i - 1][j + 1] = 0;
            upperRightChildNode.state[i - 2][j + 2] = 0;
            solverStack.stack_push(upperRightChildNode);
         }
      }

      if (i > 1) {
         // case 4 upper left
         if ((node.state[i - 1][j] == 1) && (node.state[i - 2][j] == 1)) {
            Node upperLeftChildNode = new Node();
            upperLeftChildNode.state = node.getState();
            node.children.add(upperLeftChildNode);
            upperLeftChildNode.name = "(" + Integer.toString(i - 2) + "," + Integer.toString(j) + ")" + "->" + "("
                  + Integer.toString(i) + "," + Integer.toString(j) + ")";
            upperLeftChildNode.path = node.getPath(upperLeftChildNode.name);
            upperLeftChildNode.state[i][j] = 1;
            upperLeftChildNode.state[i - 1][j] = 0;
            upperLeftChildNode.state[i - 2][j] = 0;
            solverStack.stack_push(upperLeftChildNode);
         }
      }

      if ((i < (node.state.length - 2)) && (j > 1)) {
         // case 5 lower left
         if ((node.state[i + 1][j - 1] == 1) && (node.state[i + 2][j - 2] == 1)) {
            Node lowerLeftChildNode = new Node();
            lowerLeftChildNode.state = node.getState();
            node.children.add(lowerLeftChildNode);
            lowerLeftChildNode.name = "(" + Integer.toString(i + 2) + "," + Integer.toString(j - 2) + ")" + "->" + "("
                  + Integer.toString(i) + "," + Integer.toString(j) + ")";
            lowerLeftChildNode.path = node.getPath(lowerLeftChildNode.name);
            lowerLeftChildNode.state[i][j] = 1;
            lowerLeftChildNode.state[i + 1][j - 1] = 0;
            lowerLeftChildNode.state[i + 2][j - 2] = 0;
            solverStack.stack_push(lowerLeftChildNode);
         }
      }

      if (i < (node.state.length - 2)) {
         // case 6 lower right
         if ((node.state[i + 1][j] == 1) && (node.state[i + 2][j] == 1)) {
            Node lowerRightChildNode = new Node();
            lowerRightChildNode.state = node.getState();
            node.children.add(lowerRightChildNode);
            lowerRightChildNode.name = "(" + Integer.toString(i + 2) + "," + Integer.toString(j) + ")" + "->" + "("
                  + Integer.toString(i) + "," + Integer.toString(j) + ")";
            lowerRightChildNode.path = node.getPath(lowerRightChildNode.name);
            lowerRightChildNode.state[i][j] = 1;
            lowerRightChildNode.state[i + 1][j] = 0;
            lowerRightChildNode.state[i + 2][j] = 0;
            solverStack.stack_push(lowerRightChildNode);
         }
      }
   }

   // end of program :)
}
