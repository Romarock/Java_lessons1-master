package ru.stqa.pft.mantis.appmanager;

import org.apache.commons.net.ftp.FTPClient;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.BeforeClass;
import ru.stqa.pft.mantis.model.User;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class FtpHelper {

    private final ApplicationManager app;
    private FTPClient ftp;

    public  FtpHelper(ApplicationManager app) {

        this.app = app;
        ftp = new FTPClient();
    }

    public void upload (File file, String target, String backup) throws IOException {

        ftp.connect(app.getProperty("ftp.host"));
        ftp.login(app.getProperty("ftp.login"), app.getProperty("ftp.password"));
        ftp.deleteFile(backup);
        ftp.rename(target, backup);
        ftp.enterLocalPassiveMode();
        ftp.storeFile(target, new FileInputStream(file));
        ftp.disconnect();
    }
    public void restore(String backup, String target) throws IOException {
        ftp.connect(app.getProperty("ftp.host"));
        ftp.login(app.getProperty("ftp.login"), app.getProperty("ftp.password"));
        ftp.deleteFile(backup);
        ftp.rename(target, backup);
        ftp.disconnect();

    }

    public static class DbHelper {

        @BeforeClass
        protected void setUp() throws Exception {
            // A SessionFactory is set up once for an application!
            final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                    .configure() // configures settings from hibernate.cfg.xml
                    .build();
            try {
                sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
            }
            catch (Exception e) {
                e.printStackTrace();
                // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
                // so destroy it manually.
                StandardServiceRegistryBuilder.destroy( registry );
            }
        }

        private SessionFactory sessionFactory;

        public DbHelper() {


            // A SessionFactory is set up once for an application!
            final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                    .configure() // configures settings from hibernate.cfg.xml
                    .build();

            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();


        }

        public List<User> users() {

            Session session = sessionFactory.openSession();
            session.beginTransaction();
            List<User> users = session.createQuery("from User").list();

            session.getTransaction().commit();
            session.close();
            return users;


        }



    }
}
