package aplication;

import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.widgets.Display;

import view.LoginView;
import view.PerfumeriaView;


@SuppressWarnings("deprecation")
public class App {

	public static void main(String args[]){

		Display display = Display.getDefault();
		Realm.runWithDefault(SWTObservables.getRealm(display),new Runnable() {
			
			@Override
			public void run() {
				
				Display display = Display.getDefault();
				Realm.runWithDefault(SWTObservables.getRealm(display),new Runnable() {
					
					@Override
					public void run() {
					
						//PerfumeriaView perfumeriaView = new PerfumeriaView();
					    //perfumeriaView.open();
					    
						LoginView login = new LoginView();
						login.open();
						
						Display.getCurrent().dispose();
                    
					}
				});
				
			}
		});
	}
}
