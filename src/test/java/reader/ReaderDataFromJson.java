package reader;

import com.google.gson.Gson;
import readerdata.DataModel;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class ReaderDataFromJson {
    public DataModel readerJsonFile() throws FileNotFoundException {
        FileReader fileReader = new FileReader("readerdata/data.json");
        DataModel dataModel = new Gson().fromJson(fileReader , DataModel.class);
        return dataModel;
    }

    public static DataModel dataModel() throws FileNotFoundException {
        ReaderDataFromJson readerDataFromJson = new ReaderDataFromJson();
        return readerDataFromJson.readerJsonFile();
    }


}
