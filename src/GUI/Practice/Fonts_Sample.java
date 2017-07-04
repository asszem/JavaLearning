package GUI.Practice;

import java.awt.Font;

import GUI.JFrame_Collection;

public class Fonts_Sample {

	public static void main(String[] args) {
		Font[] fonts = JFrame_Collection.getAllFonts();
//		System.out.println(java.util.Arrays.toString(fonts));
		int counter=0;
		for (Font font:fonts){
			System.out.println("-------------");
			System.out.println("Font number: "+counter++);
			System.out.println(font);
			System.out.println("-------------");
		}
		Font myTimes = fonts[357].deriveFont(Font.BOLD, 20);
		System.out.println(myTimes);
		
		String[] fontnames = JFrame_Collection.getAllFontNames();
		counter=0;
		System.out.println("Printing out fontnames only");
		for (String font:fontnames){
			System.out.println("Font number: "+(counter++)+" "+ font);
		}
		Font myTimes2 = new Font(fontnames[195], Font.ITALIC | Font.BOLD, 12);
		System.out.println(myTimes2);
	}

}
