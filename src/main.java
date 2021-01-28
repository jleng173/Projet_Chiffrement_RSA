import java.math.BigInteger;
import java.util.ArrayList;

public class main {
	
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
	public static ArrayList<BigInteger> chiffrement(ArrayList<BigInteger> asciiList, Key k){
		ArrayList<BigInteger> chiffreList = new ArrayList<BigInteger>();
		for(BigInteger c: asciiList) {
			BigInteger Si = c.modPow(k.getN(), k.getY());
			chiffreList.add(Si);
		}
		return chiffreList;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Partie 2
		PublicKey p = new PublicKey();
		Key clePublic = new Key(p.get_n(),p.get_e());
		
		BigInteger e = BigInteger.valueOf(101);
		BigInteger m = BigInteger.valueOf(2320);
		
		PrivateKey pk = new PrivateKey(p.get_e(),p.get_m(),clePublic.getN());
		pk.CreationKey();
		Key clePrive = pk.getPKey();

//		char car = 'B';
//		int ascii = (int) car;
//		p.convert_ascii("Bonjour !");
		p.setE(new BigInteger("7"));
		p.setN(new BigInteger("5141"));
		ArrayList<BigInteger> phrase = p.convert_ascii("Bonjour !");
		p.chiffrement(phrase);
	}

}
