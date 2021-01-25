import java.math.BigInteger;
import java.util.ArrayList;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PublicKey p = new PublicKey();
		Key clePublic = new Key(p.get_n(),p.get_e());
		
		BigInteger e = BigInteger.valueOf(7);
		BigInteger m = BigInteger.valueOf(4992);
		
		PrivateKey pk = new PrivateKey(p.get_e(),p.get_m(),clePublic.getX());
		pk.CreationKey();
		Key clePrive = pk.getPKey();
//		Key clePublic = new Key(p.get_n(),p.get_e());
//		
//		BigInteger e = BigInteger.valueOf(7);
//		BigInteger m = BigInteger.valueOf(4992);
//		
//		PrivateKey pk = new PrivateKey(e,m);
//		pk.CreationKey();
//		char car = 'B';
//		int ascii = (int) car;
//		p.convert_ascii("Bonjour !");
		p.setE(new BigInteger("7"));
		p.setN(new BigInteger("5141"));
		ArrayList<BigInteger> phrase = p.convert_ascii("Bonjour !");
		p.chiffrement(phrase);
	}

}
