import java.math.BigInteger;
import java.util.ArrayList;

public class Cryptage {
	//Première étape
	public static ArrayList<BigInteger> convert_ascii(String s){
		ArrayList<BigInteger> asciiList = new ArrayList<BigInteger>();
		char[] characters = s.toCharArray();
		for(char c : characters) {
			asciiList.add(BigInteger.valueOf((long) c));
		}

		return asciiList;
	}
	
	//Seconde étape
	public static ArrayList<BigInteger> chiffrement(ArrayList<BigInteger> asciiList, Key kpublic){
		ArrayList<BigInteger> chiffreList = new ArrayList<BigInteger>();
		for(BigInteger c: asciiList) {
			BigInteger Si = c.modPow(kpublic.getY(), kpublic.getN());
			chiffreList.add(Si);
		}
		return chiffreList;
	}
	
	public static ArrayList<BigInteger> dechiffrement(ArrayList<BigInteger> asciiList, Key kprivate){
		ArrayList<BigInteger> chiffreList = new ArrayList<BigInteger>();
		for(BigInteger c: asciiList) {
			BigInteger Si = c.modPow(kprivate.getY(), kprivate.getN());
			chiffreList.add(Si);
		}
		return chiffreList;
	}
	
	public static String ascii_to_string(ArrayList<BigInteger> asciiList ){
		String s ="";
		for(BigInteger ascii : asciiList) {
			long n = ascii.longValueExact();
			char ch = (char) n;
			s+=ch;
		}

		return s;
	}
}
