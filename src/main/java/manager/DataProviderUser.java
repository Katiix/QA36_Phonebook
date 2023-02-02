package manager;

import model.User;
import org.testng.annotations.DataProvider;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderUser {
    @DataProvider
    public Iterator<Object[]> loginDataCls(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"kate@gmail.com","Kkate12345$"});
        list.add(new Object[]{"kate@gmail.com","Kkate12345$"});
        list.add(new Object[]{"kate@gmail.com","Kkate12345$"});
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> loginDataUser(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{User.builder().email("kate@gmail.com").password("Kkate12345$").build()});
        
        return list.iterator();
    }
    @DataProvider
    public Iterator<Object[]> loginDataUserFromFile() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("src/test/resources/data.csv")));
        String line = bufferedReader.readLine();
        while (line!=null){
            String[] split = line.split(",");
            list.add(new Object[]{User.builder().email(split[0]).password(split[1]).build()});
            line = bufferedReader.readLine();
        }
        return list.iterator();
    }
}
