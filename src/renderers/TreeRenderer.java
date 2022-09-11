package renderers;

import java.awt.Component;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.border.Border;
import javax.swing.tree.DefaultTreeCellRenderer;

public class TreeRenderer extends DefaultTreeCellRenderer {
	private static final long serialVersionUID = 1L;
	Icon dbIcon = new ImageIcon("images/db.png");
	Icon tableIcon = new ImageIcon("images/duot.png");
	Font  treeFont  = new Font(Font.SERIF, Font.PLAIN,  15);
	private Border border = BorderFactory.createEmptyBorder ( 10, 10, 10, 10 );

	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf,
			int row, boolean hasFocus) {
		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
		setFont(treeFont);
		if (leaf) {
			setIcon(tableIcon);
			setBorder(border);
		} else {
			setIcon(dbIcon);
		}
		return this;
	}

}
