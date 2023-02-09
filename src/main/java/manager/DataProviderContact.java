package manager;

import model.Contact;
import model.User;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class DataProviderContact {
    @DataProvider
    public Iterator <Object[]> contactData(){
        List<Object[]> list = new ArrayList<Object[]>();
        Random random = new Random();
        int i = random.nextInt(1000)+1000;
        list.add(new Object[]{"Alina"+i,"White","0863435566","alinaw"+i+"@gmail.com","Kyiv","Childhood friend"});
        return list.iterator();
    }
    @DataProvider
    public Iterator<Object[]> addDataContact(){
        List<Object[]> list = new ArrayList<>();
        Random random = new Random();
        int i = random.nextInt(1000)+1000;
        list.add(new Object[]{Contact.builder().name("Alina"+i).lastName("White").phone("0863434"+i).email("alina"+i+"@gmail.com").address("Kyiv").description("Childhood friend").build()});

        return list.iterator();
    }
    @DataProvider
    public Iterator<Object[]> addDataContactFromFile() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("src/test/resources/contact.csv")));
        String line = bufferedReader.readLine();
        while (line!=null){
            String[] split = line.split(",");
            list.add(new Object[]{new User().withEmail(split[0]).withPassword(split[1])});
            line = bufferedReader.readLine();
        }
        return list.iterator();
    }
}
