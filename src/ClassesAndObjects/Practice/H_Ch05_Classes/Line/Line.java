package ClassesAndObjects.Practice.H_Ch05_Classes.Line;

class Line {			//Non-public, so it is accessible only from classes in the same package
  Point start;                              // Start point of line
  Point end;                                // End point of line

  // Create a line from two points
  Line(final Point start, final Point end) {
	//Ha itt nem new Pointot használok, csak simán a start/end párost, akkor a hivatkozott Point változó értékének változtatása a vonalat is változtatja!
    this.start = new Point(start);							//a vonalban létrehozott Point egy új objektum lesz!
    this.end = new Point(end);
  }

  // Create a line from two coordinate pairs
  Line(double xStart, double yStart, double xEnd, double yEnd) {
    start = new Point(xStart, yStart);      // Create the start point
    end = new Point(xEnd, yEnd);            // Create the end point
  }

  // Calculate the length of a line
  double length() {
    return start.distance(end);             // Use the method from the Point class
  }

// Return a point as the intersection of two lines
Point intersects(final Line line1) {											//Point osztály metódusa, paramétere egy Line objektum
  Point localPoint = new Point(0, 0);											//lokális objektum, mert a localPoint változó csak a metóduson belül él

  				
  double num = (end.y - start.y)*(start.x - line1.start.x) -					//Az "end" egy Point típusú objektum, két változó van benne: end.x és end.y
               (end.x - start.x)*(start.y - line1.start.y);						//Az "end" a Line objektum egyik instance változója
  																				//line.1.start.x a paraméter Line objektum start point objektumának x értéke
																		//end.y - start.y --> függőleges hossz (kiinduló objektum)
																		//end.x - start.x --> vízszintes hossz (kiinduló objektum)
																		//start.x - line1.start.x --> vízszintes méret különbsége kiinduló és cél obj között
																		//start.y - line1.start.y --> függőleges méret különbség kiinduló és cél obj között. 
																		//függőleges hossz * vízszintes méret különbség
																		//vízszintes hossz * függőleges méret különbség

  double denom = (end.y - start.y)*(line1.end.x - line1.start.x) -		//line1.end.x - line1.start.x --> a cél obj hossza
                 (end.x - start.x)*(line1.end.y - line1.start.y);

  localPoint.x = line1.start.x + (line1.end.x - line1.start.x)*num/denom;	//A localPoint objektumnak két field-je van: x és y. 
  localPoint.y = line1.start.y + (line1.end.y - line1.start.y)*num/denom;   //A metódus kimenetele egy Point objektum lesz, a kerezsteződés koordinátáival

  return localPoint;
}

  // Convert a line to a string
  @Override
  public String toString() {
    return "(" + start+ "):(" + end + ")";  // As "(start):(end)"
  }                                         // that is, "(x1, y1):(x2, y2)"
}
