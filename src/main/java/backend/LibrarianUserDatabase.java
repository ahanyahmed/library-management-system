package backend;

class LibrarianUserDatabase extends Database {
    
    public LibrarianUserDatabase(String filename) {
        super(filename);
    }

    @Override
    public LibraryObject createRecordFrom(String line) {
            String data [] = line.split(",");
            LibrarianUser temp = new LibrarianUser(data[0], data[1], data[2], data[3], data[4]);
            return temp;
    }
    
    
}
