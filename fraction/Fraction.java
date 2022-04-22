//Jacob Burt

package fraction;

public class Fraction implements Comparable<Fraction> {
	private int numerator, denominator;
	
	public Fraction() {
		this.numerator = 0;
		this.denominator = 1;
	}
	
	public Fraction(int numerator, int denominator) {
		if(denominator == 0)
			throw new IllegalArgumentException("The denominator with the value of 0 is not permitted");
		
		int gcd = findGCD(numerator, denominator);
		if(gcd < 0)
			gcd = gcd *-1;
		
		this.numerator = numerator/gcd;
		this.denominator = denominator/gcd;
		
		if(this.numerator < 0 && this.denominator < 0) {
			this.numerator = this.numerator *-1;
			this.denominator = this.denominator *-1;
		}
		
		if(this.denominator < 0 && this.numerator > 0) {
			this.numerator = this.numerator*-1;
			this.denominator = this.denominator*-1;
		}
	}
	
	public int getNum() {
		return this.numerator;
	}
	
	public int getDen() {
		return this.denominator;
	}
	
	public String toString() {
		return this.numerator + "/" + this.denominator; 
	}
	
	public boolean equals(Object obj) {
		if(obj == null) return false;
		if(obj == this) return true;
		if(!(obj instanceof Fraction)) return false;
		Fraction another = (Fraction) obj;
		
		return this.numerator == another.numerator && 
				this.denominator == another.denominator;
	}

	@Override
	public int compareTo(Fraction another) {
		int f1Numer = createCommonDivisor(this.numerator, another.denominator);
		int f2Numer = createCommonDivisor(another.numerator, this.denominator);
		
		return f1Numer - f2Numer;
	}
	
	public int findGCD(int numerator, int denominator) {

		if(numerator == denominator || numerator == 1)
			return numerator;
		if(numerator == 0)
			return 1;
		
		int a = Math.max(numerator, denominator);
		int n = Math.min(numerator, denominator);
		int temp;
		while(a != 0 && n != 0) {
			temp = n;
			n = a%n;
			a = temp;
		}
		
		return a;
	}
	
	private Fraction reduce(Fraction fraction) {
		int gcd = findGCD(fraction.getNum(), fraction.getDen());	
		Fraction reducedFraction = new Fraction(fraction.getNum()/gcd,fraction.getDen()/gcd);
		
		return reducedFraction;
	}
	
	public Fraction add(Fraction fraction) {
		if(this == null || fraction == null)
			throw new IllegalArgumentException("Cannot perform math operations on a null fraction object!");
		
		int f1Numer, f2Numer, newDenom;
		f1Numer = this.getNum() * fraction.getDen();
		newDenom = this.getDen() * fraction.getDen();
		f2Numer = fraction.getNum() * this.getDen();
		
		Fraction newFraction = new Fraction(f1Numer + f2Numer, newDenom);
		
		return reduce(newFraction);
	}
	
	public Fraction add(int wholeNum) {

		int f1Denom = this.getDen();
		Fraction f2 = new Fraction(wholeNum * f1Denom, f1Denom);
		Fraction newFraction = this.add(f2);
		
		return reduce(newFraction);
	}
	
	private int createCommonDivisor(int numer1, int denom2) {
		int f1Numer = numer1, f2Denom = denom2; 
		
		f1Numer = f1Numer * f2Denom;
		
		return f1Numer;
	}
	
	public Fraction multiply(Fraction fraction) {
		if(this == null || fraction == null)
			throw new IllegalArgumentException("Cannot perform math operations on a null fraction object!");
		
		
		int f1Numer = this.numerator;
		int f1Denom = this.denominator;
		int f2Numer = fraction.getNum();
		int f2Denom = fraction.getDen();
		
		Fraction newFraction = new Fraction(f1Numer * f2Numer, f1Denom * f2Denom);
		
		return reduce(newFraction);
	}
	
	public double realValue() {
		double dNum = this.numerator;
		double dDen = this.denominator;
		
		return dNum / dDen;
	}
}
