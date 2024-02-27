package backend;

class BookDatabase extends Database{

    public BookDatabase(String filename) {
        super(filename);
    }

    @Override
    public LibraryObject createRecordFrom(String line) {
        String data [] = line.split(",");
        int quantity = Integer.parseInt(data[4]);
        Book temp = new Book(data[0], data[1], data[2], data[3], quantity);
        return temp;
    }
    
}
