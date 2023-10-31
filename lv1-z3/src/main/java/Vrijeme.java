public class Vrijeme {
    public static void main(String[] args) {
        int i, faktorijel = 1;
        int n = Integer.parseInt(args[0]);
        for (i = 1; i <= n; i++) {
            faktorijel = faktorijel * i;
        }
        double s = Math.sin(n);
        System.out.printf("Faktorijel broja %d je %d, a sinus je %f", n, faktorijel, s);
    }
}
