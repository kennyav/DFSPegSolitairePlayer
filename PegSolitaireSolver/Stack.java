import java.util.*;

public class Stack {

   Deque<Node> stack;

   public Stack() {
      stack = new ArrayDeque<>();
   }

   // pushes elements to the top of the stack
   public void stack_push(Node node) {
      stack.push(node);
   }

   // take the top of the stack
   public Node stack_pop() {
      if (!stack.isEmpty()) {
         return stack.pop();
      }
      System.out.println("stack is empty");
      return null;
   }

}
