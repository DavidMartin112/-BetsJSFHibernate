package beans;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccessHibernate;

public class FacadeBean {
	private static FacadeBean singleton = new FacadeBean( );
	private static BLFacade facadeInterface;
	private FacadeBean(){
		try { facadeInterface=new BLFacadeImplementation(new DataAccessHibernate()); }
		catch (Exception e) {
			System.out.println("FacadeBean: negozioaren logika sortzean errorea: "+e.getMessage());
		}}
	public static BLFacade getBusinessLogic( ) {
		return facadeInterface;
	}
}