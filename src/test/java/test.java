import java.util.HashMap;
import java.util.*;

public class test {
    
    
    
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            String line = scanner.nextLine();
            Map<Character, Integer> map = new LinkedHashMap<Character, Integer>();
            
            //write your code here......
//         遍历line
    
            String newLine = line.replace(" ", "");
    
            for(int i = 0;i< newLine.length();i++){
                
                Character temp = newLine.charAt(i);
                if(map.containsKey(temp)){
                    int m = map.get(temp);
                    map.replace(temp,m,++m);
                }else if(temp.equals(" ")){
                    continue;
                }
                else{
                    map.put(temp,1);
                }
                
            }
            
            
            
            
            Set<Map.Entry<Character, Integer>> entrys = map.entrySet();
            for (Map.Entry<Character, Integer> entry : entrys) {
                System.out.println(entry.getKey() + ":" + entry.getValue());
            }
        }
    }

