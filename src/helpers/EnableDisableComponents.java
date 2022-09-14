package helpers;

import java.awt.Component;

import javax.swing.JPanel;

import views.input.FieldView;

public class EnableDisableComponents {
	public static void setPanelEnabled(JPanel panel, Boolean isEnabled) {
		panel.setEnabled(isEnabled);

		Component[] components = panel.getComponents();

		for (Component component : components) {
			if (component instanceof JPanel) {
				if (component instanceof FieldView) {
					FieldView f = (FieldView) component;
					if (f.getColumn().isLenkedField()) {
						setPanelEnabled((JPanel) component, isEnabled);
						f.getTxtContent().setEnabled(false);
					} else {
						setPanelEnabled((JPanel) component, isEnabled);
					}

				} else {
					setPanelEnabled((JPanel) component, isEnabled);
				}
			}
			component.setEnabled(isEnabled);
		}
	}
}
