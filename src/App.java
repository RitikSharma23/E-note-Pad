import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;
import org.json.*;
import java.io.*;
import java.net.URLEncoder;






public class App extends JFrame implements ActionListener{
  JFrame frame;
  // String link="http://localhost/java/javaapi.php";
  String link="https://farouche-sentries.000webhostapp.com/javaapi.php";
  // JButton edit,view;
  // JTextArea a;
  JMenu menu,menu2,menu3,sub;
  JMenuItem neww,nw,open,save,savea,saveo,log,refresh,page,print,exit,undo,cut,copy,paste,delete,find,findn,findp,replace,got,select,time,font,zoom,status,word,in,out,restore;
  int flag=0;
  String filedetail;
  
      public boolean login(){
        
        frame = new JFrame("E-Notepad User Login");
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel welcomeLabel = new JLabel("Welcome To Advanced Notepad");
        welcomeLabel.setBounds(150, 50, 400, 25);
        welcomeLabel.setForeground(Color.RED);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(welcomeLabel);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(180, 100, 80, 25);
        panel.add(userLabel);

        JTextField userText = new JTextField(40);
        userText.setBounds(260, 100, 180, 25);
        panel.add(userText);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(180, 150, 80, 25);
        panel.add(passLabel);

        JPasswordField passText = new JPasswordField(20);
        passText.setBounds(260, 150, 180, 25);
        panel.add(passText);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(270, 200, 80, 25);
        panel.add(loginButton);

        JButton registerButton = new JButton("New User Registeration");
        registerButton.setBounds(200, 250, 230, 25);
        panel.add(registerButton);

        frame.add(panel);
        frame.setVisible(true);
  
  
        loginButton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
              String username = userText.getText();
              String password = String.valueOf(passText.getPassword());
              
              if (username.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Name field is empty!");
                userText.requestFocus();
              }else if(password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Password field is empty!");
                passText.requestFocus();
              }else{
              
              try {
                URL url = new URL(link+"?obj=login&userid="+username+"&pass="+password);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                int responseCode = connection.getResponseCode();    
                if (responseCode == HttpURLConnection.HTTP_OK) {
                  BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                  String response = in.lines().collect(Collectors.joining());

                  JSONObject jsonObject=new JSONObject(response);

                  String message=jsonObject.getString("message");

                  if(message.equals("nouser")){
                    
                    JOptionPane.showMessageDialog(frame, "no user found \nPlease Try Again");
                   

                  }else if(message.equals("nopass")){

                    JOptionPane.showMessageDialog(frame, "Wrong Password \nFor This Userid");

                  }else{
        
                    JSONArray jsonArray=jsonObject.getJSONArray("data");
                    JSONObject data=jsonArray.getJSONObject(0);
                    // System.out.println(data.getString("name"));
                    loadfiles(data.getString("userid"));
                    frame.setVisible(false);
                    App l=new App();
                    l.home(data.getString("userid"), data.getString("name"));
                    l.information();

                  }
                  in.close();

                } else {


                }    
                connection.disconnect();
              } catch (Exception ee) {
                ee.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Unable to Connect \nPlease Check Your Internet Connection", "Connection Failed",JOptionPane.ERROR_MESSAGE);

              }
              
          }
        }
        });


        registerButton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            frame.setVisible(false);
            App l=new App();
            l.register();
          }
        });
  
  
      return true;
      }
  
      public boolean register(){
        
        frame = new JFrame("E-Notepad User Registeration");
        frame.setSize(600, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel welcomeLabel = new JLabel("Please Register Yourself");
        welcomeLabel.setBounds(180, 50, 400, 25);
        welcomeLabel.setForeground(Color.GREEN);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(welcomeLabel);

        JLabel nameLabel = new JLabel("Enter Name : ");
        nameLabel.setBounds(150, 100, 80, 25);
        panel.add(nameLabel);

        JTextField nameText = new JTextField(40);
        nameText.setBounds(260, 100, 180, 25);
        panel.add(nameText);

        JLabel phoneLabel = new JLabel("Enter Phone : ");
        phoneLabel.setBounds(150, 150, 100, 25);
        panel.add(phoneLabel);

        JTextField phoneText = new JTextField(40);
        phoneText.setBounds(260, 150, 180, 25);
        panel.add(phoneText);


        JLabel userLabel = new JLabel("Enter UserID :");
        userLabel.setBounds(150, 200, 80, 25);
        panel.add(userLabel);

        JTextField userText = new JTextField(40);
        userText.setBounds(260, 200, 180, 25);
        panel.add(userText);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(150, 250, 80, 25);
        panel.add(passLabel);

        JPasswordField passText = new JPasswordField(20);
        passText.setBounds(260, 250, 180, 25);
        panel.add(passText);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(230, 300, 120, 25);
        panel.add(registerButton);

        JButton  loginButton= new JButton("Already A Member? Login");
        loginButton.setBounds(180, 350, 230, 25);
        panel.add(loginButton);

        frame.add(panel);
        frame.setVisible(true);
  
  
        registerButton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
              String name = nameText.getText().replaceAll("\\s","");
              String userid = userText.getText().replaceAll("\\s","");
              String phone = phoneText.getText().replaceAll("\\s","");
              String password = String.valueOf(passText.getPassword()).replaceAll("\\s","");

              if (name.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Name field is empty!");
                nameText.requestFocus();
              }else if (userid.isEmpty()) {
                JOptionPane.showMessageDialog(null, "UserId field is empty!");
                userText.requestFocus();
              }else if (phone.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Phone field is empty!");
                phoneText.requestFocus();
              }else if (password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Password field is empty!");
                passText.requestFocus();
              }else if (phone.length() != 10) {
                JOptionPane.showMessageDialog(null, "Phone Number Can't Be Lass Than Or Greater Than 10");
                phoneText.requestFocus();
              }else{
                
                try {
                  URL url = new URL(link+"?obj=register&userid="+userid+"&pass="+password+"&name="+name+"&phone="+phone);
                  HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                  connection.setRequestMethod("GET");
                  int responseCode = connection.getResponseCode();    
                  if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String response = in.lines().collect(Collectors.joining());

                    System.out.println(response);



                    JSONObject jsonObject=new JSONObject(response);


                    String message=jsonObject.getString("message");

                    if(message.equals("userid")){
                      JOptionPane.showMessageDialog(frame, "UserId Already Exists \nPlease Try A Different One","UserId Already Exists",JOptionPane.WARNING_MESSAGE);
                    }else if(message.equals("phone")){
                      JOptionPane.showMessageDialog(frame, "Phone Number Already Exists \nPlease Try A Different One","Phone Number Already Exists",JOptionPane.WARNING_MESSAGE);              
                    }else if(message.equals("success")){
                      JOptionPane.showMessageDialog(frame, "Account Created Successfully ","Account Created",JOptionPane.INFORMATION_MESSAGE);
                      frame.setVisible(false);
                      App l=new App();
                      l.login();
                    }
                  in.close();
                  } else {
                    System.out.println("HTTP error: " + responseCode + " " + connection.getResponseMessage());
                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                    String response = in.lines().collect(Collectors.joining());
                    // JOptionPane.showMessageDialog(frame, response, "Server Error", JOptionPane.ERROR_MESSAGE);
                    System.out.println(response);
                  in.close();


                  }    
                  connection.disconnect();
                  
                } catch (Exception ee) {
                  ee.printStackTrace();
                  JOptionPane.showMessageDialog(frame, "Unable to Connect \nPlease Check Your Internet Connection", "Connection Failed",JOptionPane.ERROR_MESSAGE);

                }

              }
              
          }
        });
  

        loginButton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            frame.setVisible(false);
            App l=new App();
            l.login();
          }
        });
      return true;
      }
  
      

  // ===============================================    `SAVING    ==========================================================

      public boolean save(String userid) {

        String dirPath = "C:\\\\"+userid; 
        JSONObject dirJson = new JSONObject(); 
        dirJson.put("path", dirPath);
        JSONArray filesJson = new JSONArray(); 
        listFilesAndFolders(new File(dirPath), filesJson);
        dirJson.put("content", filesJson);
        String jsonStr = dirJson.toString();

        try {
              String encodedJsonStr = URLEncoder.encode(jsonStr, "UTF-8");
              String urlString = link+"?obj=savefile&userid="+userid+"&content="+encodedJsonStr;
              URL url = new URL(urlString);
              HttpURLConnection connection = (HttpURLConnection) url.openConnection();
              connection.setRequestMethod("GET");
              int responseCode = connection.getResponseCode();    
              if (responseCode == HttpURLConnection.HTTP_OK) {
                  BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                  String response = in.lines().collect(Collectors.joining());
                  JSONObject jsonObject=new JSONObject(response);

                  String message=jsonObject.getString("message");
                  if(message.equals("success")){
                       in.close();
                      JOptionPane.showMessageDialog(frame, "Online Saved Successfully!","Logout",JOptionPane.INFORMATION_MESSAGE);
                      return true;
                  }
                  in.close();
              } else {
                  System.out.println("HTTP error: " + responseCode + " " + connection.getResponseMessage());
                JOptionPane.showMessageDialog(frame, "Unable to Connect \nPlease Check Your Internet Connection", "Connection Failed",JOptionPane.ERROR_MESSAGE);
                return false;

              }    
              connection.disconnect();
          } catch (Exception ee) {
                System.err.println("Exception:");
                ee.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Unable to Connect \nPlease Check Your Internet Connection", "Connection Failed",JOptionPane.ERROR_MESSAGE);
                return false;
          }
          return false;

       
    }   

    private static void listFilesAndFolders(File directory, JSONArray filesJson) {
      File[] files = directory.listFiles();
      for (File file : files) {
          if (file.isDirectory()) {
              JSONObject folderJson = new JSONObject();
              folderJson.put("name", file.getName());
              folderJson.put("type", "folder");
              JSONArray folderContentJson = new JSONArray();
              listFilesAndFolders(file, folderContentJson);
              folderJson.put("content", folderContentJson);
              filesJson.put(folderJson);
          }
  
          else {
              JSONObject fileJson = new JSONObject();
              fileJson.put("name", file.getName());
              fileJson.put("type", "file");
              fileJson.put("size", file.length());
              try {
                  String content = readFileContents(file);
                  fileJson.put("content", content);
              } catch (IOException e) {
                  e.printStackTrace();
              }
              filesJson.put(fileJson);
          }
      }
  }

  private static String readFileContents(File file) throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(file));
    StringBuilder content = new StringBuilder();
    String line;
    while ((line = reader.readLine()) != null) {
        content.append(line);
        content.append(System.lineSeparator()); // Add the newline character
    }
    reader.close();
    return content.toString();
}

  // ===============================================    /SAVING    ==========================================================


  // ===============================================    loading    ==========================================================


  public void loadfiles(String userid){

    try {
      String urlString = link+"?obj=loadfile&userid="+userid;
      URL url = new URL(urlString);
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("GET");
      int responseCode = connection.getResponseCode();    
      if (responseCode == HttpURLConnection.HTTP_OK) {
          BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
          String response = in.lines().collect(Collectors.joining());


          JSONObject jsonObject=new JSONObject(response);

          JSONArray jsonArray=jsonObject.getJSONArray("data");

          JSONObject data=jsonArray.getJSONObject(0);
          
          String content=data.getString("content");
          String jsonStr = content.toString();


          // String jsonString = "{\"path\":\"C:\\\\ritik123\",\"content\":[{\"size\":40,\"name\":\"myfile.txt\",\"type\":\"file\",\"content\":\"Hello, world! this is first file upper\"},{\"size\":39,\"name\":\"myfile2.txt\",\"type\":\"file\",\"content\":\"Hello, world! this is second file upper\"},{\"name\":\"myfolder\",\"type\":\"folder\",\"content\":[{\"size\":50,\"name\":\"myfile.txt\",\"type\":\"file\",\"content\":\"Hello, world! this is first file inside myfolder\"},{\"size\":50,\"name\":\"myfile2.txt\",\"type\":\"file\",\"content\":\"Hello, world! this is second file inside my folder\"}]}]}";

          // System.out.println(jsonString);

          // System.out.println(jsonStr);

          // // jsonStr="{\"path\":\"C:\\ritik123\",\"content\":[{\"size\":7,\"name\":\"myfile.txt\",\"type\":\"file\",\"content\":\"\"ritik\"\"},{\"size\":39,\"name\":\"myfile2.txt\",\"type\":\"file\",\"content\":\"Hello, world! this is second file upper\"},{\"name\":\"myfolder\",\"type\":\"folder\",\"content\":[{\"size\":48,\"name\":\"myfile.txt\",\"type\":\"file\",\"content\":\"Hello, world! this is first file inside myfolder\"},{\"size\":50,\"name\":\"myfile2.txt\",\"type\":\"file\",\"content\":\"Hello, world! this is second file inside my folder\"}]}]}";

          try {
            JSONObject dirJson = new JSONObject(jsonStr);
            String dirPath = dirJson.getString("path");
            JSONArray filesJson = dirJson.getJSONArray("content");
            createFilesAndFolders(new File(dirPath), filesJson);

          } catch (JSONException e) {
              e.printStackTrace();
          }
          in.close();

      } else {
          System.out.println("HTTP error: " + responseCode + " " + connection.getResponseMessage());
      }    
      connection.disconnect();
    } catch (Exception ee) {
          System.err.println("Exception:");
          ee.printStackTrace();
    }

  }


  private static void createFilesAndFolders(File directory, JSONArray filesJson) {
    if (!directory.exists()) {
        directory.mkdirs();
    }
    for (int i = 0; i < filesJson.length(); i++) {
        JSONObject fileJson = filesJson.getJSONObject(i);
        String name = fileJson.getString("name");
        String type = fileJson.getString("type");
        if (type.equals("file")) {
            createFile(directory, fileJson);
        } else if (type.equals("folder")) {
            createFolder(directory, fileJson);
        }
    }
}

private static void createFile(File directory, JSONObject fileJson) {
    try {
        String name = fileJson.getString("name");
        long size = fileJson.getLong("size");
        String content = fileJson.getString("content");
        File file = new File(directory, name);
        FileWriter writer = new FileWriter(file);
        writer.write(content);
        writer.close();
    } catch (IOException | JSONException e) {
        e.printStackTrace();
    }
}

private static void createFolder(File directory, JSONObject folderJson) {
    try {
        String name = folderJson.getString("name");
        JSONArray contentJson = folderJson.getJSONArray("content");
        File folder = new File(directory, name);
        folder.mkdir();
        createFilesAndFolders(folder, contentJson);
    } catch (JSONException e) {
        e.printStackTrace();
    }
}








  // ===============================================    `/loading    ==========================================================

  


  // ================================================== home =============================================

  public void home(String userid,String name){

    frame = new JFrame("E-Notepad : "+name);
    frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
// frame.setUndecorated(true);
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // frame.setResizable(false);

    JTextArea textArea = new JTextArea();
    textArea.setFont(new Font("SansSerif", Font.PLAIN, 16));
    
    JScrollPane scrollPane = new JScrollPane(textArea);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    
    add(scrollPane, BorderLayout.CENTER);

    // JButton loadfile = new JButton("Load Files");
    // loadfile.setBounds(200, 200, 130, 25);
    // panel.add(loadfile);

    // JButton savefile = new JButton("Save Files");
    // savefile.setBounds(200, 250, 130, 25);
    // panel.add(savefile);

    // JButton logoutButton = new JButton("Logout");
    // logoutButton.setBounds(200, 300, 230, 25);
    // panel.add(logoutButton);

    JMenuBar mb=new JMenuBar();  
    menu=new JMenu("        Home       ");  

    nw=new JMenuItem("     New Window   ");  
    open=new JMenuItem("     Open   ");  
    save=new JMenuItem("     Save   ");  
    savea=new JMenuItem("     Save As   ");  
    saveo=new JMenuItem("     Save Online   ");  
    refresh=new JMenuItem("     Reload Online   ");  
    log=new JMenuItem("     Logout   ");  
    exit=new JMenuItem("     Exit   "); 

    menu.add(nw);  
    menu.add(open);  
    menu.add(save);  
    menu.add(savea);
    menu.addSeparator();
    menu.add(saveo);
    menu.add(refresh);
    menu.add(log);
    menu.addSeparator();  
    menu.add(exit);  

    mb.add(menu);
    frame.setJMenuBar(mb);

    frame.add(scrollPane);
    frame.setVisible(true);

    
    refresh.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        loadfiles(userid);
      }
    });
    
    saveo.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        save(userid);
      }
    });

    log.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
          if(save(userid)){
          String dirPath = "C:\\"+userid;
          File directory = new File(dirPath);
          if (directory.exists()) {
            boolean result = deleteDirectory(directory);
            JOptionPane.showMessageDialog(frame, "LogOut Successfull","Logout",JOptionPane.INFORMATION_MESSAGE);
            frame.setVisible(false);
            App l=new App();
            l.login();
          }
        }

        
      }
    });

    nw.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        App l=new App();
        l.home(userid,name);
      }
    });

    open.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        JFileChooser fc=new JFileChooser();
        fc.setCurrentDirectory(new File("C:\\"+userid));      

        int i=fc.showOpenDialog(new JFrame());

           if (i == JFileChooser.APPROVE_OPTION) {
               filedetail=fc.getSelectedFile().getPath();
               flag=1;
               File f = fc.getSelectedFile();
               String filepath=f.getPath();
               try{
                   BufferedReader br=new BufferedReader(new FileReader(filepath));
                   String sr1="",sr2="";
                   while((sr1=br.readLine())!=null){
                       sr2+=sr1+"\n";
                   }
                   textArea.setText(sr2);
                   br.close();

               }catch(Exception ex){ex.getStackTrace();}
           }
           
      }
    });

    save.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        JFileChooser js=new JFileChooser();  
        js.setCurrentDirectory(new File("C:\\"+userid));

        if(flag==0){     
        int i=js.showSaveDialog(new JFrame());
        File f = js.getSelectedFile();
         js.setDialogTitle("Save File");

        if(i==JFileChooser.APPROVE_OPTION){
        filedetail=js.getSelectedFile().getPath();
            
            int x=0;
            System.out.println(f.getPath());
            if(f.exists()==true){
                x=JOptionPane.showConfirmDialog(js, "waha par pahale se hi hai \n Phir bhi save karna hai final", "Copy Cat", i);
                if(x==0){                   
                    try {
                        // textArea.write(new OutputStreamWriter(new FileOutputStream(f),"utf-8"));
                        FileOutputStream fd=new FileOutputStream(f);
                        OutputStreamWriter os=new OutputStreamWriter(fd,"utf-8");
                        textArea.write(os);
                        frame.setTitle(f.getName());
                        flag=1;
                        os.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
            }
            }else{
                try {
                  FileOutputStream fd=new FileOutputStream(f);
                  OutputStreamWriter os=new OutputStreamWriter(fd,"utf-8");
                    textArea.write(os);
                    frame.setTitle(f.getName());
                    flag=1;
                  os.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }                    
            }

        }
    }else{

        try{
        FileWriter fw=new FileWriter(filedetail);
        BufferedWriter buffer = new BufferedWriter(fw);  
        buffer.write(textArea.getText().toString());  
        buffer.close(); 
        fw.close(); 
        }catch(Exception ex){
            ex.getStackTrace();
        }
    }
      }
    });

    savea.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        JFileChooser js=new JFileChooser();   
        js.setCurrentDirectory(new File("C:\\"+userid));      
        int i=js.showSaveDialog(new JFrame());
        js.setDialogTitle("Save File");

        if(i==JFileChooser.APPROVE_OPTION){
            File f = js.getSelectedFile();
            int x=0;
            System.out.println(f.getPath());
            if(f.exists()==true){
                x=JOptionPane.showConfirmDialog(js, "waha par pahale se hi hai \n Phir bhi save karna hai final", "Copy Cat", i);
                System.out.println(x);
                if(x==0){                   
                    try {
                        textArea.write(new OutputStreamWriter(new FileOutputStream(f),"utf-8"));
                        frame.setTitle(f.getName());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
            }
            }else{
                try {
                    textArea.write(new OutputStreamWriter(new FileOutputStream(f),"utf-8"));
                    frame.setTitle(f.getName());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }                    
            }
        }
      }
    });

    // jf.setTitle("Notepad Home");
    // jf.getContentPane().setLayout(null);
    // jf.setVisible(true);
    // jf.setBounds(200,200,1000,700);
    // jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  
  }


  public static boolean deleteDirectory(File directory) {
    File[] files = directory.listFiles();

    if (files != null) {
      for (File file : files) {
        if (file.isDirectory()) {
          deleteDirectory(file);
        } else {
          file.delete();
        }
      }
    }

    return directory.delete();
  }
  

        
  public void information(){
        
    frame = new JFrame("E-Notepad Information");
    frame.setSize(600, 400);
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    frame.setResizable(false);

    JPanel panel = new JPanel();
    panel.setLayout(null);

    JLabel welcomeLabel = new JLabel("MADE BY RITIK SHARMA");
    welcomeLabel.setBounds(170, 50, 400, 25);
    welcomeLabel.setForeground(Color.RED);
    welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
    panel.add(welcomeLabel);

    JLabel userLabel = new JLabel("Sem : 4");
    userLabel.setBounds(180, 100, 180, 25);
    panel.add(userLabel);

    JLabel passLabel = new JLabel("Email : ritiksharma0723@gmail.com");
    passLabel.setBounds(180, 130, 280, 25);
    panel.add(passLabel);

    JLabel enroll = new JLabel("Enroll : 2021004500210167");
    enroll.setBounds(180, 160, 280, 25);
    panel.add(enroll);

    JButton loginButton = new JButton("Thanks");
    loginButton.setBounds(270, 200, 80, 25);
    panel.add(loginButton);

    frame.add(panel);
    frame.setVisible(true);

    loginButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        frame.setVisible(false);
      }
    });


  }



  
  public void actionPerformed(ActionEvent ae){}
  
    public static void main(String[] args) throws Exception {
    
        App l=new App();
        l.login();
        // l.home("ritik123","Ritik Sharma");
    
    }
}
