package GUI.JSamples;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

//TODO make a window to display all border types
public class Border_Sample {

	EtchedBorder borderEtched = new EtchedBorder(EtchedBorder.RAISED);
	BevelBorder borderBevel = new BevelBorder(BevelBorder.RAISED);
	CompoundBorder compoundBorder = new CompoundBorder(BorderFactory.createLineBorder(Color.black, 1),              // Outer border
			BorderFactory.createBevelBorder(BevelBorder.RAISED));      												// Inner border
	TitledBorder titledBorder = new TitledBorder(new EtchedBorder(), "Line Color");
}
