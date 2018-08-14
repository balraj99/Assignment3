import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

                result = output;
                System.out.println(output);
            }
        }catch(IOException ex){

        }

        Pattern pattern = Pattern.compile("([0-9]+\\.[0-9]+)");
        if(result != null){

            Matcher matcher = pattern.matcher(result);

            int count = 0;
            while(matcher.find()){

                count++;
                if(count == 2) {

                    result = matcher.group();
                    break;
                }
            }

        }

        System.out.println("\nMedian time taken for ping = " + result);
    }

    public static void main(String[] args) {

        pingIp("google.com");
    }
}
