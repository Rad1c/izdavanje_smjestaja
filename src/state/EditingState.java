package state;

import views.AppView;
import views.ToolBarView;

public class EditingState implements ApplicationState {

	@Override
	public Boolean isIdle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean isReady() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean isEditing() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean isSelection() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ToolBarView getToolBarView(AppView applicationView) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void enanbleButtons(ToolBarView toolBarView) {
		toolBarView.enableAllButtons();
		
	}
	@Override
	public String toString()
	{
		return "Editing";
		
	}
}