package view.cleaner;

import view.Window;
import java.io.File;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Activity;
import model.ActivityType;
import model.Cleaner;
import view.cleaner.CleanerNotificationBundle;

public class CleanerNotification extends Scene {
	public CleanerNotification(VBox container, Window window, Cleaner cleaner,
	                           ArrayList<CleanerNotificationBundle> notifications) {
		super(container, 800, 600);
		// TODO: Loop over missions and display the notifications

		System.out.println("CleanerNotification constructor");
		Button backButton = new Button("Back");

		backButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ev) {
				window.setScene(new CleanerMain(new VBox(), window, cleaner));
			}
		});

		ListView<VBox> menu = new ListView<VBox>();

		for (CleanerNotificationBundle notification : notifications) {
			// boolean removable = false;
			// TODO: Make a button to remove the notification or mark it as read
			VBox global = new VBox();

			HBox title = new HBox();

			Label titleLabel = new Label("Title");

			String main_text = "";

			switch (notification.getActivity().getType()) {
			case WELCOME_CLEANER:
				main_text = "Bienvenue " + cleaner.getName() + " !";
			case CLEANER_ACCOUNT_CONFIRMED:
				main_text = "Votre compte a été confirmé !";
			case NEW_MISSION_AVAILABLE:
				main_text = "Une nouvelle mission est disponible !\nVisitez le menu principal pour plus d'informations";
			case PROPOSAL_ACCEPTED:
				main_text = "Votre proposition de mission chez" + notification.getOwner().getName() + " au "
				            + notification.getMission().getProperty().getPropertyAddress().toString() + " pour le "
				            + notification.getMission().getMissionDate() + " à "
				            + notification.getMission().getMissionDateTime().toLocalTime().toString()
				            + " a été validée !";
			case MISSION_CANCELED:
				main_text = "Votre mission chez " + notification.getOwner().getName() + " au "
				            + notification.getMission().getProperty().getPropertyAddress().toString() + " pour le "
				            + notification.getMission().getMissionDate() + " à "
				            + notification.getMission().getMissionDateTime().toLocalTime().toString()
				            + " a été annulée.";
			case DISPUTE_OPENED:
				main_text = "Un litige à été ouvert pour la mission chez " + notification.getOwner().getName()
				            + " au "
				            + notification.getMission().getProperty().getPropertyAddress().toString() + " pour le "
				            + notification.getMission().getMissionDate() + " à "
				            + notification.getMission().getMissionDateTime().toLocalTime().toString();
			case DISPUTE_RESOLVED:
				main_text = "Le litige pour la mission chez " + notification.getOwner().getName() + " au "
				            + notification.getMission().getProperty().getPropertyAddress().toString() + " pour le "
				            + notification.getMission().getMissionDate() + " à "
				            + notification.getMission().getMissionDateTime().toLocalTime().toString()
				            + " a été resolu.";
			case MISSION_FINISHED_BY_OWNER:
				main_text = "Votre mission chez " + notification.getOwner().getName() + " au "
				            + notification.getMission().getProperty().getPropertyAddress().toString() + " pour le "
				            + notification.getMission().getMissionDate() + " à "
				            + notification.getMission().getMissionDateTime().toLocalTime().toString()
				            + "  a été marquée comme terminée";
			case REVIEW_THE_MISSION:
				main_text = "Votre mission chez " + notification.getOwner().getName() + " au "
				            + notification.getMission().getProperty().getPropertyAddress().toString() + " pour le "
				            + notification.getMission().getMissionDate() + " à "
				            + notification.getMission().getMissionDateTime().toLocalTime().toString()
				            + " vas passer en revue.";
			case REVIEW_HAS_BEEN_RECEIVED:
				main_text = "Votre mission chez " + notification.getOwner().getName() + " au "
				            + notification.getMission().getProperty().getPropertyAddress().toString() + " pour le "
				            + notification.getMission().getMissionDate() + " à "
				            + notification.getMission().getMissionDateTime().toLocalTime().toString()
				            + " a été passée en revue";
			case CLEANER_OFFERS_SERVICE:
			case CLEANER_WAITING_TO_BE_CONFIRMED:
			case MISSION_FINISHED_BY_CLEANER:
			case PROPERTY_IS_PUBLISHED:
			default:
				main_text = "You were not supposed to get this notification, please report it to an Admin";
			}

			Label main = new Label(main_text);

			title.getChildren().addAll(titleLabel);

			global.getChildren().addAll(title, main);
			// global.getChildren().add(updateButton);

			menu.getItems().add(global);
		}

		container.getChildren().addAll(backButton, menu);

		this.getStylesheets().add("file:///" + new File("src/main/css/style.css").getAbsolutePath().replace("\\", "/"));

	}
}