import java.math.BigInteger;

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
	}

}
