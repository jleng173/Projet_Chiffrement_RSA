package crypt;
import java.math.BigInteger;
import java.util.ArrayList;

public class main {
	

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
		Key clePrive = pk.getPKey();
		
		ArrayList<BigInteger> phrase = Cryptage.convert_ascii("Bonjour !");
		
		System.out.println("-----CHIFFREMENT-----");
		
		ArrayList<BigInteger> texte_chiffre =  Cryptage.chiffrement(phrase,clePublic);
		
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
		
		
		ArrayList<BigInteger> texte_asciichiffre =  Cryptage.dechiffrement(texte_chiffre,clePrive);
		
		for ( BigInteger t : texte_asciichiffre) {
			System.out.println(t.longValueExact());
		}
		
		System.out.println(Cryptage.ascii_to_string(texte_asciichiffre));
		
	}
	
}