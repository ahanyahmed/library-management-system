package backend;

import java.util.ArrayList;
import java.io.*;


abstract class Database {
    protected ArrayList <LibraryObject> records = new ArrayList <>();
    protected String filename;

    public Database(String filename) {
        this.filename = filename;
    }
    public void readFromFile(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while((line = reader.readLine()) != null) {
              records.add(createRecordFrom(line));
            }
            reader.close();
          } catch (IOException e) {
            e.printStackTrace();
          }
    }
    
    public abstract LibraryObject createRecordFrom(String line);

    public ArrayList<LibraryObject> returnAllRecords(){
        return records;
    }
    public boolean contains(String key){
        if(getRecord(key)!=null)
            return true;
        else 
            return false;
    }
    public LibraryObject getRecord(String key){
        for(int i=0;i<records.size();i++){
            if(records.get(i).getSearchKey().equals(key))
                return records.get(i);
        }
        return null;
    }
    public void insertRecord(LibraryObject record){
        if(!contains(record.getSearchKey()))
            records.add(record);
        else
            System.out.println("ID already exists");
    }
    public void deleteRecord(String key){
        if(!contains(key))
        {}
        else{
        for(int i=0;i<records.size();i++){
            if(records.get(i).getSearchKey().equals(key))
                records.remove(i);
        }
    }
    }
    public void saveToFile(){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            for(int i=0;i<records.size();i++){
                writer.write(records.get(i).lineRepresentation());
                if(i<records.size()-1)
                    writer.write("\n");
            }
            writer.close();
          } catch (IOException e) {
            e.printStackTrace();
          }
    }
}
