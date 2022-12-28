package businessLogic;

import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import dataAccess.DataAccessInterface;
import domain.Erabiltzailea;
import domain.Event;
import domain.Question;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;
import exceptions.UserAlreadyExist;

/**
 * It implements the business logic as a web service.
 */
public class BLFacadeImplementation  implements BLFacade {
	DataAccessInterface dbManager;

	public BLFacadeImplementation()  {		
		System.out.println("Creating BLFacadeImplementation instance");

		/*if (c.getDataBaseOpenMode().equals("initialize")) {

		    dbManager=new DataAccessInterface(new ObjectDbDAOManager());
			dbManager.initializeDB();
			dbManager.close();
			}
		 */
	}

	public BLFacadeImplementation(DataAccessInterface da)  {

		System.out.println("Creating BLFacadeImplementation instance with DataAccess parameter");

		da.open();
		da.initializeDB();
		da.close();


		dbManager=da;		
	}


	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws EventFinished if current data is after data of the event
	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */
	public Question createQuestion(Event event, String question, float betMinimum) throws EventFinished, QuestionAlreadyExist{

		//The minimum bed must be greater than 0
		dbManager.open();
		Question qry=null;


		if(new Date().compareTo(event.getEventDate())>0)
			throw new EventFinished("ErrorEventHasFinished");


		qry=dbManager.createQuestion(event,question,betMinimum);		

		dbManager.close();

		return qry;
	};

	/**
	 * This method invokes the data access to retrieve the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	public List<Event> getEvents(Date date)  {
		dbManager.open();
		List<Event>  events=dbManager.getEvents(date);
		dbManager.close();
		return events;
	}


	/**
	 * This method invokes the data access to retrieve the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	public List<Date> getEventsMonth(Date date) {
		dbManager.open();
		List<Date>  dates=dbManager.getEventsMonth(date);
		dbManager.close();
		return dates;
	}


	public void close() {
		//DataAccess dB4oManager=new DataAccess(false);

		//dB4oManager.close();
		dbManager.close();


	}

	/**
	 * This method invokes the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	public void initializeBD(){
		dbManager.open();
		dbManager.initializeDB();
		dbManager.close();
	}

	public Erabiltzailea isLogin(String erabiltzaileIzena, String pasahitza) {
    	dbManager.open();
    	Erabiltzailea emaitza = dbManager.isLogin(erabiltzaileIzena, pasahitza);
		dbManager.close();
		return emaitza;
    }
    
	public Erabiltzailea register(String izena, String abizena1, String abizena2, String erabiltzaileIzena, String pasahitza, String telefonoZbkia, String email, Date jaiotzeData) throws UserAlreadyExist{
    	dbManager.open();
    	Erabiltzailea emaitza = dbManager.register(izena, abizena1, abizena2, erabiltzaileIzena, pasahitza, telefonoZbkia, email, jaiotzeData);
		dbManager.close();
		return emaitza;
    }
	
	public boolean deleteQuestion(Integer eventNumber, Integer questionNumber, String question) {
		dbManager.open();
		boolean boo = dbManager.deleteQuestion(eventNumber, questionNumber, question);
		dbManager.close();
		return boo;
	}
	
	public boolean deleteEvent(Integer eventNumber) {
		dbManager.open();
		boolean boo = dbManager.deleteEvent(eventNumber);
		dbManager.close();
		return boo;
	}
}

