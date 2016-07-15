package evntHandler;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;

public class KeyEventHandlerTableview implements EventHandler<KeyEvent> {

		KeyCodeCombination copyKeyCodeCompination = new KeyCodeCombination(KeyCode.C, KeyCombination.CONTROL_ANY);

		public void handle(final KeyEvent keyEvent) {
			if (!copyKeyCodeCompination.match(keyEvent) || !(keyEvent.getSource() instanceof TableView)) {
				return;
			}
			copy2clipboard((TableView) keyEvent.getSource());
			keyEvent.consume();
		}

		private void copy2clipboard(TableView source) {
			StringBuilder out = new StringBuilder();

			ObservableList<Integer> selectedCells = source.getSelectionModel().getSelectedIndices();

			int columns = source.getColumns().size();
			
			for (Integer row : selectedCells) {
				for (int idx = 0; idx < columns; idx++) {
					TableColumn tc = (TableColumn) source.getColumns().get(idx);
					Object item = tc.getCellData((int) row);
					out.append(item.toString());
					if (idx != columns - 1) {
						out.append('\t');
					}
				}
				out.append("\n");
			}

			ClipboardContent clipboardContent = new ClipboardContent();
			clipboardContent.putString(out.toString());
			Clipboard.getSystemClipboard().setContent(clipboardContent);
		
		}

}