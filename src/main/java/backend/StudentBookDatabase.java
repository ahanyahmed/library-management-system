package backend;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class StudentBookDatabase extends Database{

    public StudentBookDatabase(String filename) {
        super(filename);
    }

    @Override
    public LibraryObject createRecordFrom(String line) {
        String data [] = line.split(",");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
        LocalDate l = LocalDate.parse(data[2],formatter);
        StudentBook temp = new StudentBook(data[0],data[1],l);
        return temp;
    }
    
    
}
