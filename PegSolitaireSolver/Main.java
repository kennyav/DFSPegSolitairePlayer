public class Main {
   public static Stack stack;
   public static Node node;

   public static void main(String[] args) {
      // creates new node and stack
      node = new Node();
      stack = new Stack();
      node.path = node.getPath(node.name);

      new Solver(node, stack);
   }

}
