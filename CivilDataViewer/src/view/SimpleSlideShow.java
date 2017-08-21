package view;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

class SimpleSlideShow {

    StackPane root = new StackPane();
    ImageView[] slides;

    public SimpleSlideShow() {
      this.slides = new ImageView[4];
      Image image1 = new Image("/1.png", true);
      Image image2 = new Image("/2.png", true);
      Image image3 = new Image("/3.png", true);
      Image image4 = new Image("/4.png", true);
      slides[0] = new ImageView(image1);
      slides[1] = new ImageView(image2);
      slides[2] = new ImageView(image3);
      slides[3] = new ImageView(image4);

    }

    public StackPane getRoot() {
      return root;
    }

    // The method I am running in my class

    public void start() {

      SequentialTransition slideshow = new SequentialTransition();

      for (ImageView slide : slides) {

        SequentialTransition sequentialTransition = new SequentialTransition();

        FadeTransition fadeIn = getFadeTransition(slide, 0.0, 1.0, 2000);
        PauseTransition stayOn = new PauseTransition(Duration.millis(2000));
        FadeTransition fadeOut = getFadeTransition(slide, 1.0, 0.0, 2000);
        
        
        
        sequentialTransition.getChildren().addAll(fadeIn, stayOn, fadeOut);
        slide.setOpacity(0);
        this.root.getChildren().add(slide);
        slideshow.getChildren().add(sequentialTransition);

      }
      slideshow.setCycleCount(Timeline.INDEFINITE);
      slideshow.play();
    }

// the method in the Transition helper class:

    public FadeTransition getFadeTransition(ImageView imageView, double fromValue, double toValue, int durationInMilliseconds) {

      FadeTransition ft = new FadeTransition(Duration.millis(durationInMilliseconds), imageView);
      ft.setFromValue(fromValue);
      ft.setToValue(toValue);
     
      return ft;

    }
  }
