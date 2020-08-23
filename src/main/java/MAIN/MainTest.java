package MAIN;

import Entity.Department;
import Entity.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainTest {
        public static void main(String[] args) {
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");
            EntityManager entityManager = entityManagerFactory.createEntityManager();

            Logger logger = Logger.getLogger(MAIN.Main3.class.getName());
            Scanner scanner = new Scanner(System.in);
            int input=0;
            boolean quit = false;
            do{
                try{
                    input = menu();
                }catch (Exception e){
                    // logger.log(Level.WARNING, "Ati introdus un caracter nepermis!");
                    logger.warning("Ati introdus un caracter nepermis");
                }
                switch (input) {
                    case 1:
                        logger.log(Level.INFO, "Ati selectat afisarea departamentelor");
                        searchAllDepartments();
                        Scanner scanner1 = new Scanner(System.in);
                        int test;
                        do {
                            System.out.println("Doriti sa efectuati alta operatie?");
                            System.out.println("1 - DA || 2 - NU");
                            try{
                                test = scanner1.nextInt();
                                if (test == 1)
                                    break;
                                else if (test == 2)
                                    quit = true;
                            }catch(Exception e){
                                logger.log(Level.SEVERE, "Ati introdus un caracter nepermis!");
                                break;
                            }
                        }while(test<1||test>2);
                        break;
                    case 2:
                        System.out.println("Introduceti ID-ul departamentului: ");
                        int department = scanner.nextInt();
                        Department department1 = entityManager.find(Department.class, department);
                        if(department1 == null){
                            logger.log(Level.WARNING, "Departamentul introdus nu exista!");
                            break;
                        }
                        searchByDepartment(department);
                        Scanner scanner2 = new Scanner(System.in);
                        int test1;
                        do {
                            System.out.println("Doriti sa efectuati alta operatie?");
                            System.out.println("1 - DA || 2 - NU");
                            try{
                                test1 = scanner2.nextInt();
                                if (test1 == 1)
                                    break;
                                else if (test1 == 2)
                                    quit = true;
                            }catch(Exception e){
                                logger.log(Level.SEVERE, "Ati introdus un caracter nepermis!");
                                break;
                            }
                        }while(test1<1||test1>2);
                        break;
                    case 3:
                        orderByName();
                        Scanner scanner3 = new Scanner(System.in);
                        int test2;
                        do {
                            System.out.println("Doriti sa efectuati alta operatie?");
                            System.out.println("1 - DA || 2 - NU");
                            try{
                                test2 = scanner3.nextInt();
                                if (test2 == 1)
                                    break;
                                else if (test2 == 2)
                                    quit = true;
                            }catch(Exception e){
                                logger.log(Level.SEVERE, "Ati introdus un caracter nepermis!");
                                break;
                            }
                        }while(test2<1||test2>2);
                        break;
                    case 4:
                        orderBySalary();
                        Scanner scanner4 = new Scanner(System.in);
                        int test3;
                        do {
                            System.out.println("Doriti sa efectuati alta operatie?");
                            System.out.println("1 - DA || 2 - NU");
                            try{
                                test3 = scanner4.nextInt();
                                if (test3 == 1)
                                    break;
                                else if (test3 == 2)
                                    quit = true;
                            }catch(Exception e){
                                logger.log(Level.SEVERE, "Ati introdus un caracter nepermis!");
                                break;
                            }
                        }while(test3<1||test3>2);
                        break;
                    case 5:
                        //Scanner idAngajat = new Scanner(System.in);
                        System.out.println("Introduceti ID-ul angajatului: ");
                        int id = scanner.nextInt();
                        Employee employee = entityManager.find(Employee.class, id);
                        if(employee == null){
                            logger.log(Level.WARNING,"ID-ul introdus nu exista");
                            break;
                        }
                        else
                            modifyEmployee(id);
                        Scanner scanner5 = new Scanner(System.in);
                        int test4;
                        do {
                            System.out.println("Doriti sa efectuati alta operatie?");
                            System.out.println("1 - DA || 2 - NU");
                            try{
                                test4 = scanner5.nextInt();
                                if (test4 == 1)
                                    break;
                                else if (test4 == 2)
                                    quit = true;
                            }catch(Exception e){
                                logger.log(Level.SEVERE, "Ati introdus un caracter nepermis!");
                                break;
                            }
                        }while(test4<1||test4>2);
                        break;
                    case 6:
                        quit = true;
                        break;
                    default:
                        break;

                }}while(!quit);
        }

        public static int menu(){
            Scanner obj = new Scanner(System.in);
            System.out.println("Introduceti optiunea dorita: ");
            System.out.println("1 - Vizualizare departamente");
            System.out.println("2 - Vizualizare angajati pe departamente");
            System.out.println("3 - Afisarea angajatilor in ordine alfabetica");
            System.out.println("4 - Afisarea angajatilor dupa salariu");
            System.out.println("5 - Modificare informatii angajat");
            System.out.println("6 - Exit");
            int option = obj.nextInt();
            return option;
        }

        public static List<Department> searchAllDepartments(){
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            Logger logger = Logger.getLogger(MAIN.Main3.class.getName());
            entityManager.getTransaction().begin();
            List<Department> results = entityManager.createQuery( "from Department", Department.class ).getResultList();
            if(results.size()==0){
                logger.log(Level.INFO, "Nu exista departamente");
            }
            else {
                for (int i = 0; i < results.size(); i++) {
                    Department department = results.get(i);
                    System.out.println(department.getId() + " " + department.getName());
                }
                entityManager.getTransaction().commit();
                entityManager.close();
            }

            return results;
        }

        public static boolean searchByDepartment(int departmentId){
            Logger logger = Logger.getLogger(MAIN.Main3.class.getName());
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");
            EntityManager entityManager = entityManagerFactory.createEntityManager();

            String query="FROM Employee WHERE departmentId = "+departmentId;

            entityManager.getTransaction().begin();
            List<Employee> result = entityManager.createQuery( query, Employee.class ).getResultList();
            if(result.size()==0){
                Department department = entityManager.find(Department.class, departmentId);
                String string = "Nu exista angajati in departamentul "+department.getName();
                logger.log(Level.INFO, string);
            }
            for(int i=0;i<result.size();i++)
            {
                Employee employee = result.get(i);
                System.out.println(employee);
            }
            entityManager.getTransaction().commit();
            entityManager.close();
            return false;
        }

        public static void orderByName(){
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");
            EntityManager entityManager = entityManagerFactory.createEntityManager();

            String query="FROM Employee ORDER BY firstName";

            entityManager.getTransaction().begin();
            List<Employee> result = entityManager.createQuery( query, Employee.class ).getResultList();
            for(int i=0;i<result.size();i++)
            {
                Employee employee = result.get(i);
                System.out.println(employee);

            }
            entityManager.getTransaction().commit();
            entityManager.close();
        }
        public static void orderBySalary(){
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");
            EntityManager entityManager = entityManagerFactory.createEntityManager();

            String query="FROM Employee ORDER BY salary";

            entityManager.getTransaction().begin();
            List<Employee> result = entityManager.createQuery( query, Employee.class ).getResultList();
            for(int i=0;i<result.size();i++)
            {
                Employee employee = result.get(i);
                System.out.println(employee);

            }
            entityManager.getTransaction().commit();
            entityManager.close();
        }

        public static void modifyEmployee(int id){
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            Logger logger = Logger.getLogger(MAIN.Main3.class.getName());
            entityManager.getTransaction().begin();
            Employee employee = entityManager.find(Employee.class, id);
            System.out.println(employee);
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Introduceti noul nume: ");
                String name = scanner.nextLine();
                employee.setLastName(name);
                System.out.println("Introduceti noul prenume: ");
                String firstName = scanner.nextLine();
                employee.setFirstName(firstName);
                System.out.println("Introduceti noul salariu: ");
                double salary = scanner.nextDouble();
                employee.setSalary(salary);
                System.out.println("Introduceti daca are sau nu permis: ");
                boolean drivingLicense = scanner.nextBoolean();
                employee.setHasDrivingLicense(drivingLicense);
                System.out.println(employee);
                entityManager.getTransaction().commit();
                entityManager.close();
            }catch(Exception e){
                entityManager.getTransaction().rollback();
                logger.log(Level.SEVERE, "Ati introdus o valoare gresita, operatie esuata!");
            }
        }

        public static void showEmployee(Employee employee){
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            //JobCategories jobCategoryId = employee.getJobCategories();
            Department department1 = employee.getDepartment();
            Department department = entityManager.find(Department.class, department1.getId());
            //JobCategories jobCategories = entityManager.find(JobCategories.class, jobCategoryId);
            System.out.println("First Name " + employee.getFirstName());
            System.out.println("Last Name " + employee.getLastName());
            //System.out.println("Job ID " + jobCategories.getName());
            System.out.println("Department ID " + department.getName());
            System.out.println("Manager " + employee.isManager());
            System.out.println("Start Date " + employee.getStartDate());
            System.out.println("End Date" + employee.getEndDate());
            System.out.println("Active " + employee.isActive());
            System.out.println("Address " + employee.getAddress());
            System.out.println("Postal Code " + employee.getPostalCode());
            System.out.println("Phone number " + employee.getTelephone());
            System.out.println("Email " + employee.getEmail());
            System.out.println("Birthday " + employee.getBirthday());
            System.out.println("Children " + employee.getNoChildren());
            System.out.println("Salary " + employee.getSalary());
            System.out.println("Studies " + employee.getStudies());
            System.out.println("Social Security " + employee.getSocialSecurityNumber());
            System.out.println("Driving License " + employee.isHasDrivingLicense());
            entityManager.getTransaction().commit();
            entityManager.close();
        }
    }


