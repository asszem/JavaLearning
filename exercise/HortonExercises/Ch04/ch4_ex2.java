/*
Write a program to create a rectangular array containing a multiplication table from 1 * 1 up to 12 * 12. Output
the table as 13 columns with the numeric values right-aligned in the columns. (The first line of output is the
column headings, the first column with no heading, then the numbers 1 to 12 for the remaining columns.
The first item in each of the succeeding lines is the row heading, which ranges from 1 to 12.)
 */
package HortonExercises.Ch04;

public class ch4_ex2 {

	public static void main(String[] args) {
		int[][] mtable = new int[12][12];
		for (int i = 0; i < 12; i++) {
			mtable[i][i] = i + 1;
			if (i + 1 < 10) {
				System.out.print("\t  " + (i + 1));
			} else {
				System.out.print("\t " + (i + 1));
			}
		}
		StringBuffer sep = new StringBuffer(1);
		for (int i = 0; i < mtable.length * 8 + 3; i++) {
			sep.append("=");
		}
		System.out.println("\n" + sep);
		for (int i = 0; i < mtable.length; i++) {
			System.out.print("Row " + (i + 1 < 10 ? " " : "") + mtable[i][i] + ":");
			for (int j = 0; j < mtable[1].length; j++) {
				int szorzat = (i + 1) * (j + 1);
				if (szorzat < 10) {
					System.out.print("\t  " + szorzat);
				} else if (szorzat < 100) {
					System.out.print("\t " + szorzat);
				} else {
					System.out.print("\t" + szorzat);
				}
			}
			System.out.println("");
		}
	}
}
