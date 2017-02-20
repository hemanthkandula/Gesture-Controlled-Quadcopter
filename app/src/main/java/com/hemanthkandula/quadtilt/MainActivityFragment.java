package com.hemanthkandula.quadtilt;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Locale;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment  {
//    private JoystickView joystick;
//    private JoystickView joystick1;
    private TextView rollView;
    private TextView pitchView;
    private TextView yawView;
    private TextView throttleView;
    private TriToggleButton aux1View;
    private TriToggleButton aux2View;
    private TriToggleButton aux3View;
    private TriToggleButton aux4View;
    public float[] ypr;

    private WebSocketControl webSocketController;
    private WebSocketClient mWebSocketClient;

    //SeekBar seekBar;
    public SeekBar seekBar2;
    public Button dec,inc,send,stop;
   public Switch switch1;
    public Button go;
    public SensorManager sensorManager;
    float[]  mValuesMagnet      = new float[3];
    float[] mValuesAccel       = new float[3];
    float[] mValuesOrientation = new float[3];
    float[] mRotationMatrix    = new float[9];


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

        aux1View = (TriToggleButton) view.findViewById(R.id.auxButton2) ;
        aux2View = (TriToggleButton) view.findViewById(R.id.auxButton2) ;
        aux3View = (TriToggleButton) view.findViewById(R.id.auxButton2) ;
        aux4View = (TriToggleButton) view.findViewById(R.id.auxButton2) ;

        //sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);




        //joystick = (JoystickView) view.findViewById(R.id.joystickView);

//        joystick1 = (JoystickView) view.findViewById(R.id.joystickView1);

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



        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
//
//        final float[] mValuesMagnet      = new float[3];
//        final float[] mValuesAccel       = new float[3];
//        final float[] mValuesOrientation = new float[3];
//        final float[] mRotationMatrix    = new float[9];
//

        final SensorEventListener mEventListener = new SensorEventListener() {
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
            }

            public void onSensorChanged(SensorEvent event) {
                // Handle the events for which we registered
                switch (event.sensor.getType()) {
                    case Sensor.TYPE_ACCELEROMETER:
                        System.arraycopy(event.values, 0, mValuesAccel, 0, 3);
                       // System.out.println(mValuesAccel);
                        break;

                    case Sensor.TYPE_MAGNETIC_FIELD:
                        System.arraycopy(event.values, 0, mValuesMagnet, 0, 3);
                        break;
                }
            };
        };

        // You have set the event lisetner up, now just need to register this with the
        // sensor manager along with the sensor wanted.
        setListners(sensorManager, mEventListener);









        webSocketController = new WebSocketControl();






        ///////////////











        //////////////////



        return view;
    }


    // Register the event listener and sensor type.
    public void setListners(SensorManager sensorManager, SensorEventListener mEventListener)
    {
        sensorManager.registerListener(mEventListener, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(mEventListener, sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),
                SensorManager.SENSOR_DELAY_NORMAL);
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




        private boolean isConnected = false;

        public WebSocketControl(){



//
//            sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
//                System.out.print("Sensor called ");
//
//
//            sensorManager.registerListener(new SensorEventListener() {
//                @Override
//                public void onSensorChanged(SensorEvent event) {
//
//                    System.arraycopy(event.values, 0, mValuesAccel, 0, 3);
//                    System.out.println(mValuesAccel);
//
//                }
//
//                @Override
//                public void onAccuracyChanged(Sensor sensor, int accuracy) {
//
//
//
//                }
//            }, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
//                    SensorManager.SENSOR_DELAY_NORMAL);
//
//
//
//
//            sensorManager.registerListener(new SensorEventListener() {
//                @Override
//                public void onSensorChanged(SensorEvent event) {
//                    System.arraycopy(event.values, 0, mValuesMagnet, 0, 3);
//
//                }
//
//                @Override
//                public void onAccuracyChanged(Sensor sensor, int accuracy) {
//
//                }
//            },sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),
//                    SensorManager.SENSOR_DELAY_NORMAL);
//






            seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                 //   System.out.println("progress");
                    int y = progress*10+1000;

                    System.out.println("throttle   " +y);


                    if(y!=throttle){
                        throttle = y;
                        setUIText(throttleView, String.format(Locale.US, "throttle: %d", throttle));
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
                    int t = yaw-50;
                    if(t<1000){t=1000;}
                    if (t != yaw){
                        yaw = t;
                        setUIText(yawView, String.format(Locale.US, "yaw: %d", yaw));
                    }
                    sendMessage();
                }
            });



            inc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int t = yaw+50;
                    if(t>2000){t=2000;}



                    if (t != yaw){
                        yaw = t;
                        setUIText(yawView, String.format(Locale.US, "Yaw: %d", yaw));
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
                        //System.out.println("Performing action...");
                        float[] temp;
                        temp=ypr;
                        ypr =getsensordata();
                       // System.out.println(ypr);
                        getrollpitch(ypr);
                        //System.out.println(ypr[2]);




                        sendMessage();
                        mHandler.postDelayed(this, 100);
                    }
                };

            });

            System.out.println("i came here");
//            joystick1.setOnJoystickMoveListener( new JoystickView.OnJoystickMoveListener() {
//
//                @Override
//                public void onValueChanged(int joystickRadius, int currx, int curry, int centerx, int centery) {
//                    //angle = Math.round(angle);
//                    //Log.d("angle",angle+"");
//                    int p = (int)Math.round(1500+500.0*((centery-curry)/((double)joystickRadius)));//(int) (1500+(500*(Math.sin(Math.toRadians(angle)) * power)));
//                    int r = (int)Math.round(1500+500.0*((currx-centerx)/((double)joystickRadius)));//(int) (1500+(500*(Math.cos(Math.toRadians(angle)) * power)));
//                    p = roundToTens(p);
//                    r = roundToTens(r);
//
//                    if(p!=pitch || r != roll)
//                        sendMessage();
//                    if (p != pitch){
//                        pitch = p;
//                        setUIText(pitchView,String.format(Locale.US,"Pitch: %d",pitch));
//                    }
//                    if(r!=roll){
//                        roll = r;
//
//                        setUIText(rollView,String.format(Locale.US,"Roll: %d",roll));
//                    }
//                }
//            });

//            aux1View.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    TriToggleButton ttb = (TriToggleButton) v;
//                    aux1 = ttb.getValue();
//                    sendMessage();
//                }
//            });
            aux2View.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TriToggleButton ttb = (TriToggleButton) v;
                    aux2 = ttb.getValue();
                    sendMessage();
                }
            });
//            aux3View.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    TriToggleButton ttb = (TriToggleButton) v;
//                    aux3 = ttb.getValue();
//                    sendMessage();
//                }
//            });
//            aux4View.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    TriToggleButton ttb = (TriToggleButton) v;
//                    aux4 = ttb.getValue();
//                    sendMessage();
//                }
//            });

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

        // Register the event listener and sensor type.


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
        public float[] getsensordata(){

            SensorManager.getRotationMatrix(mRotationMatrix, null, mValuesAccel, mValuesMagnet);
            SensorManager.getOrientation(mRotationMatrix, mValuesOrientation);
            final CharSequence test;
            //System.out.println( "results: " + mValuesOrientation[0] +" "+mValuesOrientation[1]+ " "+ mValuesOrientation[2]);

            return mValuesOrientation;
        }
        private void getrollpitch(float[] ypr) { //System.out.println(ypr);
            if(ypr[2]>.7) {roll = 1600 ;}

            else if(ypr[2]>.4){roll = 1550;}

            else if(ypr[2]<-.7) {roll = 1400;}
            else if(ypr[2]<-.4) {roll = 1450;}
            else {roll = 1500;}
            if(ypr[1]>.55) {pitch = 1600 ;}

            else if(ypr[1]>.24){pitch = 1550;}
            else if(ypr[1]<-.55) {pitch = 1400;}
            else if(ypr[1]<-.24) {pitch = 1450;}

            else {pitch = 1500;}
            //System.out.println("roll"+roll+"pitch"+pitch);



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
            if(isConnected) {
                System.out.println(String.format("%d,%d,%d,%d,%d,%d,%d,%d", roll, pitch, yaw, throttle, aux1, aux2, aux3, aux4));
                mWebSocketClient.send(String.format("%d,%d,%d,%d,%d,%d,%d,%d", roll, pitch, yaw, throttle, aux1, aux2, aux3, aux4));
            }
            else {
                Toast.makeText(getActivity(),
                        "Please connect your DRONE", Toast.LENGTH_SHORT).show();
            }
        }







    }





}
