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
    public Iterator<Object[]> xxxx(){
        List<Object[]> list = new ArrayList<>();


        return list.iterator();
    }
    @DataProvider
    public Iterator<Object[]> loginDataUser(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{new User().withEmail("kate@gmail.com").withPassword("Kkate12345$")});
        
        return list.iterator();
    }
    @DataProvider
    public Iterator<Object[]> loginDataUserFromFile() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("src/test/resources/data.csv")));
        String line = bufferedReader.readLine();
        while (line!=null){
            String[] split = line.split(",");
            list.add(new Object[]{new User().withEmail(split[0]).withPassword(split[1])});
            line = bufferedReader.readLine();
        }
        return list.iterator();
    }
}
