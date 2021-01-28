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
		char[] characters = s.toCharArray();
		for(BigInteger ascii : asciiList) {
			int n = ascii.intValueExact();
			char ch = (char) n;
			s+=ch;
		}

		return s;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Partie 2
		PublicKey p = new PublicKey();
//		p.setE(new BigInteger("73"));
//		p.setN(new BigInteger("1927"));
//		p.setM(new BigInteger("1840"));
		System.out.println("     e: "+p.get_e().longValueExact()+ "   n: "+p.get_n().longValueExact()+ "   m: "+p.get_m().longValueExact());
		
		Key clePublic = new Key(p.get_n(),p.get_e());
	
		
		PrivateKey pk = new PrivateKey(clePublic.getY(),p.get_m(),clePublic.getN());
		pk.CreationKey();
		Key clePrive = pk.getPKey();

//		char car = 'B';
//		int ascii = (int) car;
//		p.convert_ascii("Bonjour !");

		
		ArrayList<BigInteger> phrase = convert_ascii("Bonjour !");
		
		System.out.println("-----CHIFFREMENT-----");
		
		ArrayList<BigInteger> texte_chiffre =  chiffrement(phrase,clePublic);
		
		for ( BigInteger t : texte_chiffre) {
			System.out.println(t.longValueExact());
		}
	
		System.out.println("-----DECHIFFREMENT-----");
		
//		BigInteger n = BigInteger.valueOf(5141);
//		BigInteger u = BigInteger.valueOf(4279);
//		
//		Key pk_exemple = new Key(n, u);
//		
//		ArrayList<BigInteger> dechiffrement = new ArrayList();
//		dechiffrement.add(BigInteger.valueOf(386));
//		dechiffrement.add(BigInteger.valueOf(737));
//		dechiffrement.add(BigInteger.valueOf(970));
//		dechiffrement.add(BigInteger.valueOf(204));
//		dechiffrement.add(BigInteger.valueOf(1858));
		
		
		ArrayList<BigInteger> texte_asciichiffre =  dechiffrement(texte_chiffre,clePrive);
		
		for ( BigInteger t : texte_asciichiffre) {
			System.out.println(t.longValueExact());
		}
		
		System.out.println(ascii_to_string(texte_asciichiffre));
		
	}
	


}
