public class Planet{
	// gravitational constant
	private static final double G = 6.67e-11;
	// current x position
	public double xxPos;
	// current y position
	public double yyPos;
	// current velocity in the x direction
	public double xxVel;
	// current velocity in the y direction
	public double yyVel;
	public double mass;
	public String imgFileName;


	/**
	 * Constructor for Planet
	 * @param xP  x position
	 * @param yP  y position
	 * @param xV  velocity in x direction
	 * @param yV  velocity in y direction
	 * @param m   mass
	 * @param img img file name
	 */
	public Planet(double xP, double yP, double xV,
		double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	/**
	 * Constructor to copy instance
	 * @param p  instance will be copied
	 */
	public Planet(Planet p){
		this(p.xxPos, p.yyPos, p.xxVel, p.yyVel, p.mass, p.imgFileName);
	}

	/**
	 * Calculate the distance between two Planets
	 * @param otherP
	 * @return
	 */
	public double calcDistance(Planet otherP){
		double dx = this.xxPos - otherP.xxPos;
		double dy = this.yyPos - otherP.yyPos;
		double sq = dx * dx + dy * dy;
		return Math.sqrt(sq);
	}

	/**
	 * Calculate the force between two Planets
	 * @param otherP
	 * @return
	 */
	public double calcForceExertedBy(Planet otherP){
		return (G * this.mass * otherP.mass) / (Math.pow(this.calcDistance(otherP), 2));
	}

	/**
	 * Calculate the Force in x direction
	 * @param otherP
	 * @return  Fx
	 */
	public double calcForceExertedByX(Planet otherP){
		double dx = otherP.xxPos - this.xxPos;
		return (this.calcForceExertedBy(otherP) * dx) / this.calcDistance(otherP);
	}

	/**
	 * Calculate the Force in y direction
	 * @param otherP
	 * @return  Fy
	 */
	public double calcForceExertedByY(Planet otherP){
		double dy = otherP.yyPos - this.yyPos;
		return (this.calcForceExertedBy(otherP) * dy) / this.calcDistance(otherP);
	}

	/**
	 * Calculate Net Force in x direction
	 * @param planets array of some Planets
	 * @return  Fnet,x
	 */
	public double calcNetForceExertedByX(Planet[] planets){
		double res = 0.00;
		for (int i = 0; i < planets.length; i++){
			if (this.equals(planets[i])){
				continue;
			}
			res += this.calcForceExertedByX(planets[i]);
		}
		return res;
	}

	/**
	 * Calculate Net Force in y direction
	 * @param planets array of some Planets
	 * @return Fnet,y
	 */
	public double calcNetForceExertedByY(Planet[] planets){
		double res = 0.00;
		for (int i = 0; i < planets.length; i++){
			if (this.equals(planets[i])){
				continue;
			}
			res += this.calcForceExertedByY(planets[i]);
		}
		return res;
	}

	public void update(double dt, double Fx,double Fy){
		 double ax = Fx / this.mass;
		 double ay = Fy / this.mass;
		 this.xxVel = this.xxVel + dt * ax;
		 this.yyVel = this.yyVel + dt * ay;
		 this.xxPos = this.xxPos + dt * this.xxVel;
		 this.yyPos = this.yyPos + dt * this.yyVel;
	}

	public void draw(){
		StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
	}

}