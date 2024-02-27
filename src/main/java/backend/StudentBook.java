package backend;

import java.time.LocalDate;

public class StudentBook implements LibraryObject {
    private String studentId;
    private String bookId;
    private LocalDate borrowDate;

    public StudentBook(String studentId, String bookId, LocalDate borrowDate){
        this.studentId = studentId;
        this.bookId = bookId;
        this.borrowDate = borrowDate;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getBookId() {
        return bookId;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    @Override
    public String lineRepresentation() {
        return studentId+","+bookId+","+borrowDate;
    }

    @Override
    public String getSearchKey() {
        return studentId+","+bookId;
    }
    

}
