public class Main
{
    public static void main(String[] args) {
        HashTableChain<String, String> test_chain = new HashTableChain<>();
        HashtableOpen<String, String> test_open = new HashtableOpen<>();

        String rand1 = generateString(10);
        String rand2 = generateString(10);

        int data = 20000;

        for(int count = 0 ; count < 5 ; ++count, data *= 2) {

            test_chain.put(rand1, rand2);
            test_open.put(rand1, rand2);
            long start_open = System.nanoTime();
            for(int i = 0 ; i < data ; ++i) {
                test_open.put(generateString(10), generateString(10));
            }

            test_chain.put(rand2, rand1);

            long end_open = System.nanoTime();

            long start_chain = System.nanoTime();
            for(int i = 0 ; i < data ; ++i) {
                test_chain.put(generateString(10), generateString(10));
            }

            test_open.put(rand2, rand1);

            long end_chain = System.nanoTime();

            long final_open = (end_open - start_open) / 1000000;
            long final_chain = (end_chain - start_chain) / 1000000;

            System.out.println("OpenAdreessing time: " + final_open + " Data: " + data);
            System.out.println("Chain time: " + final_chain + " Data: " + data);
        }
    }

    public static String generateString(int n) {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {
            int index = (int) (AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }

        return sb.toString();
    }
}
