package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import ru.stqa.pft.addressbook.model.ContactData;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

    @Parameter(names = "-c", description = "Contact count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex) {
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<ContactData> contacts = generatorContacts(count);
        save(contacts, new File(file));
    }

    private static void save(List<ContactData> contacts, File file) throws IOException {
        System.out.println(new File(".").getAbsolutePath());
        Writer writer = new FileWriter(file);
        for (ContactData contact : contacts) {
            writer.write(String.format("%s;%s;%s;%s;%s\n", contact.getFirstname(), contact.getLastname(),
                    contact.getAddress(), contact.getAllEmails(), contact.getAllPhones()));
        }
        writer.close();

    }


    private static List<ContactData> generatorContacts(int count) {
        List<ContactData> contacts = new ArrayList<>();
        for (int i = 0; i <count; i++) {
            contacts.add(new ContactData().withFirstname(String.format("FirstName %s", i))
                    .withLastname(String.format("Last %s", i)).withAddress(String.format("address 76-90 %s", i))
                    .withAllEmails(String.format("e@mail.com %s", i)).withAllPhones(String.format("3456789 %s", i)));
        }
        return contacts;
    }

}
