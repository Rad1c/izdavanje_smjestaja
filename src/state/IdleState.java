package state;

import views.AppView;
import views.ToolBarView;

public class IdleState implements ApplicationState {

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
		applicationView.setToolbarView(new ToolBarView(applicationView.getPnlHeader()));
		// applicationView.add(applicationView.getToolbarView(),BorderLayout.NORTH);
		return applicationView.getToolbarView();
	}

	@Override
	public void enanbleButtons(ToolBarView toolBarView) {
		// TODO Auto-generated method stub

	}

	@Override
	public String toString() {
		return "Idle";

	}
}