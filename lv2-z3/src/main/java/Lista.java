import java.util.Scanner;
import java.util.ArrayList;
public class Lista {
    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
    public static void main(String[] args) {
        Scanner ulaz = new Scanner(System.in);
        ArrayList<Integer> lista = new ArrayList<>();
        while(true) {
            System.out.print("Unesite broj ili stop za prekid");
            String unos = ulaz.nextLine();
            if(unos.equals("stop")) break;
            else if(isNumeric(unos)) {
                try{
                    int br = Integer.parseInt(unos);
                    lista.add(br);
                }
                catch (NumberFormatException ex){
                    ex.printStackTrace();
                }
            }
        }
    }
}
