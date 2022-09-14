package state;

import views.AppView;
import views.ToolBarView;

public interface ApplicationState {
	
	   Boolean isIdle();
	   Boolean isReady();
	   Boolean isEditing();
	   Boolean isSelection();
	   void enanbleButtons(ToolBarView toolBarView);
	   ToolBarView getToolBarView(AppView applicationView);
	   String toString();
	   
}