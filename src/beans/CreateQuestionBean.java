package beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.event.SelectEvent;

import businessLogic.BLFacade;
import domain.Event;
import domain.Question;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

public class CreateQuestionBean {
	BLFacade bl = FacadeBean.getBusinessLogic();
	private String inputQuery;
	private float inputPrice;
	private Event event;
	private Date data;
	private static List<Event> eventuak=new ArrayList<Event>();

	public CreateQuestionBean() {}


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
		CreateQuestionBean.eventuak = eventuak;
	}

	public void onDateSelect(SelectEvent event) {
		setEventuak(bl.getEvents((Date) event.getObject()));
	}	
	public String createQuestion() {
		try {
			bl.createQuestion(event, inputQuery, inputPrice);
			for(Question q: this.event.getQuestions()) {
				System.out.println(q.getQuestion());
			}
			return "ok";
		} catch (EventFinished e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Gertaera pasa da."));
			return "error";
		} catch (QuestionAlreadyExist e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Galdera existitzen da"));
			return "error";
		}catch(Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Unknown error"));
			return "error";
		}
	}
}
