import java.math.BigInteger;

public class PrivateKey {
	
	//Coefficient de Bézout
	private BigInteger u;
	private BigInteger v;
	
	//indicatrice d'Euler
	private BigInteger e;
	private BigInteger m;
	
	private Key Pkey;

	public PrivateKey(BigInteger e, BigInteger m) {
		this.m = m;
		this.e = e;
	}
	
	private void AlgoEuclide() {
		
		//On commence à partir de S1
		BigInteger k = BigInteger.ONE;
		int i = 1;
		
		BigInteger r = m;
		BigInteger rmoins1 = e;
		
		u = BigInteger.ZERO;
		BigInteger umoins1 = BigInteger.ONE;
		
		v = BigInteger.ONE;
		BigInteger vmoins1 = BigInteger.ZERO;
		
		System.out.println(i+"   r-1: "+rmoins1.intValue()+ "   u-1: "+umoins1.intValue()+ "   v-1: "+vmoins1.intValue());
		System.out.println(i+"     r: "+r.intValue()+ "   u: "+u.intValue()+ "   v: "+v.intValue());
		
		BigInteger tmp;
		
		while( !r.equals(BigInteger.ZERO)) {
			i++;		
			tmp = u;
			u = umoins1.subtract((rmoins1.divide(r)).multiply(u));
			umoins1 = tmp;
			//Vérifie que 2 < u < m
//			if (u.compareTo(BigInteger.valueOf(2))==1 || u.compareTo(m)==-1) {
//				//System.out.println("Condition ");
//			}
			
			tmp = v;
			v = vmoins1.subtract((rmoins1.divide(r)).multiply(v));
			vmoins1 = tmp;
			
			tmp = r;
			r = rmoins1.subtract((rmoins1.divide(r)).multiply(r));
			rmoins1 = tmp;
			
			System.out.println(i+"   r-1: "+rmoins1.intValue()+ "   u-1: "+umoins1.intValue()+ "   v-1: "+vmoins1.intValue());
			System.out.println(i+"     r: "+r.intValue()+ "   u: "+u.intValue()+ "   v: "+v.intValue());
			
			k.subtract(BigInteger.ONE);
		}
		
		//System.out.println("   r: "+r.intValue()+ "   u: "+u.intValue()+ "   v: "+v.intValue());
	}
	
//	private boolean VerificationEqDiophantienne () {
//		if( (e*u)+(m*v))
//	}
	
	public void CreationKey() {
		AlgoEuclide();
	}
	
	
	
	
}
