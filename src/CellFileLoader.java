import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

class CellFileLoader extends FileLoader{
    HashMap<String,int[][]> cellsMap;
    CellFileLoader(String directory, String regExp) {
        super(directory, regExp);
        cellsMap = new HashMap<>();
        for (File file : files) {
            try {
                Scanner fileContent = new Scanner(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            
        }
    }
    
    int[][] getCellArrayWithName(String name){
        return new int[10][10];
    }
}
