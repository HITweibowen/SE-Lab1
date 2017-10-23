package lab1;

import java.io.*;
import javafx.geometry.Pos;
import javafx.scene.control.TextArea;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.effect.DropShadow;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.geometry.Insets;
import java.io.File;
import java.util.Vector;
import java.io.IOException;
import java.io.Reader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;


public class GUI extends Application {
	class Input
	{
		int Count = 0;
		StringBuffer s = new StringBuffer("");
	}
	int F = 0;
	Digraph D = new Digraph();
	private Stage window;
	private GridPane gridpane = new GridPane();
	private GridPane gridpaneLeft = new GridPane();
	private GridPane gridpaneRight = new GridPane();
	private Vector<Text> VText = new Vector<Text>();
	String preword="";
	String word="";
	Stage a = new Stage();
	GridPane pane=new GridPane();
	Scene scene=new Scene(pane,700,950);

	@Override

	public void start(Stage primaryStage){
		window = primaryStage;

		Task<Void> task = new Task<Void>() {
		  @Override public Void call() {
		  for (int i = 1; i <= 10; i++) {
		   try {
		   Thread.sleep(100);
		   } catch (InterruptedException e) {
		   e.printStackTrace();
		   }
		   updateProgress(i, 10);
		  }
		  return null;
		  }
		 };//

		 ProgressBar updProg = new ProgressBar();
		 updProg.progressProperty().bind(task.progressProperty());

		 Text T = new Text("���ؽ���...");
		 T.setFill(Color.RED);
		 T.setFont(Font.font(15));
		 Button t = new Button("����");
		 t.setStyle("-fx-font: 15 arial; -fx-base: #ee2211;");
		 t.setOnAction(e->{
				 EnterSystem(window);
		 });

		 Thread th = new Thread(task);
		 th.setDaemon(true);
		 th.start();

        // ����Image��ImageView����
        Image image = new Image("1.png", 150, 150, false, false);
        ImageView imageView = new ImageView();
        imageView.setImage(image);


		// File Menu
		Menu SystemMenu = new Menu("_ϵͳ��¼����");
		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().addAll(SystemMenu);

		// BorderPane
		BorderPane layout = new BorderPane();
		layout.setTop(menuBar);

		gridpaneLeft.setPadding(new Insets(5));
	    gridpaneLeft.setHgap(5);
	    gridpaneLeft.setVgap(5);

		gridpaneLeft.add(imageView, 19, 10);
		gridpaneLeft.add(T, 19, 19);
        gridpaneLeft.add(updProg, 19, 20);
        layout.setCenter(gridpaneLeft);

        gridpaneLeft.add(t, 21, 20);

		layout.setCenter(gridpaneLeft);

		Scene scene = new Scene(layout, 330, 330);
		window.setTitle("ϵͳ��������");
		window.setScene(scene);
		window.setResizable(false);
		window.show();

	}

	public void EnterSystem(Stage window)
	{
		BorderPane layout = new BorderPane();
	    gridpaneLeft = new GridPane();
	    gridpaneRight = new GridPane();

	    /*Text text2 = new Text("�����ļ�����Ϊ��");
		VText.addElement(text2);
		text2.setFill(Color.RED);
        text2.setFont(Font.font(15));

	    ListView<Text> list2 = new ListView<>();
		list2.getItems().add(text2);

        Text text1 = new Text("ԭ�ļ�����Ϊ��");
        text1.setFill(Color.RED);
        text1.setFont(Font.font(15));
	    VText.addElement(text1);

		ListView<Text> list = new ListView<>();
		list.getItems().add(text1);*/

		// File Menu
		Menu FileMenu = new Menu("_�ļ�");

		MenuItem NewItem = new MenuItem("_���ļ���ͼ");
		NewItem.setOnAction(e->{
			 ReadFile(VText);//, list, list2);
			 F = 1;
		}
		);

		MenuItem Separator = new SeparatorMenuItem();
		MenuItem Exit = new MenuItem("_�˳�ϵͳ");
		Exit.setOnAction(e -> window.close());
		FileMenu.getItems().addAll(NewItem, Separator, Exit);

		// BridgeWords Menu
		Menu BridgeWordsMenu = new Menu("_�ŽӴ�");

		MenuItem GetBWords = new MenuItem("_��ѯ�ŽӴ�");
		GetBWords.setOnAction(e ->
		{
			if (F == 1)
			    InputWords(layout);
			else
				PromptBuiltFigure();
		});

		MenuItem GetNewFile = new MenuItem("_�������ı�");
		GetNewFile.setOnAction(e ->
		{
			if (F == 1)
				InputSents(layout);
			else
				PromptBuiltFigure();
		});

		BridgeWordsMenu.getItems().addAll(GetBWords, Separator, GetNewFile);

		// ShortestPath Menu
		Menu ShortestPathMenu = new Menu("_���·��");

		MenuItem TwoWordsShortestPath = new MenuItem("_��ѯ���������·��");
		TwoWordsShortestPath.setOnAction(e->{
			if (F == 1)
				GetTwoWordsShortestPath(layout);
			else
				PromptBuiltFigure();
		});

		MenuItem OneWordShortestPath = new MenuItem("_��ѯһ�����ʵ��������·��");
		OneWordShortestPath.setOnAction(e->{
			if (F == 1)
				GetOneWordsShortestPath(layout);
			else
				PromptBuiltFigure();
		});

		ShortestPathMenu.getItems().addAll(TwoWordsShortestPath, Separator, OneWordShortestPath);

		//Help Menu
		Menu HelpMenu = new Menu("_����");

		MenuItem HelpTxt = new MenuItem("_��ѯ�����ĵ�");
		HelpTxt.setOnAction(e->{
			Stage secondWindow = new Stage();
			secondWindow.setTitle("                   ʹ��˵��");
			Group g = new Group();

		    DropShadow ds = new DropShadow();
		    ds.setOffsetY(0.3);
		    ds.setColor(Color.color(0.9, 0.9, 0.9));

		    Text t = new Text(10, 20, "This is a test");

		    t.setEffect(ds);
		    t.setCache(true);
		    t.setFill(Color.BLUE);

		    t.setWrappingWidth(800);
		    t.setText("------------------------------------------\n"
		    		 +"**     **        **          **        **       **\n"
		    		 +"------------------------------------------\n"
		             +"                      ϵͳʹ��˵��                                                      \n"
		    		 +"     ��ϵͳһ���а˸����ܣ���Ӧ����Ŀ¼                                \n\n"
		             +"     ����һ���ļ�Ŀ¼�¶��ļ���ͼ����\n"
		    		 +"     ���ܶ�����Ļ��ť������ͼ��չʾ\n"
		             +"     ���������ŽӴ�Ŀ¼��չʾ��������\n"
		    		 +"                   ���ŽӴ�                             \n"
		             +"     �����ģ��ŽӴ�Ŀ¼�¸��������ı�\n"
		    		 +"                   �����µ��ı�                    \n"
		             +"     �����壺���·��Ŀ¼�¼�����������\n"
		    		 +"                   ���ʵ����·��                    \n"
		             +"     �����������·��Ŀ¼�¼���һ������\n"
		    		 +"                   ���ʵ����е��ʵ����·��\n"
		             +"     �����ߣ��������Ŀ¼���������Ϊ��\n"
		    		 +"                   ������ߣ����д���ļ���\n"
		             +"     ���ܰˣ�����Ŀ¼�¸ı�������ɫ      \n\n"
		             +"------------------------------------------\n"
   		             +"**     **        **          **        **       **\n"
   		             +"------------------------------------------\n");

		    t.setFont(new Font(20));
		    t.textAlignmentProperty();

		    g.getChildren().add(t);

	        Scene scene = new Scene(g, 450, 600);

			secondWindow.setScene(scene);
			secondWindow.show();

		});

		Menu WordsColor = new Menu("_������ɫ");
		MenuItem Blue = new MenuItem("Blue");
		Blue.setOnAction(e->{
			for (int i = 0; i < VText.size(); i++)
			VText.get(i).setFill(Color.BLUE);
		});

		MenuItem Red = new MenuItem("Red");
		Red.setOnAction(e->{
			for (int i = 0; i < VText.size(); i++)
				VText.get(i).setFill(Color.RED);
		});
		MenuItem Yellow = new MenuItem("Yellow");
		Yellow.setOnAction(e->{
			for (int i = 0; i < VText.size(); i++)
				VText.get(i).setFill(Color.YELLOW);
		});
		MenuItem Green = new MenuItem("Green");
		Green.setOnAction(e->{
			for (int i = 0; i < VText.size(); i++)
				VText.get(i).setFill(Color.GREEN);
		});

		Menu WordsSize = new Menu("_�����С");

		MenuItem Size10 = new MenuItem("10");
		Size10.setOnAction(e->{
			for (int i = 0; i < VText.size(); i++)
				VText.get(i).setFont(Font.font (10));
		});

		MenuItem Size12 = new MenuItem("12");
		Size12.setOnAction(e->{
			for (int i = 0; i < VText.size(); i++)
				VText.get(i).setFont(Font.font (12));
		});
		MenuItem Size14 = new MenuItem("14");
		Size14.setOnAction(e->{
			for (int i = 0; i < VText.size(); i++)
				VText.get(i).setFont(Font.font (14));
		});

		MenuItem Size16 = new MenuItem("16");
		Size16.setOnAction(e->{
			for (int i = 0; i < VText.size(); i++)
				VText.get(i).setFont(Font.font (16));
		});

		WordsSize.getItems().addAll(Size10, new SeparatorMenuItem(), Size12, new SeparatorMenuItem(), Size14, new SeparatorMenuItem(), Size16);
		WordsColor.getItems().addAll(Blue, new SeparatorMenuItem(), Red, new SeparatorMenuItem(), Yellow, new SeparatorMenuItem(), Green);
		HelpMenu.getItems().addAll(HelpTxt, Separator, WordsColor, new SeparatorMenuItem(), WordsSize);


		// RandomWalk Menu
		Menu RandomWalkMenu = new Menu("_�������");

		MenuItem RandomWalk = new MenuItem("_�����������");
		RandomWalk.setOnAction(e->{
			if (F == 1)
			{
				word="";
				preword="";
				StartRandomWalk(layout);
			}
			else
				PromptBuiltFigure();
		});

		RandomWalkMenu.getItems().addAll(RandomWalk);

		// MenuBar
		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().addAll(FileMenu, BridgeWordsMenu, HelpMenu, ShortestPathMenu, RandomWalkMenu);

		layout.setTop(menuBar);

		gridpane.setPadding(new Insets(5));
		gridpane.setHgap(5);
	    gridpane.setVgap(5);

		gridpaneLeft.setPadding(new Insets(5));
		gridpaneLeft.setHgap(5);
	    gridpaneLeft.setVgap(5);

	    gridpaneRight.setPadding(new Insets(5));
	    gridpaneRight.setHgap(5);
	    gridpaneRight.setVgap(5);

		//gridpaneLeft.add(list, 0,2);
		//gridpaneLeft.add(list2, 0,5);

	    layout.setLeft(gridpaneLeft);
		layout.setRight(gridpaneRight);

		Scene scene = new Scene(layout, 750, 600);
		window.setTitle("ͼϵͳ");
		window.setScene(scene);
		window.setResizable(false);
		window.show();
		}

	public void ReadFile(Vector<Text> VText)//, ListView<Text> list, ListView<Text>list2)
	 {
		 gridpaneRight.getChildren().clear();
		 gridpaneLeft.getChildren().clear();

		 D =  new Digraph();

	    FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(window);
        Text text0 = new Text("�ļ�·��Ϊ��" + file);


        text0.setFill(Color.RED);
        text0.setFont(Font.font(10));

        Button button = new Button("��ӡͼƬ");
        button.setStyle("-fx-font: 20 arial; -fx-base: #ee2211;");

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	D.picture();


            	pane.setAlignment(Pos.CENTER);
                Image image=new Image("file:\\D:\\out.png", 700, 950, false, false);
                pane.getChildren().add(new ImageView(image));

                a.setTitle("����ͼ");
                a.setScene(scene);
                a.setX(40);
                a.setY(20);
                a.show();
            }
        });

        gridpaneLeft.add(text0,  0, 0);
		gridpaneLeft.add(button, 0, 1);

		Text text2 = new Text("�����ļ�����Ϊ��");
		VText.addElement(text2);
		text2.setFill(Color.RED);
        text2.setFont(Font.font(15));

	     ListView<Text> list2 = new ListView<>();
		 list2.getItems().add(text2);

        Text text1 = new Text("ԭ�ļ�����Ϊ��");
        text1.setFill(Color.RED);
        text1.setFont(Font.font(15));
	    VText.addElement(text1);

		ListView<Text> list = new ListView<>();
		list.getItems().add(text1);

		gridpaneLeft.add(list, 0,2);
        gridpaneLeft.add(list2, 0,5);

		 try {
		     D.ReadFileBuildDigraph(file.toString());
	     } catch (Exception e1) {
	        // TODO Auto-generated catch block
	         e1.printStackTrace();}

		try(
				Reader reader = new InputStreamReader(new FileInputStream(file)))
				{
			        int Flag = 1;
					int tempchar;
					int Count = 0;
					String S = "";
					while((tempchar = reader.read()) != -1)
					{
						if (tempchar != '\r')
						{
							if ((char)tempchar == '\n')
							{
								Text T = new Text(S);
				 				T.setFill(Color.RED);
				 				T.setFont(Font.font(15));
				 				VText.addElement(T);
				 				list.getItems().add(T);
				 			    Count = 0;
				 			    S = "";
							}

							else
							{
								S += (char)tempchar;

								if ((char)tempchar == ' ')
								{
									if (Flag == 1)
										Count++;

									if (Count == 6)//��
		 							{
		 								Text T = new Text(S);
		 				 				T.setFill(Color.RED);
		 				 				T.setFont(Font.font(15));
		 				 				VText.addElement(T);
		 				 				list.getItems().add(T);
		 				 			    Count = 0;
		 				 			    S = "";

		 							}
									Flag  = 0;
	 							}
								else
		 							Flag = 1;
							}
						}
				    }
					if (!S.equals(""))
					{
						Text T = new Text(S);
		 				T.setFill(Color.RED);
		 				T.setFont(Font.font(15));
		 				VText.addElement(T);
		 				list.getItems().add(T);
					}
			}catch(IOException e){
			    e.printStackTrace();}

		int Count  = 0;
		String S = "";
		for (int i =  0; i < D.FileText.size(); i++)
		{
			if (Count == 5)//��
			{
				Text T = new Text(S);
				T.setFill(Color.RED);
				T.setFont(Font.font(15));
				VText.addElement(T);
				list2.getItems().add(T);
			    Count = 0;
			    S = "";
			    S += D.FileText.get(i);
			    S += " ";
			}
			else
			{
				S += D.FileText.get(i);
			    S += " ";
			    Count++;
			}
		}
		if (!S.equals(""))
		{
			Text T = new Text(S);
			T.setFill(Color.RED);
			T.setFont(Font.font(15));
			VText.addElement(T);
			list2.getItems().add(T);
		}

	 }
	 public String ChangeText(String S1)
	 {
		 String Change = "";
		 int Flag = -1;//

		 for (int i = 0; i < S1.length(); i++)
		 {
			 if (S1.charAt(i) != ' ' && S1.charAt(i) != '\n' && ((S1.charAt(i) >='a' && S1.charAt(i) <= 'z') || (S1.charAt(i) >='A' && S1.charAt(i) <= 'Z')))
			 {
				 if (Flag == 0)
				     Change += ' ';
				 Change += S1.charAt(i);
				 Flag = 1;
			 }
			 else if (Flag == 1 && i != S1.length() - 1)
				 Flag = 0;
		 }

		 return Change;
	 }



	public void InputWords(BorderPane layout)
	 {
		 gridpaneRight.getChildren().clear();

		 ListView<Text> list = new ListView<>();
		 TextField TFiled1 = new TextField ();
		 TFiled1.setPromptText("�������һ������");
		 TextField TFiled2 = new TextField ();
		 TFiled2.setPromptText("������ڶ�������");
		 Text T1 = new Text("Word1");
		 Text T2 = new Text("Word2");
		 Text T = new Text("������Ҫ��ѯ�ŽӴʵ���������");

		 T.setFill(Color.RED);
		 T.setFont(Font.font(15));

		 T1.setFill(Color.RED);
		 T2.setFill(Color.RED);
		 T1.setFont(Font.font(15));
		 T2.setFont(Font.font(15));


		 VText.addElement(T1);
		 VText.addElement(T2);
		 VText.addElement(T);


		 Button t = new Button("ȷ��");
		 t.setStyle("-fx-font: 15 arial; -fx-base: #ee2211;");
		 t.setOnAction(e->{

			 String S1 = TFiled1.getText();
			 String S2 = TFiled2.getText();
			 if (S1.length() >= 1 && S2.length() >= 1)
			 {
				 gridpaneRight.getChildren().remove(list);
				 list.getItems().clear();

				 String Temp1 = ChangeText(S1);
				 String Temp2 = ChangeText(S2);

				 if (Temp1.length() == 0 || Temp2.length() == 0)
				 {
					 Text T3 = new Text("���������벻Ҫֻ����ո�");
				     T3.setFill(Color.RED);
				     T3.setFont(Font.font(15));
				     VText.add(T3);
				     list.getItems().add(T3);
				     gridpaneRight.add(list, 14, 12);
				     return;
				 }

				 if (Temp1.contains(" ") || Temp2.contains(" "))
				 {
					 Text T3 = new Text("һ����������ֻ����һ������");
				     T3.setFill(Color.RED);
				     T3.setFont(Font.font(15));
				     VText.add(T3);
				     list.getItems().add(T3);
				     gridpaneRight.add(list, 14, 12);
				     return;
				 }

				 String STr = D.GetBridgeWords(Temp1, Temp2);
				 if (D.BridgeWords.size() != 0)
				 {
					 Text T3;
					 if (D.BridgeWords.size() > 1)
					 {
					 	 T3 = new Text( "The bridge words from ");  //+ "\"" + S1 + "\" to \""  + S2 + "\" are: ");//��
						 T3.setFill(Color.RED);
					     T3.setFont(Font.font(15));
					     VText.add(T3);
					     list.getItems().add(T3);

					     T3 = new Text( "\"" + Temp1 + "\" to \""  + Temp2 + "\" are: ");
					     T3.setFill(Color.RED);
					     T3.setFont(Font.font(15));
					     VText.add(T3);
					     list.getItems().add(T3);

					}
					else
					{
						 T3 = new Text( "The bridge words from ");  //+ "\"" + S1 + "\" to \""  + S2 + "\" are: ");//��
						 T3.setFill(Color.RED);
					     T3.setFont(Font.font(15));
					     VText.add(T3);
					     list.getItems().add(T3);

					     T3 = new Text( "\"" + Temp1 + "\" to \""  + Temp2 + "\" is: ");
					     T3.setFill(Color.RED);
					     T3.setFont(Font.font(15));
					     VText.add(T3);
					     list.getItems().add(T3);
					 }

				     int Count = 0;
				     String S = "";
				     for (int i = 0; i < D.BridgeWords.size(); i++)
				     {
				    	 if (Count++ == 4)//��
				    	 {
				    		 Text TEXT = new Text(S);
						     TEXT.setFill(Color.RED);
						     TEXT.setFont(Font.font(15));
						     VText.add(TEXT);
						     list.getItems().add(TEXT);
				    		 Count = 0;
				    		 S = "";
				    	 }
				    	 else
				    	 {
				    		 S += D.BridgeWords.get(i);
				    		 S += ", ";
				    	 }
				     }
				     if (!S.equals(""))
				     {
				    	 Text TEXT = new Text(S);
					     TEXT.setFill(Color.RED);
					     TEXT.setFont(Font.font(15));
					     VText.add(TEXT);
					     list.getItems().add(TEXT);


					     D.picture_qjc();

					 	 pane.setAlignment(Pos.CENTER);
					     Image image=new Image("file:\\D:\\qjcout.png", 700, 950, false, false);
					     pane.getChildren().add(new ImageView(image));


					     a.setTitle("�ŽӴ�����ͼ");
					     a.setScene(scene);
					     a.setX(40);
			             a.setY(20);
			             a.show();
				     }
				 }
				 else
				 {
					 String Find;

				     Find = (STr.contains("from")) ? "from": "in";
					 String S = "";//��
					 int Index = STr.indexOf(Find);

					 int Limit = (STr.contains("from"))? Index + 4 : (Index + 2);

					 for (int i = 0; i < Limit; i++)
						 S += STr.charAt(i);
					 Text TEXT = new Text(S);
				     TEXT.setFill(Color.RED);
				     TEXT.setFont(Font.font(15));
				     VText.add(TEXT);
				     list.getItems().add(TEXT);
				     S = "";
				     for (int i = Limit; i < STr.length(); i++)
					     S += STr.charAt(i);
				     TEXT = new Text(S);
				     TEXT.setFill(Color.RED);
				     TEXT.setFont(Font.font(15));
				     VText.add(TEXT);
				     list.getItems().add(TEXT);

				 }
				 gridpaneRight.add(list, 14, 12);
			 }
			 else
			 {
				 gridpaneRight.getChildren().remove(list);
				 list.getItems().clear();
				 Text T3 = new Text("��������������");
				 T3.setFill(Color.RED);
				 T3.setFont(Font.font(15));
				 VText.add(T3);
				 list.getItems().add(T3);
				 gridpaneRight.add(T3, 14, 12);

			 }
		 });

		 gridpaneRight.add(T, 14, 1);
		 gridpaneRight.add(T1, 13, 4);
		 gridpaneRight.add(T2, 13, 7);
		 gridpaneRight.add(t, 14, 9);
		 gridpaneRight.add(TFiled1, 14, 4);

		 gridpaneRight.add(TFiled2, 14, 7);

		 window.show();
	 }


	public void  InputSents(BorderPane layout)
	 {
		 gridpaneRight.getChildren().clear();

		 ListView<Text> list = new ListView<>();
		 TextArea TArea = new TextArea ();
		 TArea.setPromptText("�������ѯ�ı�");
		 TArea.setPrefColumnCount(15);
		 Text T = new Text("�������ѯ�ı�");
		 T.setFill(Color.RED);
		 T.setFont(Font.font(15));

		 VText.addElement(T);

		 Button t = new Button("ȷ��");
		 t.setStyle("-fx-font: 15 arial; -fx-base: #ee2211;");
		 t.setOnAction(e->{

			 String S1 = TArea.getText();
			 if (S1.length() == 0)
			 {
				 gridpaneRight.getChildren().remove(list);
				 list.getItems().clear();
				 Text T3 = new Text("�����ĵ�����Ϊ��");
				 T3.setFill(Color.RED);
				 T3.setFont(Font.font(15));
				 VText.add(T3);
				 gridpaneRight.add(T3, 14, 12);
				 return;
			 }

			 String Change = ChangeText(S1);
			 if (Change.length()  == 0)
			 {
				 gridpaneRight.getChildren().remove(list);
				 list.getItems().clear();
				 Text T3 = new Text("����ֻ�����㡢�ո�ȷ���ĸ����");
				 T3.setFill(Color.RED);
				 T3.setFont(Font.font(15));
				 VText.add(T3);
				 list.getItems().add(T3);
				 gridpaneRight.add(list, 14, 12);
				 return;
			 }

			 gridpaneRight.getChildren().remove(list);
			 list.getItems().clear();

			 Text T3 = new Text("ת�����Ϊ��");
			 T3.setFill(Color.RED);
			 T3.setFont(Font.font(15));
			 VText.add(T3);
			 list.getItems().add(T3);

			 String Return = "";
			 String Result = "";

			 Result = D.GetNewFiles(Change);

			 Change = ChangeText(Result);
			 String[] Words = Change.split(" ");
			 for (int i = 1; i < Words.length + 1; i++)
			 {
				 if (i % 6 == 0)
				 {
					 Text TT = new Text(Return);
					 TT.setFill(Color.RED);
					 TT.setFont(Font.font(15));
					 VText.add(TT);
					 Return = (Words[i - 1] + " ");
					 list.getItems().add(TT);

				 }
				 else
				     Return += (Words[i - 1] + " ");
			 }

			 Text TT = new Text(Return);
			 TT.setFill(Color.RED);
			 TT.setFont(Font.font(15));
			 VText.add(TT);
			 list.getItems().add(TT);

			 gridpaneRight.add(list, 14, 12);
		 });

		 gridpaneRight.add(T, 14, 4);
		 gridpaneRight.add(t, 14, 10);
		 gridpaneRight.add(TArea, 14, 7);
		 window.show();
	 }

	public void GetTwoWordsShortestPath(BorderPane layout)
	 {
		 gridpaneRight.getChildren().clear();

		 ListView<Text> list = new ListView<>();
		 TextField TFiled1 = new TextField ();
		 TFiled1.setPromptText("�������һ������");
		 TextField TFiled2 = new TextField ();
		 TFiled2.setPromptText("������ڶ�������");
		 Text T1 = new Text("Word1");
		 Text T2 = new Text("Word2");

		 Text T = new Text("������Ҫ��ѯ���·������������");
		 T.setFill(Color.RED);
		 T.setFont(Font.font(15));

		 T1.setFill(Color.RED);
		 T2.setFill(Color.RED);
		 T1.setFont(Font.font(15));
		 T2.setFont(Font.font(15));


		 VText.addElement(T1);
		 VText.addElement(T2);
		 VText.addElement(T);

		 Button t = new Button("ȷ��");
		 t.setStyle("-fx-font: 15 arial; -fx-base: #ee2211;");
		 t.setOnAction(e->{

			 String S1 = TFiled1.getText();
			 String S2 = TFiled2.getText();

			 if (S1.length() < 1 || S2.length() < 1)
			 {
				 gridpaneRight.getChildren().remove(list);
				 list.getItems().clear();
				 Text T3 = new Text("�������������ȴ���һ�ĵ���");
				 T3.setFill(Color.RED);
				 T3.setFont(Font.font(15));
				 VText.add(T3);
				 list.getItems().add(T3);
				 gridpaneRight.add(T3, 14, 12);
                return;
			 }


			 String Temp1 = ChangeText(S1);
			 String Temp2 = ChangeText(S2);

			 if (Temp1.length() == 0 || Temp2.length() == 0)
			 {
				 gridpaneRight.getChildren().remove(list);
				 list.getItems().clear();
				 Text T3 = new Text("���������벻Ҫֻ����ո�");
			     T3.setFill(Color.RED);
			     T3.setFont(Font.font(15));
			     VText.add(T3);
			     list.getItems().add(T3);
			     gridpaneRight.add(list, 14, 12);
			     return;
			 }

			 if (Temp1.contains(" ") || Temp2.contains(" "))
			 {
				 gridpaneRight.getChildren().remove(list);
				 list.getItems().clear();
				 Text T3 = new Text("һ����������ֻ����һ������");
			     T3.setFill(Color.RED);
			     T3.setFont(Font.font(15));
			     VText.add(T3);
			     list.getItems().add(T3);
			     gridpaneRight.add(list, 14, 12);
			     return;
			 }

			 if (!D.NodeList.contains(Temp1) || !D.NodeList.contains(Temp2))
			 {
				 gridpaneRight.getChildren().remove(list);
				 list.getItems().clear();
				 Text T3;
				 if (!D.NodeList.contains(Temp1) && !D.NodeList.contains(Temp2))
					 T3 = new Text("\"" + Temp1 + "\" ��  \"" + Temp2 + "\" ������Text��");
				 else if(!D.NodeList.contains(Temp1))
					 T3 = new Text("\"" + Temp1 + "\" ����Text��");
				 else
					 T3 = new Text("\"" + Temp2 + "\"  ����Text��");
				 T3.setFill(Color.RED);
				 T3.setFont(Font.font(15));
				 VText.add(T3);
				 list.getItems().add(T3);
				 gridpaneRight.add(list, 14, 12);
				 return;
			 }

			 list.getItems().clear();
			 if (Temp1.equals(Temp2))
			 {
				 gridpaneRight.getChildren().remove(list);
				 list.getItems().clear();
				 Text T3 = new Text("�������ʲ�����ͬ");
				 T3.setFill(Color.RED);
				 T3.setFont(Font.font(15));
				 VText.add(T3);
				 list.getItems().add(T3);
				 gridpaneRight.add(list, 14, 12);
			 }
			 else
			 {
				 gridpaneRight.getChildren().remove(list);
			     list.getItems().clear();
				 D.TwoPointsGetShortPath(Temp1, Temp2);
				 Text T3;

				 Vector <PQueue> V = D.NodeShortPath;


				 T3 = new Text("\"" + Temp1 + "\" ��   \"" +  Temp2 + "\"  �����·�����£�");
				 T3.setFill(Color.RED);
				 T3.setFont(Font.font(15));

				 Text T4 = new Text("\"" + Temp1 + "\" ��   \"" +  Temp2 + "\" ������·��");
				 T4.setFill(Color.RED);
				 T4.setFont(Font.font(15));

				 for (int i = 0; i < V.size(); i++)
				 {

                     if (V.get(i).End.equals(Temp2))
					 {
                    	 if(V.get(i).Costs==0)
                    	 {
                    		 VText.add(T4);
           				 	 list.getItems().add(T4);
                    	 }
                    	 else{

                    	 VText.add(T3);
       				 	 list.getItems().add(T3);

       				 	 String Text = "";
				         Text = "·��ȨֵΪ��" + V.get(i).Costs;//��
				         Text Tt = new Text(Text);
						 Tt.setFill(Color.RED);
						 Tt.setFont(Font.font(15));
						 VText.add(Tt);
						 list.getItems().add(Tt);
						 Text = "";
		                 for (int k = 0; k < V.get(i).P.size(); k++)
						 {
		                	 String words[] = V.get(i).P.get(k).split(" ");
		                	 for (int X = 0; X < words.length; X++)
		                	 {
		                		 if (X % 3 == 0 && X > 0)//��
								 {
		                			 Text += words[X];
									 Text TTT = new Text(Text);
									 TTT.setFill(Color.RED);
									 TTT.setFont(Font.font(15));
									 VText.add(TTT);
									 list.getItems().add(TTT);
									 Text = "->";
								 }
							     else
							    	 if (X != words.length - 1)
							    		 Text += (words[X] + " -> ");
							    	 else
							    		 Text += words[X];
		                	 }
		                	 if (!Text.equals("") && !Text.equals("->"))
							 {
								 Text TTT = new Text(Text);
								 TTT.setFill(Color.RED);
								 TTT.setFont(Font.font(15));
								 VText.add(TTT);
								 list.getItems().add(TTT);
								 Text = "";
							 }
		                	 Text = "";//��
						 }

		 				D.picture_zdlj();

		 				pane.setAlignment(Pos.CENTER);
		 				Image image=new Image("file:\\D:\\zdljout.png", 700, 950, false, false);
		 				pane.getChildren().add(new ImageView(image));

		 				a.setTitle("���·������ͼ");
		 				a.setScene(scene);
		 				a.setX(40);
		 	            a.setY(20);
		 				a.show();
                    	}
                    	 break;
					 }

				}
		    }

		 gridpaneRight.add(list, 14, 12);

		 });

		 gridpaneRight.add(T, 14, 4);
		 gridpaneRight.add(T1, 13, 7);
		 gridpaneRight.add(T2, 13, 10);
		 gridpaneRight.add(t, 14, 11);
		 gridpaneRight.add(TFiled1, 14, 7);

		 gridpaneRight.add(TFiled2, 14, 10);

		 window.show();
	 }

	public void GetOneWordsShortestPath(BorderPane layout)
	 {
		 gridpaneRight.getChildren().clear();

		 ListView<Text> list = new ListView<>();
		 TextField TFiled1 = new TextField ();
		 TFiled1.setPromptText("�������ѯ����");
		 Text T1 = new Text("Word");
		 Text T = new Text("������Ҫ��ѯ���·���ĵ���");
		 T.setFill(Color.RED);
		 T.setFont(Font.font(15));

		 T1.setFill(Color.RED);
		 T1.setFont(Font.font(15));

		 VText.addElement(T1);
		 VText.addElement(T);

		 Button t = new Button("ȷ��");
		 t.setStyle("-fx-font: 15 arial; -fx-base: #ee2211;");
		 t.setOnAction(e->{

		 String S1 = TFiled1.getText();
		 Vector<PQueue> V;
		 if (S1.length() >= 1 )
		 {
			 String Temp1 = ChangeText(S1);

			 if (Temp1.length() == 0)
			 {
				 gridpaneRight.getChildren().remove(list);
				 list.getItems().clear();
				 Text T3 = new Text("���������벻Ҫֻ����ո�");
			     T3.setFill(Color.RED);
			     T3.setFont(Font.font(15));
			     VText.add(T3);
			     list.getItems().add(T3);
			     gridpaneRight.add(list, 14, 12);
			     return;
			 }

			 if (Temp1.contains(" "))
			 {
				 gridpaneRight.getChildren().remove(list);
				 list.getItems().clear();
				 Text T3 = new Text("һ����������ֻ����һ������");
			     T3.setFill(Color.RED);
			     T3.setFont(Font.font(15));
			     VText.add(T3);
			     list.getItems().add(T3);
			     gridpaneRight.add(list, 14, 12);
			     return;
			 }

			 if (!D.NodeList.contains(Temp1))
			 {
				 gridpaneRight.getChildren().remove(list);
				 list.getItems().clear();
				 Text T3 = new Text(S1 + "  ����Text��");
				 list.getItems().add(T3);
				 T3.setFill(Color.RED);
				 T3.setFont(Font.font(15));
				 VText.add(T3);
				 gridpaneRight.add(list, 14, 12);
			 }
			 else
			 {
				 gridpaneRight.getChildren().remove(list);
				 list.getItems().clear();
				 //System.out.print(S1 + "  ");
				 Text T3 = new Text("\"" + Temp1 + "\"  �� " + " ������ľ������£�");
				 T3.setFill(Color.RED);
				 T3.setFont(Font.font(15));
				 VText.add(T3);
				 list.getItems().add(T3);

				 D.TwoPointsGetShortPath(Temp1, Temp1);
				 V = D.NodeShortPath;

				 for (int i = 0; i < V.size(); i++)
				 {
					 if (V.get(i).P.size() == 0)
					 {
						 Text TTT = new Text("\"" + V.get(i).End + "\" : ���ɵ���");
						 TTT.setFill(Color.RED);
						 TTT.setFont(Font.font(15));
						 VText.add(TTT);
						 list.getItems().add(TTT);
					 }
					 else
					 {
						 String Text = ("�ﵽ�㣺 \"" +  V.get(i).End + "\"  " + "ȨֵΪ�� " + V.get(i).Costs);//��
				         Text TTT = new Text(Text);
						 TTT.setFill(Color.RED);
						 TTT.setFont(Font.font(15));
						 VText.add(TTT);
						 list.getItems().add(TTT);
						 Text = "";//��
		                 for (int k = 0; k < V.get(i).P.size(); k++)
						 {
		                	 String words[] = V.get(i).P.get(k).split(" ");
		                	 for (int X = 0; X < words.length; X++)
		                	 {
		                		 if (X % 3 == 0 && X > 0)//��
								 {
		                			 Text += words[X];
									 Text TTt = new Text(Text);
									 TTt.setFill(Color.RED);
									 TTt.setFont(Font.font(15));
									 VText.add(TTt);
									 list.getItems().add(TTt);
									 Text = "->";
								 }
							     else
							    	 if (X != words.length - 1)
							    		 Text += (words[X] + " -> ");
							    	 else
							    		 Text += words[X];
		                	 }
		                	 if (!Text.equals("") && !Text.equals("->"))
							 {
								 Text TTt = new Text(Text);
								 TTt.setFill(Color.RED);
								 TTt.setFont(Font.font(15));
								 VText.add(TTt);
								 list.getItems().add(TTt);
								 Text = "";
							 }
		                	 Text = "";//��
						 }

					 }
				}
				gridpaneRight.add(list, 14, 12);
			}
		}
		else
		{
		     gridpaneRight.getChildren().remove(list);
			 list.getItems().clear();
			 Text T3 = new Text("��ѯ���ʲ���Ϊ��");
			 T3.setFill(Color.RED);
			 T3.setFont(Font.font(15));
			 VText.add(T3);
			 gridpaneRight.add(T3, 14, 12);
		 }
		 });

		 gridpaneRight.add(T, 14, 4);
		 gridpaneRight.add(T1, 13, 7);
		 gridpaneRight.add(t, 14, 10);
		 gridpaneRight.add(TFiled1, 14, 7);
		 window.show();
	 }

	 public void PromptBuiltFigure()
	 {
		 Stage secondWindow = new Stage();
		 secondWindow.setTitle("����");
         GridPane grid = new GridPane();
         grid.setAlignment(Pos.CENTER);
         grid.setHgap(10);
         grid.setVgap(10);
         grid.setPadding(new Insets(25, 25, 25, 25));

         DropShadow ds = new DropShadow();
	     ds.setOffsetY(0.3);
	     ds.setColor(Color.color(0.9, 0.9, 0.9));

         Text scenetitle = new Text("\n     ���ѣ�");

         scenetitle.setEffect(ds);
         scenetitle.setCache(true);
         scenetitle.setFill(Color.BLUE);
         scenetitle.setWrappingWidth(200);
         scenetitle.setFont(Font.font(20));
         grid.add(scenetitle, 0, 0, 2, 1);

         Text t = new Text("\n���ȶ����ļ�����ͼƬ");

         t.setEffect(ds);
	     t.setCache(true);
	     t.setFill(Color.BLUE);
	     t.setWrappingWidth(200);
	     t.setText("\n     ���ȶ��ļ���ͼ");
	     t.setFont(Font.font(20));
         grid.add(t, 0, 1);

         Button btn = new Button("ȷ��");
         btn.setStyle("-fx-font: 20 arial; -fx-base: #ee2211;");
		 btn.setOnAction(e->{secondWindow.close();
		 });
         HBox hbBtn = new HBox(10);
         hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
         hbBtn.getChildren().add(btn);
         grid.add(hbBtn, 1, 4);

         final Text actiontarget = new Text();
         grid.add(actiontarget, 1, 6);

         Scene scene = new Scene(grid, 330, 250);
         secondWindow.setScene(scene);
         secondWindow.show();
	 }

	 public void StartRandomWalk(BorderPane layout)
	 {
		 Input I = new Input();
		 Vector<String> Str = new Vector<String>();
		 ListView<Text> list = new ListView<>();
		 gridpaneRight.getChildren().clear();
		 gridpaneRight.getChildren().remove(list);
		 list.getItems().clear();


		 Text T1 = new Text("������߽�����£�");

		 T1.setFill(Color.RED);
		 T1.setFont(Font.font(15));

		 VText.addElement(T1);

		 Button t = new Button("  ����   ");
		 t.setStyle("-fx-font: 15 arial; -fx-base: #ee2211;");
		 t.setOnAction(e->{

			 preword=D.RandomWalk(word);
			 word=preword;
			 if(!preword.equals(word) || !preword.equals("-end-"))
			 {
				 if(preword.length()>=5 && preword.subSequence(0, 5).equals("-end-"))
				 {
					 String s=preword.substring(5);
					 InputView(I, list, s);
					 Str.add(s+" ");
					 WalkEnd();
				 }
				 else
				 {
					 InputView(I, list, preword);
					 Str.add(preword+" ");
				 }
			 }
			 else
			 {
				 WalkEnd();
			 }

			 D.picture_sjyz();

         	 pane.setAlignment(Pos.CENTER);
             Image image=new Image("file:\\D:\\sjyzout.png", 700, 950, false, false);
             pane.getChildren().add(new ImageView(image));

             a.setTitle("�����������ͼ");
             a.setScene(scene);
             a.setX(40);
             a.setY(20);
             a.show();
		 });

		 Button T = new Button("����д���ļ�");
		 T.setStyle("-fx-font: 15 arial; -fx-base: #ee2211;");
		 T.setOnAction(e->{
			WriteToFile(Str);

		 });

		 gridpaneRight.add(T1, 13, 7);
		 gridpaneRight.add(list, 13, 9);
		 gridpaneRight.add(t, 13, 11);
		 gridpaneRight.add(T, 13, 12);
		 window.show();
	 }

	 public void InputView(Input I, ListView<Text> list, String s)
	 {
		 if (I.Count == 5)
	     {
	    	 I.s.delete(0, I.s.length());
	    	 I.s.append("");
	    	 I.Count = 0;
	     }
	     else
	     {
	    	 if (list.getItems().size() != 0)
	    		 list.getItems().remove(list.getItems().size() - 1);
	     }
	     if (I.Count == 0)
	    	 I.s.append(s);
	     else
	    	 I.s.append(" " + s);
	     I.Count++;
    	 Text TT = new Text(I.s.toString());

		 TT.setFill(Color.RED);
		 TT.setFont(Font.font(15));

		 VText.addElement(TT);

		 list.getItems().add(TT);
	 }

	 public void WriteToFile(Vector<String> Str)
	 {
			File file = new File("RandomWalkResult.txt");
			/*if (file.exists())
			{
				System.out.println("File already exists");
			}*/

			/*try
			(PrintWriter output = new PrintWriter(file) )
			{
				 String STR = "";
				 for (int i = 1; i < Str.size() + 1; i++)
				 {
					 if (i % 8 == 0)
					 {
						 output.println(STR);
						 STR = Str.get(i - 1);
					 }
					 STR += (i == 1)? (Str.get(i - 1)): ( Str.get(i - 1));
				 }
				 if (STR.length() >= 1)
					 output.println(STR);
				 //output.close();*/
			try
			(PrintWriter output = new PrintWriter(file) )
			{
				 for (int i = 0; i < Str.size(); i++)
				 {
					output.print(Str.get(i));
				 }
				 output.close();
			}catch(IOException e){
				e.printStackTrace();}
	 }

	 public void WalkEnd()
	 {
		 Stage secondWindow = new Stage();
		 secondWindow.setTitle("��ʾ");
         GridPane grid = new GridPane();
         grid.setAlignment(Pos.CENTER);
         grid.setHgap(10);
         grid.setVgap(10);
         grid.setPadding(new Insets(25, 25, 25, 25));

         DropShadow ds = new DropShadow();
	     ds.setOffsetY(0.3);
	     ds.setColor(Color.color(0.9, 0.9, 0.9));

         Text scenetitle = new Text("\n     ��ʾ��");

         scenetitle.setEffect(ds);
         scenetitle.setCache(true);
         scenetitle.setFill(Color.BLUE);
         scenetitle.setWrappingWidth(200);
         scenetitle.setFont(Font.font(20));
         grid.add(scenetitle, 0, 0, 2, 1);

         Text t = new Text("\n������߽���");

         t.setEffect(ds);
	     t.setCache(true);
	     t.setFill(Color.BLUE);
	     t.setWrappingWidth(200);
	     t.setText("\n     ������߽���");
	     t.setFont(Font.font(20));
         grid.add(t, 0, 1);

         Button btn = new Button("ȷ��");
         btn.setStyle("-fx-font: 20 arial; -fx-base: #ee2211;");
		 btn.setOnAction(e->{secondWindow.close();
		 });
         HBox hbBtn = new HBox(10);
         hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
         hbBtn.getChildren().add(btn);
         grid.add(hbBtn, 1, 4);

         final Text actiontarget = new Text();
         grid.add(actiontarget, 1, 6);

         Scene scene = new Scene(grid, 330, 250);
         secondWindow.setScene(scene);
         secondWindow.show();
	 }


	/* public static void main(String[] args)
	 { launch(args); }*/
}
