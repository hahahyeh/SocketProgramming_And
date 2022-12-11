package com.example.chatting;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
public class ChatActivity2 extends AppCompatActivity {
    ListView m_ListView;
    CustomAdapter m_Adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat2);

        // 커스텀 어댑터 생성
        m_Adapter = new CustomAdapter();

        // Xml에서 추가한 ListView 연결
        m_ListView = (ListView) findViewById(R.id.listView1);

        // ListView에 어댑터 연결
        m_ListView.setAdapter(m_Adapter);

        m_Adapter.add("이건 뭐지",1);
        m_Adapter.add("쿨쿨",1);
        m_Adapter.add("쿨쿨쿨쿨",0);
        m_Adapter.add("재미있게",1);
        m_Adapter.add("안녕",1);
        m_Adapter.add("재미있게",1);
        m_Adapter.add("재미있게",0);
        m_Adapter.add("2015/11/20",2);
        m_Adapter.add("재미있게",1);
        m_Adapter.add("재미있게",1);

    }

}
//public class ChatActivity2 extends AppCompatActivity {
//    ListView m_ListView;
//    CustomAdapter m_Adapter;
//
//    private Socket socket;
//    private ObjectInputStream reader = null;
//    private ObjectOutputStream writer = null;
//    private String nickName;
//    EditText editText_msg;
//    TextView chatroom;
//    Button sendBtn;
//    private Handler mHandler;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_chat2);
//
//        // 커스텀 어댑터 생성
//        m_Adapter = new CustomAdapter();
//
//        // Xml에서 추가한 ListView 연결
//        m_ListView = (ListView) findViewById(R.id.listView1);
//
//        // ListView에 어댑터 연결
//        m_ListView.setAdapter(m_Adapter);
//
//        m_Adapter.add("이건 뭐지",1);
//        m_Adapter.add("쿨쿨",1);
//        m_Adapter.add("쿨쿨쿨쿨",0);
//        m_Adapter.add("재미있게",1);
//        m_Adapter.add("놀자라구나힐힐 감사합니다. 동해물과 백두산이 마르고 닳도록 놀자 놀자 우리 놀자",1);
//        m_Adapter.add("재미있게",1);
//        m_Adapter.add("재미있게",0);
//        m_Adapter.add("2015/11/20",2);
//        m_Adapter.add("재미있게",1);
//        m_Adapter.add("재미있게",1);
//
//        String serverIP = getIntent().getStringExtra("serverIP");
//        String nickName = getIntent().getStringExtra("nickname");
////        sendBtn = findViewById(R.id.btn_send);
////        editText_msg = findViewById(R.id.text_chat);
////        chatroom = findViewById(R.id.tv_chatroom);
//        mHandler = new Handler();
//
//        if (serverIP == null || serverIP.length() == 0) {
//            System.out.println("Didn't enter Server IP");
//            System.exit(0);
//        }
//
//        new Thread() {
//            public void run() {
//                // 서버 연결
//                try {
//                    socket = new Socket(serverIP, 8888);
//                    reader = new ObjectInputStream(socket.getInputStream());
//                    writer = new ObjectOutputStream(socket.getOutputStream());
//                    System.out.println("Client is ready");
//                } catch (UnknownHostException e) {
//                    System.out.println("Can't find the server");
//                    e.printStackTrace();
//                    System.exit(0);
//                } catch (IOException e) {
//                    System.out.println("Can't connect to server");
//                    e.printStackTrace();
//                    System.exit(0);
//                }
//
//                // 처음 연결하면 JOIN
//                try {
//                    InfoDTO dto = new InfoDTO();
//                    dto.setCommand(Info.JOIN);
//                    dto.setNickName(nickName);
//                    writer.writeObject(dto);
//                    writer.flush();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//                // listen
//                InfoDTO dto = null;
//                while (true) {
//                    try {
//                        dto = (InfoDTO) reader.readObject();
//                        if (dto.getCommand() == Info.EXIT) {
//                            reader.close();
//                            writer.close();
//                            socket.close();
//                            System.exit(0);
//                        } else if (dto.getCommand() == Info.SEND || dto.getCommand() == Info.WHISPER) {
//                            mHandler.post(new msgUpdate(dto));
//
//                        }
//                    } catch(IOException e) {
//                        e.printStackTrace();
//                    } catch (ClassNotFoundException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }}.start();
//
//        sendBtn.setOnClickListener(
//                new View.OnClickListener(){
//                    public void onClick(View view){
//
//                        // message 작성하고 전송 버튼 누를때마다
//                        String msg = editText_msg.getText().toString();
//                        InfoDTO dto = new InfoDTO();
//
//                        new Thread() {
//                            @Override
//                            public void run() {
//                                super.run();
//                                try {
//                                    if (msg.equals("exit")) {
//                                        dto.setNickName(nickName);
//                                        dto.setCommand(Info.EXIT);
//                                    } else if (msg.contains("/to ")) {
//                                        dto.setCommand(Info.WHISPER);
//                                        dto.setNickName(nickName);
//                                        dto.setMessage(msg);
//                                    } else {
//                                        dto.setCommand(Info.SEND);
//                                        dto.setMessage(msg);
//                                        dto.setNickName(nickName);
//                                    }
//                                    writer.writeObject(dto);
//                                    writer.flush();
////                                    mHandler.post(new msgUpdate(dto));
//
//                                } catch (IOException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                        }.start();
//                    }
//                });
//    }
//
//    class msgUpdate implements Runnable{
//        private String msg;
//        public msgUpdate(InfoDTO dto) {this.msg=dto.getMessage();}
//
//        @Override
//        public void run() {
//            editText_msg.setText("");
//            System.out.println("chatroom: " + chatroom.getText());
//            chatroom.setText(chatroom.getText().toString() + msg + "\n");
//            System.out.println("msg: " + msg);
//        }
//    }
//
//    // https://drink-vita.tistory.com/96
//
//}