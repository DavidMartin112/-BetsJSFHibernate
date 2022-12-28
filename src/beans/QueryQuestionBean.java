package beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.primefaces.expression.impl.ThisExpressionResolver;

import businessLogic.BLFacade;
import domain.Event;
import domain.Question;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

public class QueryQuestionBean {
	BLFacade bl = FacadeBean.getBusinessLogic();
	private String inputQuery;
	private float inputPrice;
	private Event event;
	private Date data;
	private static List<Event> eventuak=new ArrayList<Event>();
	private static List<Question> questions=new ArrayList<Question>();
	private Question question;
	
	public QueryQuestionBean() {}


	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
	public String getInputQuery() {
		return inputQuery;
	}
	public void setInputQuery(String inputQuery) {
		this.inputQuery = inputQuery;
	}
	public float getInputPrice() {
		return inputPrice;
	}
	public void setInputPrice(float inputPrice) {
		this.inputPrice = inputPrice;
	}
	public List<Event> getEventuak() {
		return eventuak;
	}
	public void setEventuak(List<Event> eventuak) {
		QueryQuestionBean.eventuak = eventuak;
	}
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	

	public List<Question> getQuestions() {
		return questions;
	}


	public void setQuestions(List<Question> questions) {
		QueryQuestionBean.questions = questions;
	}



	public void onDateSelect(SelectEvent event) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Data aukeratua: "+event.getObject()));
		setEventuak(bl.getEvents((Date) event.getObject()));
	}	
	
	public void onEventSelect(SelectEvent event) {
		setEvent((Event)event.getObject());
		setQuestions(this.event.getQuestions());
		System.out.println(this.event.getDescription());
	}
	public void onQuestionSelect(SelectEvent event) {
			this.setQuestion((Question)event.getObject());
			System.out.println(question);
	}
	
	//Meter delete question aqui pa reciclar un bean, hay q crear el metodo en hibernate tmbn
	public void createQuestion() {
		try {
			bl.createQuestion(event, inputQuery, inputPrice);
			for(Question q: this.event.getQuestions()) {
				System.out.println(q.getQuestion());
			}
		} catch (EventFinished e) {
		} catch (QuestionAlreadyExist e) {
		}
	}
	
	public void deleteQuestion() {
		try{	
			bl.deleteQuestion(this.event.getEventNumber(), this.question.getQuestionNumber(), this.question.getQuestion());
			
			//Update the primefaces tables values
			setEventuak(bl.getEvents(data));
			setEvent(this.eventuak.get(getEventuak().indexOf(this.event)));
			setQuestions(this.event.getQuestions());
		}catch(NullPointerException e) {
			System.out.println("NULL POINTER EXCEPTION");
		}
	}
	
	public void deleteEvent() {
		try{	
			bl.deleteEvent(event.getEventNumber());
			
			//Update the primefaces tables values
			setEventuak(bl.getEvents(data));
//			setEvent(this.eventuak.get(getEventuak().indexOf(this.event)));
//			setQuestions(this.event.getQuestions());
		}catch(NullPointerException e) {
			System.out.println("NULL POINTER EXCEPTION");
		}
	}
}
