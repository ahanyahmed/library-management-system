package backend;
import constants.FileNames;
public class AdminRole {
    private LibrarianUserDatabase database = new LibrarianUserDatabase(FileNames.LIBRARIANS_FILENAME);
    public AdminRole() {
        database.readFromFile();
    }
    public void addLibrarian(String librarianId, String name, String email, String address, String phoneNumber){
        database.insertRecord(database.createRecordFrom(librarianId+","+name+","+email+","+address+","+phoneNumber));
    }
    public LibrarianUser[] getListOfLibrarians(){
        LibrarianUser users[] = new LibrarianUser [database.returnAllRecords().size()];
        for(int i=0;i<database.returnAllRecords().size();i++){
            users[i]=(LibrarianUser) database.returnAllRecords().get(i);
        }
        return users;
    }
    public void removeLibrarian(String key){
        database.deleteRecord(key);
    }
    public void logout(){
        database.saveToFile();
    }
}
