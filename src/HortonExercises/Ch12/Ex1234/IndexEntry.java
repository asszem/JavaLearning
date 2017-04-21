package HortonExercises.Ch12.Ex1234;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class IndexEntry implements Serializable, Filepaths {
/*
	IndexEntry.bin
	filePosition=1 (in the Object file)
	nameObject=reference to the Name object  --- not sure why this is needed
				maybe if there are multiple objects with the same name?
				the filePosition field exactly identifies the user

	Person.bin
	Object1
		Read object 1 -> the reading sequence number will be the index
		Check if readed sequence number(1) == IndexEntry filePosition
		return name object
	Object2
	Object3....
	
	*/
	private static final long serialVersionUID = 0001L;
	private int filePosition;
	Person.Name nameObject;

	//Constructor
	public IndexEntry(Person.Name newNameObject){
		nameObject=newNameObject;
		ArrayList personFileObjectCount = Ex1234_Andras.readAllObjectsFromFile(personFile);
		//TODO to be continued 
		filePosition=personFileObjectCount.size()+1;
	}
}
