import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;


public class Ping {

    public static void pingIp(String ip){

        String command = "ping " + ip + " -c 10";
        String output = "";
        String result = "";
        try{

            Process p;
            p = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));


            while((output = reader.readLine()) != null){

                result = result + "\n" + output;
                System.out.println(output);
            }
        }catch(IOException ex){

            System.err.println("Exception occured");
        }

        System.out.println(result);

        if(result == "" || result.contains("unknown host")){

            System.err.println("Entered host is invalid");
            return;
        }

        ArrayList<Double> list = new ArrayList<Double>();
        String[] str = result.split("\n");

        int len = str.length;
        for(int i = 0; i < len; i++){
            if(str[i].contains("time=")){

                String s = str[i].split("time=")[1];
                s = s.replace("ms", "").trim();
                //System.out.println("String s  = " + s);
                list.add(Double.valueOf(s));
            }
        }

        list.sort(null);

       /* for(int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }*/

        int n = list.size();

        if(n % 2 != 0){

            System.out.println("Median Value = " + list.get(n / 2));
        }
        else {

            double val = list.get((n - 1) / 2) + list.get(n / 2);
            System.out.println("Median Value = " + val / 2);
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter host to ping");
        String host = sc.next();
        pingIp(host);
    }
}
