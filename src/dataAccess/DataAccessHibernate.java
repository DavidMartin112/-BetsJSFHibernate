package dataAccess;

import java.util.ArrayList;
//hello
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

import org.hibernate.Query;
import org.hibernate.Session;

import configuration.HibernateUtil;
import configuration.UtilDate;
import domain.Erabiltzailea;
import domain.Event;
import domain.Question;
import exceptions.QuestionAlreadyExist;
import exceptions.UserAlreadyExist;

/**
 * It implements the data access to the objectDb database
 */
public class DataAccessHibernate implements DataAccessInterface {
	protected static Session db;

	public DataAccessHibernate()  {	
	}

	/**
	 * This is the data access method that initializes the database with some events and questions.
	 * This method is invoked by the business logic (constructor of BLFacadeImplementation) when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	public void initializeDB(){

		db.beginTransaction();
		try {
			Erabiltzailea admin = new Erabiltzailea("", "", "", "Admin", "1234", "", "", new Date());
			db.persist(admin);
			
			Calendar today = Calendar.getInstance();

			int month=today.get(Calendar.MONTH);
			month+=1;
			int year=today.get(Calendar.YEAR);
			if (month==12) { month=0; year+=1;}  

			Event ev1=new Event( "Atlético-Athletic", UtilDate.newDate(year,month,17));
			Event ev2=new Event( "Eibar-Barcelona", UtilDate.newDate(year,month,17));
			Event ev3=new Event( "Getafe-Celta", UtilDate.newDate(year,month,17));
			Event ev4=new Event( "Alavés-Deportivo", UtilDate.newDate(year,month,17));
			Event ev5=new Event( "Español-Villareal", UtilDate.newDate(year,month,17));
			Event ev6=new Event( "Las Palmas-Sevilla", UtilDate.newDate(year,month,17));
			Event ev7=new Event( "Malaga-Valencia", UtilDate.newDate(year,month,17));
			Event ev8=new Event( "Girona-Leganés", UtilDate.newDate(year,month,17));
			Event ev9=new Event( "Real Sociedad-Levante", UtilDate.newDate(year,month,17));
			Event ev10=new Event( "Betis-Real Madrid", UtilDate.newDate(year,month,17));

			Event ev11=new Event( "Atletico-Athletic", UtilDate.newDate(year,month,1));
			Event ev12=new Event( "Eibar-Barcelona", UtilDate.newDate(year,month,1));
			Event ev13=new Event( "Getafe-Celta", UtilDate.newDate(year,month,1));
			Event ev14=new Event( "Alavés-Deportivo", UtilDate.newDate(year,month,1));
			Event ev15=new Event( "Español-Villareal", UtilDate.newDate(year,month,1));
			Event ev16=new Event( "Las Palmas-Sevilla", UtilDate.newDate(year,month,1));


			Event ev17=new Event( "Málaga-Valencia", UtilDate.newDate(year,month,28));
			Event ev18=new Event("Girona-Leganés", UtilDate.newDate(year,month,28));
			Event ev19=new Event( "Real Sociedad-Levante", UtilDate.newDate(year,month,28));
			Event ev20=new Event( "Betis-Real Madrid", UtilDate.newDate(year,month,28));

			Question q1;
			Question q2;
			Question q3;
			Question q4;
			Question q5;
			Question q6;

			if (Locale.getDefault().equals(new Locale("es"))) {
				q1=ev1.addQuestion("¿Quién ganará el partido?",1);
				q2=ev1.addQuestion("¿Quién meterá el primer gol?",2);
				q3=ev11.addQuestion("¿Quién ganará el partido?",1);
				q4=ev11.addQuestion("¿Cuántos goles se marcarán?",2);
				q5=ev17.addQuestion("¿Quién ganará el partido?",1);
				q6=ev17.addQuestion("¿Habrá goles en la primera parte?",2);
			}
			else if (Locale.getDefault().equals(new Locale("en"))) {
				q1=ev1.addQuestion("Who will win the match?",1);
				q2=ev1.addQuestion("Who will score first?",2);
				q3=ev11.addQuestion("Who will win the match?",1);
				q4=ev11.addQuestion("How many goals will be scored in the match?",2);
				q5=ev17.addQuestion("Who will win the match?",1);
				q6=ev17.addQuestion("Will there be goals in the first half?",2);
			}			
			else {
				q1=ev1.addQuestion("Zeinek irabaziko du partidua?",1);
				q2=ev1.addQuestion("Zeinek sartuko du lehenengo gola?",2);
				q3=ev11.addQuestion("Zeinek irabaziko du partidua?",1);
				q4=ev11.addQuestion("Zenbat gol sartuko dira?",2);
				q5=ev17.addQuestion("Zeinek irabaziko du partidua?",1);
				q6=ev17.addQuestion("Golak sartuko dira lehenengo zatian?",2);

			}


//			db.persist(q1);
//			db.persist(q2);
//			db.persist(q3);
//			db.persist(q4);
//			db.persist(q5);
//			db.persist(q6);


			db.persist(ev1);
			db.persist(ev2);
			db.persist(ev3);
			db.persist(ev4);
			db.persist(ev5);
			db.persist(ev6);
			db.persist(ev7);
			db.persist(ev8);
			db.persist(ev9);
			db.persist(ev10);
			db.persist(ev11);
			db.persist(ev12);
			db.persist(ev13);
			db.persist(ev14);
			db.persist(ev15);
			db.persist(ev16);
			db.persist(ev17);
			db.persist(ev18);
			db.persist(ev19);
			db.persist(ev20);			

			db.getTransaction().commit();
			System.out.println("Db initialized");
		}
		catch (Exception e){
			System.err.println("Error while initializing DB");
			e.printStackTrace();
		}
	}

	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */
	public Question createQuestion(Event event, String question, float betMinimum) throws  QuestionAlreadyExist {
		
		System.out.println(">> DataAccess: createQuestion=> event= "+event+" question= "+question+" betMinimum="+betMinimum);
		System.out.println(db+" "+event);

		db.beginTransaction();
		//Event ev = db.find(Event.class, event.getEventNumber());
		Query q1 = db.createQuery("from Event where eventNumber= :eventNumber");
		q1.setParameter("eventNumber", event.getEventNumber());
		List result=q1.list();
		Event ev = (Event)result.get(0);
		
		if (ev.DoesQuestionExists(question)) throw new QuestionAlreadyExist("ErrorQueryAlreadyExist");

		Question q = ev.addQuestion(question, betMinimum);
		//db.persist(q);
		db.persist(ev); // db.persist(q) not required when CascadeType.PERSIST is added in questions property of Event class
		// @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
		db.getTransaction().commit();
		return q;

	}

	/**
	 * This method retrieves from the database the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	public List<Event> getEvents(Date date) {
		System.out.println(">> DataAccess: getEvents");
		List<Event> res = new Vector<Event>();	
		db.beginTransaction();
		//TypedQuery<Event> query = db.createQuery("SELECT ev FROM Event ev WHERE ev.eventDate=?1",Event.class);
		Query q1 = db.createQuery("from Event where eventDate= :eventDate");
		q1.setParameter("eventDate", date);
		List<Event> events=q1.list();
		db.getTransaction().commit();
		for (Event ev:events){
			System.out.println(ev.toString());		 
			res.add(ev);
		}
		return res;
	}

	/**
	 * This method retrieves from the database the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	public List<Date> getEventsMonth(Date date) {
		System.out.println(">> DataAccess: getEventsMonth");
		Vector<Date> res = new Vector<Date>();	

		Date firstDayMonthDate= UtilDate.firstDayMonth(date);
		Date lastDayMonthDate= UtilDate.lastDayMonth(date);

		db.beginTransaction();
		//TypedQuery<Date> query = db.createQuery("SELECT DISTINCT ev.eventDate FROM Event ev WHERE ev.eventDate BETWEEN ?1 and ?2",Date.class);   
		Query q1 = db.createQuery("FROM Event ev WHERE ev.eventDate BETWEEN :1 and :2");
		q1.setParameter("1", firstDayMonthDate);
		q1.setParameter("2", lastDayMonthDate);
		List<Date> dates=q1.list();
		db.getTransaction().commit();

		for (Date d:dates){
			System.out.println(d.toString());		 
			res.add(d);
		}
		return res;
	}

	@Override
	public void open(){
		System.out.println("Opening DataAccess instance => ");	
		db = HibernateUtil.getSessionFactory().getCurrentSession();
	}

	public boolean existQuestion(Event event, String question) {
		System.out.println(">> DataAccess: existQuestion=> event= "+event+" question= "+question);
		db.beginTransaction();
		//Event ev = db.find(Event.class, event.getEventNumber());
		Query q = db.createQuery("from Event where eventNumber= :eventNumber");
		q.setParameter("eventNumber", event.getEventNumber());
		List result=q.list();
		Event ev = (Event)result.get(0);
		db.getTransaction().commit();
		return ev.DoesQuestionExists(question);

	}
	public void close(){
//		db.close();
		System.out.println("DataBase closed");
	}



	@Override
	public void emptyDatabase() {

		//		File f=new File(c.getDbFilename());
		//		f.delete();
		//		File f2=new File(c.getDbFilename()+"$");
		//		f2.delete();
		db.createSQLQuery("DROP DATABASE bets;");
		db.createSQLQuery("CREATE DATABASE bets;");

	}
	
	public Erabiltzailea isLogin(String erab, String pass) {
		db.beginTransaction();
		Query query = db.createQuery("FROM Erabiltzailea WHERE erabiltzaileIzena=:erabiltzaileIzena AND pasahitza=:pasahitza");
		query.setParameter("erabiltzaileIzena", erab);
		query.setParameter("pasahitza", pass);
		List pertsona = query.list();
		db.getTransaction().commit();
		if(pertsona.isEmpty()) {
			return null;
		}else {
			return (Erabiltzailea) pertsona.get(0);
		}
	}
	
	public Erabiltzailea register(String izena, String abizena1, String abizena2, String erabiltzaileIzena, String pasahitza, String telefonoZbkia, String emaila, Date jaiotzeData) throws UserAlreadyExist{
		db.beginTransaction();
		Query query = db.createQuery("FROM Erabiltzailea WHERE erabiltzaileIzena=:erabiltzaileIzena");
		query.setParameter("erabiltzaileIzena", erabiltzaileIzena);
		List pertsona = query.list();
		if(!pertsona.isEmpty()) {
			throw new UserAlreadyExist();
		}else {
			Erabiltzailea berria = null;
			berria = new Erabiltzailea(izena, abizena1, abizena2, erabiltzaileIzena, pasahitza, telefonoZbkia, emaila, jaiotzeData);
			db.getTransaction().begin();
			db.persist(berria);
			db.getTransaction().commit();
			return berria;
		}
	}
	
	public boolean deleteQuestion(Integer eventNumber, Integer questionNumber, String question) {
		try {
			System.out.println(">> DataAccess: deleteQuestion=> question= "+question);
			db.beginTransaction();
			
			Query q1= db.createQuery("from Event where eventNumber= :eV");
			q1.setParameter("eV", eventNumber);
			List results = q1.list();
			Event eventUpdate = (Event) results.get(0);
			
			Question qUpdate = null;
			for(Question i: eventUpdate.getQuestions()) {
				if(i.getQuestionNumber()==questionNumber) qUpdate = i;
			}
			
			boolean removed = eventUpdate.removeQuestion(qUpdate);
			System.out.println(eventUpdate.getQuestions().contains(qUpdate));
			db.update(eventUpdate);
			
			Query q3 = db.createQuery("from Question where questionNumber= :qN");
			q3.setParameter("qN", questionNumber);
			Question q = (Question) q3.list().get(0);
			db.delete(q);
			
			db.getTransaction().commit();
			return removed;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteEvent(Integer eventNumber) {
		try {
			System.out.println(">> DataAccess: deleteEvent=> eventNumber= "+eventNumber);
			db.beginTransaction();
			
			Query q1= db.createQuery("from Event where eventNumber= :eV");
			q1.setParameter("eV", eventNumber);
			List results = q1.list();
			Event eventUpdate = (Event) results.get(0);
			
//			List<Question> qList = eventUpdate.removeQuestions();
//			db.update(eventUpdate);
//			
//			for(Question q: qList) {
//				Query q3 = db.createQuery("from Question where questionNumber= :qN");
//				q3.setParameter("qN", q.getQuestionNumber());
//				Question question = (Question) q3.list().get(0);
//				db.delete(question);
//			}
			db.delete(eventUpdate);
			db.getTransaction().commit();
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}

