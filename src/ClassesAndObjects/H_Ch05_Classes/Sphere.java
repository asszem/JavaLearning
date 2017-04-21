package ClassesAndObjects.H_Ch05_Classes;

public class Sphere {

	static final double PI = 3.14;  //static class variable, final
	static int count = 0;           //static class variable used to count number of objects

	double xCenter;                 //non-static instance variables
	double zCenter;
	double yCenter;
	double radius;

// Construct a unit sphere at the origin
	public Sphere() {
		radius = 1.0;
// Other data members will be zero by default
		++count; // Update object count
	}
// Construct a unit sphere at a point

	Sphere(double x, double y, double z) {
		this(); // Call the Sphere() constructor with no arguments -> sets radius to 1.0
		xCenter = x;
		yCenter = y;
		zCenter = z;
	}

	Sphere(double theRadius, double x, double y, double z) {
		this(x, y, z); // Call the 3 argument constructor
		radius = theRadius; // Set the radius
	}

	Sphere(int color, double radius, double x, double y, double z) {
		this(radius, x, y, z);
		System.out.println("A színérték pediglen" + color);
	}

	Sphere(final Sphere oldSphere) {
		radius = oldSphere.radius;
		xCenter = oldSphere.xCenter;
		yCenter = oldSphere.yCenter;
		zCenter = oldSphere.zCenter;
		++count;
	}

	/*
  Sphere(double theRadius, double x, double y, double z) {
//    System.out.println("Sphere 4 param called");
    xCenter = x;
    zCenter = z;
    yCenter = y;
    radius = theRadius;
    count++;
  }

  // Construct a unit sphere at a point
  Sphere(double x, double y, double z) {
//    System.out.println("Sphere(3 param) called");
    xCenter = x;
    yCenter = y;
    zCenter = z;
    radius = 1.0;
    ++count; // Update object count
  }

// Construct a unit sphere at the origin
  Sphere() {
//    System.out.println("Sphere() called");
    xCenter = 0.0;
    yCenter = 0.0;
    zCenter = 0.0;
    radius = 1.0;
    ++count; // Update object count
  }
	 */
	static int getCount() {
//    System.out.println("Runnning static int GetCount(). A count változó értéke:" + count);
		return count;
	}

	void changeRadius(double newRadius) {
		this.radius = newRadius;
	}

	double volume() {
		return 4.0 / 3.0 * Math.PI * Math.pow(radius, 3);
	}
}
