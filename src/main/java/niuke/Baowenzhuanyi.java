package niuke;

import java.util.Scanner;

public class Baowenzhuanyi {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        scanner.close();
        String[] strArr = str.split(" ");
        String strOut = "";
        int countNum = Integer.parseInt(strArr[0], 16);
        for(int i=1; i<strArr.length; i++){
            if(strArr[i].equals("A")){
                strOut += "12";
                strOut += " ";
                strOut +="34";
                strOut += " ";
                countNum += 1;
            }
            else  if(strArr[i].equals("B")){
                strOut += "AB";
                strOut += " ";
                strOut +="CD";
                strOut += " ";
                countNum += 1;
            }
            else{
                strOut += strArr[i];
                strOut += " ";
            }
        }
        String result = Integer.toHexString(countNum).toUpperCase() + " " + strOut.trim();
        System.out.println(result);
    }
}
