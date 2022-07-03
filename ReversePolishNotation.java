import java.util.*;
import java.lang.*;
import java.io.*;
public class ReversePolishNotation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Write the number of expressions:");
        int number = sc.nextInt();
		String validString = "";
        
		while(number-- > 0 ){
            System.out.println("Write an expression:");
            String expr = sc.next();
            
            Stack<Character> charStack = new Stack<>();
            
            validString += getPostFixExpr(charStack,expr) + "\n";
        }
			System.out.println("Result is:\n" +validString);
    }
    
    public static int priority(char ch){
        switch(ch) {
            case '+':
            case '-': return 1;
            
            case '*':
            case '/':return 2;
            
            case '^':return 3;       
        }
        return -1;
    }
    
    public static String getPostFixExpr(Stack<Character> temp, String expression){
        String postfix = "";
        int i = 0;
		
        while(i < expression.length()){
            if(expression.charAt(i) >= 'a' && expression.charAt(i) <= 'z' ){ 
                postfix += expression.charAt(i);
            }else if(expression.charAt(i) == '(' ){
                temp.push(expression.charAt(i));
            }else if(expression.charAt(i) == ')'){
                while(temp.peek() != '(' && !temp.isEmpty() ){
                    postfix += temp.pop();
                }
                
                if( temp.peek() == '(' ){
                    temp.pop();
                }
            }else{
                while(( priority(expression.charAt(i)) <= priority(temp.peek() ) ) && !temp.isEmpty() ){
                    postfix += temp.pop();
                }
                temp.push(expression.charAt(i));
            }
            i++;
        }
        return postfix;
    }
}