package MAIN;

import Entity.Department;
import Entity.Employee;
import Entity.JobCategories;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


public class Main3 {
    public static void main(String[] args) {
        optiuniMenuPrincipal();
    }

    //region Meniuri
//---------------------------------------------------------------------------------------
    public static int menuPrincipal() {
        Scanner obj = new Scanner(System.in);
        System.out.println("Introduceti optiunea dorita: ");
        System.out.println("1 - Vizualizare");
        System.out.println("2 - Adaugare");
        System.out.println("3 - Modificare");
        System.out.println("4 - Stergere");
        System.out.println("5 - Exit");
        return obj.nextInt();
    }
    public static int menuVizualizare() {
        Scanner obj = new Scanner(System.in);
        System.out.println("Introduceti optiunea dorita: ");
        System.out.println("1 - Departamente");
        System.out.println("2 - Joburi");
        System.out.println("3 - Angajati");
        System.out.println("4 - Angajatii dintr-un departament");
        System.out.println("5 - Angajatii ordonati alfabetic");
        System.out.println("6 - Angajatii ordonati dupa salariu");
        System.out.println("7 - Inapoi");
        System.out.println("8 - Exit");
        return obj.nextInt();
    }
    public static int menu() {
        Scanner obj = new Scanner(System.in);
        System.out.println("Introduceti optiunea dorita: ");
        System.out.println("1 - Departament");
        System.out.println("2 - Job");
        System.out.println("3 - Angajat");
        System.out.println("4 - Inapoi");
        System.out.println("5 - Exit");
        return obj.nextInt();
    }
    /**
     * Realizeaza actiunile din meniul principal
     * Apeleaza functia aferena pentru actiunea selectata
     * Functia se executa pana se selecteaza optiunea de iesire
     */
    public static void optiuniMenuPrincipal() {
        Logger logger = Logger.getLogger(Main3.class.getName());
        createLogFile();
        int input = 0;
        boolean quit = false;
        do {
            try {
                input = menuPrincipal();
            } catch (Exception e) {
                logger.log(Level.WARNING, "Ati introdus un caracter nepermis!");
            }
            switch (input) {
                case 1:
                    logger.log(Level.INFO, "Ati selectat meniul de vizualizare");
                    Vizualizare();
                    break;
                case 2:
                    logger.log(Level.INFO, "Ati selectat meniul de adaugare");
                    adaugare();
                    break;
                case 3:
                    logger.log(Level.INFO, "Ati selectat meniul de modificare");
                    modificare();
                    break;
                case 4:
                    logger.log(Level.INFO, "Ati selectat meniul de stergere");
                    stergere();
                    break;
                case 5:
                    logger.log(Level.INFO, "Ati selectat iesirea din aplicatie");
                    quit=true;
                    //System.exit(0);
                    break;
                default:
                    break;
            }
        } while (!quit);
    }

    /**
     * Realizeaza actiunile din meniul pentru vizualizare
     * Apeleaza functia aferenta optiunii selectate
     * Functia se executa pana la selectarea optiunii de iesire
     */
    public static void Vizualizare() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Logger logger = Logger.getLogger(Main3.class.getName());
        createLogFile();
        Scanner scanner = new Scanner(System.in);
        int optionSelected = 0;
        boolean quit = false;
        do {
            try {
                optionSelected = menuVizualizare();
            } catch (Exception e) {
                logger.log(Level.WARNING, "Ati introdus un caracter nepermis!");
            }
            switch (optionSelected) {
                case 1:
                    logger.log(Level.INFO, "Ati selectat vizualizare departamente");
                    List<Department> departmentList = searchAllDepartments();
                    for (Department department : departmentList) {
                        System.out.println(department);
                    }
                    do {
                        System.out.println("Doriti sa efectuati alta operatie?");
                        System.out.println("1 - DA || 2 - NU");
                        try {
                            optionSelected = scanner.nextInt();
                            if (optionSelected == 1)
                                break;
                            else if (optionSelected == 2)
                                quit=true;
                        } catch (Exception e) {
                            logger.log(Level.SEVERE, "Ati introdus un caracter nepermis!");
                            break;
                        }
                    } while (!quit);
                    break;
                case 2:
                    logger.log(Level.INFO, "Ati selectat vizualizare joburi");
                    List<JobCategories> jobCategoriesList = searchAllJobs();
                    for (JobCategories jobCategories : jobCategoriesList) {
                        System.out.println(jobCategories);
                    }
                    do {
                        System.out.println("Doriti sa efectuati alta operatie?");
                        System.out.println("1 - DA || 2 - NU");
                        try {
                            optionSelected = scanner.nextInt();
                            if (optionSelected == 1)
                                break;
                            else if (optionSelected == 2)
                                quit=true;
                        } catch (Exception e) {
                            logger.log(Level.SEVERE, "Ati introdus un caracter nepermis!");
                            break;
                        }
                    } while (!quit);
                    break;
                case 3:
                    logger.log(Level.INFO, "Ati selectat vizualizare angajati");
                    List<Employee> employeeList = searchAllEmployee();
                    for(Employee employee: employeeList){
                        System.out.println(employee);
                    }
                    do {
                        System.out.println("Doriti sa efectuati alta operatie?");
                        System.out.println("1 - DA || 2 - NU");
                        try {
                            optionSelected = scanner.nextInt();
                            if (optionSelected == 1)
                                break;
                            else if (optionSelected == 2)
                                quit=true;
                        } catch (Exception e) {
                            logger.log(Level.SEVERE, "Ati introdus un caracter nepermis!");
                            break;
                        }
                    } while (!quit);
                    break;
                case 4:
                    logger.log(Level.INFO, "Ati selectat vizualizare angajati dintr-un departament");
                    System.out.println("Introduceti numarul departamentului: ");
                    optionSelected = scanner.nextInt();
                    Department department = entityManager.find(Department.class, optionSelected);
                    if(department == null){
                        logger.log(Level.WARNING, "Departamentul introdus nu exista!");
                        break;
                    }
                    else{
                        List<Employee> employeeList1 = searchByDepartment(optionSelected);
                        for (Employee employee : employeeList1) {
                            System.out.println(employee);
                        }
                    }
                    do {
                        System.out.println("Doriti sa efectuati alta operatie?");
                        System.out.println("1 - DA || 2 - NU");
                        try {
                            optionSelected = scanner.nextInt();
                            if (optionSelected == 1)
                                break;
                            else if (optionSelected == 2)
                                quit=true;
                        } catch (Exception e) {
                            logger.log(Level.SEVERE, "Ati introdus un caracter nepermis!");
                            break;
                        }
                    } while (!quit);
                    break;
                case 5:
                    logger.log(Level.INFO, "Ati selectat vizualizare angajati ordonati alfabetic");
                    orderByName();
                    do {
                        System.out.println("Doriti sa efectuati alta operatie?");
                        System.out.println("1 - DA || 2 - NU");
                        try {
                            optionSelected = scanner.nextInt();
                            if (optionSelected == 1)
                                break;
                            else if (optionSelected == 2)
                                quit=true;
                        } catch (Exception e) {
                            logger.log(Level.SEVERE, "Ati introdus un caracter nepermis!");
                            break;
                        }
                    } while (!quit);
                    break;
                case 6:
                    logger.log(Level.INFO, "Ati selectat vizualizare angajati ordonati dupa salariu");
                    orderBySalary();
                    do {
                        System.out.println("Doriti sa efectuati alta operatie?");
                        System.out.println("1 - DA || 2 - NU");
                        try {
                            optionSelected = scanner.nextInt();
                            if (optionSelected == 1)
                                break;
                            else if (optionSelected == 2)
                                quit=true;
                        } catch (Exception e) {
                            logger.log(Level.SEVERE, "Ati introdus un caracter nepermis!");
                            break;
                        }
                    } while (!quit);
                    break;
                case 7:
                    logger.log(Level.INFO, "Ati selectat inapoi");
                    //optiuniMenuPrincipal();
                    quit=true;
                    break;
                case 8:
                    logger.log(Level.INFO, "Ati selectat iesire meniu vizualizare");
                    System.exit(0);
                    break;
                default:
                    break;
            }
        } while (!quit);
    }
    /**
     * Realizeaza actiunile din meniul pentru adaugare
     * Apeleaza functia aferenta optiunii selectate
     * Functia se executa pana la selectarea optiunii de iesire
     */
    public static void adaugare() {
        Logger logger = Logger.getLogger(Main3.class.getName());
        createLogFile();
        Scanner scanner = new Scanner(System.in);
        int optionSelected = 0;
        boolean quit = false;
        do {
            try {
                optionSelected = menu();
            } catch (Exception e) {
                logger.log(Level.WARNING, "Ati introdus un caracter nepermis!");
            }
            switch (optionSelected) {
                case 1:
                    logger.log(Level.INFO, "Ati selectat adaugare departament nou");
                    addDepartment();
                    do {
                        System.out.println("Doriti sa efectuati alta operatie?");
                        System.out.println("1 - DA || 2 - NU");
                        try {
                            optionSelected = scanner.nextInt();
                            if (optionSelected == 1)
                                break;
                            else if (optionSelected == 2)
                                quit=true;
                        } catch (Exception e) {
                            logger.log(Level.SEVERE, "Ati introdus un caracter nepermis!");
                            break;
                        }
                    } while (!quit);
                    break;
                case 2:
                    logger.log(Level.INFO, "Ati selectat adaugare job nou");
                    addJob();
                    do {
                        System.out.println("Doriti sa efectuati alta operatie?");
                        System.out.println("1 - DA || 2 - NU");
                        try {
                            optionSelected = scanner.nextInt();
                            if (optionSelected == 1)
                                break;
                            else if (optionSelected == 2)
                                quit=true;
                        } catch (Exception e) {
                            logger.log(Level.SEVERE, "Ati introdus un caracter nepermis!");
                            break;
                        }
                    } while (!quit);
                    break;
                case 3:
                    logger.log(Level.INFO, "Ati selectat adaugare angajat nou");
                    addEmployee();
                    do {
                        System.out.println("Doriti sa efectuati alta operatie?");
                        System.out.println("1 - DA || 2 - NU");
                        try {
                            optionSelected = scanner.nextInt();
                            if (optionSelected == 1)
                                break;
                            else if (optionSelected == 2)
                                quit=true;
                        } catch (Exception e) {
                            logger.log(Level.SEVERE, "Ati introdus un caracter nepermis!");
                            break;
                        }
                    } while (!quit);
                    break;
                case 4:
                    logger.log(Level.INFO, "Ati selectat inapoi");
                    quit=true;
                    break;
                case 5:
                    logger.log(Level.INFO, "Ati selectat iesire");
                    System.exit(0);
                    break;
                default:
                    break;
            }
        } while (!quit);
    }
    /**
     * Realizeaza actiunile din meniul pentru modificare
     * Apeleaza functia aferenta optiunii selectate
     * Functia se executa pana la selectarea optiunii de iesire
     */
    public static void modificare() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Logger logger = Logger.getLogger(Main3.class.getName());
        createLogFile();
        Scanner scanner = new Scanner(System.in);
        int optionSelected = 0;
        boolean quit = false;
        do {
            try {
                optionSelected = menu();
            } catch (Exception e) {
                logger.log(Level.WARNING, "Ati introdus un caracter nepermis!");
            }
            switch (optionSelected) {
                case 1:
                    logger.log(Level.INFO, "Ati selectat modificarea denumirii unui departament");
                    System.out.println("Introduceti ID-ul departamentului: ");
                    try{
                    optionSelected = scanner.nextInt();}catch(Exception e){
                        logger.log(Level.WARNING,"Ati introdus un caracter nepermis la selectarea departamentului pentru modificare");
                    }
                    Department department = entityManager.find(Department.class, optionSelected);
                    if(department == null){
                        logger.log(Level.WARNING, "Departamentul introdus pentru modificare nu exista!");
                    }else{
                    modifyDepartment(optionSelected);
                    do {
                        System.out.println("Doriti sa efectuati alta operatie?");
                        System.out.println("1 - DA || 2 - NU");
                        try {
                            optionSelected = scanner.nextInt();
                            if (optionSelected == 1)
                                break;
                            else if (optionSelected == 2)
                                quit=true;
                        } catch (Exception e) {
                            logger.log(Level.SEVERE, "Ati introdus un caracter nepermis!");
                            break;
                        }
                    } while (!quit);}
                    break;
                case 2:
                    logger.log(Level.INFO, "Ati selectat modificarea denumirii unui job");
                    System.out.println("Introduceti ID-ul jobului: ");
                    try{
                    optionSelected = scanner.nextInt();}catch(Exception e){
                        logger.log(Level.WARNING, "Ati introdus un caracter nepermis la selectarea jobului pentru modificare");
                    }
                    JobCategories jobCategories = entityManager.find(JobCategories.class, optionSelected);
                    if(jobCategories == null){
                        logger.log(Level.WARNING, "Eroare la introducerea id-ului pentru modificare jobului");
                    }else{
                    modifyJob(optionSelected);
                    do {
                        System.out.println("Doriti sa efectuati alta operatie?");
                        System.out.println("1 - DA || 2 - NU");
                        try {
                            optionSelected = scanner.nextInt();
                            if (optionSelected == 1)
                                break;
                            else if (optionSelected == 2)
                                quit=true;
                        } catch (Exception e) {
                            logger.log(Level.SEVERE, "Ati introdus un caracter nepermis!");
                            break;
                        }
                    } while (!quit);}
                    break;
                case 3:
                    logger.log(Level.INFO, "Ati selectat modificarea denumirii unui angajat");
                    System.out.println("Introduceti ID-ul angajatului: ");
                    try{
                    optionSelected = scanner.nextInt();}catch(Exception e){
                        logger.log(Level.WARNING, "S-a introdus un caracter nepermis la id-ul angajatului pentru modificare");
                    }
                    Employee employee = entityManager.find(Employee.class, optionSelected);
                    if(employee == null){
                        logger.log(Level.WARNING, "Id-ul angajatului introdus pentru modificare nu exista");
                    }else{
                    modifyEmployee(optionSelected);
                    do {
                        System.out.println("Doriti sa efectuati alta operatie?");
                        System.out.println("1 - DA || 2 - NU");
                        try {
                            optionSelected = scanner.nextInt();
                            if (optionSelected == 1)
                                break;
                            else if (optionSelected == 2)
                                quit=true;
                        } catch (Exception e) {
                            logger.log(Level.SEVERE, "Ati introdus un caracter nepermis!");
                            break;
                        }
                    } while (!quit);}
                    break;
                case 4:
                    logger.log(Level.INFO, "Ati selectat inapoi la meniul principal");
                    quit=true;
                    break;
                case 5:
                    logger.log(Level.INFO, "Ati selectat iesirea din aplicatie");
                    System.exit(0);
                    break;
            }
        } while (!quit);
    }
    /**
     * Realizeaza actiunile din meniul pentru stergere
     * Apeleaza functia aferenta optiunii selectate
     * Functia se executa pana la selectarea optiunii de iesire
     */
    public static void stergere() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Logger logger = Logger.getLogger(Main3.class.getName());
        createLogFile();
        Scanner scanner = new Scanner(System.in);
        int optionSelected = 0;
        boolean quit = false;
        do {
            try {
                optionSelected = menu();
            } catch (Exception e) {
                logger.log(Level.WARNING, "Ati introdus un caracter nepermis!");
            }
            switch (optionSelected) {
                case 1:
                    logger.log(Level.INFO, "Ati selectat stergerea unui departament");
                    System.out.println("Introduceti ID-ul departamentului: ");
                    try{
                        optionSelected = scanner.nextInt();}catch(Exception e){
                        logger.log(Level.WARNING,"Ati introdus un caracter nepermis la selectarea departamentului pentru stergere");
                    }
                    Department department = entityManager.find(Department.class, optionSelected);
                    if(department == null){
                        logger.log(Level.WARNING, "Departamentul introdus pentru stergere nu exista!");
                    }else{
                        deleteDepartment(optionSelected);
                        do {
                            System.out.println("Doriti sa efectuati alta operatie?");
                            System.out.println("1 - DA || 2 - NU");
                            try {
                                optionSelected = scanner.nextInt();
                                if (optionSelected == 1)
                                    break;
                                else if (optionSelected == 2)
                                    quit=true;
                            } catch (Exception e) {
                                logger.log(Level.SEVERE, "Ati introdus un caracter nepermis!");
                                break;
                            }
                        } while (!quit);}
                    break;
                case 2:
                    logger.log(Level.INFO, "Ati selectat stergerea unui job");
                    System.out.println("Introduceti ID-ul jobului: ");
                    try{
                        optionSelected = scanner.nextInt();}catch(Exception e){
                        logger.log(Level.WARNING, "Ati introdus un caracter nepermis la selectarea jobului pentru stergere");
                    }
                    JobCategories jobCategories = entityManager.find(JobCategories.class, optionSelected);
                    if(jobCategories == null){
                        logger.log(Level.WARNING, "Eroare la introducerea id-ului pentru stergerea jobului");
                    }else{
                        deleteJob(optionSelected);
                        do {
                            System.out.println("Doriti sa efectuati alta operatie?");
                            System.out.println("1 - DA || 2 - NU");
                            try {
                                optionSelected = scanner.nextInt();
                                if (optionSelected == 1)
                                    break;
                                else if (optionSelected == 2)
                                    quit=true;
                            } catch (Exception e) {
                                logger.log(Level.SEVERE, "Ati introdus un caracter nepermis!");
                                break;
                            }
                        } while (!quit);}
                    break;
                case 3:
                    logger.log(Level.INFO, "Ati selectat stergerea unui angajat");
                    System.out.println("Introduceti ID-ul angajatului: ");
                    try{
                        optionSelected = scanner.nextInt();}catch(Exception e){
                        logger.log(Level.WARNING, "S-a introdus un caracter nepermis la id-ul angajatului pentru stergere");
                    }
                    Employee employee = entityManager.find(Employee.class, optionSelected);
                    if(employee == null){
                        logger.log(Level.WARNING, "Id-ul angajatului introdus pentru stergere nu exista");
                    }else{
                        deleteEmployee(optionSelected);
                        do {
                            System.out.println("Doriti sa efectuati alta operatie?");
                            System.out.println("1 - DA || 2 - NU");
                            try {
                                optionSelected = scanner.nextInt();
                                if (optionSelected == 1)
                                    break;
                                else if (optionSelected == 2)
                                    quit=true;
                            } catch (Exception e) {
                                logger.log(Level.SEVERE, "Ati introdus un caracter nepermis!");
                                break;
                            }
                        } while (!quit);}
                    break;
                case 4:
                    logger.log(Level.INFO, "Ati selectat inapoi la meniul principal");
                    quit=true;
                    break;
                case 5:
                    logger.log(Level.INFO, "Ati selectat iesirea din aplicatie");
                    System.exit(0);
                    break;
            }
        } while (!quit);
    }

    //---------------------------------------------------------------------------------------
//endregion

    //region Vizualizare tabele
    //---------------------------------------------------------------------------------------

    /**
     * Creeaza o lista cu toate departamentele existene in baza de date
     * Lista contine id departament si nume departament
     * @return List with all departments from database
     */
    public static List<Department> searchAllDepartments() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Logger logger = Logger.getLogger(Main3.class.getName());
        createLogFile();
        List<Department> results = entityManager.createQuery("from Department", Department.class).getResultList();
        if (results.size() == 0) {
            System.out.println("Nu exista departamente in baza de date");
            logger.log(Level.INFO, "Nu exista departamente");
        }
        return results;
    }
    /**
     * Creeaza o lista cu toate joburile existe in baza de date
     * Lista contine id job si denumire job
     * @return List with all jobs from database
     */
    public static List<JobCategories> searchAllJobs() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Logger logger = Logger.getLogger(Main3.class.getName());
        createLogFile();
        List<JobCategories> results = entityManager.createQuery("from JobCategories", JobCategories.class).getResultList();
        if (results.size() == 0) {
            System.out.println("Nu exista joburi in baza de date");
            logger.log(Level.INFO, "Nu exista joburi");
        }
        return results;
    }
    /**
     * Creeaza o lista cu toti angajatii din baza de date
     * Pentru modificarea informatiilor care sa se afiseze despre un angajat
     * Trebuie modificata functia toString din clasa Employee
     * @return List with all employees from database
     */
    public static List<Employee> searchAllEmployee() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Logger logger = Logger.getLogger(Main3.class.getName());
        createLogFile();
        List<Employee> results = entityManager.createQuery("from Employee", Employee.class).getResultList();
        if (results.size() == 0) {
            System.out.println("Nu exista angajati in baza de date");
            logger.log(Level.INFO, "Nu exista angajati");
        }
        return results;
    }
    /**
     * Afiseaza angajatii dintr-un departament
     * @param  departmentId department id from database
     * @return List all employee from departmentId
     */
    public static List<Employee> searchByDepartment(int departmentId) {
        Logger logger = Logger.getLogger(Main3.class.getName());
        createLogFile();
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        String query = "FROM Employee WHERE departmentId = " + departmentId;

       // entityManager.getTransaction().begin();
        List<Employee> result = entityManager.createQuery(query, Employee.class).getResultList();
        if (result.size() == 0) {
            Department department = entityManager.find(Department.class, departmentId);
            String string = "Nu exista angajati in departamentul " + department.getName();
            logger.log(Level.INFO, string);
        }
    return result;
    }

    /**
     *  Afiseaza toti angajatii din baza de date in ordine alfabetica dupa numele de familie
     */
    public static void orderByName() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        String query = "FROM Employee ORDER BY lastName";

        entityManager.getTransaction().begin();
        List<Employee> result = entityManager.createQuery(query, Employee.class).getResultList();
        for (Employee employee : result) {
            System.out.println(employee);

        }
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    /**
     * Afiseaza toti angajatii din baza de date crescator in functie de salariu
     */
    public static void orderBySalary() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        String query = "FROM Employee ORDER BY salary";

        entityManager.getTransaction().begin();
        List<Employee> result = entityManager.createQuery(query, Employee.class).getResultList();
        for (Employee employee : result) {
            System.out.println(employee);

        }
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    //---------------------------------------------------------------------------------------
    //endregion

    //region Adaugare obiect nou
    //---------------------------------------------------------------------------------------
    /**
     * Adaugare departament nou in baza de date
     */
    public static void addDepartment() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Department> departmentList = searchAllDepartments();
        int flag=0;
        Logger logger = Logger.getLogger(Main3.class.getName());
        createLogFile();
        Department department = new Department();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduceti numele departamentului");
        try {
            String name = scanner.next();
            for(Department department1: departmentList){
                if(department1.getName().equals(name)){
                    flag=1;
                    break;
                }
            }
            if(flag==1){
                adaugare();
                System.out.println("Acest departament exista deja in baza de date!");
                logger.log(Level.WARNING,"Numele introdus pentru adaugarea unui departament exista deja!");
            }
            department.setName(name);
            entityManager.getTransaction().begin();
            entityManager.persist(department);
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            logger.log(Level.WARNING, "Eroare la introducerea departamentului, verificati numele introdus");
        }
    }

    /**
     * Adaugare job nou in baza de date
     */
    public static void addJob() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<JobCategories> jobsList = searchAllJobs();
        int flag=0;
        Logger logger = Logger.getLogger(Main3.class.getName());
        createLogFile();
        JobCategories jobCategories = new JobCategories();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduceti numele jobului: ");
        try {
            String name = scanner.nextLine();
            for(JobCategories jobCategories1: jobsList){
                if(jobCategories1.getName().equals(name)){
                    flag=1;
                    break;
                }
            }
            if(flag==1){
                adaugare();
                System.out.println("Acest jon exista deja in baza de date!");
                logger.log(Level.WARNING,"Numele introdus pentru adaugarea unui job exista deja!");
            }
            jobCategories.setName(name);
            entityManager.getTransaction().begin();
            entityManager.persist(jobCategories);
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            logger.log(Level.WARNING, "Eroare la introducerea jobului, verificati numele introdus");
        }
    }

    /**
     * Adaugare angajat nou in baza de date
     */
    public static void addEmployee() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Logger logger = Logger.getLogger(Main3.class.getName());
        createLogFile();
        List<JobCategories> jobs = searchAllJobs();
        if (jobs.size() == 0) {
            logger.log(Level.WARNING, "Pentru a adauga un angajat trebuie mai intai sa adaugati un job!");
            optiuniMenuPrincipal();
        }
        List<Department> departments = searchAllDepartments();
        if (departments.size() == 0) {
            logger.log(Level.WARNING, "Pentru a adauga un angajat trebuie mai intai sa adaugati un departament!");
            optiuniMenuPrincipal();
        }
        Employee employee = new Employee();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduceti numele angajatului: ");
        try {
            entityManager.getTransaction().begin();
            String name = scanner.nextLine();
            employee.setLastName(name);
            System.out.println("Introduceti prenumele angajatului: ");
            name = scanner.nextLine();
            employee.setFirstName(name);
            System.out.println("Introduceti id-ul jobului: ");
            int id = scanner.nextInt();
            int flag = 0;
            for (JobCategories job : jobs) {
                if (id == job.getId()) {
                    flag = 1;
                    employee.setJobCategories(job);
                }
            }
            if (flag == 0) {
                logger.log(Level.WARNING, "Job ID incorrect");
                System.out.println("Adaugati din nou informatiile angajatului");
                adaugare();
            }
            System.out.println("Introduceti id-ul departamentului: ");
            id = scanner.nextInt();
            flag = 0;
            for (Department department : departments) {
                if (id == department.getId()) {
                    flag = 1;
                    employee.setDepartment(department);
                }
            }
            if (flag == 0) {
                logger.log(Level.WARNING, "Department ID incorrect");
                System.out.println("Adaugati din nou informatiile angajatului");
                adaugare();
            }
            System.out.println("Introduceti start date(yyyy-mm-dd): ");
            name = scanner.next();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            formatter = formatter.withLocale(Locale.ENGLISH);  // Locale specifies human language for translating, and cultural norms for lowercase/uppercase and abbreviations and such. Example: Locale.US or Locale.CANADA_FRENCH
            LocalDate date = LocalDate.parse(name, formatter);
            employee.setStartDate(date);
            System.out.println("Introduceti adresa: ");
            name = scanner.next();
            employee.setAddress(name);
            System.out.println("Introduceti codul postal: ");
            name = scanner.next();
            employee.setPostalCode(name);
            System.out.println("Introduceti nr telefon: ");
            name = scanner.next();
            employee.setTelephone(name);
            System.out.println("Introduceti email: ");
            name = scanner.next();
            employee.setEmail(name);
            System.out.println("Introduceti ziua de nastere: ");
            name = scanner.next();
            date = LocalDate.parse(name, formatter);
            employee.setBirthday(date);
            System.out.println("Introduceti numarul de copii: ");
            id = scanner.nextInt();
            employee.setNoChildren(id);
            System.out.println("Introduceti salariu: ");
            double salary = scanner.nextDouble();
            employee.setSalary(salary);
            System.out.println("Introduceti studiile: ");
            name = scanner.next();
            employee.setStudies(name);
            System.out.println("Introduceti social security number: ");
            name = scanner.next();
            employee.setSocialSecurityNumber(name);
            System.out.println("Introduceti daca are sau nu permis de conducere(true/false): ");
            boolean drivingLicense = scanner.nextBoolean();
            employee.setHasDrivingLicense(drivingLicense);
            entityManager.persist(employee);
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            adaugare();
            logger.log(Level.WARNING, "Eroare la introducerea angajatului, verificati datele introduse");
        }
    }
    //---------------------------------------------------------------------------------------
    //endregion

    //region Modificare obiect existent
    //---------------------------------------------------------------------------------------
    /**
     * Modificare departament existent in baza de date
     * @param  id department id from database
     */
    public static void modifyDepartment(int id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Logger logger = Logger.getLogger(Main3.class.getName());
        createLogFile();
        Scanner scanner = new Scanner(System.in);
        entityManager.getTransaction().begin();
        Department department = entityManager.find(Department.class, id);
        if (department == null) {
            logger.log(Level.WARNING, "Departamentul introdus nu exista!");
            optiuniMenuPrincipal();
        } else {
            System.out.println(department);
            System.out.println("Introduceti noul nume: ");
            String name = scanner.next();
            try {
                department.setName(name);
                entityManager.getTransaction().commit();
                entityManager.close();
                logger.log(Level.INFO, "Denumire schimbata cu succes!");
                System.out.println(department);
            } catch (Exception e) {
                entityManager.getTransaction().rollback();
                logger.log(Level.WARNING, "Eroare la modificare denumire");
            }
        }
    }
    /**
     * Modificare job existent in baza de date
     * @param  id job id from database
     */
    public static void modifyJob(int id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Logger logger = Logger.getLogger(Main3.class.getName());
        createLogFile();
        Scanner scanner = new Scanner(System.in);
        entityManager.getTransaction().begin();
        JobCategories job = entityManager.find(JobCategories.class, id);
        if (job == null) {
            logger.log(Level.WARNING, "Jobul introdus nu exista!");
            optiuniMenuPrincipal();
        } else {
            System.out.println(job);
            System.out.println("Introduceti noul nume: ");
            String name = scanner.next();
            try {
                job.setName(name);
                entityManager.getTransaction().commit();
                entityManager.close();
                logger.log(Level.INFO, "Denumire schimbata cu succes!");
                System.out.println(job);
            } catch (Exception e) {
                entityManager.getTransaction().rollback();
                logger.log(Level.WARNING, "Eroare la modificare denumire");
            }
        }
    }

    /**
     * Modificare angajat existent in baza de date
     * @param  id employee id from database
     */
    public static void modifyEmployee(int id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<JobCategories> jobs = searchAllJobs();
        List<Department> departments = searchAllDepartments();
        Logger logger = Logger.getLogger(Main3.class.getName());
        createLogFile();
        entityManager.getTransaction().begin();
        Employee employee = entityManager.find(Employee.class, id);
        Scanner scanner = new Scanner(System.in);
        int option;
        int choice;
        int flag;
        String newLine = System.getProperty("line.separator");
        System.out.println(employee);
        System.out.println("Alegeti campul pe care vreti sa-l modificati:" + newLine+
                "1 - Nume" +newLine+
                "2 - Prenume" +newLine+
                "3 - JobID" +newLine+
                "4 - DepartmentID" +newLine+
                "5 - Manager" +newLine+
                "6 - Start Date" +newLine+
                "7 - End Date" +newLine+
                "8 - Active" +newLine+
                "9 - Address" +newLine+
                "10 - Postal Code" +newLine+
                "11 - Telephone" +newLine+
                "12 - Email" +newLine+
                "13 - Birthday" +newLine+
                "14 - Children" +newLine+
                "15 - Salary" +newLine+
                "16 - Studies" +newLine+
                "17 - SSN" +newLine+
                "18 - Driving License");
        option = scanner.nextInt();
        switch (option) {
            case 1:
                System.out.println("Introduceti noul nume: ");
                try {
                    String name = scanner.next();
                    employee.setLastName(name);
                    entityManager.getTransaction().commit();
                    entityManager.close();
                    logger.log(Level.INFO, "Nume schimbat cu succes!");
                    System.out.println(employee);
                } catch (Exception e) {
                    entityManager.getTransaction().rollback();
                    logger.log(Level.WARNING, "Eroare la schimbare nume!");
                }
                break;
            case 2:
                System.out.println("Introduceti noul prenume: ");
                try {
                    String firstName = scanner.next();
                    employee.setFirstName(firstName);
                    entityManager.getTransaction().commit();
                    entityManager.close();
                    logger.log(Level.INFO, "Prenume schimbat cu succes!");
                    System.out.println(employee);
                } catch (Exception e) {
                    entityManager.getTransaction().rollback();
                    logger.log(Level.WARNING, "Eroare la schimbare prenume!");
                }
                break;
            case 3:
                System.out.println("Introduceti id-ul jobului: ");
                flag = 0;
                try{
                choice = scanner.nextInt();
                for (JobCategories job : jobs) {
                    if (choice == job.getId()) {
                        flag = 1;
                        employee.setJobCategories(job);
                    }
                }}catch(Exception e){
                    System.out.println("Eroare la schimbare job id");
                    logger.log(Level.WARNING, "Eroare la schimbare job id angajat");
                }
                if (flag == 0) {
                    logger.log(Level.WARNING, "Job ID incorrect");
                    optiuniMenuPrincipal();
                }
                break;
            case 4:
                System.out.println("Introduceti id-ul departamentului: ");
                flag = 0;
                try{
                choice = scanner.nextInt();
                for (Department department : departments) {
                    if (choice == department.getId()) {
                        flag = 1;
                        employee.setDepartment(department);
                    }
                }}catch(Exception e){
                    System.out.println("Eroare la schimbare department id andajat");
                    logger.log(Level.WARNING, "Eroare la schimbare department id angajat!");
                }
                if (flag == 0) {
                    logger.log(Level.WARNING, "Department ID incorrect");
                    optiuniMenuPrincipal();
                }
                break;
            case 5:
                System.out.println("Introduceti true daca este manager false daca nu este: ");
                boolean bool;
                try{
                bool = scanner.nextBoolean();
                employee.setManager(bool);}catch(Exception e){
                    logger.log(Level.WARNING, "Valoare introdusa gresit pentru modificare status manager!");
                }
                break;
            case 6:
                System.out.println("Modificati data de inceput: ");
                String name;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate date;
                try{
                name = scanner.next();
                formatter = formatter.withLocale(Locale.ENGLISH);
                date = LocalDate.parse(name, formatter);
                employee.setStartDate(date);}catch(Exception e){
                    logger.log(Level.WARNING, "Eroare la modificare data inceput");
                }
                break;
            case 7:
                System.out.println("Modificati data de sfarsit: ");
                try{
                name = scanner.next();
                formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                formatter = formatter.withLocale(Locale.ENGLISH);
                date = LocalDate.parse(name, formatter);
                employee.setStartDate(date);}catch (Exception e){
                    logger.log(Level.WARNING, "Eroare la modificare data sfarsit");
                }
                break;
            case 8:
                System.out.println("Modificati daca angajatul este activ sau nu(true sau false)");
                try{
                bool = scanner.nextBoolean();
                employee.setActive(bool);}catch(Exception e){
                    logger.log(Level.WARNING, "Eroare la modificare status angajat");
                }
                break;
            case 9:
                System.out.println("Modificati adresa");
                try{
                name = scanner.next();
                employee.setAddress(name);}catch(Exception e){
                    logger.log(Level.WARNING, "Eroare la modificare adresa");
                }
                break;
            case 10:
                System.out.println("Modificati codul postal");
                try{
                name = scanner.next();
                employee.setPostalCode(name);}catch(Exception e){
                    logger.log(Level.WARNING, "Eroare la modificare cod postal");
                }
                break;
            case 11:
                System.out.println("Modificati numarul de telefon");
                try{
                name = scanner.next();
                employee.setTelephone(name);}catch(Exception e){
                    logger.log(Level.WARNING, "Eroare la modificare numar telefon");
                }
                break;
            case 12:
                System.out.println("Modificati emailul");
                try{
                name = scanner.next();
                employee.setEmail(name);}catch(Exception e){
                    logger.log(Level.WARNING, "Eroare la modificare email");
                }
                break;
            case 13:
                System.out.println("Modificati data de nastere");
                try{
                name = scanner.next();
                formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                formatter = formatter.withLocale(Locale.ENGLISH);
                date = LocalDate.parse(name, formatter);
                employee.setBirthday(date);}catch (Exception e){
                    logger.log(Level.WARNING, "Eroare la modificare data de nastere");
                }
                break;
            case 14:
                System.out.println("Modificati nr de copii: ");
                try{
                int children = scanner.nextInt();
                employee.setNoChildren(children);}catch (Exception e){
                    logger.log(Level.WARNING, "Eroare la modificare nr de copii");
                }
                break;
            case 15:
                System.out.println("Modificati salariul: ");
                try{
                double salary = scanner.nextDouble();
                employee.setSalary(salary);}catch (Exception e){
                    logger.log(Level.WARNING, "Eroare la modificare salariu");
                }
                break;
            case 16:
                System.out.println("Modificati studiile: ");
                try{
                name = scanner.next();
                employee.setStudies(name);}catch(Exception e){
                    logger.log(Level.WARNING, "Eroare la modificare studii");
                }
                break;
            case 17:
                System.out.println("Modificati SSN: ");
                try{
                name = scanner.next();
                employee.setSocialSecurityNumber(name);}catch (Exception e){
                    logger.log(Level.WARNING, "Eroare la modificare SSN");
                }
                break;
            case 18:
                System.out.println("Modificat status permis: ");
                try{
                bool = scanner.nextBoolean();
                employee.setHasDrivingLicense(bool);}catch(Exception e){
                    logger.log(Level.WARNING, "Eroare la modificare status permis");
                }
                break;
        }
    }
    //---------------------------------------------------------------------------------------
    //endregion


    //region Stergere obiect
    //---------------------------------------------------------------------------------------

    /**
     * Delete department from database
     * @param  id department id from database
     */
    public static void deleteDepartment(int id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Employee> employees = searchAllEmployee();
        int flag=0;
        Logger logger = Logger.getLogger(Main3.class.getName());
        createLogFile();
        entityManager.getTransaction().begin();
        Department department = entityManager.find(Department.class, id);
        if (department == null) {
            logger.log(Level.WARNING, "Departamentul introdus nu exista!");
            optiuniMenuPrincipal();
        } else {
            for (Employee employee : employees) {
                Department employeeDepartment = employee.getDepartment();
                if (id == employeeDepartment.getId()) {
                    flag = 1;
                    break;
                }
            }
            if(flag==0){
            System.out.println(department);
            try {
                entityManager.getTransaction().commit();
                entityManager.remove(department);
                entityManager.close();
                logger.log(Level.INFO, "Departament sters cu succes!");
            } catch (Exception e) {
                entityManager.getTransaction().rollback();
                logger.log(Level.WARNING, "Eroare la stergere departament!");
            }}
            else{
                logger.log(Level.WARNING, "Eroare stergere departament, exista angajati in acest departament!");
            }
        }
    }

    /**
     * Delete job from database
     * @param  id job id from database
     */
    public static void deleteJob(int id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Logger logger = Logger.getLogger(Main3.class.getName());
        List<Employee> employees = searchAllEmployee();
        int flag=0;
        createLogFile();
        entityManager.getTransaction().begin();
        JobCategories job = entityManager.find(JobCategories.class, id);
        if (job == null) {
            logger.log(Level.WARNING, "Job-ul introdus nu exista!");
            optiuniMenuPrincipal();
        } else {
            for (Employee employee : employees) {
                JobCategories jobCategories = employee.getJobCategories();
                if (id == jobCategories.getId()) {
                    flag = 1;
                    break;
                }
            }
            if (flag == 0) {
                System.out.println(job);
                try {
                    entityManager.getTransaction().commit();
                    entityManager.remove(job);
                    entityManager.close();
                    logger.log(Level.INFO, "Job sters cu succes!");
                } catch (Exception e) {
                    entityManager.getTransaction().rollback();
                    logger.log(Level.WARNING, "Eroare la stergere job!");
                }
            } else {
                logger.log(Level.WARNING, "Eroare stergere job, exista angajati cu acest job!");
            }
        }
    }

    /**
     * Delete employee from database
     * @param  id employee id from database
     */
    public static void deleteEmployee(int id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Logger logger = Logger.getLogger(Main3.class.getName());
        createLogFile();
        entityManager.getTransaction().begin();
        Employee employee = entityManager.find(Employee.class, id);
        if (employee == null) {
            logger.log(Level.WARNING, "Departamentul introdus nu exista!");
            optiuniMenuPrincipal();
        } else {
            System.out.println(employee);
            try {
                entityManager.getTransaction().commit();
                entityManager.remove(employee);
                entityManager.close();
                logger.log(Level.INFO, "Angajat sters cu succes!");
            } catch (Exception e) {
                entityManager.getTransaction().rollback();
                logger.log(Level.WARNING, "Eroare la stergere angajat!");
            }
        }
    }
    //---------------------------------------------------------------------------------------
    //endregion


    //region Utility tools
    /**
     * Creeaza fisierul necesar pentru salvarea log-urilor si il ataseaza la logger
     * Fisierul este creat in folderul sursa
     */
    public static void createLogFile(){
        Logger logger = Logger.getLogger(Main3.class.getName());
        FileHandler fh;
        try{
            fh = new FileHandler("./LogFile.log");
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
        }catch(Exception e){
            logger.log(Level.WARNING, "Nu s-a putut crea fisierul pentru log");
        }
    }
    //endregion
}
