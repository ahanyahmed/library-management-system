package backend;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import constants.FileNames;

public class LibrarianRole {
    private BookDatabase booksDatabase = new BookDatabase(FileNames.BOOKS_FILENAME);
    private StudentBookDatabase sBDatabase = new StudentBookDatabase(FileNames.STUDENTSBOOKS_FILENAME);

    public LibrarianRole() {
        booksDatabase.readFromFile();
        sBDatabase.readFromFile();
    }

    public void addBook(String id, String title, String authorName, String publisherName, int quantity) {
        booksDatabase.insertRecord(booksDatabase.createRecordFrom(id + "," + title + "," + authorName + "," + publisherName + "," + quantity));
    }

    public Book[] getListOfBooks() {
        Book users[] = new Book[booksDatabase.returnAllRecords().size()];
        for (int i = 0; i < booksDatabase.returnAllRecords().size(); i++)
            users[i] = (Book) booksDatabase.returnAllRecords().get(i);
        return users;
    }

    public StudentBook[] getListOfBorrowingOperations() {
        StudentBook users[] = new StudentBook[sBDatabase.returnAllRecords().size()];
        for (int i = 0; i < sBDatabase.returnAllRecords().size(); i++)
            users[i] = (StudentBook) sBDatabase.returnAllRecords().get(i);
        return users;
    }

     public int borrowBook(String studentId, String bookId, LocalDate borrowDate) {
         if (sBDatabase.contains(studentId+","+bookId))
             return 1;
        if(((Book)booksDatabase.getRecord(bookId)).getQuantity()==0)
             return 0;
         else{
             ((Book) booksDatabase.getRecord(bookId)).setQuantity(((Book) booksDatabase.getRecord(bookId)).getQuantity()-1);
             sBDatabase.insertRecord(sBDatabase.createRecordFrom(studentId+","+bookId+","+borrowDate));
             return 2;
         }
     }

     public double returnBook(String studentId, String bookId, LocalDate returnDate){
        double period=0;
         period =  ChronoUnit.DAYS.between(((StudentBook) sBDatabase.getRecord(studentId+","+bookId)).getBorrowDate(), returnDate); 
        ((Book) booksDatabase.getRecord(bookId)).setQuantity(((Book) booksDatabase.getRecord(bookId)).getQuantity()+1);
         sBDatabase.deleteRecord(studentId+","+bookId);
         if(period<7)
             return 0;
         else
             return (period-7)*0.5;
     }

    public void logout(){
        booksDatabase.saveToFile();
        sBDatabase.saveToFile();
    }
}
