package com.hemanthkandula.quadtilt;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Locale;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    private JoystickView joystick;
    private JoystickView joystick1;
    private TextView rollView;
    private TextView pitchView;
    private TextView yawView;
    private TextView throttleView;
    private TriToggleButton aux1View;
    private TriToggleButton aux2View;
    private TriToggleButton aux3View;
    private TriToggleButton aux4View;


    private WebSocketControl webSocketController;
    private WebSocketClient mWebSocketClient;
    public  ns n;
    //SeekBar seekBar;
    public SeekBar seekBar2;
    public Button dec,inc,send,stop;
   public Switch switch1;
    public Button go;



    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container,false);

        rollView = (TextView) view.findViewById(R.id.rollText);
        pitchView = (TextView) view.findViewById(R.id.pitchText);
        yawView = (TextView) view.findViewById(R.id.yawText);
        throttleView = (TextView) view.findViewById(R.id.throttleText);

        aux1View = (TriToggleButton) view.findViewById(R.id.auxButton1) ;
        aux2View = (TriToggleButton) view.findViewById(R.id.auxButton2) ;
        aux3View = (TriToggleButton) view.findViewById(R.id.auxButton3) ;
        aux4View = (TriToggleButton) view.findViewById(R.id.auxButton4) ;

        //joystick = (JoystickView) view.findViewById(R.id.joystickView);

        joystick1 = (JoystickView) view.findViewById(R.id.joystickView1);

       //  seekBar = (SeekBar) view.findViewById(R.id.seekBar2);
         seekBar2 = (SeekBar) view.findViewById(R.id.seekBar);
        //SeekBar seekBar = (SeekBar)findV
        //seekBar.setOnSeekBarChangeListener(this);
        //seekBar2.setOnSeekBarChangeListener(this);
        go = (Button) view.findViewById(R.id.go);

        switch1 =(Switch)view.findViewById(R.id.switch1);
        inc = (Button)view.findViewById(R.id.inc);
        dec = (Button)view.findViewById(R.id.dec);
       // send =(Button) view.findViewById(R.id.send);
        //stop =(Button) view.findViewById(R.id.stop);

        webSocketController = new WebSocketControl();






        ///////////////











        //////////////////



        return view;
    }
    public class ns extends AsyncTask< Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... params) {
            System.out.println("background");
            while(true){
                System.out.print("yo");
                //sendMessage();
                if (isCancelled()) break;}
            return null;
        }


        @Override
        protected void onPreExecute() {
            System.out.println("preexe");
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }


        protected void onPostExecute() {

        }
    }

    private class WebSocketControl {
        private int roll = 1500;
        private int pitch = 1500;
        private int yaw = 1500;
        private int throttle = 1000;
        private int aux1 = 1500;
        private int aux2 = 1500;
        private int aux3 = 1500;
        private int aux4 = 1500;
public volatile boolean vol = false;
public class nonstop extends AsyncTask< Void,Void,Void>{

    @Override
    protected Void doInBackground(Void... params) {
        while(true){
System.out.print("yo");
            sendMessage();
        if (isCancelled()) break;}
        return null;
    }
}
        nonstop ns =new nonstop();

        public class th implements Runnable{

            public  volatile boolean s = false;
            public void run(){
                int count =0;

                try {

                    while (!Thread.currentThread().isInterrupted()) {
                        sendMessage();
                        count++;

                    }

                }catch (Exception consumed){



                }


            }


            public void cancel(){

            }

            public Boolean getStop() {
                return s;
            }

            public void setStop(Boolean stop) {
                this.s = stop;
            }


        }
        //Thread now ;
        Thread now = new Thread(new Runnable() {

            public void run() {

                try {

                    while (!Thread.currentThread().isInterrupted()) {

                        sendMessage();


                    }

                }catch (Exception consumed){



                }

            }
public void cancel(){
}

        }

        );


        private boolean isConnected = false;

        public WebSocketControl(){

          final  th thd =new th();

           final Thread td= new Thread(thd);

            if( joystick1==null)//dont mess with null
                return;
//            joystick.setOnJoystickMoveListener( new JoystickView.OnJoystickMoveListener() {
//                @Override
//                public void onValueChanged(int joystickRadius, int currx, int curry, int centerx, int centery) {
//                    int t = (int)Math.round(1500+500.0*((centery-curry)/(double)joystickRadius));
//                    int y = (int)Math.round(1500+500.0*((currx-centerx)/(double)joystickRadius));
//                    t = roundToTens(t);
//                    y = roundToTens(y);
//
//                    if(t!=throttle || y!=throttle){
//                        sendMessage();
//                    }
//                    if(t!=throttle) {
//                        throttle = t;
//                        setUIText(throttleView, String.format(Locale.US, "Throttle: %d", throttle));
//                    }
//                    if(y!=yaw){
//                        yaw = y;
//                        setUIText(yawView, String.format(Locale.US, "Yaw: %d", yaw));
//                    }
//                }
//            });

//            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//                @Override
//                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                    int t = progress*10+1000;
//                    System.out.println("throt   " +t);
//
//
//
//                    if(t!=throttle){
//                        sendMessage();
//                        throttle = t;
//                        setUIText(throttleView, String.format(Locale.US, "Throttle: %d", throttle));
//
//                    }
//                }
//
//                @Override
//                public void onStartTrackingTouch(SeekBar seekBar) {
//                    sendMessage();
//
//                }
//
//                @Override
//                public void onStopTrackingTouch(SeekBar seekBar) {
//
//                }
//            });
            seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                 //   System.out.println("progress");
                    int y = progress*10+1000;

                    System.out.println("yaw   " +y);


                    if(y!=yaw){
                        yaw = y;
                        setUIText(yawView, String.format(Locale.US, "Yaw: %d", yaw));
                        sendMessage();

                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                    sendMessage();

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });

            dec.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int t = throttle-50;
                    if(t<1000){t=1000;}
                    if (t != throttle){
                        throttle = t;
                        setUIText(throttleView, String.format(Locale.US, "Throttle: %d", throttle));
                    }
                    sendMessage();
                }
            });



            inc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int t = throttle+50;
                    if(t>2000){t=2000;}



                    if (t != throttle){
                        throttle = t;
                        setUIText(throttleView, String.format(Locale.US, "Throttle: %d", throttle));
                    }
                    sendMessage();
                }
            });

//            stop.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    vol = false;
//
//                }
//            });

//            send.setOnTouchListener(new View.OnTouchListener() {
//                @Override
//                public boolean onTouch(View v, MotionEvent event) {
//                            sendMessage();
//                    System.out.println("yo");
//
//                    return false;
//                }
//            });

//            send.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//vol = true;
//                     now = new Thread(new Runnable() {
//
//                        public void run() {
//                            while (vol == true) {
//                                sendMessage();
//                                if (!vol) break;
//                            }
//                        }
//                    });
//now.start();
//
//                }
//            });
//
//            switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//
//                @Override
//                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                    Log.v("Switch State=", ""+isChecked);
//                    vol=isChecked;
//
//                    if (isChecked){
//                        ns.execute();
//                    }
//
//
//                    if(!isChecked) {
//ns.cancel(true);
//
//
//
//
//                    }
//
//
//
//
//
//
//                    //if(!s){th.interrupt();}
//                }
//
//            });





            go.setOnTouchListener(new View.OnTouchListener() {

                private Handler mHandler;

                @Override public boolean onTouch(View v, MotionEvent event) {
                    switch(event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            if (mHandler != null) return true;
                            mHandler = new Handler();
                            mHandler.postDelayed(mAction, 100);
                            break;
                        case MotionEvent.ACTION_UP:
                            if (mHandler == null) return true;
                            mHandler.removeCallbacks(mAction);
                            mHandler = null;
                            break;
                    }
                    return false;
                }

                Runnable mAction = new Runnable() {
                    @Override public void run() {
                        System.out.println("Performing action...");
                        sendMessage();
                        mHandler.postDelayed(this, 100);
                    }
                };

            });

            System.out.println("i came here");
            joystick1.setOnJoystickMoveListener( new JoystickView.OnJoystickMoveListener() {

                @Override
                public void onValueChanged(int joystickRadius, int currx, int curry, int centerx, int centery) {
                    //angle = Math.round(angle);
                    //Log.d("angle",angle+"");
                    int p = (int)Math.round(1500+500.0*((centery-curry)/((double)joystickRadius)));//(int) (1500+(500*(Math.sin(Math.toRadians(angle)) * power)));
                    int r = (int)Math.round(1500+500.0*((currx-centerx)/((double)joystickRadius)));//(int) (1500+(500*(Math.cos(Math.toRadians(angle)) * power)));
                    p = roundToTens(p);
                    r = roundToTens(r);

                    if(p!=pitch || r != roll)
                        sendMessage();
                    if (p != pitch){
                        pitch = p;
                        setUIText(pitchView,String.format(Locale.US,"Pitch: %d",pitch));
                    }
                    if(r!=roll){
                        roll = r;

                        setUIText(rollView,String.format(Locale.US,"Roll: %d",roll));
                    }
                }
            });

            aux1View.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TriToggleButton ttb = (TriToggleButton) v;
                    aux1 = ttb.getValue();
                    sendMessage();
                }
            });
            aux2View.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TriToggleButton ttb = (TriToggleButton) v;
                    aux2 = ttb.getValue();
                    sendMessage();
                }
            });
            aux3View.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TriToggleButton ttb = (TriToggleButton) v;
                    aux3 = ttb.getValue();
                    sendMessage();
                }
            });
            aux4View.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TriToggleButton ttb = (TriToggleButton) v;
                    aux4 = ttb.getValue();
                    sendMessage();
                }
            });

            new Thread(new Runnable() {
                @Override
                public void run() {
                    getConnected();
                }
            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {

                while (vol){

                }



                }
            }).start();


        }

        private void sendData(int milliseconds){
            while(isConnected) {
                sendMessage();
                Log.d("sending message","send message at millisecond time");
                try {
                    Thread.sleep(milliseconds);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                /*new Thread(new Runnable() {
                    @Override
                    public void run() {
                    }
                }).start();
                try {
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                */
            }
        }

        private void getConnected(){
            while(!isConnected) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        isConnected = connectWebSocket();
                    }
                }).start();
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            //sendData(100);
        }

        private void setUIText(final TextView tv,final String text ){
            getActivity().runOnUiThread(new Runnable(){
                @Override
                public void run(){
                    tv.setText(text);
                }
            });
        }

        private int roundToTens(int in){
            int out = in;
            out = (out/10)*10;

            int ones = in%10;
            if(ones>=5)//round up
                out+=10;

            //Log.d("in vs out",in + "  "+out);
            return out;
        }

        private boolean connectWebSocket() {
            URI uri;
            try {
                uri = new URI("ws://192.168.10.1:80");
//                uri = new URI("ws://192.168.137.32:80");
//                SharedPreferences prefs = getContext().getSharedPreferences("IP", 0);
//                String KEY_IP = "IP_config";

//                String ip = "ws://" + prefs.getString(KEY_IP,null) + ":80";
             Log.d("ip", "connectWebSocket:"+uri);
//                uri = new URI(ip);
            } catch (URISyntaxException e) {
                e.printStackTrace();
                return false;
            }
            mWebSocketClient = new WebSocketClient(uri) {
                @Override
                public void onOpen(ServerHandshake serverHandshake) {
                    Log.i("Websocket", "Opened");
                    mWebSocketClient.send("Hello from " + Build.MANUFACTURER + " " + Build.MODEL);
                }

                @Override
                public void onMessage(String s) {
                    Log.i("Websocket","Got Message: " + s);
                }

                @Override
                public void onClose(int i, String s, boolean b) {
                    Log.i("Websocket", "Closed " + s);
                }

                @Override
                public void onError(Exception e) {
                    Log.i("Websocket", "Error " + e.getMessage());
                }
            };

            try {
                return mWebSocketClient.connectBlocking();
            } catch (InterruptedException e) {
                e.printStackTrace();
                return false;
            }
        }
        public void sendMessage() {
            if(isConnected)
                System.out.println(String.format("%d,%d,%d,%d,%d,%d,%d,%d",roll,pitch,yaw,throttle,aux1,aux2,aux3,aux4));
                mWebSocketClient.send(String.format("%d,%d,%d,%d,%d,%d,%d,%d",roll,pitch,yaw,throttle,aux1,aux2,aux3,aux4));

        }
    }


}
